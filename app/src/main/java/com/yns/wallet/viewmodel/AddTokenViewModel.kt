package com.yns.wallet.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val walletsLiveData = MutableLiveData<MutableList<WalletModel>>(mutableListOf())

    val currentWalletLiveData = MutableLiveData<WalletModel>()

    val tokenLiveData = MutableLiveData<MutableList<TokenModel>>(mutableListOf())

    val currentTokenLiveData = MutableLiveData<TokenModel>()

    fun getPopularTokens(callback: suspend (MutableList<TokenModel>) -> Unit){
        viewModelScope.launch {
            val r = withContext(Dispatchers.IO) {
                val tokenList: MutableList<WalletApi.Token> = ArrayList()

                val stringResponse: Response<String> = tokenOverView("TRX")
                if (stringResponse.isSuccessful && stringResponse.data != null) {
                    //TODO 检查数据并返回列表
                    val response = stringResponse.data
                    val info = JsonUtils.jsonToObject(
                        response,
                        PopularTokenInfo::class.java
                    )

                    var token = WalletApi.Token()
                    token.address = "TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh"
                    token.imageUrl = "https://static.tronscan.org/production/upload/logo/new/TKevsGkyqoSux8NENgbM1An1cLt6QQfbGh.png?t=1690628898608"
                    token.name = "YNS"
                    tokenList.add(token)

                    info.tokens.forEach {
                        token = WalletApi.Token()
                        token.address = it.contractAddress
                        token.imageUrl = it.imgUrl
                        token.name = it.abbr
                        tokenList.add(token)
                    }

                }

                tokenList.map { it.toTokenModel() }.toMutableList()
            }
            callback(r)
        }
    }

}