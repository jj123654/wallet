package com.yns.wallet.bean

import android.os.Parcelable
import com.yns.wallet.api.WalletApi.TX_RESULT
import com.yns.wallet.api.WalletApi.TokenTransferTransaction
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

/**
 * 封装 TokenTransferTransaction
 */
@Parcelize
data class TokenTransferTransactionModel(
    var `data`: List<Data>? = listOf(),
    var rangeTotal: Int? = 0,
    var total: Int? = 0,
    var wholeChainTxCount: Long? = 0
) : Parcelable


@Parcelize
data class Data(
    var Events: String? = "",
    var SmartCalls: String? = "",
    var amount: String? = "",
    var block: Int? = 0,
    var cheatStatus: Boolean? = false,
    var confirmed: Boolean? = false,
    var contractData: ContractData? = ContractData(),
    var contractRet: String? = "",
    var contractType: Int? = 0,
    var cost: Cost? = Cost(),
    var `data`: String? = "",
    var fee: String? = "",
    var hash: String? = "",
    var id: String? = "",
    var ownerAddress: String? = "",
    var result: String? = "",
    var revert: Boolean? = false,
    var riskTransaction: Boolean? = false,
    var timestamp: Long? = 0,
    var toAddress: String? = "",
    var toAddressList: List<String>? = listOf(),
    var tokenInfo: TokenInfoX? = TokenInfoX(),
    var tokenType: String? = "",
    var trigger_info: TriggerInfo? = TriggerInfo()
) : Parcelable


@Parcelize
data class ContractData(
    var amount: Long? = 0,
    var asset_name: String? = "",
    var contract_address: String? = "",
    var `data`: String? = "",
    var owner_address: String? = "",
    var to_address: String? = "",
    var tokenInfo: TokenInfoX? = TokenInfoX()
) : Parcelable
@Parcelize
data class Cost(
    var energy_fee: Int? = null,
    var energy_penalty_total: Int? = null,
    var energy_usage: Int? = null,
    var energy_usage_total: Int? = null,
    var fee: Int? = null,
    var net_fee: Int? = null,
    var net_usage: Int? = null,
    var origin_energy_usage: Int? = null
) : Parcelable
@Parcelize
data class TokenInfoX(
    var tokenAbbr: String? = null,
    var tokenCanShow: Int? = null,
    var tokenDecimal: Int? = null,
    var tokenId: String? = null,
    var tokenLevel: String? = null,
    var tokenLogo: String? = null,
    var tokenName: String? = null,
    var tokenType: String? = null,
    var vip: Boolean? = null
) : Parcelable
@Parcelize
data class TriggerInfo(
    var call_value: Int? = null,
    var contract_address: String? = null,
    var `data`: String? = null,
    var method: String? = null,
    var methodId: String? = null,
    var methodName: String? = null,
    var parameter: Parameter? = null
) : Parcelable
@Parcelize
data class Parameter(
    var _to: String? = null,
    var _value: String? = null,
    var amount: String? = null,
    var recipient: String? = null
) : Parcelable
