package com.yns.wallet.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.AccountActivityBinding

class AccountActivity : BaseActivity<AccountActivityBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.rvList.layoutManager = LinearLayoutManager(this)
        viewBinding.rvList.adapter = AccountListAdapter(this)
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
