package com.yns.wallet.base

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.QbSdk.PreInitCallback
import com.yns.wallet.io.SpUtil
import com.yns.wallet.util.LanguageUtils

class BaseApplication:Application(), ViewModelStoreOwner,ImageLoaderFactory {


    companion object {
        private var _context: BaseApplication? = null

        @JvmStatic
        fun globalContext(): BaseApplication {
            return _context!!
        }

    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        _context = this
        SpUtil.initSp(this)
        initX5()
    }

    /**
     * 初始化腾讯x5内核
     */
    private fun initX5() {
        // 在调用TBS初始化、创建WebView之前进行如下配置
        val map = mapOf(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER to true
                ,TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE to true)
        QbSdk.initTbsSettings(map)
        QbSdk.setDownloadWithoutWifi(true) //为了避免消耗用户的流量，x5 内核限制只会在wifi下进行下载和更新。如果您需要在非wifi网络（移动网络、有线网络下）下载内核可在初始化前调用
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        val cb: PreInitCallback = object : PreInitCallback {
            override fun onViewInitFinished(arg0: Boolean) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is $arg0")
            }

            override fun onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        }
        //x5内核初始化接口
        QbSdk.initX5Environment(applicationContext, cb)
    }

    override fun attachBaseContext(base: Context?) {
        LanguageUtils.getInstance().saveSystemCurrentLanguage()
        super.attachBaseContext(base)
        //app刚启动getApplicationContext()为空
        LanguageUtils.getInstance().setConfiguration(applicationContext)
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LanguageUtils.getInstance().setConfiguration(applicationContext);
    }


    var modelStore: ViewModelStore? = null
    override fun getViewModelStore(): ViewModelStore {
        if (modelStore == null) {
            modelStore = ViewModelStore()
        }
        return modelStore!!
    }

}