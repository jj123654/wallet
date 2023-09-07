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
import com.yns.wallet.util.LanguageUtils


class LanguageListAdapter(context:Context, data: MutableList<LanguageBean>?) :
    BaseQuickAdapter<LanguageBean, LanguageListAdapter.BaseHolder>(R.layout.item_select_language, data) {
    override fun convert(holder: BaseHolder, item: LanguageBean) {
        holder.vb.apply {
            nameTv.text = item.language
            tagImg.isSelected = item.isSelected
        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemSelectLanguageBinding.bind(view)
    }


    fun refreshData(){
        data.forEach {
            it.isSelected = it.languageLocale?.language == LanguageUtils.getInstance().language
        }
        notifyDataSetChanged()
    }

}
