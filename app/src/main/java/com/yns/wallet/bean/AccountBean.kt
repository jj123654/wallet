package com.yns.wallet.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccountBean(
    var logo:Int = 0,
    var name:String?=null,
    var content:String?=null,
    var isSelected:Boolean = false
):Parcelable{
}
