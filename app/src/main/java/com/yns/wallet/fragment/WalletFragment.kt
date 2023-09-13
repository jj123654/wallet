package com.yns.wallet.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
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
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.bean.WalletItemInfo
import com.yns.wallet.databinding.FragmentHomeBinding
import com.yns.wallet.databinding.LayoutWalletHeaderBinding
import com.yns.wallet.util.adjustStatusBarMargin
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WalletFragment : BaseFragment<FragmentHomeBinding>() {

    val list = mutableListOf(
        WalletItemInfo(R.mipmap.eth, "ETH", "ETH", 0, 0.00),
        WalletItemInfo(R.mipmap.eth, "ETH", "ETH", 0, 0.00),
        WalletItemInfo(R.mipmap.eth, "ETH", "ETH", 0, 0.00),
        WalletItemInfo(R.mipmap.eth, "ETH", "ETH", 0, 0.00),
        WalletItemInfo(R.mipmap.eth, "ETH", "ETH", 0, 0.00)
    )

    private val walletListAdapter: WalletListAdapter by lazy {
        WalletListAdapter(requireContext(), list).apply {
            setOnItemClickListener { adapter, position, view ->
                startActivity(AssetsActivity::class.java)
            }
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        viewBinding.rvList.layoutManager =
            WrapContentLinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val viewBinding =
            LayoutWalletHeaderBinding.inflate(layoutInflater, viewBinding.rvList, false)
        walletListAdapter.addHeaderView(viewBinding.root)
        this.viewBinding.rvList.adapter = walletListAdapter
        adjustStatusBarMargin(viewBinding.ivLogo)

        viewBinding.apply {
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
                if (ivHomeBalanceEye.isSelected) {
                    tvBalance.text = "$ 0.00"
                } else {
                    tvBalance.text = "$ ⁕⁕⁕⁕⁕⁕"
                }
                ivHomeBalanceEye.isSelected = !ivHomeBalanceEye.isSelected
            }

            ivLogo.onClick {
//                startActivity(SelectTokenActivity::class.java)
            }


        }


    }


}