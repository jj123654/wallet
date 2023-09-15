package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.base.Constant
import com.yns.wallet.bean.HDWalletBean
import com.yns.wallet.bean.TokenBean
import com.yns.wallet.bean.WalletModel
import com.yns.wallet.databinding.ItemChooseAccountBinding
import com.yns.wallet.databinding.ItemSelectNetworkBinding
import com.yns.wallet.util.loadCircle


class HDWalletListAdapter(data: MutableList<HDWalletBean>?) :
    BaseQuickAdapter<HDWalletBean, HDWalletListAdapter.ViewHolder>(R.layout.item_choose_account, data),
    LoadMoreModule {
    override fun convert(holder: ViewHolder, item: HDWalletBean) {
        holder.vb.apply {
            tvAccountHash.text = item.address
            tvAccountPath.text = "${Constant.DEFAULT_PATH}${holder.absoluteAdapterPosition}"
            tvAccountBalance.text = "${holder.absoluteAdapterPosition}"
            tagImg.isSelected = item.isSelected
        }

    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemChooseAccountBinding.bind(view)
    }


    fun refreshData(position: Int) {
        data.forEach { it.isSelected = false }
        data[position].isSelected = true
        notifyDataSetChanged()
    }

}