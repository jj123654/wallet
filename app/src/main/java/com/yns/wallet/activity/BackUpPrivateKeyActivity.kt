package com.yns.wallet.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityBackUpPrivateKeyBinding
import com.yns.wallet.util.onClick
import com.yns.wallet.util.showToast
import com.yns.wallet.widget.CommonAlertDialog
import com.yns.wallet.widget.CommonCenterDialog

/**
 * 备份私钥页面
 */
class BackUpPrivateKeyActivity : BaseActivity<ActivityBackUpPrivateKeyBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.topLayout.let {
            it.tvViewMnemonic.onClick {
                CommonAlertDialog.show(
                    this,
                    title = getString(R.string.risk_warning),
                    content = getString(R.string.there_are_risks_in_copying_private_key_the_clipboard_is_vulnerable_to_hacking_recommended_to_store_in_a_secure_manner),
                )
                viewBinding.topLayout.root.visibility = View.GONE
            }
            it.tvPopContent.text =
                getString(R.string.anyone_with_your_private_key_can_access_all_your_assets_please_keep_the_information_to_yourself)
            it.tvViewMnemonic.text = getString(R.string.view_private_key)
            it.tvPopTitle.text = getString(R.string.please_correctly_copy_the_private_key_in_order)
        }
        viewBinding.copy.onClick {
            val text = viewBinding.key.text.toString()
            if (TextUtils.isEmpty(text)) {
                return@onClick
            }
            val clip = getSystemService(CLIPBOARD_SERVICE) as? ClipboardManager
            clip?.apply {
                setPrimaryClip(ClipData.newPlainText("copy", text))
            }
            showToast(getString(R.string.copy_success))
        }
        viewBinding.qrCode.onClick {
            val text = viewBinding.key.text.toString()
            if (TextUtils.isEmpty(text)) {
                return@onClick
            }
            CommonCenterDialog(this).showCenterQRDialog(text,
                getString(R.string.private_key_qr_code),
                getString(R.string.cancel),
                getString(R.string.continue_),
                View.OnClickListener {

                },
                View.OnClickListener {

                })
        }
    }


}
