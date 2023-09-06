package com.yns.wallet.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.adapter.TransactionListAdapter
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.databinding.FragmentTransactionRecordBinding
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager
import jp.wasabeef.glide.transformations.internal.Utils

class TransactionRecordFragment:BaseFragment<FragmentTransactionRecordBinding>() {

    private val transactionListAdapter:TransactionListAdapter by lazy {
        TransactionListAdapter(requireContext(), mutableListOf()).apply {

        }
    }

    var list:MutableList<TransactionRecord> = ArrayList()

    companion object {
        fun newInstance(type:String): TransactionRecordFragment {
            val arguments = Bundle()
            arguments.putString("type",type)
            val transactionRecordFragment = TransactionRecordFragment()
            transactionRecordFragment.arguments = arguments
            return transactionRecordFragment
        }
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            recyclerView.layoutManager = WrapContentLinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            recyclerView.addItemDecoration(SpacesItemDecoration(QMUIDisplayHelper.dp2px(context, 20), QMUIDisplayHelper.dp2px(context, 10)))
            recyclerView.adapter = transactionListAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        if(list==null||list.size<=0) {
            for (i in 0..11) {
                list.add(TransactionRecord())
            }

            transactionListAdapter.addData(list)
        }

    }

}