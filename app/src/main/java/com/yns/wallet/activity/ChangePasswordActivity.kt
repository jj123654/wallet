package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.api.WalletApi
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityAboutUsBinding
import com.yns.wallet.databinding.ActivityChangePwdBinding
import com.yns.wallet.util.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Objects

/**
 * 修改密码页面
 */
class ChangePasswordActivity : BaseActivity<ActivityChangePwdBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            tvConfirm.onClick {
                checkInput()
            }
        }
    }

    private fun checkInput() {
        lifecycleScope.launch {
            val old = viewBinding.etCurrentPwd.text.toString()
            val newPass = viewBinding.etPwd.text.toString()
            val repeat = viewBinding.etRepeatPwd.text.toString()
            viewBinding.walletCurrentPwdEmptyTv.visibility = View.GONE
            viewBinding.walletPwdEmptyTv.visibility = View.GONE
            viewBinding.walletRepeatPwdEmptyTv.visibility = View.GONE
            var correct = true
            //参考web端APP 的流程
            if (old.isEmpty()) {
                correct = false
                viewBinding.walletCurrentPwdEmptyTv.visibility = View.VISIBLE
                viewBinding.walletCurrentPwdEmptyTv.setText(R.string.please_enter_current_password)
            } else {
                val r = withContext(Dispatchers.IO) {
                    WalletApi.checkPassword(old)
                }
                if (!r) {
                    correct = false
                    viewBinding.walletCurrentPwdEmptyTv.visibility = View.VISIBLE
                    viewBinding.walletCurrentPwdEmptyTv.text = getString(R.string.password_error)
                }
            }
            if (newPass.isEmpty()) {
                correct = false
                viewBinding.walletPwdEmptyTv.visibility = View.VISIBLE
                viewBinding.walletPwdEmptyTv.setText(R.string.please_enter_new_password)
            }
            if (newPass.length < 8) {
                correct = false
                viewBinding.walletPwdEmptyTv.visibility = View.VISIBLE
                viewBinding.walletPwdEmptyTv.setText(R.string.password_is_not_less_than_8digits)
            }
            if (!Objects.equals(repeat, newPass)) {
                correct = false
                viewBinding.walletRepeatPwdEmptyTv.visibility = View.VISIBLE
                viewBinding.walletRepeatPwdEmptyTv.setText(R.string.repeat_password_should_equal_to_password)
            }
            if (correct) {
                withContext(Dispatchers.IO) {
                    WalletApi.changePassword(old, newPass)
                }
                showToast(getString(R.string.edit_success))
            }
        }

    }


}
