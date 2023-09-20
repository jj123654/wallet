package com.yns.wallet.adapter


import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.R
import com.yns.wallet.bean.TokenBean
import com.yns.wallet.bean.TokenModel
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.bean.WalletItemInfo
import com.yns.wallet.databinding.ItemAddTokenBinding
import com.yns.wallet.databinding.ItemAssetsBinding
import com.yns.wallet.databinding.ItemTransactionRecordBinding
import com.yns.wallet.databinding.LayoutWalletItemBinding


class TokenListAdapter(context:Context,currentWalletTokenList:MutableList<TokenModel>, data: MutableList<TokenModel>?) :
    BaseQuickAdapter<TokenModel, TokenListAdapter.BaseHolder>(R.layout.item_add_token, data) {

    var dpItem = QMUIDisplayHelper.dp2px(context,16)
    val tokenList = currentWalletTokenList

    override fun convert(holder: BaseHolder, item: TokenModel) {
        holder.vb.apply {

            var layoutParams = itemLayout.layoutParams as RecyclerView.LayoutParams
            var background = 0

            when (holder.absoluteAdapterPosition) {
                0 -> {
                    ivBalance.visibility = View.INVISIBLE
                    lineView.visibility = View.VISIBLE
                    layoutParams.topMargin = 0
                    layoutParams.bottomMargin = 0
                    background = R.drawable.shape_main_top_corner_20_normal_bg
                }
                data.size-1 -> {
                    ivBalance.visibility = View.VISIBLE
                    lineView.visibility = View.GONE
                    layoutParams.topMargin = 0
                    layoutParams.bottomMargin = dpItem
                    background = R.drawable.shape_main_bottom_corner_20_normal_bg
                }
                else -> {
                    ivBalance.visibility = View.VISIBLE
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

            ivBalance.isSelected = tokenList.firstOrNull{it.address==item.address}!=null

            addChildClickViewIds(R.id.iv_balance)
        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemAddTokenBinding.bind(view)
    }

}
