package com.yns.wallet.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {

        findViewById<View>(R.id.tv_unlock).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ImportOrCreateWallet::class.java
                )
            )
        }
    }


}