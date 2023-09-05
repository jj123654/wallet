package com.yns.wallet.activity

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.AccountActivityBinding

class AccountActivity : BaseActivity<AccountActivityBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.rvList.layoutManager = LinearLayoutManager(this)
        viewBinding.rvList.adapter = AccountListAdapter(this)

        viewBinding.tvCreate.setOnClickListener { showEditDialog() }
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


    class AccountListAdapter(private val context: Context) :
        RecyclerView.Adapter<BottomSheetViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetViewHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_account, parent, false)
            return BottomSheetViewHolder(view)
        }

        override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        }

        override fun getItemCount(): Int = 2
    }

    class BottomSheetViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
