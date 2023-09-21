package com.yns.wallet.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.adapter.TokenListAdapter
import com.yns.wallet.adapter.WalletListAdapter
import com.yns.wallet.api.WalletApi
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.TokenBean
import com.yns.wallet.bean.TokenModel
import com.yns.wallet.databinding.ActivityAddTokenBinding
import com.yns.wallet.util.ViewModelUtils.lazyViewModel
import com.yns.wallet.util.showToast
import com.yns.wallet.viewmodel.AddTokenViewModel
import com.yns.wallet.widget.bottomsheet.BottomSheet
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class AddTokenActivity : BaseActivity<ActivityAddTokenBinding>() {

    private val addTokenModel:AddTokenViewModel by lazyViewModel()

    var searchStateChanged = false//如果搜索的币种添加了，那么返回热门币种列表时需要刷新一下列表

    private val tokenListAdapter: TokenListAdapter by lazy {
        TokenListAdapter(this@AddTokenActivity,walletViewModel.tokenLiveData.value?: mutableListOf(), mutableListOf()).apply {
            setOnItemChildClickListener{ adapter, view, position->
                if(view.id == R.id.iv_balance){
                    changeSelectedState(view as ImageView,data[position])
                }
            }
        }
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        addTokenModel.tokenListLiveData.observe(this){
            dismissProgress()
            tokenListAdapter.addData(it)
        }


        viewBinding.apply {
            ivBack.setOnClickListener { finish() }

            rvList.layoutManager = WrapContentLinearLayoutManager(this@AddTokenActivity)
            rvList.adapter = tokenListAdapter

            etSearch.doAfterTextChanged {
                if(it.toString().isNullOrEmpty()){
                    popularTokenLayout.visibility = View.VISIBLE
                    searchLayout.visibility = View.GONE
                    if(searchStateChanged){
                        searchStateChanged = false
                        tokenListAdapter.refreshData(walletViewModel.tokenLiveData.value?: mutableListOf())
                    }
                }
            }

            tvSearch.onClick {
                if(etSearch.text.toString().isNullOrEmpty()){
                    showToast(getString(R.string.please_enter_name))
                    return@onClick
                }

                showProgress()
                addTokenModel.getToken(etSearch.text.toString()){
                    dismissProgress()
                    popularTokenLayout.visibility = View.GONE
                    if(it.address.isNullOrEmpty()){
                        searchLayout.visibility = View.GONE
                    }else{
                        searchLayout.visibility = View.VISIBLE
                        ivMineIcon.load(it.imageUrl){
                            placeholder(R.mipmap.icon_default_user)
                        }
                        ivMineName.text = it.name
                        ivMineHash.text = it.address

                        ivBalance.visibility = if(it.name == "YNS"||it.name=="USDT"||it.name=="TRX") View.INVISIBLE else View.VISIBLE
                        ivBalance.isSelected = walletViewModel.tokenLiveData.value?.firstOrNull{item->item.address==it.address}!=null
                        ivBalance.onClick { view->
                            changeSelectedState(ivBalance,it)
                            searchStateChanged = true

                        }
                    }
                }

            }

        }

        showProgress()
        addTokenModel.getPopularTokens()

    }


    private fun changeSelectedState(imageView: ImageView,tokenModel: TokenModel){
        if(imageView.isSelected){
            walletViewModel.deleteWalletToken(tokenModel){

            }
            imageView.isSelected = false
        }else{
            walletViewModel.addWalletToken(tokenModel){

            }
            imageView.isSelected = true
        }
    }

}
