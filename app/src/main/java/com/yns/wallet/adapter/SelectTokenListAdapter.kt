package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.TokenBean
import com.yns.wallet.databinding.ItemSelectNetworkBinding


class SelectTokenListAdapter(context:Context, data: MutableList<TokenBean>?) :
    BaseQuickAdapter<TokenBean, SelectTokenListAdapter.ViewHolder>(R.layout.item_select_network, data) {
    override fun convert(holder: ViewHolder, item: TokenBean) {
        holder.vb.apply {
            nameTv.text = item.name
            contentTv.visibility = View.VISIBLE
            contentTv.text = item.content
            tagImg.isSelected = item.isSelected
        }

    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemSelectNetworkBinding.bind(view)
    }


    fun refreshData(position:Int){
        data.forEach { it.isSelected = false }
        data[position].isSelected = true
        notifyDataSetChanged()
    }

}
