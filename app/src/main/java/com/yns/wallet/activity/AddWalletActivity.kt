package com.yns.wallet.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityAddWalletBinding
import com.yns.wallet.widget.CommonCenterDialog

class AddWalletActivity : BaseActivity<ActivityAddWalletBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            vGenerateWalletBg.onClick{
                CommonCenterDialog(this@AddWalletActivity).showPswEditDialog{
                    var intent = Intent(this@AddWalletActivity,CreateStepImportActivity::class.java)
                    intent.putExtra("isFirstLoad",false)
                    startActivity(CreateStepImportActivity::class.java)
                }

            }

            vImportWalletBg.onClick {
                var intent = Intent(this@AddWalletActivity,ImportActivity::class.java)
                intent.putExtra("isFirstLoad",false)
                startActivity(intent)
            }

        }
    }


}
