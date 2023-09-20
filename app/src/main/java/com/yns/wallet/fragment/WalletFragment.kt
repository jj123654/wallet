package com.yns.wallet.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.*
import com.yns.wallet.activity.*
import com.yns.wallet.adapter.WalletListAdapter
import com.yns.wallet.api.WalletApi
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.bean.WalletItemInfo
import com.yns.wallet.bean.WalletModel
import com.yns.wallet.databinding.FragmentHomeBinding
import com.yns.wallet.databinding.LayoutWalletHeaderBinding
import com.yns.wallet.util.adjustStatusBarMargin
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.math.BigDecimal

class WalletFragment : BaseFragment<FragmentHomeBinding>() {

    private var totalBalance:String?=null

    private val walletListAdapter: WalletListAdapter by lazy {
        WalletListAdapter(requireContext(), mutableListOf()).apply {
            setOnItemClickListener { adapter, position, view ->
                startActivity(AssetsActivity::class.java)
            }
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        walletViewModel.currentWalletLiveData.observe(this) {
            viewBinding.tvHomeName.text = it.name
        }

        walletViewModel.tokenLiveData.observe(this){
            walletListAdapter.setList(it)
            totalBalance = it.sumOf {
                it.usd?: BigDecimal("0")
            }.toString()
            changeTotalPrice()
        }

        walletViewModel.getCurrentWallet {
        }

        walletViewModel.getWalletAllToken(walletViewModel.currentWalletLiveData.value?.address?:"")

        adjustStatusBarMargin(viewBinding.ivLogo)

        viewBinding.apply {
            rvList.layoutManager =
                WrapContentLinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            rvList.adapter = walletListAdapter

            tvDetail.onClick {
                startActivity(DetailActivity::class.java)
            }
            ivWallet.onClick {
                startActivity(AddWalletActivity::class.java)
            }
            tvReceive.onClick {
                startActivity(ReceiveActivity::class.java)
            }
            tvSend.onClick {
                startActivity(SendActivity::class.java)
            }
            tvSwap.onClick {
                startActivity(SwapActivity::class.java)
            }
            ivAdd.onClick {
                startActivity(AddTokenActivity::class.java)
            }
            tvSuperLoop.onClick {
                startActivity(LoopActivity::class.java)
            }

            ivHomeBalanceEye.onClick {
                ivHomeBalanceEye.isSelected = !ivHomeBalanceEye.isSelected
                changeTotalPrice()
            }


        }


    }

    private fun changeTotalPrice(){
        if (viewBinding.ivHomeBalanceEye.isSelected) {
            viewBinding.tvBalance.text = "$ ⁕⁕⁕⁕⁕⁕"
        } else {
            viewBinding.tvBalance.text = "$ ${totalBalance?:""}"
        }
    }

}