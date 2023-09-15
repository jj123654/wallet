package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.base.KeyValuePairVO
import com.yns.wallet.base.walletViewModel
import com.yns.wallet.databinding.ActivityImport2Binding
import com.yns.wallet.util.AppManager
import com.yns.wallet.util.EventBusUtil
import com.yns.wallet.util.addEditEyeViewLogic

class Import2Activity : BaseActivity<ActivityImport2Binding>() {

    var isFirstLoad = false

    var privateKey:String?=null

    override fun initView(root: View, savedInstanceState: Bundle?) {
        isFirstLoad = intent.getBooleanExtra("isFirstLoad",false)
        privateKey = intent.getStringExtra("privateKey")
        viewBinding.apply {
            tvImport.onClick {
                walletNameEmptyTv.visibility = if(etWalletName.text.toString().isNullOrEmpty()) View.VISIBLE else View.GONE
                pswEmptyTv.visibility = if(etPwd.text.toString().isNullOrEmpty()) View.VISIBLE else View.GONE
                repeatPswEmptyTv.visibility = if(etRepeatPwd.text.toString().isNullOrEmpty()){
                    repeatPswEmptyTv.text = getString(R.string.please_repeat_password)
                    View.VISIBLE
                }else{
                    if(etPwd.text.toString() != etRepeatPwd.text.toString()){
                        repeatPswEmptyTv.text = getString(R.string.repeat_password_should_equal_to_password)
                        View.VISIBLE
                    }else{
                        View.GONE
                    }
                }

                if(walletNameEmptyTv.visibility != View.GONE
                    ||pswEmptyTv.visibility != View.GONE
                    ||repeatPswEmptyTv.visibility != View.GONE){
                    return@onClick
                }
                //TODO 此处后续需要完善可能是通过私钥也可能是通过助记词创建的钱包
                walletViewModel.createWalletFromPrivateKey(walletNameEmptyTv.text.toString(),privateKey?:"",pswEmptyTv.text.toString()){
                    if(isFirstLoad){
                        AppManager.getAppManager().returnToActivity(ImportOrCreateWallet::class.java)
                        EventBusUtil.sendEvent(KeyValuePairVO(EventBusUtil.CREATE_WALLET_SUCCESS,true))
                    }else{
                        AppManager.getAppManager().returnToActivity(MainActivity::class.java)
                    }
                }

            }
        }


        addEditEyeViewLogic(window.decorView, R.id.iv_pwd_eye, R.id.et_pwd)
        addEditEyeViewLogic(window.decorView, R.id.iv_repeat_pwd_eye, R.id.et_repeat_pwd)
    }
}
