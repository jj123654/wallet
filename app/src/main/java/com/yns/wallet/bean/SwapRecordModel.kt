package com.yns.wallet.bean

import android.os.Parcelable
import com.yns.wallet.api.WalletApi
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

/**
 * 封装token
 */
@Parcelize
data class SwapRecordModel(
    var time: Long = 0L,
    var result: WalletApi.TX_RESULT? = null,
    var swapInfo: SwapInfoModel? = null
) : Parcelable


/**
 * 2个model 的互转
 */
fun SwapRecordModel.toSwapInfo(): WalletApi.SwapRecord {
    return WalletApi.SwapRecord().also {
        it.time = this.time
        it.result = this.result
        it.swapInfo = this.swapInfo?.toSwapInfo()
    }
}

/**
 * 2个model 的互转
 */
fun WalletApi.SwapRecord.toSwapInfoModel(): SwapRecordModel {
    return SwapRecordModel().also {
        it.time = this.time
        it.result = this.result
        it.swapInfo = this.swapInfo.toSwapInfoModel()
    }
}