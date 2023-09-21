package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.api.WalletApi
import com.yns.wallet.bean.SwapRecordBean
import com.yns.wallet.bean.SwapRecordModel
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.databinding.ItemSwapRecordBinding
import com.yns.wallet.databinding.ItemTransactionRecordBinding
import com.yns.wallet.util.formatTimeString


class SwapListAdapter(context:Context, data: MutableList<SwapRecordModel>?) :
    BaseQuickAdapter<SwapRecordModel, SwapListAdapter.BaseHolder>(R.layout.item_swap_record, data) {
    override fun convert(holder: BaseHolder, item: SwapRecordModel) {
        holder.vb.apply {
            fromTv.text = item.swapInfo?.fromToken
            fromAmountTv.text = item.swapInfo?.fromAmount.toString()
            when(item.result){
                WalletApi.TX_RESULT.SUCCESS->{
                    resultTv.text = context.getString(R.string.success)
                    resultTv.setTextColor(context.getColor(R.color.transaction_green_color))
                }
                else -> {
                    resultTv.text = context.getString(R.string.fail)
                    resultTv.setTextColor(context.getColor(R.color.tips_red_color))
                }
            }
            toTv.text = item.swapInfo?.toToken
            toAmountTv.text = item.swapInfo?.toAmount.toString()
            timeTv.text = formatTimeString(item.time)
            rateTv.text = "${context.getString(R.string.rate)}${item.swapInfo?.rate}"
        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemSwapRecordBinding.bind(view)
    }

}
