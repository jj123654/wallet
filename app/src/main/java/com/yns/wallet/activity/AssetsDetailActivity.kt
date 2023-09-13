package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityAboutUsBinding
import com.yns.wallet.databinding.ActivityAssetsDetailBinding
import com.yns.wallet.util.CodeCreator
import com.yns.wallet.util.copyToClipboard
import com.yns.wallet.util.onClick

class AssetsDetailActivity : BaseActivity<ActivityAssetsDetailBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        val link = "123123123123"
        CodeCreator.setQRCode(this, link, viewBinding.qrCode)
        viewBinding.copy.onClick {
            link.copyToClipboard(this)
        }
    }


}
