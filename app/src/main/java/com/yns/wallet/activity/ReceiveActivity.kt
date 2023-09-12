package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityReceiveBinding
import com.yns.wallet.util.CodeCreator
import com.yns.wallet.util.copyToClipboard
import com.yns.wallet.util.onClick

class ReceiveActivity : BaseActivity<ActivityReceiveBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        setQRCode(viewBinding.ivMineName.text.toString())
        viewBinding.ivMineName.doAfterTextChanged {
            setQRCode(it.toString())
        }
        viewBinding.tvSuperLoop.onClick {
            viewBinding.ivMineName.text.toString().copyToClipboard(this)
        }
    }

    private fun setQRCode(code: String) {
        CodeCreator.setQRCode(this, code, viewBinding.qrCode)
    }

}
