package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityDetailResetPwdBinding
import com.yns.wallet.util.showDevToast

class DetailResetPwdActivity : BaseActivity<ActivityDetailResetPwdBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {

        findViewById<View>(R.id.tv_confirm).setOnClickListener {
            showDevToast(this)
        }
    }


}