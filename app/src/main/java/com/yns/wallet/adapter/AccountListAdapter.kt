package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.AccountBean
import com.yns.wallet.bean.LanguageBean
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.databinding.ItemSelectLanguageBinding
import com.yns.wallet.databinding.ItemSelectNetworkBinding
import com.yns.wallet.databinding.ItemTransactionRecordBinding
import com.yns.wallet.util.LanguageUtils


class AccountListAdapter(context:Context, data: MutableList<AccountBean>?) :
    BaseQuickAdapter<AccountBean, AccountListAdapter.BaseHolder>(R.layout.item_select_network, data) {
    override fun convert(holder: BaseHolder, item: AccountBean) {
        holder.vb.apply {
            nameTv.text = item.name
            contentTv.visibility = View.VISIBLE
            contentTv.text = "0xsadjkfhjlas....asdfasdf"
            tagImg.isSelected = item.isSelected
        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemSelectNetworkBinding.bind(view)
    }


    fun refreshData(position:Int){
        data.forEach { it.isSelected = false }
        data[position].isSelected = true
        notifyDataSetChanged()
    }

}
