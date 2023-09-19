package com.yns.wallet.adapter


import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.WalletModel
import com.yns.wallet.databinding.ItemSelectNetworkBinding
import com.yns.wallet.util.loadCircle


class AccountListAdapter(data: MutableList<WalletModel>?) :
    BaseQuickAdapter<WalletModel, AccountListAdapter.BaseHolder>(
        R.layout.item_select_network,
        data
    ) {
    var current = -1
    override fun convert(holder: BaseHolder, item: WalletModel) {
        holder.vb.apply {
            nameTv.text = item.name
            contentTv.visibility = View.VISIBLE
            contentTv.text = item.address
            tagImg.isSelected = holder.absoluteAdapterPosition == current
            logoImg.loadCircle("",R.mipmap.account_default_photo)
        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemSelectNetworkBinding.bind(view)
    }


    fun refreshData(position: Int) {
        current = position
        notifyDataSetChanged()
    }

}
