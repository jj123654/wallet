package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityWebViewBinding
import com.yns.wallet.util.showToast

class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        val webSettings: WebSettings = viewBinding.webView.settings
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webSettings.setSupportZoom(true)
        webSettings.javaScriptEnabled = true

        //启用地理定位，默认为true

        //启用地理定位，默认为true
        webSettings.setGeolocationEnabled(true)
        webSettings.databaseEnabled = true

        webSettings.domStorageEnabled = true //2018/6/15 打开本地缓存提供JS调用,


        webSettings.setAppCacheMaxSize((1024 * 1024 * 8).toLong()) // 实现8倍缓存

        val appCachePath = applicationContext.cacheDir.absolutePath
        webSettings.setAppCachePath(appCachePath)
        webSettings.allowFileAccess = true
        webSettings.setAppCacheEnabled(true)

        webSettings.pluginState = WebSettings.PluginState.ON
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }

        // 设置字符集编码

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//        }

        // 设置字符集编码
        webSettings.defaultTextEncodingName = "UTF-8"
        // 开启JavaScript支持
        // 传递一个Java对象，同时给他命名，这个对象可以在js中调用这个对象的方法
//        product_webView.setWebChromeClient(new WebChromeClient());
        //        product_webView.setWebChromeClient(new WebChromeClient());
        viewBinding.webView.isHardwareAccelerated

        viewBinding.titleBar.setLeftListener{
            onBackPressed()
        }

        val url = intent.getStringExtra("url")
        url?.let { viewBinding.webView.loadUrl(it) }
    }


    override fun onBackPressed() {
        if (viewBinding.webView.canGoBack()) {
            viewBinding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
