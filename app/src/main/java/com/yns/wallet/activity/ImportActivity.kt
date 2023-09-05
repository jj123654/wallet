package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityImportBinding
import com.yns.wallet.util.addEditEyeViewLogic

class ImportActivity : BaseActivity<ActivityImportBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {

        findViewById<View>(R.id.tv_import).setOnClickListener {
            Toast.makeText(this, "开发中", Toast.LENGTH_SHORT).show()
        }


        addEditEyeViewLogic(window.decorView, R.id.iv_pwd_eye, R.id.et_pwd)
        addEditEyeViewLogic(window.decorView, R.id.iv_repeat_pwd_eye, R.id.et_repeat_pwd)
    }
}
