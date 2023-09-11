package com.yns.wallet.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class SwapRecordBean(
    var language:String?=null,
    var languageLocale:Locale?=null,
    var isSelected:Boolean = false
):Parcelable{
}
