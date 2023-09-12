package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.BackupRecordModel
import com.yns.wallet.databinding.ActivityBackUpRecordsBinding

class BackUpRecordActivity : BaseActivity<ActivityBackUpRecordsBinding>() {

    private val adapter: BackupAdapter by lazy {
        BackupAdapter(mutableListOf<BackupRecordModel>().apply {
            repeat(10) {
                add(BackupRecordModel())
            }
        })
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.let {
            it.rv.adapter = adapter
        }
    }


    class BackupAdapter(data: MutableList<BackupRecordModel>) :
        BaseQuickAdapter<BackupRecordModel, BackupViewHolder>(
            R.layout.list_item_back_up_record,
            data
        ) {
        override fun convert(holder: BackupViewHolder, item: BackupRecordModel) {
        }

    }

    class BackupViewHolder(view: View) : BaseViewHolder(view) {

    }
}
