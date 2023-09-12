package com.yns.wallet.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityLoginBinding
import com.yns.wallet.widget.CommonCenterDialog

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {

        viewBinding.apply {
            tvUnlock.onClick {
                startActivity(
                    ImportOrCreateWallet::class.java
                )
            }

            tvResetWallet.onClick {
                CommonCenterDialog(this@LoginActivity).showCenterTipsDialog(null,null,null,null){

                }
            }

        }
    }


}