package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.Data
import com.yns.wallet.bean.TokenTransferTransactionModel
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.databinding.ItemAssetsBinding
import com.yns.wallet.databinding.ItemTransactionRecordBinding
import com.yns.wallet.util.formatTimeString


class AssetsListAdapter(address: String, context: Context, data: MutableList<Data>?) :
    BaseQuickAdapter<Data, AssetsListAdapter.BaseHolder>(R.layout.item_assets, data) {

    private val address: String = address

    override fun convert(holder: BaseHolder, item: Data) {
        holder.vb.apply {
            nameTv.text = "(${item.ownerAddress})"
            if (address == item.ownerAddress) {
                priceTv.text = "-${item.amount.toString()}"
                priceTv.setTextColor(context.getColor(R.color.tips_red_color))
            } else {
                priceTv.text = "+${item.amount.toString()}"
                priceTv.setTextColor(context.getColor(R.color.transaction_green_color))
            }
            timeTv.text = formatTimeString(item.timestamp ?: 0L)
        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemAssetsBinding.bind(view)
    }

}
