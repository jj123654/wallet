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
class BackUpViewModel : ViewModel() {

    val backUpRecordLiveData = UnPeekLiveData<MutableList<BackUpRecordModel>>(mutableListOf())


    fun getBackUpRecord(wallet:String?){
        viewModelScope.launch {
            val r = withContext(Dispatchers.IO) {
                WalletApi.listBackupRecord(wallet).map {
                    it.toBackUpModel()
                }
            }
            backUpRecordLiveData.value = r.toMutableList()
        }
    }

    fun addBackUpRecord(backUpRecordModel: BackUpRecordModel,callback: suspend (Boolean) -> Unit){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                WalletApi.addBackupRecord(backUpRecordModel.toBackUpRecord())
            }
        }
    }

}