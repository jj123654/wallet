package com.yns.wallet.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HDWalletBean(
    var address:String?=null,
    var isSelected:Boolean = false
):Parcelable{
}
