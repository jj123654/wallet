package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.adapter.SwapListAdapter
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.SwapRecordBean
import com.yns.wallet.databinding.ActivitySwapBinding
import com.yns.wallet.databinding.LayoutChooseTokenBinding
import com.yns.wallet.databinding.LayoutSwapHeaderBinding
import com.yns.wallet.fragment.ConfirmLoopFragment
import com.yns.wallet.util.onClick
import com.yns.wallet.widget.bottomsheet.BottomSheet
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class SwapActivity : BaseActivity<ActivitySwapBinding>() {

    private val swapListAdapter: SwapListAdapter by lazy {
        SwapListAdapter(this@SwapActivity, mutableListOf()).apply {

        }
    }

    var list: MutableList<SwapRecordBean> = ArrayList()

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            recyclerView.layoutManager = WrapContentLinearLayoutManager(
                this@SwapActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            recyclerView.addItemDecoration(
                SpacesItemDecoration(
                    QMUIDisplayHelper.dp2px(
                        this@SwapActivity,
                        20
                    ), QMUIDisplayHelper.dp2px(this@SwapActivity, 15)
                )
            )
            recyclerView.adapter = swapListAdapter
        }
        val header = viewBinding.header
        if (list.size <= 0) {
            for (i in 0..11) {
                list.add(SwapRecordBean())
            }

            swapListAdapter.addData(list)
        }
        header.rlFromLayout.onClick {
            showChooseToken()
        }
        header.rlToLayout.onClick {
            showChooseToken()
        }
        header.tvSwap.onClick {
            ConfirmLoopFragment().show(supportFragmentManager)
        }
    }

    private fun showChooseToken() {
        val view = LayoutChooseTokenBinding.inflate(layoutInflater)
        val bottomSheet = BottomSheet.Builder(this).contentView(view.root).build()
        bottomSheet.show()
    }

}
