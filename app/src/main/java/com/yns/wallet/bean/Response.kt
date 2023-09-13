package com.yns.wallet.bean

import com.google.gson.annotations.SerializedName

/**
 * 服务器的返回值
 */
class Response<T> private constructor() {
    /**
     * 返回的code
     */
    @SerializedName("resCode", alternate = ["status"])
    var code: Int = 0

    /**
     * 返回的msg
     */
    @SerializedName("resDesc", alternate = ["message"])
    var msg: String? = ""

    /**
     * 返回的数据
     */
    @SerializedName("result")
    var data: T? = null

    /**
     * 是否为成功的请求
     */
    val isSuccessful get() = code == 0

    /**
     * 历史记录专用
     */
    val isSuccessfulForHistory get() = code == 1

    companion object {
        /**
         * 返回一个表示成功的Response
         */
        fun <T> success(data: T? = null, msg: String? = null): Response<T> {
            return Response<T>().apply {
                code = 0
                this.data = data
                this.msg = msg
            }
        }

        /**
         * 返回一个表示错误的Response
         */
        fun <T> error(code: Int = -1, data: T? = null, msg: String? = null): Response<T> {
            return Response<T>().apply {
                this.code = code
                this.data = data
                this.msg = msg
            }
        }
    }
}