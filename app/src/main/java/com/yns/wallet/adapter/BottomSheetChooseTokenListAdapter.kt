package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.LanguageBean
import com.yns.wallet.bean.TokenBean
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.databinding.ItemChooseTokenBinding
import com.yns.wallet.databinding.ItemSelectLanguageBinding
import com.yns.wallet.databinding.ItemSelectNetworkBinding
import com.yns.wallet.databinding.ItemTransactionRecordBinding


class BottomSheetChooseTokenListAdapter(context:Context, data: MutableList<TokenBean>?) :
    BaseQuickAdapter<TokenBean, BottomSheetChooseTokenListAdapter.ViewHolder>(R.layout.item_choose_token, data) {
    override fun convert(holder: ViewHolder, item: TokenBean) {
        holder.vb.apply {
            tvName.text = item.name
            tvAccountPath.text = "0${item.name}"
//            contentTv.visibility = View.VISIBLE
//            contentTv.text = item.content
            tagImg.isSelected = item.isSelected
        }

    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemChooseTokenBinding.bind(view)
    }


    fun refreshData(position:Int){
        data.forEach { it.isSelected = false }
        data[position].isSelected = true
        notifyDataSetChanged()
    }

}
