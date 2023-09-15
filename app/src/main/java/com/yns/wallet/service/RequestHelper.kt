package com.yns.wallet.service

import com.google.gson.Gson
import com.yns.wallet.bean.JsonRequestBody
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

/**
 * 网络请求访问帮助类
 */
object RequestHelper {

    var baseUrl = "https://apilist.tronscanapi.com/api/"
    private val MEDIA_TYPE_JSON: MediaType? = "application/json;charset=utf-8".toMediaTypeOrNull()

    /**
     * 添加通用参数或者header
     */
    fun addCommonParams(builder: okhttp3.Request.Builder) {

    }

    /**
     * 构建okhttp 需要用到的body,目前是form表单形式
     */
    fun createBody(body: Map<String, Any>): RequestBody {
        return JsonRequestBody(MEDIA_TYPE_JSON, Gson().toJson(body))
    }


    /**
     * 检查url 是相对地址还是 绝对地址
     */
    fun check(url: String): String {
        if (url.startsWith("http")) {
            return url
        }
        val a = baseUrl
        if (a?.endsWith("/") == true) {
            return a + url
        } else {
            return "$a/$url"
        }
    }
}