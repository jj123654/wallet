package com.yns.wallet.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.activity.*
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.databinding.FragmentMineBinding
import com.yns.wallet.util.LanguageUtils
import com.yns.wallet.util.getStatusBarHeight
import com.yns.wallet.util.onClick

class MineFragment : BaseFragment<FragmentMineBinding>() {

    override fun initView(view: View, savedInstanceState: Bundle?) {
        Log.i("walletTest","mineFragment创建")
        view.findViewById<View>(R.id.v_about_bg).setOnClickListener {
            startActivity(Intent(view.context, AboutUsActivity::class.java))
        }
        view.findViewById<View>(R.id.v_mine_account).setOnClickListener {
            startActivity(Intent(view.context, AccountActivity::class.java))
        }

        walletViewModel.currentWalletLiveData.observe(this) {
            refreshData()
        }

        viewBinding.apply {
//            ivMineName.text = walletViewModel.currentWalletLiveData.value?.name

            languageTv.text = LanguageUtils.getInstance().getLanguageName(activity)
            transactionRecordLayout.onClick{
                startActivity(TransactionRecordsActivity::class.java)
            }

            languageLayout.onClick {
                startActivity(SelectLanguageActivity::class.java)
            }

            logoutLayout.onClick{
                startActivity(LoginActivity::class.java)
            }
        }

    }

    private fun refreshData(){
        viewBinding.ivMineName.text = walletViewModel.currentWalletLiveData.value?.name
        viewBinding.ivMineHash.text = walletViewModel.currentWalletLiveData.value?.address
    }

}
