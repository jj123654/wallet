package com.yns.wallet.widget

import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.loadmore.BaseLoadMoreView
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.chad.library.adapter.base.util.getItemView
import com.yns.wallet.R

class LoadMoreView : BaseLoadMoreView() {

    override fun getRootView(parent: ViewGroup): View =
            parent.getItemView(R.layout.load_more_view)

    override fun getLoadingView(holder: BaseViewHolder): View =
            holder.getView(R.id.load_more_loading_view)

    override fun getLoadComplete(holder: BaseViewHolder): View =
            holder.getView(R.id.load_more_load_complete_view)

    override fun getLoadEndView(holder: BaseViewHolder): View =
            holder.getView(R.id.load_more_load_end_view)

    override fun getLoadFailView(holder: BaseViewHolder): View =
            holder.getView(R.id.load_more_load_fail_view)
}