package com.yns.wallet.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {

        findViewById<View>(R.id.iv_mine_name).setOnClickListener {
            showEditDialog()
        }
        findViewById<View>(R.id.tv_mnemonic).setOnClickListener {
            showEditDialog()
        }
        findViewById<View>(R.id.tv_private_key).setOnClickListener {
            showEditDialog()
        }
        findViewById<View>(R.id.tv_reset_wallet).setOnClickListener {
            showEditDialog()
        }
        findViewById<View>(R.id.iv_change_pwd).setOnClickListener {
            startActivity(Intent(this, DetailResetPwdActivity::class.java))
        }
    }

    private fun showEditDialog() {
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null)
        val dialog = AlertDialog.Builder(this).create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setView(view)
        dialog.show()
        val width = resources.getDimensionPixelOffset(R.dimen.dialog_width)
        val height = dialog.window?.attributes?.height as Int
        dialog.window?.setLayout(width, height)
        view.findViewById<View>(R.id.tv_cancel).setOnClickListener { dialog.dismiss() }
        view.findViewById<View>(R.id.tv_confirm).setOnClickListener { dialog.dismiss() }
    }


}