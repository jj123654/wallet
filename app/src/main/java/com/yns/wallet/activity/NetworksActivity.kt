package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityNetworksBinding

class NetworksActivity : BaseActivity<ActivityNetworksBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {

        viewBinding.apply {
            addCustomNetworksTv.onClick {
                startActivity(CustomNetworksActivity::class.java)
            }
        }
    }


}
