package com.yns.wallet.fragment

import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.PopupWindow
import coil.load
import com.luck.lib.camerax.utils.DensityUtil
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.bean.TokenModel
import com.yns.wallet.databinding.FragmentConfirmLoopBinding
import com.yns.wallet.databinding.PopupWindowBalanceBinding
import com.yns.wallet.util.onClick
import java.math.BigDecimal

class ConfirmLoopFragment : BaseFragment<FragmentConfirmLoopBinding>() {
    override val isDialog: Boolean = true

    var amount:String?=null
    var tokenModel:TokenModel?=null
    var toAddress:String?=null
    var sendOrSwap = true
    var contractAddress:String?=null

    companion object {
        fun newInstance(fromWhich:Boolean,contract:String?,amount: String?,toAddress:String?,tokenModel: TokenModel?): ConfirmLoopFragment {
            val arguments = Bundle()
            arguments.putBoolean("fromWhich",fromWhich)
            arguments.putString("contract",contract)
            arguments.putString("amount", amount)
            arguments.putString("toAddress", toAddress)
            arguments.putParcelable("tokenModel", tokenModel)
            val confirmLoopFragment = ConfirmLoopFragment()
            confirmLoopFragment.arguments = arguments
            return confirmLoopFragment
        }
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.close.onClick {
            removeSelf()
        }
        viewBinding.walletName.onClick {
            showPopup()
        }
        viewBinding.confirm.onClick {
            removeSelf()
        }

        initData()
    }

    private fun initData(){
        sendOrSwap = arguments?.getBoolean("fromWhich")?:true
        contractAddress = arguments?.getString("contract")
        amount = arguments?.getString("amount")
        tokenModel = arguments?.getParcelable("tokenModel")
        toAddress = arguments?.getString("toAddress")

        viewBinding.apply {
            if(sendOrSwap){
                viewBinding.contractArea.visibility = View.GONE
                viewBinding.transferArea.visibility = View.VISIBLE
            }else{
                viewBinding.contractArea.visibility = View.VISIBLE
                viewBinding.transferArea.visibility = View.GONE
            }

            walletName.paint.flags = Paint.UNDERLINE_TEXT_FLAG
            walletName.paint.isAntiAlias = true
            walletName.text = walletViewModel.currentWalletLiveData.value?.name
            walletName2Tv.text = walletViewModel.currentWalletLiveData.value?.name
            tokenImg.load(tokenModel?.imageUrl){
                placeholder(R.mipmap.account_default_photo)
            }
            amountTv.text = "$amount ${tokenModel?.name}"
            fromAddrTv.text = walletViewModel.currentWalletLiveData.value?.address
            toAddrTv.text = toAddress
            if(contractAddress.isNullOrEmpty()){
                contractLayout.visibility = View.GONE
            }else{
                contractLayout.visibility = View.VISIBLE
                contractTv.text = contractAddress
            }
        }
    }


    var popup: PopupWindow? = null
    var popupViewBinding: PopupWindowBalanceBinding? = null
    private fun showPopup() {
        val p = popup ?: PopupWindow(requireContext()).let {
            val view = PopupWindowBalanceBinding.inflate(layoutInflater)
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.width = DensityUtil.dip2px(requireContext(), 260f)
            it.isOutsideTouchable = true
            it.isTouchable = true
            popupViewBinding = view
            it.isFocusable = false
            it.contentView = view.root
//            view.balanceTv.text = walletViewModel.tokenLiveData.value?.firstOrNull { it.name=="TRX" }?.balance.toString()
            it
        }
        p.showAsDropDown(viewBinding.walletName)
    }
}