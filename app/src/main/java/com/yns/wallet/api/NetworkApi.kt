package com.yns.wallet.api

import android.text.TextUtils
import android.util.Log
import com.yns.wallet.bean.Response
import com.yns.wallet.service.Request
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.closeQuietly
import java.lang.StringBuilder

/**
 *  //时间 默认穿0
//1.https://apilist.tronscanapi.com/api/tokens/overview?filter=TRX
//2. https://apilist.tronscanapi.com/api/new/transfer?sort=-timestamp&start=页数&limit=50&address=地址
//3.https://apilist.tronscanapi.com/api/token_trc20/transfers?limit=50&start=0&contract_address=地址&start_timestamp=时间&relatedAddress=地址
//4.https://apilist.tronscanapi.com/api/transaction?sort=-timestamp&limit=50&address=地址&start_timestamp=时间
//5.https://apilist.tronscanapi.com/api/account/tokens?address=地址&start=0&limit=20&hidden=0&show=0&sortType=0&sortBy=0&token=

实现5个API
 */
object NetworkApi {
    const val BASE_URL = "https://apilist.tronscanapi.com/api/"
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @JvmStatic
    fun test() {
        scope.launch {
            withContext(Dispatchers.IO) {
                Log.i("httpTest", "第一个----${tokenOverView().data.toString()}")
                Log.i("httpTest", "第二个----${transfer().data.toString()}")
                Log.i("httpTest", "第三个----${tokenTRC20Transfer().data.toString()}")
                Log.i("httpTest", "第四个----${transaction().data.toString()}")
                Log.i("httpTest", "第五个----${accountTokens().data.toString()}")
            }
        }

    }

    /**
     * //1.https://apilist.tronscanapi.com/api/tokens/overview?filter=TRX

     */
    @JvmStatic
    @JvmOverloads
    fun tokenOverView(filter: String? = "TRX"): Response<String> {
        return getSuspend("tokens/overview", mapOf("filter" to filter))
    }

    /**
     * //1.https://apilist.tronscanapi.com/api/tokens/overview?filter=TRX

     */
    @JvmStatic
    @JvmOverloads
    fun searchToken(contract: String): Response<String> {
        return getSuspend("token_trc20", mapOf("contract" to contract))
    }


    /**
     * //2. https://apilist.tronscanapi.com/api/new/transfer?sort=-timestamp&start=页数&limit=50&address=地址
     */
    @JvmOverloads
    @JvmStatic
    fun transfer(
        sort: String? = "-timestamp",
        start: Int = 1,
        limit: Int = 50,
        address: String = ""
    ): Response<String> {
        return getSuspend(
            "new/transfer",
            mapOf("sort" to sort, "start" to start, "limit" to limit, "address" to address)
        )
    }

    /**
    //3.https://apilist.tronscanapi.com/api/token_trc20/transfers?limit=50&start=0&contract_address=地址&start_timestamp=时间&relatedAddress=地址
     */
    @JvmOverloads
    @JvmStatic
    fun tokenTRC20Transfer(
        start: Int = 1,
        limit: Int = 50,
        contract_address: String = "",
        relatedAddress: String = "",
        start_timestamp: Long = 0
    ): Response<String> {
        return getSuspend(
            "token_trc20/transfers",
            mapOf(
                "start" to start,
                "limit" to limit,
                "contract_address" to contract_address,
                "relatedAddress" to relatedAddress,
                "start_timestamp" to start_timestamp
            )
        )
    }


    /**
    //4.https://apilist.tronscanapi.com/api/transaction?sort=-timestamp&limit=50&address=地址&start_timestamp=时间
     */
    @JvmOverloads
    @JvmStatic
    fun transaction(
        type: Int = 0,
        start_timestamp: Long = 0,
        limit: Int = 50,
        address: String = ""
    ): Response<String> {
        return getSuspend(
            "transaction",
            mapOf(
                "sort" to "-timestamp",
                "start_timestamp" to start_timestamp,
                "limit" to limit,
                "address" to address,
                "type" to type
            )
        )
    }

    /**
    //5.https://apilist.tronscanapi.com/api/account/tokens?address=地址&start=0&limit=20&hidden=0&show=0&sortType=0&sortBy=0&token=
     */
    @JvmOverloads
    @JvmStatic
    fun accountTokens(
        start: Int = 0,
        limit: Int = 50,
        hidden: Int = 0,
        show: Int = 0,
        sortType: Int = 0,
        sortBy: Int = 0,
        address: String = "",
        token: String = ""
    ): Response<String> {
        return getSuspend(
            "account/tokens",
            mapOf(
                "start" to start,
                "hidden" to hidden,
                "limit" to limit,
                "address" to address,
                "show" to show,
                "sortType" to sortType,
                "sortBy" to sortBy,
                "token" to token
            )
        )
    }

    private fun getSuspend(path: String, params: Map<String, Any?>): Response<String> {
        val client = Request.okhttp
        val url = BASE_URL + path //拼接url
        var response: okhttp3.Response? = null
        try {
            val r = client.newCall(
                okhttp3.Request.Builder().url(makeUrl(url, params)).method("GET", null).build()
            )
            response = r.execute()
            if (response.isSuccessful) {
                val body = response.body?.string()
                if (!TextUtils.isEmpty(body)) {
                    return Response.success(body)
                }
            }
            return Response.error(response.code, msg = response.body?.string())
        } catch (e: Exception) {
            e.printStackTrace()
            return Response.error(-1, msg = e.message)
        } finally {
            response?.closeQuietly()
        }
    }

    private fun makeUrl(url: String, map: Map<String, Any?>): String {
        val u = if (!url.contains("?")) "$url?" else url
        val strs = mutableListOf<String>()
        map.forEach {
            val p = StringBuilder()
            p.append(it.key)
            p.append("=")
            p.append(it.value?.toString())
            strs.add(p.toString())
        }
        return u + strs.joinToString("&")
    }
}