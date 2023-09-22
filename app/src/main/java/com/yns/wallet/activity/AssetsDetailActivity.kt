package com.yns.wallet.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.bumptech.glide.util.Util
import com.yns.wallet.R
import com.yns.wallet.api.WalletApi
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.TokenTransferTransactionModel
import com.yns.wallet.databinding.ActivityAboutUsBinding
import com.yns.wallet.databinding.ActivityAssetsDetailBinding
import com.yns.wallet.util.CodeCreator
import com.yns.wallet.util.ViewModelUtils.lazyViewModel
import com.yns.wallet.util.copyToClipboard
import com.yns.wallet.util.formatTimeString
import com.yns.wallet.util.onClick
import com.yns.wallet.viewmodel.AssetDetailViewModel

class AssetsDetailActivity : BaseActivity<ActivityAssetsDetailBinding>() {

    var tokenModel: TokenTransferTransactionModel?=null

    private val assetDetailViewModel:AssetDetailViewModel by lazyViewModel()

    override fun initView(root: View, savedInstanceState: Bundle?) {
        tokenModel = intent.getParcelableExtra("tokenTransferTransactionModel")

        assetDetailViewModel.tokenTransferTransactionDetailLiveData.observe(this){
            dismissProgress()
            viewBinding.sendTv.text = it.from
            viewBinding.receiveTv.text = it.to
            viewBinding.hexTv.text = it.tx
            viewBinding.typeTv.text = it.transferType.toString()
            viewBinding.timeTv.text = formatTimeString(it.time)
            viewBinding.feeTv.text = "${it.fee} TRX"
        }

        viewBinding.apply {
            if(walletViewModel.currentWalletLiveData.value?.address == tokenModel?.from){
                amountTv.text = "-${tokenModel?.amount}"
                amountTv.setTextColor(getColor(R.color.tips_red_color))
            }else{
                amountTv.text = "+${tokenModel?.amount}"
                amountTv.setTextColor(getColor(R.color.transaction_green_color))
            }

            when(tokenModel?.result){
                WalletApi.TX_RESULT.SUCCESS->{
                    resultTagTv.text = getString(R.string.success)
                    resultTagTv.setTextColor(getColor(R.color.transaction_green_color))
                    resultTagTv.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.mipmap.icon_success),null,null,null)
                }
                else -> {
                    resultTagTv.text = getString(R.string.fail)
                    resultTagTv.setTextColor(getColor(R.color.tips_red_color))
                    resultTagTv.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.mipmap.icon_fail),null,null,null)
                }
            }


            val link = "123123123123"
            CodeCreator.setQRCode(this@AssetsDetailActivity, link, viewBinding.qrCode)

            copy.onClick {
                link.copyToClipboard(this@AssetsDetailActivity)
            }

            bindCopyButton(copyReceive, receiveTv)
            bindCopyButton(copySend, sendTv)
            bindCopyButton(copyHex, hexTv)

        }


        loadData()
    }


    private fun bindCopyButton(view: View, textView: TextView) {
        view.onClick {
            val text = textView.text.toString()
            if (!TextUtils.isEmpty(text)) {
                text.copyToClipboard(this)
            }
        }
    }

    private fun loadData(){
        showProgress()
        assetDetailViewModel.getTokenTransactionRecord(tokenModel?.tx)
    }
}
