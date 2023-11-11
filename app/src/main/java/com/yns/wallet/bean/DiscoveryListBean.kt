package com.yns.wallet.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiscoveryListBean(
    var logo:Int = 0,
    var name:String?=null,
    var content:String?=null,
):Parcelable{
}
