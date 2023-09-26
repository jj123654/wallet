package com.yns.wallet.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityLoginBinding
import com.yns.wallet.util.addEditEyeViewLogic
import com.yns.wallet.util.showToast
import com.yns.wallet.widget.CommonCenterDialog

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {

        viewBinding.apply {
            tvUnlock.onClick {
                if(etPwd.text.toString().isNullOrEmpty()){
                    pwdEmptyTv.visibility =  View.VISIBLE
                    pwdEmptyTv.text = getString(R.string.please_enter_password)
                }else{
                    walletViewModel.checkPassword(etPwd.text.toString()){result->
                        if(result){
                            pwdEmptyTv.visibility =  View.GONE
                            finish()
                        }else{
                            pwdEmptyTv.visibility =  View.VISIBLE
                            pwdEmptyTv.text = getString(R.string.password_is_wrong)
                        }
                    }
                }

            }

            addEditEyeViewLogic(window.decorView, R.id.iv_pwd_eye, R.id.et_pwd)

            tvResetWallet.onClick {
                CommonCenterDialog(this@LoginActivity).showCenterTipsDialog(null,null,null,null){
                    Intent(this@LoginActivity, ImportOrCreateWallet::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(this)
                    }
                }
            }

        }
    }


}