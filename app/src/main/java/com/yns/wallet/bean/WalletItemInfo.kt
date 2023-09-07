package com.yns.wallet.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WalletItemInfo(
    var iconId: Int = 0,
    var name: String ?= null,
    var desc: String ?= null,
    var index: Int = 0,
    var balance: Double = 0.0
):Parcelable{

}
