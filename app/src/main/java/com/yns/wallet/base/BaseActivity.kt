package com.yns.wallet.base

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.yns.wallet.R
import com.yns.wallet.util.getStatusBarHeight
import java.lang.reflect.ParameterizedType


abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {

    lateinit var viewBinding: VB

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
        //利用反射，调用指定ViewBinding中的inflate方法填充视图
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments[0] as Class<VB>
                val method = clazz.getMethod("inflate", LayoutInflater::class.java)
                method.isAccessible = true
                viewBinding = method.invoke(null, layoutInflater) as VB
                setContentView(viewBinding.root)
                initView(viewBinding.root, savedInstanceState)
                initObserver()
            } catch (e: Exception) {
            }
        }
        adjustStatusBar()
    }
    abstract fun initView(root: View, savedInstanceState: Bundle?)

    //livedata监听
    open fun initObserver() {}

    private fun adjustStatusBar() {
        val statusBar = findViewById<View>(R.id.v_status_bar)
        if (statusBar != null) {
            statusBar.layoutParams.height = getStatusBarHeight(this)
        }
    }

    fun startActivity(activity: Class<out Activity>) {
        startActivity(Intent(this@BaseActivity, activity))
    }

}