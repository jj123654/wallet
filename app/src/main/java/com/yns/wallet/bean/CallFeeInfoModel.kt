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
data class CallFeeInfoModel(
    var trx: BigDecimal? = BigDecimal("0"),
    var bandwidth: BigDecimal? = BigDecimal("0"),
    var energy: BigDecimal? = BigDecimal("0"),
) : Parcelable


/**
 * 2个model 的互转
 */
fun CallFeeInfoModel.toCallFeeModel(): WalletApi.CallFeeModel {
    return WalletApi.CallFeeModel().also {
        it.trx = this.trx
        it.bandwidth = this.bandwidth
        it.energy = this.energy
    }
}

/**
 * 2个model 的互转
 */
fun WalletApi.CallFeeModel.toCallFeeInfoModel(): CallFeeInfoModel {
    return CallFeeInfoModel().also {
        it.trx = this.trx
        it.bandwidth = this.bandwidth
        it.energy = this.energy
    }
}