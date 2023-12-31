package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.R
import com.yns.wallet.bean.TokenModel
import com.yns.wallet.databinding.ItemAddTokenBinding


class TokenListAdapter(context:Context,currentWalletTokenList:MutableList<TokenModel>, data: MutableList<TokenModel>?) :
    BaseQuickAdapter<TokenModel, TokenListAdapter.BaseHolder>(R.layout.item_add_token, data) {

    var dpItem = QMUIDisplayHelper.dp2px(context,16)
    private var tokenList = currentWalletTokenList

    override fun convert(holder: BaseHolder, item: TokenModel) {
        holder.vb.apply {

            var layoutParams = itemLayout.layoutParams as RecyclerView.LayoutParams
            var background = 0

            when (holder.absoluteAdapterPosition) {
                0 -> {
                    lineView.visibility = View.VISIBLE
                    layoutParams.topMargin = 0
                    layoutParams.bottomMargin = 0
                    background = R.drawable.shape_main_top_corner_20_normal_bg
                }
                data.size-1 -> {
                    lineView.visibility = View.GONE
                    layoutParams.topMargin = 0
                    layoutParams.bottomMargin = dpItem
                    background = R.drawable.shape_main_bottom_corner_20_normal_bg
                }
                else -> {
                    lineView.visibility = View.VISIBLE
                    layoutParams.topMargin = 0
                    layoutParams.bottomMargin = 0
                    background = R.drawable.shape_main_blue_bg
                }
            }
            itemLayout.layoutParams = layoutParams
            itemLayout.background = context.resources.getDrawable(background)

            ivMineIcon2.load(item.imageUrl){
                placeholder(R.mipmap.icon_default_user)
            }
            ivMineName2.text = item.name
            ivMineHash2.text = item.address

            ivBalance.visibility = if(item.name == "YNS"||item.name=="USDT"||item.name=="TRX") View.INVISIBLE else View.VISIBLE
            ivBalance.isSelected = tokenList.firstOrNull{it.address==item.address}!=null

            addChildClickViewIds(R.id.iv_balance)
        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemAddTokenBinding.bind(view)
    }

    fun refreshData(currentWalletTokenList:MutableList<TokenModel>){
        tokenList = currentWalletTokenList
        notifyDataSetChanged()
    }

}
