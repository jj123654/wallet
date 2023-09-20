package com.yns.wallet.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import com.yns.wallet.databinding.ActivityAddTokenBinding
import com.yns.wallet.util.ViewModelUtils.lazyViewModel
import com.yns.wallet.util.showToast
import com.yns.wallet.viewmodel.AddTokenViewModel
import com.yns.wallet.widget.bottomsheet.BottomSheet
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class AddTokenActivity : BaseActivity<ActivityAddTokenBinding>() {

    private val addTokenModel:AddTokenViewModel by lazyViewModel()

    private val tokenListAdapter: TokenListAdapter by lazy {
        TokenListAdapter(this@AddTokenActivity,walletViewModel.tokenLiveData.value?: mutableListOf(), mutableListOf()).apply {
            setOnItemChildClickListener{ adapter, view, position->
                if(view.id == R.id.iv_balance){
                    if((view as ImageView).isSelected){
                        walletViewModel.addWalletToken(data[position].address?:""){

                        }
                        (view as ImageView).isSelected = false
                    }else{
                        walletViewModel.deleteWalletToken(data[position].address?:""){

                        }
                        (view as ImageView).isSelected = true
                    }
                }
            }
        }
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            ivBack.setOnClickListener { finish() }

            rvList.layoutManager = WrapContentLinearLayoutManager(this@AddTokenActivity)
            rvList.adapter = tokenListAdapter

            tvSearch.onClick {
                if(etSearch.text.toString().isNullOrEmpty()){
                    showToast(getString(R.string.please_enter_name))
                    return@onClick
                }

                walletViewModel.getToken(etSearch.text.toString()){

                }

            }

        }

//        if(list==null||list.size<=0) {
//            list.add(TokenBean(0,"Account-1","0xsadtr234",true))
//            list.add(TokenBean(0,"Account-2","0xsadtr234",false))
//            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
//            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
//            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
//            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
//            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
//            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
//            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
//            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
//            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
//
//
//            tokenListAdapter.addData(list)
//        }

        showProgress()
        addTokenModel.getPopularTokens{
            dismissProgress()
            tokenListAdapter.addData(it)
        }

    }

}
