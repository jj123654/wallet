package com.yns.wallet

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yns.wallet.api.WalletApi
import com.yns.wallet.bean.WalletModel
import com.yns.wallet.bean.toWalletModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 钱包的viewModel
 */
class WalletViewModel : ViewModel() {

    //创建钱包
    fun createMenomic(callback: suspend (String) -> Unit) {
        viewModelScope.launch {
            val menomic: String = withContext(Dispatchers.IO) {
                WalletApi.createMenomic()
            }
            callback(menomic)
        }
    }

    //创建钱包
    fun createWalletFromMenomic(
        menomic: String,
        password: String,
        callback: suspend (WalletModel) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            val walletEntity: WalletModel = withContext(Dispatchers.IO) {
                val m =
                    WalletApi.createWalletFromMenomic(menomic, WalletApi.getWalletCnt(), password)
                WalletApi.saveWallet(m)
                m.toWalletModel()
            }
            callback(walletEntity)
        }
    }

    fun createWalletFromPrivateKey(
        privateKey: String,
        password: String,
        callback: suspend (WalletModel) -> Unit
    ) {
        viewModelScope.launch {
            val walletEntity: WalletModel = withContext(Dispatchers.IO) {
                val m = WalletApi.createWalletFromPrivateKey(privateKey, password)
                WalletApi.saveWallet(m)
                m.toWalletModel()
            }
            callback(walletEntity)
        }
    }

    fun getWalletCount(): Int {
        return WalletApi.getWalletCnt()
    }

    fun getCurrentWallet(callback: suspend (WalletModel) -> Unit) {
        viewModelScope.launch {
            callback(WalletApi.getCurrentWallet().toWalletModel())
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
}