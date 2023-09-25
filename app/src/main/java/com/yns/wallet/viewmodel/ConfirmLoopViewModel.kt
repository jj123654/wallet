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
class ConfirmLoopViewModel : ViewModel() {

    val accountResourceLiveData = UnPeekLiveData<AccountResourceModel>()


    fun getAccountResource(address:String){
        viewModelScope.launch {
            val r = withContext(Dispatchers.IO) {
                WalletApi.getAccountResource(address).toAccountResourceModel()
            }
            accountResourceLiveData.value = r
        }
    }



}