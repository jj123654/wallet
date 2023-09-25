package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import com.yns.wallet.adapter.BackUpRecordListAdapter
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityBackUpRecordsBinding
import com.yns.wallet.util.ViewModelUtils.lazyViewModel
import com.yns.wallet.viewmodel.BackUpViewModel
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class BackUpRecordActivity : BaseActivity<ActivityBackUpRecordsBinding>() {

    private val backUpViewModel: BackUpViewModel by lazyViewModel()

    private val backUpRecordListAdapter: BackUpRecordListAdapter by lazy {
        BackUpRecordListAdapter(mutableListOf()).apply {
        }
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        backUpViewModel.backUpRecordLiveData.observe(this){
            dismissProgress()
            backUpRecordListAdapter.addData(it)
        }

        viewBinding.recyclerView.layoutManager = WrapContentLinearLayoutManager(this@BackUpRecordActivity)
        viewBinding.recyclerView.adapter = backUpRecordListAdapter


        loadData()
    }

    private fun loadData(){
        showProgress()
        backUpViewModel.getBackUpRecord(walletViewModel.currentWalletLiveData.value?.address)
    }

}
