package com.yns.wallet.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tencent.smtt.sdk.WebSettings
import com.yns.wallet.R
import com.yns.wallet.activity.AssetsDetailActivity
import com.yns.wallet.adapter.AssetsListAdapter
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.bean.TransactionRecordModel
import com.yns.wallet.databinding.FragmentDiscoverBinding
import com.yns.wallet.databinding.FragmentTransactionRecordBinding
import com.yns.wallet.util.ViewModelUtils.lazyViewModel
import com.yns.wallet.viewmodel.AssetViewModel
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class DiscoverFragment:BaseFragment<FragmentDiscoverBinding>() {



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

        val appCachePath = context?.cacheDir?.absolutePath
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

//        viewBinding.titleBar.setLeftListener{
//            onBackPressed()
//        }
//
//        val url = intent.getStringExtra("url")
        viewBinding.webView.loadUrl("https://www.google.co.uk/")
    }


}