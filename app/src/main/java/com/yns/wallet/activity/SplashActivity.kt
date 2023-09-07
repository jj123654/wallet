package com.yns.wallet.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(MainActivity::class.java)
            finish()
        },2000)
    }

}
