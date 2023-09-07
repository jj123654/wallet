package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.databinding.ItemAssetsBinding
import com.yns.wallet.databinding.ItemTransactionRecordBinding


class AssetsListAdapter(context:Context, data: MutableList<TransactionRecord>?) :
    BaseQuickAdapter<TransactionRecord, AssetsListAdapter.DeviceHolder>(R.layout.item_assets, data) {
    override fun convert(holder: DeviceHolder, item: TransactionRecord) {
        holder.vb.apply {
            nameTv.text = "Transfer(0xasdf...345345)"
            priceTv.text = "-5734523.435"
            timeTv.text = "2023/08/08 19:04:04"
        }

    }

    class DeviceHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemAssetsBinding.bind(view)
    }

}
