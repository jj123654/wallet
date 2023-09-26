package com.yns.wallet.bean

import android.os.Parcelable
import com.yns.wallet.api.WalletApi
import com.yns.wallet.api.WalletApi.BackupRecord
import com.yns.wallet.api.WalletApi.Token
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

/**
 * 封装token
 */
@Parcelize
data class BackUpRecordModel(
    var type: WalletApi.BACKUP_TYPE? = null,
    var name: String? = null,
    var address: String? = null,
    var time:Long = 0
) : Parcelable


/**
 * 2个model 的互转
 */
fun BackUpRecordModel.toBackUpRecord(): BackupRecord {
    return BackupRecord().also {
        it.type = this.type
        it.name = this.name
        it.address = this.address
        it.time = this.time
    }
}


/**
 * 2个model 的互转
 */
fun BackupRecord.toBackUpModel(): BackUpRecordModel {
    return BackUpRecordModel().also {
        it.type = this.type
        it.name = this.name
        it.address = this.address
        it.time = this.time
    }

}