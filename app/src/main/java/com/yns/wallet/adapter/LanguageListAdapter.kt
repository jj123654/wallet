package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.LanguageBean
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.databinding.ItemSelectLanguageBinding
import com.yns.wallet.databinding.ItemTransactionRecordBinding


class LanguageListAdapter(context:Context, data: MutableList<LanguageBean>?) :
    BaseQuickAdapter<LanguageBean, LanguageListAdapter.DeviceHolder>(R.layout.item_select_language, data) {
    override fun convert(holder: DeviceHolder, item: LanguageBean) {
        holder.vb.apply {
            nameTv.text = item.name
            tagImg.isSelected = item.isSelected
        }

    }

    class DeviceHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemSelectLanguageBinding.bind(view)
    }


    fun refreshData(position:Int){
        data.forEach { it.isSelected = false }
        data[position].isSelected = true
        notifyDataSetChanged()
    }

}
