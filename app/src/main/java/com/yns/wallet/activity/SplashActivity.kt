package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            delay(2000)
            startActivity(MainActivity::class.java)
            finish()
        }
    }

    override fun onBackPressed() {
    }

}
