package com.yns.wallet.adapter


import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.yns.wallet.R
import com.yns.wallet.bean.WalletModel
import com.yns.wallet.databinding.ItemDiscorveryHistoryBinding
import com.yns.wallet.databinding.ItemSelectNetworkBinding
import com.yns.wallet.util.loadCircle


class DiscoveryHistoryListAdapter(data: MutableList<String>?) :
    BaseQuickAdapter<String, DiscoveryHistoryListAdapter.BaseHolder>(
        R.layout.item_discorvery_history,
        data
    ) {

    override fun convert(holder: BaseHolder, item: String) {
        holder.vb.apply {
            linkTv.text = item

        }


    }

    class BaseHolder(view: View) : BaseViewHolder(view) {
        val vb = ItemDiscorveryHistoryBinding.bind(view)
    }


}
