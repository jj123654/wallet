package com.yns.wallet.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.yns.wallet.api.WalletApi
import com.yns.wallet.bean.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 钱包的viewModel
 */
class AssetDetailViewModel : ViewModel() {

    val tokenTransferTransactionDetailLiveData = UnPeekLiveData<TokenTransferTransactionDetailModel>()


    //获取token交易记录详情
    fun getTokenTransactionRecord(tx:String?) {
        viewModelScope.launch {
            val r = withContext(Dispatchers.IO) {
                WalletApi.getTokenTransactionDetail(tx).toTokenTransferTransactionDetailModel()
            }
            tokenTransferTransactionDetailLiveData.value = r
        }
    }

}