package com.yns.wallet.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.commit
import com.yns.wallet.R
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.databinding.FragmentCreateWalletBinding
import com.yns.wallet.util.addEditEyeViewLogic

class CreateWalletFragment : BaseFragment<FragmentCreateWalletBinding>() {

    private lateinit var protocolCheckView: ImageView

    override fun initView(view: View, savedInstanceState: Bundle?) {

        protocolCheckView = view.findViewById<ImageView>(R.id.rb_check)
        protocolCheckView.setOnClickListener {
            protocolCheckView.isSelected = !protocolCheckView.isSelected
        }
        view.findViewById<View>(R.id.tv_next).setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fl_content, CreateWallet2Fragment())
            }
        }

        addEditEyeViewLogic(view, R.id.iv_pwd_eye, R.id.et_pwd)
        addEditEyeViewLogic(view, R.id.iv_repeat_pwd_eye, R.id.et_repeat_pwd)
    }

}