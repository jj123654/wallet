package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.yns.wallet.api.NetworkApi
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            delay(2000)
            checkWallet()
        }
    }

    /**
     * 检查是否存在钱包，如果存在，则直接跳转主页，如果不存在，则跳转导入或新建页面
     */
    private fun checkWallet() {
        if (walletViewModel.getWalletCount() > 0) {
            startActivity(MainActivity::class.java)
        } else {
            startActivity(ImportOrCreateWallet::class.java)
        }
        finish()
    }

}
