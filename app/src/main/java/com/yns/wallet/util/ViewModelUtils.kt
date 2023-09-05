package com.yns.wallet.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.yns.wallet.base.BaseApplication

object ViewModelUtils {

    /**
     * Fragment 和 Activity 可以使用此方法获取自己对应的Model
     * 获取一个ViewModel ，懒加载
     */
    inline fun <reified T : ViewModel> ViewModelStoreOwner.lazyViewModel(
        key: String? = null,
        factory: ViewModelProvider.Factory? = null
    ): Lazy<T> {
        return lazy(LazyThreadSafetyMode.NONE) {
            this.viewModel<T>(key, factory) as T
        }
    }

    /**
     *  非懒加载
     */
    inline fun <reified T : ViewModel> ViewModelStoreOwner.viewModel(
        key: String? = null,
        factory: ViewModelProvider.Factory? = null
    ): T {
        val provider =
            if (factory == null) ViewModelProvider(this) else ViewModelProvider(this, factory)
        return if (key != null) {
            provider[key, T::class.java]
        } else {
            provider[T::class.java]
        }
    }


    /**
     * Fragment 和 Activity 可以使用此方法获取自己对应的Model
     * 获取一个ViewModel ，懒加载
     */
    inline fun <reified T : ViewModel> lazyGlobalViewModel(
        key: String? = null,
        factory: ViewModelProvider.Factory? = null
    ): Lazy<T> {
        return lazy(LazyThreadSafetyMode.NONE) {
            BaseApplication.globalContext().viewModel(key, factory) as T
        }
    }

}

