package com.yns.wallet

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams


abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. 使内容区域全屏
//        WindowCompat.setDecorFitsSystemWindows(window, true)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        // 2. 设置 System bar 透明
        window.statusBarColor = Color.TRANSPARENT

        super.onCreate(savedInstanceState)
        init(savedInstanceState)
        adjustStatusBar()
    }

    open fun init(savedInstanceState: Bundle?) {}

    private fun adjustStatusBar() {
        val statusBar = findViewById<View>(R.id.v_status_bar)
        if (statusBar != null) {
            statusBar.layoutParams.height = getStatusBarHeight(this)
        }
    }
}