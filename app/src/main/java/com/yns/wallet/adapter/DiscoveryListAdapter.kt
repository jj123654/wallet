package com.yns.wallet.adapter


import android.view.View
import coil.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.DiscoveryListBean
import com.yns.wallet.databinding.ItemDiscoveryRecommendBinding
import com.yns.wallet.databinding.ItemSelectNetworkBinding
import com.yns.wallet.util.loadCircle
import kotlin.math.round


class DiscoveryListAdapter(data: MutableList<DiscoveryListBean>?) :
    BaseQuickAdapter<DiscoveryListBean, DiscoveryListAdapter.BaseHolder>(
        R.layout.item_discovery_recommend,
        data
    ) {
    var current = -1
    override fun convert(holder: BaseHolder, item: DiscoveryListBean) {
        holder.vb.apply {
            nameTv.text = item.name
            contentTv.text = item.content
            logoImg.load(item.logo)
        }

    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemDiscoveryRecommendBinding.bind(view)
    }


    fun refreshData(position: Int) {
        current = position
        notifyDataSetChanged()
    }

}
