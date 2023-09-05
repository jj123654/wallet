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
import com.yns.wallet.databinding.ActivityAddTokenBinding
import com.yns.wallet.widget.bottomsheet.BottomSheet

class AddTokenActivity : BaseActivity<ActivityAddTokenBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {

        val rvList = findViewById<RecyclerView>(R.id.rv_list)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = BottomSheetList(this)

        findViewById<View>(R.id.iv_back).setOnClickListener { finish() }
    }

    class BottomSheetList(private val context: Context) :
        RecyclerView.Adapter<BottomSheetViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetViewHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_add_token, parent, false)
            return BottomSheetViewHolder(view)
        }

        override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        }

        override fun getItemCount(): Int = 8
    }

    class BottomSheetViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
