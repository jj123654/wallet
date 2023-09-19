package com.yns.wallet.api

import android.text.TextUtils
import com.yns.wallet.bean.Response
import com.yns.wallet.service.Request
import com.yns.wallet.service.RequestHelper
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
        tokenOverView {

        }

        transfer {

        }
        tokenTRC20Transfer {

        }
        transaction {

        }
        accountTokens {

        }
    }

    /**
     * //1.https://apilist.tronscanapi.com/api/tokens/overview?filter=TRX

     */
    @JvmStatic
    @JvmOverloads
    fun tokenOverView(filter: String? = "TRX", callback: (Response<String>) -> Unit) {
        scope.launch {
            val r = getSuspend("tokens/overview", mapOf("filter" to filter))
            callback.invoke(r)
        }
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
        address: String = "",
        callback: (Response<String>) -> Unit
    ) {
        scope.launch {
            val r = getSuspend(
                "new/transfer",
                mapOf("sort" to sort, "start" to start, "limit" to limit, "address" to address)
            )
            callback.invoke(r)
        }
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
        start_timestamp: Long = 0,
        callback: (Response<String>) -> Unit
    ) {
        scope.launch {
            val r = getSuspend(
                "token_trc20/transfers",
                mapOf(
                    "start" to start,
                    "limit" to limit,
                    "contract_address" to contract_address,
                    "relatedAddress" to relatedAddress,
                    "start_timestamp" to start_timestamp
                )
            )
            callback.invoke(r)
        }
    }


    /**
    //4.https://apilist.tronscanapi.com/api/transaction?sort=-timestamp&limit=50&address=地址&start_timestamp=时间
     */
    @JvmOverloads
    @JvmStatic
    fun transaction(
        sort: String? = "-timestamp",
        start_timestamp: Long = 0,
        limit: Int = 50,
        address: String = "",
        callback: (Response<String>) -> Unit
    ) {
        scope.launch {
            val r = getSuspend(
                "transaction",
                mapOf(
                    "sort" to sort,
                    "start_timestamp" to start_timestamp,
                    "limit" to limit,
                    "address" to address
                )
            )
            callback.invoke(r)
        }
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
        address: String = "",
        callback: (Response<String>) -> Unit
    ) {
        scope.launch {
            val r = getSuspend(
                "account/tokens",
                mapOf(
                    "start" to start,
                    "hidden" to hidden,
                    "limit" to limit,
                    "address" to address,
                    "show" to show,
                    "sortType" to sortType,
                )
            )
            callback.invoke(r)
        }
    }

    private suspend fun getSuspend(path: String, params: Map<String, Any?>): Response<String> {
        return withContext(Dispatchers.IO) {
            val client = Request.okhttp
            val url = BASE_URL + path //拼接url
            val r = client.newCall(
                okhttp3.Request.Builder().url(makeUrl(url, params)).method("GET", null).build()
            ).execute()
            try {
                if (r.isSuccessful) {
                    val body = r.body?.string()
                    if (!TextUtils.isEmpty(body)) {
                        return@withContext Response.success(body)
                    }
                }
                return@withContext Response.error(r.code, msg = r.body?.string())
            } catch (e: Exception) {
                e.printStackTrace()
                Response.error(-1, msg = e.message)
            } finally {
                r.closeQuietly()
            }

        }
    }

    private fun makeUrl(url: String, map: Map<String, Any?>): String {
        val u = if (!url.contains("?")) "$url?" else url
        val p = StringBuilder()
        map.forEach {
            p.append(it.key)
            p.append("=")
            p.append(it.value?.toString())
        }
        return u + p.toString()
    }
}