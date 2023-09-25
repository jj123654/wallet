package com.yns.wallet.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.yns.wallet.activity.AssetsDetailActivity
import com.yns.wallet.adapter.AssetsListAdapter
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.bean.TransactionRecordModel
import com.yns.wallet.databinding.FragmentTransactionRecordBinding
import com.yns.wallet.util.ViewModelUtils.lazyViewModel
import com.yns.wallet.viewmodel.AssetViewModel
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class AssetsFragment:BaseFragment<FragmentTransactionRecordBinding>() {

    private val assetViewModel: AssetViewModel by lazyViewModel()

    private val assetsListAdapter: AssetsListAdapter by lazy {
        AssetsListAdapter(walletViewModel.currentWalletLiveData.value?.address?:"",requireContext(), mutableListOf()).apply {
            setOnItemClickListener{adapter,view,position->
                var intent = Intent(activity,AssetsDetailActivity::class.java)
                intent.putExtra("tokenTransferTransactionModel",data[position])
                startActivity(intent)
            }
        }
    }

    var list:MutableList<TransactionRecordModel> = ArrayList()

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

        assetViewModel.tokenTransferTransactionLiveData.observe(this){
            assetsListAdapter.addData(it)
        }
    }

    override fun onResume() {
        super.onResume()

        if(!isLoaded) {
            isLoaded = true
            assetViewModel.getTokenTransactionRecord("","",0L,50,type,true)

        }

    }

}