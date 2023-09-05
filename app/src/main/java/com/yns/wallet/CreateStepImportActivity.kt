package com.yns.wallet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yns.wallet.util.SharedPreferencesUtils
import com.yns.wallet.widget.bottomsheet.BottomSheet

class CreateStepImportActivity : BaseActivity() {

    private lateinit var bottomSheet: BottomSheet

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_create_step_import)

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

        // activity
        findViewById<View>(R.id.tv_choose_account).setOnClickListener {
            bottomSheet.show()
        }
        findViewById<View>(R.id.tv_confirm).setOnClickListener {
            SharedPreferencesUtils.putString(this, Constant.walletStateKey, "true")
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    class BottomSheetList(private val context: Context) :
        RecyclerView.Adapter<BottomSheetViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomSheetViewHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_choose_account, parent, false)
            return BottomSheetViewHolder(view)
        }

        override fun onBindViewHolder(holder: BottomSheetViewHolder, position: Int) {
        }

        override fun getItemCount(): Int = 5
    }

    class BottomSheetViewHolder(view: View) : RecyclerView.ViewHolder(view)
}