package com.yns.wallet

import android.os.Bundle
import android.view.View

class DetailResetPwdActivity : BaseActivity() {

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_detail_reset_pwd)

        findViewById<View>(R.id.tv_confirm).setOnClickListener {
            showDevToast(this)
        }
    }
}