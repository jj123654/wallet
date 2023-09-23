package com.yns.wallet.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.adapter.TransactionListAdapter
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.databinding.FragmentTransactionRecordBinding
import com.yns.wallet.viewmodel.TransactionRecordViewModel
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class TransactionRecordFragment : BaseFragment<FragmentTransactionRecordBinding>() {

    private val transactionListAdapter: TransactionListAdapter by lazy {
        TransactionListAdapter(requireContext(), mutableListOf()).apply {

        }
    }
    val transactionRecordViewModel: TransactionRecordViewModel by viewModels()
    var list: MutableList<TransactionRecord> = ArrayList()

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
        transactionRecordViewModel.getTransactionRecord(arguments?.getInt("index", 0) ?: 0) {
            transactionListAdapter.setNewInstance(it.toMutableList())
        }
    }

}