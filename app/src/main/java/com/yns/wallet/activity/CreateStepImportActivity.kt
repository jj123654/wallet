package com.yns.wallet.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.base.KeyValuePairVO
import com.yns.wallet.databinding.ActivityCreateStepImport2Binding
import com.yns.wallet.databinding.ItemChooseAccountBinding
import com.yns.wallet.databinding.LayoutChooseAccountBinding
import com.yns.wallet.util.AppManager
import com.yns.wallet.util.EventBusUtil
import com.yns.wallet.widget.bottomsheet.BottomSheet

class CreateStepImportActivity : BaseActivity<ActivityCreateStepImport2Binding>() {

    private lateinit var bottomSheet: BottomSheet

    var isFirstLoad = false

    var menomic: String? = null
    var password: String? = null
    var page = 1
    override fun initView(root: View, savedInstanceState: Bundle?) {
        isFirstLoad = intent.getBooleanExtra("isFirstLoad", false)
        menomic = intent.getStringExtra("menomic")
        password = intent.getStringExtra("password")

        viewBinding.apply {
            if (isFirstLoad) {
                topLayout.visibility = View.GONE
            } else {
                topLayout.visibility = View.VISIBLE
            }

            // activity
            tvChooseAccount.setOnClickListener {
                bottomSheet.show()
            }
            tvConfirm.setOnClickListener {
                walletViewModel.createWalletFromMenomic(menomic ?: "", password ?: "") {
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
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = adapter
        loadData(page)
    }

    private val adapter: BottomSheetList by lazy {
        BottomSheetList(mutableListOf()).apply {
            loadMoreModule.isEnableLoadMore = true
            loadMoreModule.setOnLoadMoreListener {
                page++
                loadData(page)
            }
        }
    }

    private fun loadData(page: Int) {
        walletViewModel.getAddressFromMenomic(menomic ?: "", page) {
            val r = it.toMutableList()
            if (page == 1) {
                adapter.setNewInstance(r)
            } else {
                adapter.addData(r)
            }
            if (r.size < 20) {
                adapter.loadMoreModule.loadMoreComplete()
                adapter.loadMoreModule.loadMoreEnd(true)
            } else {
                adapter.loadMoreModule.loadMoreComplete()

            }
        }
    }

    class BottomSheetList(data: MutableList<String>?) :
        BaseQuickAdapter<String, BottomSheetViewHolder>(R.layout.item_choose_account, data),
        LoadMoreModule {
        override fun convert(holder: BottomSheetViewHolder, item: String) {
            holder.vb.tvAccountHash.text = item
            holder.vb.tvAccountBalance.text = "${holder.absoluteAdapterPosition + 1}"
        }

    }

    class BottomSheetViewHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemChooseAccountBinding.bind(view)
    }

}