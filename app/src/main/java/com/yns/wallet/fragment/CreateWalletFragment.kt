package com.yns.wallet.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.commit
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.BuildConfig
import com.yns.wallet.BuildConfig.DEBUG
import com.yns.wallet.R
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.base.walletViewModel
import com.yns.wallet.databinding.FragmentCreateWalletBinding
import com.yns.wallet.util.addEditEyeViewLogic
import com.yns.wallet.util.showToast

class CreateWalletFragment : BaseFragment<FragmentCreateWalletBinding>() {

    private lateinit var protocolCheckView: ImageView

    override fun initView(view: View, savedInstanceState: Bundle?) {

        viewBinding.apply {
            if(BuildConfig.DEBUG){
                etPwd.setText("123456789")
                etRepeatPwd.setText("123456789")
            }

            etName.setText("Wallet-"+ walletViewModel.getWalletCount())

            rbCheck.isSelected = true

            rbCheck.onClick {
                rbCheck.isSelected = !rbCheck.isSelected
            }

            tvNext.onClick {
                walletNameEmptyTv.visibility = if(etName.text.toString().isNullOrEmpty()) View.VISIBLE else View.GONE
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

                if(!rbCheck.isSelected){
                   showToast(getString(R.string.agree_first))
                    return@onClick
                }

                walletViewModel.createMenomic{
                    parentFragmentManager.commit(true) {
                        replace(R.id.fl_content, CreateWallet2Fragment.newInstance(it,etName.text.toString(),etPwd.text.toString()))
                    }
                }


            }

        }

        addEditEyeViewLogic(view, R.id.iv_pwd_eye, R.id.et_pwd)
        addEditEyeViewLogic(view, R.id.iv_repeat_pwd_eye, R.id.et_repeat_pwd)
    }

}