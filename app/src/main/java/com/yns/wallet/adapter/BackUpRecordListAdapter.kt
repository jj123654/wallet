package com.yns.wallet.adapter


import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.api.WalletApi
import com.yns.wallet.bean.BackUpRecordModel
import com.yns.wallet.databinding.ItemBackUpRecordBinding
import com.yns.wallet.databinding.ItemTransactionRecordBinding
import com.yns.wallet.util.formatTimeString


class BackUpRecordListAdapter(data: MutableList<BackUpRecordModel>?) :
    BaseQuickAdapter<BackUpRecordModel, BackUpRecordListAdapter.BaseHolder>(
        R.layout.item_back_up_record,
        data
    ) {
    override fun convert(holder: BaseHolder, item: BackUpRecordModel) {
        holder.vb.apply {
            when(item.type){
                WalletApi.BACKUP_TYPE.PRIVATE_KEY->{
                    titleTv.text = context.getString(R.string.back_up_private_key)
                }
                else->{
                    titleTv.text = context.getString(R.string.back_up_mnemonic)
                }
            }

            nameTv.text = "${context.getString(R.string.back_up_name)}${item.name}"
            addressTv.text = "${context.getString(R.string.back_up_address)}${item.address}"
            timeTv.text = "${context.getString(R.string.back_up_time)}${formatTimeString(item.time)}"

        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemBackUpRecordBinding.bind(view)
    }

}
