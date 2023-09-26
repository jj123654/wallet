package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityAboutUsBinding
import com.yns.wallet.databinding.ActivityChangePwdBinding

class ChangePasswordActivity : BaseActivity<ActivityChangePwdBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            tvConfirm.onClick {


            }
        }
    }


}
