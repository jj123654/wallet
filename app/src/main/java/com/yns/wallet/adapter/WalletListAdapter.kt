package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.bean.WalletItemInfo
import com.yns.wallet.databinding.ItemAssetsBinding
import com.yns.wallet.databinding.ItemTransactionRecordBinding
import com.yns.wallet.databinding.LayoutWalletItemBinding


class WalletListAdapter(context:Context, data: MutableList<WalletItemInfo>?) :
    BaseQuickAdapter<WalletItemInfo, WalletListAdapter.BaseHolder>(R.layout.layout_wallet_item, data) {
    override fun convert(holder: BaseHolder, item: WalletItemInfo) {
        holder.vb.apply {
            ivIcon.setImageResource(item.iconId)
            tvName.text = item.name
            tvDesc.text = item.desc
            tvIndex.text = item.index.toString()
            tvBalance.text = item.balance.toString()
        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = LayoutWalletItemBinding.bind(view)
    }

}
