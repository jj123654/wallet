package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityAboutUsBinding
import com.yns.wallet.databinding.ActivityDetail2Binding
import com.yns.wallet.util.copyToClipboard
import com.yns.wallet.util.onClick

class WalletDetailActivity : BaseActivity<ActivityDetail2Binding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.ivMineHash.onClick {
            val text = viewBinding.ivMineHash.text.toString()
            text.copyToClipboard(this)
        }
    }


}
