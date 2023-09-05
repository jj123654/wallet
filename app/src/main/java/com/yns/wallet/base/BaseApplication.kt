package com.yns.wallet.base

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class BaseApplication:Application(), ViewModelStoreOwner {

    override fun onCreate() {
        super.onCreate()
        _context = this
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

}