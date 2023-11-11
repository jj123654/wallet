package com.yns.wallet.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.activity.SearchDiscoveryActivity
import com.yns.wallet.adapter.DiscoveryListAdapter
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.bean.DiscoveryListBean
import com.yns.wallet.databinding.FragmentDiscoverBinding
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class DiscoverFragment:BaseFragment<FragmentDiscoverBinding>() {

    private val discoveryListAdapter:DiscoveryListAdapter by lazy {
        DiscoveryListAdapter(mutableListOf())
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            gotoWebviewTv.onClick {
                startActivity(Intent(activity,SearchDiscoveryActivity::class.java))
            }


            recyclerView.apply {
                layoutManager = WrapContentLinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
                adapter = discoveryListAdapter
            }

            var list:MutableList<DiscoveryListBean> = mutableListOf()
            list.add(DiscoveryListBean(R.mipmap.yns,getString(R.string.yunus),getString(R.string.discovery_recommend_tips)))
            list.add(DiscoveryListBean(R.mipmap.sunswap,getString(R.string.sunswap),getString(R.string.discovery_recommend_tips)))
            discoveryListAdapter.addData(list)


        }

    }


}