package com.yns.wallet.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityMainBinding
import com.yns.wallet.fragment.MineFragment
import com.yns.wallet.fragment.WalletFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var walletCreateDialog: AlertDialog

    override fun initView(root: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.commit(true) {
                setReorderingAllowed(true)
                add<WalletFragment>(R.id.fragment_container)
            }
        }

        viewBinding.apply {
            ivWallet.onClick {
                changeState(true)
                supportFragmentManager.commit(true) {
                    replace(R.id.fragment_container, WalletFragment())
                }
            }
            ivMine.onClick {
                changeState(false)
                supportFragmentManager.commit(true) {
                    replace(R.id.fragment_container, MineFragment())
                }
            }
        }


//        val view = LayoutInflater.from(this).inflate(R.layout.dialog_one_btn, null)
//        view.findViewById<View>(R.id.tv_confirm).setOnClickListener { openImportOrCreate() }
//        walletCreateDialog = AlertDialog.Builder(this).create()
//        walletCreateDialog.setView(view)
//        walletCreateDialog.show()
//        val width = resources.getDimensionPixelOffset(R.dimen.dialog_width)
//        val height = walletCreateDialog.window?.attributes?.height as Int
//        walletCreateDialog.window?.setLayout(width, height)
    }

    private fun changeState(walletOrMe:Boolean){
        viewBinding.apply {
            if(walletOrMe){
                ivWallet.setImageResource(R.mipmap.icon_wallet_selected)
                ivMine.setImageResource(R.mipmap.icon_user_normal)
            }else{
                ivWallet.setImageResource(R.mipmap.icon_wallet_normal)
                ivMine.setImageResource(R.mipmap.icon_user_selected)
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        walletCreateDialog.dismiss()
    }

    private fun openImportOrCreate() {
        startActivity(Intent(this, ImportOrCreateWallet::class.java))
    }

}