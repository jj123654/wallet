package com.yns.wallet.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yns.wallet.api.WalletApi
import com.yns.wallet.bean.*
import kotlinx.coroutines.*

/**
 * 钱包的viewModel
 */
class WalletViewModel : ViewModel() {

    val walletsLiveData = MutableLiveData<MutableList<WalletModel>>(mutableListOf())

    val currentWalletLiveData = MutableLiveData<WalletModel>()

    val tokenLiveData = MutableLiveData<MutableList<TokenModel>>(mutableListOf())

    val currentTokenLiveData = MutableLiveData<TokenModel>()

    init {
        //开一个全局计时器每隔60秒刷新一次首页余额
        viewModelScope.launch {
            while (isActive){
                delay(1000*60)
                currentWalletLiveData.value?.address?.let {
                    //getAllTokenWithAalletAddress
                }
            }

        }
    }

    //创建助记词
    fun createMenomic(callback: suspend (String) -> Unit) {
        viewModelScope.launch {
            val menomic: String
            withContext(Dispatchers.IO) {
                menomic = WalletApi.createMenomic()
                WalletApi.saveMenomic(menomic)
            }
            callback(menomic)
        }
    }

    //保存助记词
    fun saveMenomic(menomic:String,callback: suspend () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                WalletApi.saveMenomic(menomic)
            }
            callback()
        }
    }

    //通过助记词创建钱包
    fun createWalletFromMenomic(
        name:String,
        menomic: String,
        index:Int,
        password: String,
        callback: suspend (WalletModel) -> Unit
    ) {
        viewModelScope.launch {
            val walletEntity: WalletModel = withContext(Dispatchers.IO) {
                var m =
                    WalletApi.createWalletFromMenomic(menomic, index, password)
                m.name = name
                WalletApi.saveWallet(m)
                WalletApi.setCurrentWallet(m)
                m.toWalletModel()
            }
            currentWalletLiveData.value = walletEntity
            callback(walletEntity)
        }
    }

    fun modifyWalletName(address: String, name: String) {
        viewModelScope.launch {
            walletsLiveData.value?.forEach {
                if (it.address == address) {
                    it.name = name
                }
            }
            currentWalletLiveData.value?.name = name
            WalletApi.saveWallet(currentWalletLiveData.value?.toWalletEntity())
        }
    }

    //通过私钥创建钱包
    fun createWalletFromPrivateKey(
        name:String,
        privateKey: String,
        password: String,
        callback: suspend (WalletModel) -> Unit
    ) {
        viewModelScope.launch {
            val walletEntity: WalletModel = withContext(Dispatchers.IO) {
                var m = WalletApi.createWalletFromPrivateKey(privateKey, password)
                m.name = name
                WalletApi.saveWallet(m)
                WalletApi.setCurrentWallet(m)
                m.toWalletModel()
            }
            currentWalletLiveData.value = walletEntity
            callback(walletEntity)
        }
    }

    fun getWalletCount(): Int {
        return WalletApi.getWalletCnt()
    }

    fun deleteWallet(address: String){
        WalletApi.deleteWallet(address)
    }

    fun switchCurrentWallet(position:Int){
        walletsLiveData.value?.let {
            val walletModel = walletsLiveData.value?.get(position)
            WalletApi.setCurrentWallet(walletModel?.toWalletEntity())
            currentWalletLiveData.value = walletModel!!
        }

    }

    fun getCurrentWallet(callback: suspend (WalletModel) -> Unit) {
        viewModelScope.launch {
            if(currentWalletLiveData.value == null){
                currentWalletLiveData.value = WalletApi.getCurrentWallet().toWalletModel()
            }
            callback(currentWalletLiveData.value?: WalletModel())
        }
    }

    fun checkPassword(password:String,callback: suspend (Boolean) -> Unit){
        viewModelScope.launch {
            val checkResult = withContext(Dispatchers.IO){
                WalletApi.checkPassword(password)
            }
            callback(checkResult)
        }
    }

    /**
     * 获取所有钱包
     */
    fun getAllWallet() {
        viewModelScope.launch {
            val r = withContext(Dispatchers.IO) {
                WalletApi.listAllWallet().map {
                    it.toWalletModel()
                }
            }
            walletsLiveData.value = r.toMutableList()
        }
    }

    /**
     * 获取当前钱包下的所有token
     */
    fun getWalletAllToken(address:String) {
        viewModelScope.launch {
            val r = withContext(Dispatchers.IO) {
                WalletApi.getAllWalletToken(address).map {
                    it.toTokenModel()
                }
            }
            tokenLiveData.value = r.toMutableList()
        }
    }

    /**
     * 获取当前钱包下的所有token
     */
    fun getToken(address:String,callback: suspend (TokenModel) -> Unit) {
        viewModelScope.launch {
            val r = withContext(Dispatchers.IO) {
                WalletApi.getToken(address).toTokenModel()
            }
            callback(r)
        }
    }

    fun addWalletToken(tokenModel: TokenModel,callback: suspend (Boolean) -> Unit){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                WalletApi.addWalletToken(currentWalletLiveData.value?.address,tokenModel?.address)
            }
            tokenLiveData.value = tokenLiveData.value?.apply { add(tokenModel) }
            callback(true)
        }
    }

    fun deleteWalletToken(tokenModel: TokenModel,callback: suspend (Boolean) -> Unit){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                WalletApi.deleteWalletToken(currentWalletLiveData.value?.address,tokenModel?.address)
            }
            tokenLiveData.value = tokenLiveData.value?.apply { removeIf { it.address == tokenModel?.address } }
            callback(true)
        }
    }

    /**
     *         // 实现从助记词获取地址的逻辑
     */
    fun getAddressFromMenomic(
        menomic: String,
        index: Int,
        callback: suspend (List<String>) -> Unit
    ) {
        viewModelScope.launch {
            val from = (index - 1) * 20
            val to = from + 20
            val r = withContext(Dispatchers.IO) {
                val result = mutableListOf<String>()
                for (i in from until to) {
                    val p = WalletApi.getAddressFromMenomic(menomic, i)
                    if (!TextUtils.isEmpty(p)) {
                        result.add(p)
                    }
                }
                result
            }
            callback.invoke(r)
        }
    }

    fun hasHDWallet(callback: suspend (Boolean) -> Unit){
        viewModelScope.launch {
            val r = withContext(Dispatchers.IO) {
                WalletApi.hasHDWallet()
            }
            callback(r)
        }
    }

}