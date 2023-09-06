package com.yns.wallet.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.*
import com.yns.wallet.activity.*
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.databinding.FragmentHomeBinding
import com.yns.wallet.util.adjustStatusBarMargin

class WalletFragment : BaseFragment<FragmentHomeBinding>() {


    override fun initView(view: View, savedInstanceState: Bundle?) {

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
                startActivity(NetworksActivity::class.java)
            }

            ivLogo.onClick {
                startActivity(SelectTokenActivity::class.java)
            }

        }


        adjustStatusBarMargin(view.findViewById(R.id.iv_logo))
        initListDate(view)
    }

    private fun initListDate(view: View) {
        val rvListView = view.findViewById<RecyclerView>(R.id.rv_list)
        val list = listOf(
            WalletItem(R.mipmap.eth, "ETH", "ETH", 0, 0.00),
            WalletItem(R.mipmap.eth, "ETH", "ETH", 0, 0.00),
            WalletItem(R.mipmap.eth, "ETH", "ETH", 0, 0.00),
            WalletItem(R.mipmap.eth, "ETH", "ETH", 0, 0.00),
            WalletItem(R.mipmap.eth, "ETH", "ETH", 0, 0.00)
        )
        rvListView.adapter = WalletListAdapter(view.context, list)
        rvListView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, true)
    }

    data class WalletItem(
        var iconId: Int,
        var name: String,
        var desc: String,
        var index: Int,
        var balance: Double
    )

    class WalletListAdapter(private var context: Context, private val items: List<WalletItem>) :
        RecyclerView.Adapter<WalletListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletListViewHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.layout_wallet_item, parent, false)
            return WalletListViewHolder(view)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: WalletListViewHolder, position: Int) {
            val item = items[position]
            holder.ivIcon.setImageResource(item.iconId)
            holder.tvName.text = item.name
            holder.tvDesc.text = item.desc
            holder.tvIndex.text = item.index.toString()
            holder.tvBalance.text = item.balance.toString()
        }
    }

    class WalletListViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon: ImageView = view.findViewById(R.id.iv_icon)
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvDesc: TextView = view.findViewById(R.id.tv_desc)
        val tvIndex: TextView = view.findViewById(R.id.tv_index)
        val tvBalance: TextView = view.findViewById(R.id.tv_balance)
    }

}