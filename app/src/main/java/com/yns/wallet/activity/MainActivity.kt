package com.yns.wallet.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.adapter.ContentPagerAdapter
import com.yns.wallet.api.WalletApi
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityMainBinding
import com.yns.wallet.fragment.AssetsFragment
import com.yns.wallet.fragment.MineFragment
import com.yns.wallet.fragment.WalletFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val tabFragments: MutableList<Fragment> = ArrayList()

    override fun initView(root: View, savedInstanceState: Bundle?) {
//        if (savedInstanceState == null) {
//            supportFragmentManager.commit(true) {
//                setReorderingAllowed(true)
//                add<WalletFragment>(R.id.fragment_container)
//            }
//        }

        WalletApi.initWebView(viewBinding.webView)
        viewBinding.apply {
            tabFragments.add(WalletFragment())
            tabFragments.add(MineFragment())
            viewPager.adapter =
                ContentPagerAdapter(
                    supportFragmentManager,
                    tabFragments
                )

            ivWallet.onClick {
                if(viewPager.currentItem == 1){
                    changeState(true)
                    viewPager.setCurrentItem(0,false)
                }

            }
            ivMine.onClick {
                if(viewPager.currentItem == 0){
                    changeState(false)
                    viewPager.setCurrentItem(1,false)
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
                ivWallet.setCompoundDrawablesWithIntrinsicBounds(null,resources.getDrawable(R.mipmap.icon_wallet_selected),null,null)
                ivMine.setCompoundDrawablesWithIntrinsicBounds(null,resources.getDrawable(R.mipmap.icon_user_normal),null,null)
            }else{
                ivWallet.setCompoundDrawablesWithIntrinsicBounds(null,resources.getDrawable(R.mipmap.icon_wallet_normal),null,null)
                ivMine.setCompoundDrawablesWithIntrinsicBounds(null,resources.getDrawable(R.mipmap.icon_user_selected),null,null)
            }
        }
    }

//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        walletCreateDialog.dismiss()
//    }

    private fun openImportOrCreate() {
        startActivity(Intent(this, ImportOrCreateWallet::class.java))
    }

}