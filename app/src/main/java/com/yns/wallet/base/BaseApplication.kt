package com.yns.wallet.base

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.yns.wallet.WalletViewModel
import com.yns.wallet.io.SpUtil
import com.yns.wallet.util.LanguageUtils

val walletViewModel:WalletViewModel by lazy {BaseApplication.walletViewModelInstance}

class BaseApplication:Application(), ViewModelStoreOwner {

    override fun onCreate() {
        super.onCreate()
        _context = this
        SpUtil.initSp(this)
        walletViewModelInstance = ViewModelProvider(this)[WalletViewModel::class.java]
    }

    companion object {
        private var _context: BaseApplication? = null

        @JvmStatic
        fun globalContext(): BaseApplication {
            return _context!!
        }

        lateinit var walletViewModelInstance: WalletViewModel
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