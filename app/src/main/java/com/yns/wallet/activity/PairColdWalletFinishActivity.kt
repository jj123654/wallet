package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityAboutUsBinding
import com.yns.wallet.databinding.ActivityPairColdWalletFinishBinding
import com.yns.wallet.util.AppManager

class PairColdWalletFinishActivity : BaseActivity<ActivityPairColdWalletFinishBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            tvHasSigned.onClick {
                AppManager.getAppManager().returnToActivity(AddWalletActivity::class.java)
            }

        }
    }


}
