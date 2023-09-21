package com.yns.wallet.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.yns.wallet.api.NetworkApi.tokenOverView
import com.yns.wallet.api.WalletApi
import com.yns.wallet.bean.*
import com.yns.wallet.io.JsonUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 钱包的viewModel
 */
class AddTokenViewModel : ViewModel() {

    val tokenListLiveData = UnPeekLiveData<MutableList<TokenModel>>(mutableListOf())


    fun getPopularTokens(){
        viewModelScope.launch {
            val r = withContext(Dispatchers.IO) {
                val tokenList = WalletApi.getPopularToken()

                tokenList.map { it.toTokenModel() }.toMutableList()
            }
            tokenListLiveData.value = r
        }
    }

    fun getToken(contract:String,callback: suspend (TokenModel) -> Unit){
        viewModelScope.launch {
            val r = withContext(Dispatchers.IO) {
                WalletApi.getToken(contract).toTokenModel()
            }
            callback(r)
        }
    }

}