package com.yns.wallet.bean

import android.os.Parcelable
import com.yns.wallet.api.WalletApi.WalletEntity
import kotlinx.parcelize.Parcelize

/**
 * 钱包model,封装 WalletEntity
 */
@Parcelize
data class WalletModel(
    var name: String? = null,
    var address: String? = null,
    var keystore: String? = null
) : Parcelable


/**
 * 2个model转换方法
 */
fun WalletModel.toWalletEntity(): WalletEntity {
    return WalletEntity().also {
        it.name = this.name
        it.address = this.address
        it.keystore = this.keystore
    }
}

/**
 * 2个model转换方法
 */
fun WalletEntity.toWalletModel(): WalletModel {
    return WalletModel().also {
        it.name = this.name
        it.address = this.address
        it.keystore = this.keystore
    }
}