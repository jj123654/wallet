package com.yns.wallet.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yns.wallet.*

class WalletFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        adjustStatusBarMargin(view.findViewById(R.id.iv_logo))
        initListDate(view)
    }

    private fun initView(view: View) {
        view.findViewById<View>(R.id.tv_detail)
            .setOnClickListener {
                startActivity(Intent(view.context, DetailActivity::class.java))
            }
        view.findViewById<View>(R.id.iv_wallet)
            .setOnClickListener {
                startActivity(Intent(view.context, AddWalletActivity::class.java))
            }
        view.findViewById<View>(R.id.tv_receive)
            .setOnClickListener {
                startActivity(Intent(view.context, ReceiveActivity::class.java))
            }
        view.findViewById<View>(R.id.tv_send)
            .setOnClickListener {
                startActivity(Intent(view.context, SendActivity::class.java))
            }
        view.findViewById<View>(R.id.tv_swap)
            .setOnClickListener {
                startActivity(Intent(view.context, SwapActivity::class.java))
            }
//        view.findViewById<View>(R.id.iv_add)
//            .setOnClickListener {
//                startActivity(Intent(view.context, AddTokenActivity::class.java))
//            }
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
            val view = LayoutInflater.from(context).inflate(R.layout.layout_wallet_item, parent, false)
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