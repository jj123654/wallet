package com.yns.wallet.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.adapter.HDWalletListAdapter
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.base.Constant
import com.yns.wallet.base.KeyValuePairVO
import com.yns.wallet.bean.HDWalletBean
import com.yns.wallet.databinding.ActivityCreateStepImport2Binding
import com.yns.wallet.databinding.ItemChooseAccountBinding
import com.yns.wallet.databinding.LayoutChooseAccountBinding
import com.yns.wallet.util.AppManager
import com.yns.wallet.util.EventBusUtil
import com.yns.wallet.util.showToast
import com.yns.wallet.widget.bottomsheet.BottomSheet
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class CreateStepImportActivity : BaseActivity<ActivityCreateStepImport2Binding>() {

    private lateinit var bottomSheet: BottomSheet

    var isFirstLoad = false

    var menomic: String? = null
    var name:String?=null
    var password: String? = null
    var page = 1
    var index = 0

    var addressList = mutableListOf<HDWalletBean>()


    private val adapter: HDWalletListAdapter by lazy {
        HDWalletListAdapter(mutableListOf()).apply {
            loadMoreModule.isEnableLoadMore = true
            loadMoreModule.setOnLoadMoreListener {
                page++
                loadData(page)
            }
            setOnItemClickListener{ adapter, view, position ->
                refreshData(position)
                bottomSheet.dismiss()
                index = position
                setAddressAndPath()
            }
        }
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        isFirstLoad = intent.getBooleanExtra("isFirstLoad", false)
        name = intent.getStringExtra("name")
        menomic = intent.getStringExtra("menomic")
        password = intent.getStringExtra("password")

        viewBinding.apply {
            if (isFirstLoad) {
                topLayout.visibility = View.GONE
                etWalletName.setText(name?:"")
            } else {
                topLayout.visibility = View.VISIBLE
                etWalletName.setText("Wallet-"+ walletViewModel.getWalletCount())
            }

            // activity
            tvChooseAccount.onClick {
                bottomSheet.show()
            }
            tvConfirm.onClick {
                if(!isFirstLoad&&etWalletName.text.toString().isNullOrEmpty()){
                    showToast(getString(R.string.please_enter_name))
                    return@onClick
                }
                //TODO 此处后续需要完善。这里也有可能是通过私钥也可能是通过助记词创建的钱包（第一次导入以私钥导入，此时没有助记词）
                walletViewModel.createWalletFromMenomic(etWalletName.text.toString(),menomic ?: "", index,password ?: "") {
                    if (isFirstLoad) {
                        AppManager.getAppManager()
                            .returnToActivity(ImportOrCreateWallet::class.java)
                        EventBusUtil.sendEvent(
                            KeyValuePairVO(
                                EventBusUtil.CREATE_WALLET_SUCCESS,
                                true
                            )
                        )
                    } else {
                        AppManager.getAppManager().returnToActivity(MainActivity::class.java)
                    }
                }

            }

        }


        // bottom sheet
        val view = LayoutChooseAccountBinding.inflate(LayoutInflater.from(this))
        bottomSheet = BottomSheet.Builder(this).contentView(view.root).build()
        view.closeImg.setOnClickListener {
            bottomSheet.dismiss()
        }
        val rvList = view.rvList
        rvList.layoutManager = WrapContentLinearLayoutManager(this)
        rvList.adapter = adapter
        adapter.setEmptyView(R.layout.common_empty_view)
        loadData(page)
    }

    private fun setAddressAndPath(){
        viewBinding.apply {
            tvSeedHash.text = addressList[0].address
            tvSeedPath.text = "${Constant.DEFAULT_PATH}${0}"
            tvSeedNum.text = "0"

            tvAccountHash.text = addressList[index].address
            tvAccountPath.text = "${Constant.DEFAULT_PATH}${index}"
            tvAccountBalance.text = "$index"
        }
    }

    private fun loadData(page: Int) {
        walletViewModel.getAddressFromMenomic(menomic ?: "", page) {
            val r = it.map {addres-> HDWalletBean(addres,false) }.toMutableList()
            if (page == 1) {
                if(r.size>0){
                    r[0].isSelected = true
                }
                adapter.setList(r)
                addressList.clear()
            } else {
                adapter.addData(r)
            }
            addressList.addAll(r)
            setAddressAndPath()
            if (r.size < 20) {
                adapter.loadMoreModule.loadMoreComplete()
                adapter.loadMoreModule.loadMoreEnd(true)
            } else {
                Log.i("loadMoreTest","加载更多")
                adapter.loadMoreModule.loadMoreComplete()

            }
        }
    }


}