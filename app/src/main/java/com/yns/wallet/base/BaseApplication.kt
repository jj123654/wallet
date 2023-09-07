package com.yns.wallet.base

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.yns.wallet.io.SpUtil
import com.yns.wallet.util.LanguageUtils

class BaseApplication:Application(), ViewModelStoreOwner {

    override fun onCreate() {
        super.onCreate()
        _context = this
        SpUtil.initSp(this)
    }

    companion object {
        private var _context: BaseApplication? = null

        @JvmStatic
        fun globalContext(): BaseApplication {
            return _context!!
        }
    }

    var modelStore: ViewModelStore? = null
    override fun getViewModelStore(): ViewModelStore {
        if (modelStore == null) {
            modelStore = ViewModelStore()
        }
        return modelStore!!
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

}