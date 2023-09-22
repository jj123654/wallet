package com.yns.wallet.bean

import android.os.Parcelable
import com.yns.wallet.api.WalletApi
import com.yns.wallet.api.WalletApi.TRANSFER_TYPE
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

/**
 * 封装token
 */
@Parcelize
data class TokenTransferTransactionDetailModel(
    var from: String? = null,
    var to: String? = null,
    var tx: String? = null,
    var time: Long = 0L,
    var amount: BigDecimal? = null,
    var result: WalletApi.TX_RESULT? = null,
    var fee: BigDecimal? = null,
    var transferType: TRANSFER_TYPE? = null
) : Parcelable


/**
 * 2个model 的互转
 */
fun TokenTransferTransactionDetailModel.toTokenTransferTransactionDetail(): WalletApi.TokenTransferTransactionDetail {
    return WalletApi.TokenTransferTransactionDetail().also {
        it.from = this.from
        it.to = this.to
        it.tx = this.tx
        it.time = this.time
        it.amount = this.amount
        it.result = this.result
        it.fee = this.fee
        it.transferType = this.transferType
    }
}

/**
 * 2个model 的互转
 */
fun WalletApi.TokenTransferTransactionDetail.toTokenTransferTransactionDetailModel(): TokenTransferTransactionDetailModel {
    return TokenTransferTransactionDetailModel().also {
        it.from = this.from
        it.to = this.to
        it.tx = this.tx
        it.time = this.time
        it.amount = this.amount
        it.result = this.result
        it.fee = this.fee
        it.transferType = this.transferType
    }
}