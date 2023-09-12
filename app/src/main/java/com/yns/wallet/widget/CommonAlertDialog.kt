package com.yns.wallet.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams
import androidx.appcompat.app.AppCompatDialog
import com.yns.wallet.R
import com.yns.wallet.databinding.DialogCommonAlertBinding
import com.yns.wallet.util.onClick

/**
 * 居中的白底  2个按钮的 对话框
 */
class CommonAlertDialog(context: Context) : AppCompatDialog(context, R.style.Dialog) {

    lateinit var viewBinding: DialogCommonAlertBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DialogCommonAlertBinding.inflate(
            layoutInflater,
            window?.decorView as ViewGroup?, false
        )
        window?.let {
            it.setBackgroundDrawable(null)
            it.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }
        setContentView(viewBinding.root)
    }

    companion object {
        /**
         * 显示对话框
         */
        @JvmStatic
        @JvmOverloads
        fun show(
            context: Context,
            title: String = "",
            content: String = "",
            confirmClick: (Dialog) -> Unit = { it.dismiss() },
            cancelClick: (Dialog) -> Unit = { it.dismiss() },
            showCancel: Boolean = true
        ): CommonAlertDialog {
            val dialog = CommonAlertDialog(context)
            dialog.show()
            dialog.viewBinding.title.text = title
            dialog.viewBinding.content.text = content
            dialog.viewBinding.confirm.onClick {
                confirmClick(dialog)
            }
            dialog.viewBinding.cancel.onClick {
                cancelClick(dialog)
            }
            if (showCancel) {
                dialog.viewBinding.space.visibility = View.VISIBLE
                dialog.viewBinding.cancel.visibility = View.VISIBLE
            } else {
                dialog.viewBinding.space.visibility = View.GONE
                dialog.viewBinding.cancel.visibility = View.GONE
            }
            return dialog
        }
    }
}