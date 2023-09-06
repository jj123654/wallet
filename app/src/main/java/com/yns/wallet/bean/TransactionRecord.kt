package com.yns.wallet.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionRecord(
    var type:String?=null
):Parcelable{
}
