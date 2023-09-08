package com.yns.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yns.wallet.api.WalletApi
import kotlinx.coroutines.launch

class WalletViewModel:ViewModel() {

    //创建钱包
    fun createWallet(callback: suspend (String) -> Unit){
        viewModelScope.launch {
            callback(WalletApi.createMenomic())
        }
    }

}