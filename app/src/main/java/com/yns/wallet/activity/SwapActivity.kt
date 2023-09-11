package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.adapter.SwapListAdapter
import com.yns.wallet.adapter.TransactionListAdapter
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.SwapRecordBean
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.databinding.ActivitySwapBinding
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class SwapActivity : BaseActivity<ActivitySwapBinding>() {

    private val swapListAdapter: SwapListAdapter by lazy {
        SwapListAdapter(this@SwapActivity, mutableListOf()).apply {

        }
    }

    var list:MutableList<SwapRecordBean> = ArrayList()

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            recyclerView.layoutManager = WrapContentLinearLayoutManager(this@SwapActivity, LinearLayoutManager.VERTICAL,false)
            recyclerView.addItemDecoration(SpacesItemDecoration(QMUIDisplayHelper.dp2px(this@SwapActivity, 20), QMUIDisplayHelper.dp2px(this@SwapActivity, 15)))
            recyclerView.adapter = swapListAdapter
        }

        if(list==null||list.size<=0) {
            for (i in 0..11) {
                list.add(SwapRecordBean())
            }

            swapListAdapter.addData(list)
        }
    }

}
