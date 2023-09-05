package com.yns.wallet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yns.wallet.widget.bottomsheet.BottomSheet

class SendActivity :BaseActivity() {

    private lateinit var bottomSheet: BottomSheet

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_send)

        initView()
    }

    private fun initView() {
        // bottom sheet
        val view: View = LayoutInflater.from(this).inflate(R.layout.layout_choose_account, null)
        bottomSheet = BottomSheet.Builder(this).contentView(view).build()
        view.findViewById<View>(R.id.tv_cancel).setOnClickListener {
            bottomSheet.dismiss()
        }
        val rvList = view.findViewById<RecyclerView>(R.id.rv_list)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = BottomSheetList(this)

        findViewById<View>(R.id.iv_mine_name2).setOnClickListener {
            bottomSheet.show()
        }
    }

    class BottomSheetList(private val context: Context) :
        RecyclerView.Adapter<BottomSheetViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetViewHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_choose_token, parent, false)
            return BottomSheetViewHolder(view)
        }

        override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        }

        override fun getItemCount(): Int = 3
    }

    class BottomSheetViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
