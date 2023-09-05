package com.yns.wallet

import android.content.Intent
import android.os.Bundle
import android.view.View

class ImportOrCreateWallet : BaseActivity() {

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_import_create)

        findViewById<View>(R.id.tv_import).setOnClickListener {
            startActivity(Intent(this, ImportActivity::class.java))
        }
        findViewById<View>(R.id.tv_create).setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }
    }

}
