package com.yns.wallet.base

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.Build.VERSION.SDK_INT
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
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