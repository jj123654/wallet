package com.yns.wallet.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VerifyAddressResult(
    var result:Boolean = false,
    var visible:String?=null,
):Parcelable{
}
