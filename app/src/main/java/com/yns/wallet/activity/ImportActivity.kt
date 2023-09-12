package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityImportBinding
import com.yns.wallet.util.addEditEyeViewLogic
import com.yns.wallet.util.showToast

class ImportActivity : BaseActivity<ActivityImportBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {

        viewBinding.apply {
            tvImport.onClick {
                if(inputEdit.text.toString().isNullOrEmpty()){
                    showToast(getString(R.string.please_enter_name))
                }else {
                    startActivity(Import2Activity::class.java)
                    finish()
                }

            }
        }

    }
}
