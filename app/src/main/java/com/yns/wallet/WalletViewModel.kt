package com.yns.wallet

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yns.wallet.api.WalletApi
import com.yns.wallet.bean.WalletModel
import com.yns.wallet.bean.toWalletEntity
import com.yns.wallet.bean.toWalletModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 钱包的viewModel
 */
class WalletViewModel : ViewModel() {

    val walletsLiveData = MutableLiveData<MutableList<WalletModel>>(mutableListOf())

    val currentWalletLiveData = MutableLiveData<WalletModel>()

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

    //通过助记词创建钱包
    fun createWalletFromMenomic(
        name:String,
        menomic: String,
        index:Int,
        password: String,
        callback: suspend (WalletModel) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.Main) {
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
                Log.i("walletTest","初次舒适化")
                currentWalletLiveData.value = WalletApi.getCurrentWallet().toWalletModel()
            }
            Log.i("walletTest","再次舒适化")
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

    fun getPopularTokens(){

    }

}