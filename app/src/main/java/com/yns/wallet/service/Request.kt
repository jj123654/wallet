package com.yns.wallet.service

import android.text.TextUtils
import com.google.gson.Gson
import com.yns.wallet.bean.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.internal.closeQuietly
import org.json.JSONObject
import java.io.File
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import java.net.URLEncoder
import java.security.SecureRandom
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager

/**
 * 网络请求访问工具
 */
object Request {

    /**
     * 公用同一个okhttp
     */
    val okhttp = OkHttpClient.Builder().apply {
        addInterceptor(LoggingInterceptor())
        connectTimeout(30L, TimeUnit.SECONDS)
        readTimeout(30L, TimeUnit.SECONDS)
        writeTimeout(30L, TimeUnit.SECONDS)
//            .apply {
//            val ssl = SSLContext.getInstance("SSL")
//            val manager = HttpsUtils.UnSafeTrustManager()
//            ssl.init(null, arrayOf(manager), SecureRandom())
//            this.sslSocketFactory(ssl.socketFactory, manager)
//            this.hostnameVerifier(object : HostnameVerifier {
//                override fun verify(hostname: String?, session: SSLSession?): Boolean {
//                    return true
//                }
//
//            })
//        }
    }.build()

    /**
     * 公用同一个gson
     */
    val gson = Gson()

    @JvmStatic
    val CONTENT_TYPE_JSON = "application/json;charset=utf-8".toMediaType() //json形式

//    val loginViewModel: LoginViewModel by lazyGlobalViewModel()

    /**
     * 使用协程来访问网络
     */
    suspend inline fun <reified T> postSuspend(
        path: String,
        body: Map<String, Any> = HashMap(),
        type: Type = T::class.java
    ): Response<T> {
        return withContext(Dispatchers.IO) {
            post(path, body, type)
        }
    }

    /**
     * 不要直接调用这个
     */
    @JvmStatic
    fun <T> post(
        path: String,
        body: Map<String, Any> = HashMap(), clazz: Type
    ): Response<T> {
        val body = RequestHelper.createBody(body)
        var response: okhttp3.Response? = null
        try {
            val r = okhttp.newCall(
                Request.Builder().url(RequestHelper.check(path))
                    .post(body).apply {
                        RequestHelper.addCommonParams(this)
                    }.build()
            ).execute()
            response = r
            return convert(r, clazz)
        } catch (e: Exception) {
            e.printStackTrace()
            return Response.error(-1, msg = e.message)

        } finally {
            response?.closeQuietly()
        }
        return Response.error()

    }


    @JvmStatic
    suspend inline fun <reified T> postWithFile(
        path: String,
        body: Map<String, File> = HashMap(), type: Type = T::class.java
    ): Response<T> {
        return withContext(Dispatchers.IO) {
            val body = createBody2(body)
            var response: okhttp3.Response? = null
            try {
                val r = okhttp.newCall(
                    Request.Builder().url(RequestHelper.check(path))
                        .post(body).apply {
                            RequestHelper.addCommonParams(this)
                        }.build()
                ).execute()
                response = r
                return@withContext convert(r, T::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext Response.error(-1, msg = e.message)
            } finally {
                response?.closeQuietly()
            }
            return@withContext Response.error()
        }


    }

    /**
     * get 方法，不要直接调用这个
     */
    fun <T> get(
        path: String,
        params: Map<String, Any?> = HashMap(),
        type: Type
    ): Response<T> {
        var response: okhttp3.Response? = null
        try {
            val r =
                okhttp.newCall(
                    Request.Builder()
                        .url(
                            getUrl(
                                RequestHelper.check(path),
                                params
                            )
                        )
                        .method("GET", null).apply {
                            RequestHelper.addCommonParams(this)
                        }.build()
                )
                    .execute()
            response = r
            return convert(r, type)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            response?.closeQuietly()
        }
        return Response.error()
    }

    /**
     * get请求的协程版本
     */
    suspend inline fun <reified T> getSuspend(
        path: String,
        params: Map<String, Any?> = HashMap(), type: Type = T::class.java
    ): Response<T> {
        return withContext(Dispatchers.IO) {
            get(path, params, type)
        }
    }


    fun getUrl(url: String, params: Map<String, Any?>): String {
        if (params.isEmpty()) {
            return url
        }
        val result = StringBuilder(url)
        result.append("?")
        params.forEach {
            result.append(it.key)
            result.append("=")
            result.append(URLEncoder.encode(it.value?.toString()))
            result.append("&")
        }
        return result.substring(0, result.length - 1).toString()
    }

    fun <T> convert(r: okhttp3.Response, typeOfT: Type): Response<T> {
        if (r.isSuccessful && r.body != null) {
            val str = r.body?.string()
            val json = JSONObject(str)
            val code = json.optInt("resCode", json.optInt("status"))
            if (code != 1 && code != 0) {
                return Response.error<T>(
                    code = code,
                    msg = json.optString("resDesc", json.optString("message"))
                ).apply {
                    checkResponse(this)
                }

            }
            val data = json.optString("data", json.optString("result"))
            if (!TextUtils.isEmpty(data)) {
                if (typeOfT == String::class.java) {
                    return Response.success(data = data.toString()) as Response<T>
                }
                val re = gson.fromJson<T>(data, typeOfT)
                return Response.success(data = re)
            } else {
                return Response.success()
            }
        }
        return Response.error<T>(code = r.code, msg = r.body?.string()).let {
            checkResponse(it)
            it
        }
    }

    private fun checkResponse(response: Response<*>) {
        if (response.code == 401) {
        }
    }


    fun createBody2(body: Map<String, File>): RequestBody {
        return MultipartBody.Builder().let { _b ->
            body.forEach {
                _b.addFormDataPart(
                    it.key,
                    it.value.name,
                    it.value.asRequestBody("application/octet-stream".toMediaType())
                )
            }
            _b
        }.setType("multipart/form-data".toMediaType()).build()
    }
}