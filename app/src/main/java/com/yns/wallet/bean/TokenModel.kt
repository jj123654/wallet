package com.yns.wallet.bean

import android.os.Parcelable
import com.yns.wallet.api.WalletApi.Token
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

/**
 * 封装token
 */
@Parcelize
data class TokenModel(
    var address: String? = null,
    var imageUrl: String? = null,
    var name: String? = null,
    var balance: BigDecimal? = BigDecimal("0"),
    var usd: BigDecimal? = BigDecimal("0")
) : Parcelable


/**
 * 2个model 的互转
 */
fun TokenModel.toToken(): Token {
    return Token().also {
        it.address = this.address
        it.imageUrl = this.imageUrl
        it.name = this.name
        it.balance = this.balance
        it.usd = this.usd
    }
}

/**
 * 2个model 的互转
 */
fun Token.toTokenModel(): TokenModel {
    return TokenModel().also {
        it.address = this.address
        it.imageUrl = this.imageUrl
        it.name = this.name
        it.balance = this.balance
        it.usd = this.usd
    }
}