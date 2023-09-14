package com.yns.wallet.bean

import android.os.Parcelable
import com.yns.wallet.api.WalletApi.TX_RESULT
import com.yns.wallet.api.WalletApi.TokenTransferTransaction
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

/**
 * 封装 TokenTransferTransaction
 */
@Parcelize
data class TokenTransferTransactionModel(
    var from: String? = null,
    var to: String? = null,
    var tx: String? = null,
    var time: Long = 0L,
    var amount: BigDecimal? = null,
    var result: TX_RESULT? = null,
) : Parcelable


/**
 * 2个model 的互转
 */
fun TokenTransferTransaction.toTokenTransferTransactionModel(): TokenTransferTransactionModel {
    return TokenTransferTransactionModel().also {
        it.from = this.from
        it.to = this.to
        it.tx = this.tx
        it.time = this.time
        it.amount = this.amount
        it.result = this.result
    }
}

/**
 * 2个model 的互转
 */
fun TokenTransferTransactionModel.toTokenTransferTransaction(): TokenTransferTransaction {
    return TokenTransferTransaction().also {
        it.from = this.from
        it.to = this.to
        it.tx = this.tx
        it.time = this.time
        it.amount = this.amount
        it.result = this.result
    }
}