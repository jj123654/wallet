package com.yns.wallet.bean

import android.os.Parcelable
import com.yns.wallet.api.WalletApi
import com.yns.wallet.api.WalletApi.Token
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

/**
 * 封装token
 */
@Parcelize
data class SwapInfoModel(
    var fromToken: String? = null,
    var toToken: String? = null,
    var rate: BigDecimal? = BigDecimal("0"),
    var fee: BigDecimal? = BigDecimal("0"),
    var priceImpact: BigDecimal? = BigDecimal("0"),
    var fromAmount: BigDecimal? = BigDecimal("0"),
    var toAmount: BigDecimal? = BigDecimal("0"),
    var minReceive: BigDecimal? = BigDecimal("0")
) : Parcelable


/**
 * 2个model 的互转
 */
fun SwapInfoModel.toSwapInfo(): WalletApi.SwapInfo {
    return WalletApi.SwapInfo().also {
        it.fromToken = this.fromToken
        it.toToken = this.toToken
        it.rate = this.rate
        it.fee = this.fee
        it.priceImpact = this.priceImpact
        it.fromAmount = this.fromAmount
        it.toAmount = this.toAmount
        it.minReceive = this.minReceive
    }
}

/**
 * 2个model 的互转
 */
fun WalletApi.SwapInfo.toSwapInfoModel(): SwapInfoModel {
    return SwapInfoModel().also {
        it.fromToken = this.fromToken
        it.toToken = this.toToken
        it.rate = this.rate
        it.fee = this.fee
        it.priceImpact = this.priceImpact
        it.fromAmount = this.fromAmount
        it.toAmount = this.toAmount
        it.minReceive = this.minReceive
    }
}