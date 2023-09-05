package com.yns.wallet.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.yns.wallet.R
import com.yns.wallet.addEditEyeViewLogic

class CreateWalletFragment : Fragment(R.layout.fragment_create_wallet) {

    private lateinit var protocolCheckView: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        initPwdEyeLogic(view)
    }

    private fun initPwdEyeLogic(view: View) {
        addEditEyeViewLogic(view, R.id.iv_pwd_eye, R.id.et_pwd)
        addEditEyeViewLogic(view, R.id.iv_repeat_pwd_eye, R.id.et_repeat_pwd)
    }

    private fun initView(view: View) {
        protocolCheckView = view.findViewById<ImageView>(R.id.rb_check)
        protocolCheckView.setOnClickListener {
            protocolCheckView.isSelected = !protocolCheckView.isSelected
        }
        view.findViewById<View>(R.id.tv_next).setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fl_content, CreateWallet2Fragment())
            }
        }
    }

}