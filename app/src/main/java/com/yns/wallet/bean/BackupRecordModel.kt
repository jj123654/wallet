package com.yns.wallet.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 恢复记录
 */
@Parcelize
data class BackupRecordModel(var name: String? = null, var address: String? = null) : Parcelable {
}