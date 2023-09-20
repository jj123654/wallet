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
import com.yns.wallet.util.ViewModelUtils.lazyViewModel
import com.yns.wallet.viewmodel.TokenTransactionRecordViewModel
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager
import jp.wasabeef.glide.transformations.internal.Utils

class AssetsFragment:BaseFragment<FragmentTransactionRecordBinding>() {

    private val tokenTransactionRecordViewModel: TokenTransactionRecordViewModel by lazyViewModel()

    private val assetsListAdapter: AssetsListAdapter by lazy {
        AssetsListAdapter(walletViewModel.currentWalletLiveData.value?.address?:"",requireContext(), mutableListOf()).apply {
            setOnItemClickListener{adapter,view,position->
                startActivity(AssetsDetailActivity::class.java)
            }
        }
    }

    var list:MutableList<TransactionRecord> = ArrayList()

    var type:Int = 0

    var isLoaded = false

    companion object {
        fun newInstance(type:Int): AssetsFragment {
            val arguments = Bundle()
            arguments.putInt("type",type)
            val assetsFragment = AssetsFragment()
            assetsFragment.arguments = arguments
            return assetsFragment
        }
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        type = arguments?.getInt("type")?:0
        viewBinding.apply {
            recyclerView.layoutManager = WrapContentLinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
            recyclerView.adapter = assetsListAdapter
        }

        tokenTransactionRecordViewModel.tokenTransferTransactionLiveData.observe(this){
            assetsListAdapter.addData(it)
        }
    }

    override fun onResume() {
        super.onResume()

        if(!isLoaded) {
            isLoaded = true
            tokenTransactionRecordViewModel.getTokenTransactionRecord("","",0L,50,type,true)

        }

    }

}