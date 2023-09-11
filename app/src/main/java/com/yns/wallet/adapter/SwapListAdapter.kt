package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.SwapRecordBean
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.databinding.ItemSwapRecordBinding
import com.yns.wallet.databinding.ItemTransactionRecordBinding


class SwapListAdapter(context:Context, data: MutableList<SwapRecordBean>?) :
    BaseQuickAdapter<SwapRecordBean, SwapListAdapter.BaseHolder>(R.layout.item_swap_record, data) {
    override fun convert(holder: BaseHolder, item: SwapRecordBean) {
        holder.vb.apply {
//            sourceTv.text = "智能触发合约"
//            addressTv.text = "sdfasdfasdf....asdf"
//            addressTv2.text = "sdfasdfasdf....asdf"
//            numTitleTv.text = "转账数量："
//            numContentTv.text = "100TRX"
//            timeTv.text = "2023/07/19 22:33:00"
//            submitTv.text = "已确认"
        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemSwapRecordBinding.bind(view)
    }

}
