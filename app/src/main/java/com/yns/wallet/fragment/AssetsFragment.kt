package com.yns.wallet.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.activity.AssetsActivity
import com.yns.wallet.activity.AssetsDetailActivity
import com.yns.wallet.adapter.AssetsListAdapter
import com.yns.wallet.adapter.TransactionListAdapter
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.databinding.FragmentTransactionRecordBinding
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager
import jp.wasabeef.glide.transformations.internal.Utils

class AssetsFragment:BaseFragment<FragmentTransactionRecordBinding>() {

    private val assetsListAdapter: AssetsListAdapter by lazy {
        AssetsListAdapter(requireContext(), mutableListOf()).apply {
            setOnItemClickListener{adapter,view,position->
                startActivity(AssetsDetailActivity::class.java)
            }
        }
    }

    var list:MutableList<TransactionRecord> = ArrayList()

    companion object {
        fun newInstance(type:String): AssetsFragment {
            val arguments = Bundle()
            arguments.putString("type",type)
            val assetsFragment = AssetsFragment()
            assetsFragment.arguments = arguments
            return assetsFragment
        }
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            recyclerView.layoutManager = WrapContentLinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            recyclerView.adapter = assetsListAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        if(list==null||list.size<=0) {
            for (i in 0..11) {
                list.add(TransactionRecord())
            }

            assetsListAdapter.addData(list)
        }

    }

}