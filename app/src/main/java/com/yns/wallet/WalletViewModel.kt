package com.yns.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yns.wallet.api.WalletApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WalletViewModel:ViewModel() {

    //创建钱包
    fun createMenomic(callback: suspend (String) -> Unit){
        viewModelScope.launch {
            val menomic:String
            withContext(Dispatchers.IO){
                menomic = WalletApi.createMenomic()
            }
            callback(menomic)
        }
    }

    //创建钱包
    fun createWalletFromMenomic(menomic:String,password:String,callback: suspend (WalletApi.WalletEntity) -> Unit){
        viewModelScope.launch(Dispatchers.Main) {
            val walletEntity:WalletApi.WalletEntity
            withContext(Dispatchers.IO){
                walletEntity = WalletApi.createWalletFromMenomic(menomic,WalletApi.getWalletCnt(),password)
                WalletApi.saveWallet(walletEntity)
            }
            callback(walletEntity)
        }
    }

    fun createWalletFromPrivateKey(privateKey:String,password:String,callback: suspend (WalletApi.WalletEntity) -> Unit){
        viewModelScope.launch {
            val walletEntity:WalletApi.WalletEntity
            withContext(Dispatchers.IO){
                walletEntity = WalletApi.createWalletFromPrivateKey(privateKey,password)
                WalletApi.saveWallet(walletEntity)
            }
            callback(walletEntity)
        }
    }

    fun getWalletCount():Int{
        return WalletApi.getWalletCnt()
    }

    fun getCurrentWallet(callback: suspend (WalletApi.WalletEntity) -> Unit){
        viewModelScope.launch {
            callback(WalletApi.getCurrentWallet())
        }
    }

}