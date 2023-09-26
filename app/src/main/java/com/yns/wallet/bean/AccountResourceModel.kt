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
data class AccountResourceModel(
    var trx: BigDecimal? = BigDecimal("0"),
    var bandwidth: BigDecimal? = BigDecimal("0"),
    var energy: BigDecimal? = BigDecimal("0"),
) : Parcelable


/**
 * 2个model 的互转
 */
fun AccountResourceModel.toAccountResource(): WalletApi.AccountResource {
    return WalletApi.AccountResource().also {
        it.trx = this.trx
        it.bandwidth = this.bandwidth
        it.energy = this.energy
    }
}

/**
 * 2个model 的互转
 */
fun WalletApi.AccountResource.toAccountResourceModel(): AccountResourceModel {
    return AccountResourceModel().also {
        it.trx = this.trx
        it.bandwidth = this.bandwidth
        it.energy = this.energy
    }
}