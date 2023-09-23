package com.yns.wallet.viewmodel

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


    /**
     * //个人中心获取交易记录  adress地址 start_timestamp时间  type筛选类型(1全部  2.转账  3接受)
    +(void)getAllTradeListWithAdress:(NSString *)adress start_timestamp:(NSInteger)start_timestamp type:(NSString *)type success:(void (^)(NSDictionary *dic))success  failure:(void (^)(NSError *error))failure   给你发过的
     */
    fun getTransactionRecord(
        type: Int,
        callback: suspend (List<TokenTransferTransactionModel>) -> Unit
    ) {
        viewModelScope.launch {
            val r = withContext(Dispatchers.IO) {
                WalletApi.getTokenTransaction(
                    WalletApi.getCurrentWallet().address,
                    WalletApi.getCurrentWallet().address,
                    0,
                    50,
                    type,
                    false
                ).map {
                    it.toTokenTransferTransactionModel()
                }
            }
            callback.invoke(r)
        }
    }

}