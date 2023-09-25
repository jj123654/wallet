package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.TokenModel
import com.yns.wallet.databinding.ItemChooseTokenBinding


class BottomSheetChooseTokenListAdapter(context:Context, data: MutableList<TokenModel>?) :
    BaseQuickAdapter<TokenModel, BottomSheetChooseTokenListAdapter.ViewHolder>(R.layout.item_choose_token, data) {

    var current = -1

    override fun convert(holder: ViewHolder, item: TokenModel) {
        holder.vb.apply {
            ivIcon.load(item.imageUrl) {
                placeholder(R.mipmap.account_default_photo)
            }
            tvName.text = item.name
            tvAccountPath.text = "${item.balance.toString()} ${item.name}"
            tagImg.isSelected = holder.absoluteAdapterPosition == current
        }

    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemChooseTokenBinding.bind(view)
    }


    fun refreshData(position: Int) {
        current = position
        notifyDataSetChanged()
    }

    fun getCurrentSelect() = current

}
