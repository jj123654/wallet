package com.yns.wallet.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LanguageBean(
    var name:String?=null,
    var isSelected:Boolean = false
):Parcelable{
}
