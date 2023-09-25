package com.yns.wallet.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.adapter.TransactionListAdapter
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.bean.TransactionRecordModel
import com.yns.wallet.databinding.FragmentTransactionRecordBinding
import com.yns.wallet.viewmodel.TransactionRecordViewModel
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class TransactionRecordFragment : BaseFragment<FragmentTransactionRecordBinding>() {

    private val transactionListAdapter: TransactionListAdapter by lazy {
        TransactionListAdapter(requireContext(), mutableListOf()).apply {
            loadMoreModule.isEnableLoadMore = true
        }
    }
    val transactionRecordViewModel: TransactionRecordViewModel by viewModels()
    var list: MutableList<TransactionRecordModel> = ArrayList()
    var start = 0

    companion object {
        fun newInstance(index: Int): TransactionRecordFragment {
            val arguments = Bundle()
            arguments.putInt("index", index)
            val transactionRecordFragment = TransactionRecordFragment()
            transactionRecordFragment.arguments = arguments
            return transactionRecordFragment
        }
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            recyclerView.layoutManager =
                WrapContentLinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            recyclerView.addItemDecoration(
                SpacesItemDecoration(
                    QMUIDisplayHelper.dp2px(
                        context,
                        20
                    ), QMUIDisplayHelper.dp2px(context, 10)
                )
            )
            recyclerView.adapter = transactionListAdapter
        }
        viewBinding.refresh.isRefreshing = true
        viewBinding.refresh.setOnRefreshListener {
            start = 0
            loadData()
        }
        loadData()
    }

    private fun loadData() {
        transactionRecordViewModel.getTransactionRecord(
            (arguments?.getInt("index", 0) ?: 0) + 1,
            start
        ) {
            val d = it.toMutableList()
            if (start == 0) {
                transactionListAdapter.setNewInstance(d)
                viewBinding.refresh.isRefreshing = false
            } else {
                transactionListAdapter.addData(d)
            }
            if (d.size < 50) {
                transactionListAdapter.loadMoreModule.loadMoreEnd(true)
            } else {
                transactionListAdapter.loadMoreModule.loadMoreComplete()

            }
        }
    }

}