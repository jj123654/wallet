package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityAddWalletBinding

class AddWalletActivity : BaseActivity<ActivityAddWalletBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            vGenerateWalletBg.onClick{
                startActivity(CreateStepImportActivity::class.java)
            }
        }
    }


}
