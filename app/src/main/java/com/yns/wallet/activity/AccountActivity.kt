package com.yns.wallet.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.adapter.AccountListAdapter
import com.yns.wallet.api.NetworkApi
import com.yns.wallet.api.WalletApi
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityAccountBinding
import com.yns.wallet.widget.CommonCenterDialog
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class AccountActivity : BaseActivity<ActivityAccountBinding>() {

    private val accountListAdapter: AccountListAdapter by lazy {
        AccountListAdapter(mutableListOf()).apply {
            setOnItemClickListener { adapter, view, position ->
                this.refreshData(position)
//                walletViewModel.switchCurrentWallet(position)
                finish()
            }
        }
    }


    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            tvCreate.setOnClickListener {
                showVerifyPasswordDialog {
                    var intent = Intent(this@AccountActivity, CreateStepImportActivity::class.java)
                    intent.putExtra("isFirstLoad", false)
                    intent.putExtra("password", it)
                    startActivity(intent)
                }

            }

            tvImport.setOnClickListener {
                startActivity(ImportActivity::class.java)
            }

            rvList.layoutManager = WrapContentLinearLayoutManager(
                this@AccountActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            rvList.addItemDecoration(
                SpacesItemDecoration(
                    QMUIDisplayHelper.dp2px(
                        this@AccountActivity,
                        20
                    ), QMUIDisplayHelper.dp2px(this@AccountActivity, 20)
                )
            )
            rvList.adapter = accountListAdapter
        }

        walletViewModel.walletsLiveData.observe(this) {
            accountListAdapter.setList(it)
            accountListAdapter.refreshData(it.indexOfFirst { wallet -> walletViewModel.currentWalletLiveData.value?.address == wallet.address })
        }
        walletViewModel.getAllWallet()

    }


}
