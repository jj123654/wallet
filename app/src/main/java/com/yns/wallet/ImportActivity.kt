package com.yns.wallet

import android.os.Bundle
import android.view.View
import android.widget.Toast

class ImportActivity : BaseActivity() {

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_import)

        initView()
    }

    private fun initView() {
        findViewById<View>(R.id.tv_import).setOnClickListener {
            Toast.makeText(this, "开发中", Toast.LENGTH_SHORT).show()
        }
        addEditEyeViewLogic(window.decorView, R.id.iv_pwd_eye, R.id.et_pwd)
        addEditEyeViewLogic(window.decorView, R.id.iv_repeat_pwd_eye, R.id.et_repeat_pwd)
    }
}
