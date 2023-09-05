package com.yns.wallet

import android.content.Intent
import android.os.Bundle
import android.view.View

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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