package com.yns.wallet.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityDetailBinding
import com.yns.wallet.widget.CommonCenterDialog

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {

        viewBinding.apply {
            ivMineName.onClick {
                CommonCenterDialog(this@DetailActivity).showCenterEditDialog(
                    false,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            }
            changePasswordTv.onClick {
                CommonCenterDialog(this@DetailActivity).showPswEditDialog {
                    startActivity(ChangePasswordActivity::class.java)
                }

            }
            tvPrivateKey.onClick {
                CommonCenterDialog(this@DetailActivity).showPswEditDialog {
                    startActivity(BackUpPrivateKeyActivity::class.java)
                }
            }
            tvMnemonic.onClick {
                CommonCenterDialog(this@DetailActivity).showPswEditDialog {
                    startActivity(BackUpMnemonicActivity::class.java)
                }
            }
            backUpRecordsTv.onClick {
                startActivity(BackUpRecordActivity::class.java)
            }
            tvResetWallet.onClick {
                CommonCenterDialog(this@DetailActivity).showPswEditDialog {

                }
            }
        }

    }


}