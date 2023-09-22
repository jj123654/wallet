package com.yns.wallet.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yns.wallet.api.WalletApi
import com.yns.wallet.bean.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 钱包的viewModel
 */
class TransactionRecordViewModel : ViewModel() {


    //创建助记词
    fun getTransactionRecord(callback: suspend (String) -> Unit) {
        viewModelScope.launch {
//            val menomic: String
//            withContext(Dispatchers.IO) {
//                menomic = WalletApi.getTokenTransaction()
//                WalletApi.saveMenomic(menomic)
//            }
//            callback(menomic)
        }
    }

}