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
import com.yns.wallet.base.Constant
import com.yns.wallet.databinding.ActivityDetailBinding
import com.yns.wallet.util.copyToClipboard
import com.yns.wallet.widget.CommonCenterDialog

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {

        walletViewModel.currentWalletLiveData.observe(this) {
            viewBinding.ivMineName.text = it.name
            viewBinding.ivMineHash.text = it.address
        }

        viewBinding.apply {
            ivMinePath.text = Constant.DEFAULT_PATH

            ivMineName.onClick {
                CommonCenterDialog(this@DetailActivity).showCenterEditDialog(
                    false,
                    getString(R.string.set_wallet_name),
                    getString(R.string.set_wallet_name),
                    null,
                    null,
                    null
                ){
                    ivMineName.text = it
                }
            }
            ivMineHash.onClick {
                ivMineHash.text.toString().copyToClipboard(this@DetailActivity)
            }
            changePasswordTv.onClick {
                showVerifyPasswordDialog {
                    var intent = Intent(this@DetailActivity, ChangePasswordActivity::class.java)
                    intent.putExtra("password", it)
                    startActivity(intent)
                }

            }
            tvPrivateKey.onClick {
                showVerifyPasswordDialog {
                    var intent = Intent(this@DetailActivity, BackUpPrivateKeyActivity::class.java)
                    intent.putExtra("password", it)
                    startActivity(intent)
                }
            }
            tvMnemonic.onClick {
                showVerifyPasswordDialog {
                    var intent = Intent(this@DetailActivity, BackUpMnemonicActivity::class.java)
                    intent.putExtra("password", it)
                    startActivity(intent)
                }
            }
            backUpRecordsTv.onClick {
                startActivity(BackUpRecordActivity::class.java)
            }
            tvResetWallet.onClick {
                showVerifyPasswordDialog {
                    walletViewModel.currentWalletLiveData.value?.address?.let { it1 ->
                        walletViewModel.deleteWallet(
                            it1
                        )
                    }
                }
            }
        }

    }


}