package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityAboutUsBinding
import com.yns.wallet.databinding.ActivityPairColdWalletBinding

class PairColdWalletActivity : BaseActivity<ActivityPairColdWalletBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
                tvNext.onClick {
                    startActivity(PairColdWalletFinishActivity::class.java)
                }

        }
    }


}
