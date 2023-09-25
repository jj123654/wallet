package com.yns.wallet.adapter


import android.content.Context
import android.text.TextUtils
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.Data
import com.yns.wallet.databinding.ItemTransactionRecordBinding
import com.yns.wallet.util.formatTimeString
import java.text.SimpleDateFormat


class TransactionListAdapter(context: Context, data: MutableList<Data>?) :
    BaseQuickAdapter<Data, TransactionListAdapter.BaseHolder>(
        R.layout.item_transaction_record,
        data
    ), LoadMoreModule {
    override fun convert(holder: BaseHolder, item: Data) {
        holder.vb.apply {
            if (TextUtils.isEmpty(item.toAddress)) {
                sourceTv.text = context.getString(R.string.use_contract)
            } else {
                sourceTv.text = context.getString(R.string.transfer)
            }
            addressTv.text = item.ownerAddress
            addressTv2.text = item.toAddress
            numTitleTv.text = context.getString(R.string.money_fee)
            numContentTv.text = item.amount +"TRX"
            timeTv.text = formatTimeString(item.timestamp ?: 0)
            if (item.confirmed == true) {
                submitTv.text = context.getString(R.string.confirmed)
                submitTv.visibility = View.VISIBLE
            } else {
                submitTv.visibility = View.INVISIBLE

            }
        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemTransactionRecordBinding.bind(view)
    }

}
