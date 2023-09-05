package com.yns.wallet.util

import android.content.Context
import android.content.SharedPreferences


object SharedPreferencesUtils {
    private var sp: SharedPreferences? = null

    // 1,存储boolean变量方法
    fun putBoolean(ctx: Context, key: String?, value: Boolean) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putBoolean(key, value).apply()
    }

    // 2,读取boolean变量方法
    fun getBoolean(ctx: Context, key: String?, defValue: Boolean): Boolean {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getBoolean(key, defValue)
    }

    fun putString(ctx: Context, key: String?, value: String?) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putString(key, value).apply()
    }

    fun getString(ctx: Context, key: String?, defValue: String?): String? {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getString(key, defValue)
    }

    /**
     * @param ctx
     * 上下文环境
     * @param key
     * 要从config.xml移除节点的name的名称
     */
    fun removeKey(ctx: Context, key: String?) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().remove(key).apply()
    }

    // 反射(扩展)
    //
    fun putInt(ctx: Context, key: String?, value: Int) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putInt(key, value).apply()
    }

    fun getInt(ctx: Context, key: String?, defValue: Int): Int {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getInt(key, defValue)
    }
}