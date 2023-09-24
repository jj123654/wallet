package com.yns.wallet.viewmodel

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
class AssetViewModel : ViewModel() {

    val tokenTransferTransactionLiveData = MutableLiveData<MutableList<Data>>(mutableListOf())

    //创建助记词
    fun getTokenTransactionRecord(
        address: String,
        tokenContractAddress: String,
        startTime: Long,
        size: Int,
        type: Int,
        hide: Boolean
    ) {
        viewModelScope.launch {
            val r = withContext(Dispatchers.IO) {
                WalletApi.getTokenTransaction(
                    address,
                    tokenContractAddress,
                    startTime,
                    size,
                    type,
                    hide
                )
            }
            tokenTransferTransactionLiveData.value = r.toMutableList()
        }
    }

}