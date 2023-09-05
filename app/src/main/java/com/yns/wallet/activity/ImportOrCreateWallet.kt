package com.yns.wallet.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityImportCreateBinding

class ImportOrCreateWallet : BaseActivity<ActivityImportCreateBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_import_create)

        findViewById<View>(R.id.tv_import).setOnClickListener {
            startActivity(Intent(this, ImportActivity::class.java))
        }
        findViewById<View>(R.id.tv_create).setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }
    }


}
