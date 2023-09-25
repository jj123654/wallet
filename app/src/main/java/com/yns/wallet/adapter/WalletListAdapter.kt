package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.TokenModel
import com.yns.wallet.databinding.LayoutWalletItemBinding


class WalletListAdapter(context:Context, data: MutableList<TokenModel>?) :
    BaseQuickAdapter<TokenModel, WalletListAdapter.BaseHolder>(R.layout.layout_wallet_item, data) {
    override fun convert(holder: BaseHolder, item: TokenModel) {
        holder.vb.apply {
            ivIcon.load(item.imageUrl) {
                placeholder(R.mipmap.account_default_photo)
            }
            tvName.text = item.name
            tvDesc.text = item.balance.toString()
            tvIndex.text = item.name.toString()
            tvBalance.text = "${item.usd.toString()}"
        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = LayoutWalletItemBinding.bind(view)
    }

}
