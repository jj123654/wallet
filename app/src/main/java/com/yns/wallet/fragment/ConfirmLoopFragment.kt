package com.yns.wallet.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.PopupWindow
import com.luck.lib.camerax.utils.DensityUtil
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.databinding.FragmentConfirmLoopBinding
import com.yns.wallet.databinding.PopupWindowBalanceBinding
import com.yns.wallet.util.onClick

class ConfirmLoopFragment : BaseFragment<FragmentConfirmLoopBinding>() {
    override val isDialog: Boolean = true
    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.walletName.onClick {
            showPopup()
        }
    }

    var popup: PopupWindow? = null
    var popupViewBinding: PopupWindowBalanceBinding? = null
    private fun showPopup() {
        val p = popup ?: PopupWindow(requireContext()).let {
            val view = PopupWindowBalanceBinding.inflate(layoutInflater)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.width = DensityUtil.dip2px(requireContext(), 260f)
            it.isOutsideTouchable = true
            it.isTouchable = true
            popupViewBinding = view
            it.isFocusable = false
            it.contentView = view.root
            it
        }
        p.showAsDropDown(viewBinding.walletName)
    }
}