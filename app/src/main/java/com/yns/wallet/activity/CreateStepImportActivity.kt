package com.yns.wallet.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.base.Constant
import com.yns.wallet.base.KeyValuePairVO
import com.yns.wallet.base.walletViewModel
import com.yns.wallet.databinding.ActivityCreateStepImport2Binding
import com.yns.wallet.util.AppManager
import com.yns.wallet.util.EventBusUtil
import com.yns.wallet.util.SharedPreferencesUtils
import com.yns.wallet.widget.bottomsheet.BottomSheet

class CreateStepImportActivity : BaseActivity<ActivityCreateStepImport2Binding>() {

    private lateinit var bottomSheet: BottomSheet

    var isFirstLoad = false

    var menomic:String?=null
    var password:String?=null

    override fun initView(root: View, savedInstanceState: Bundle?) {
        isFirstLoad = intent.getBooleanExtra("isFirstLoad",false)
        menomic = intent.getStringExtra("menomic")
        password = intent.getStringExtra("password")

        viewBinding.apply {
            if(isFirstLoad){
                topLayout.visibility = View.GONE
            }else{
                topLayout.visibility = View.VISIBLE
            }

            // activity
            tvChooseAccount.setOnClickListener {
                bottomSheet.show()
            }
            tvConfirm.setOnClickListener {
                walletViewModel.createWalletFromMenomic(menomic?:"",password?:"") {
                    if(isFirstLoad){
                        AppManager.getAppManager().returnToActivity(ImportOrCreateWallet::class.java)
                        EventBusUtil.sendEvent(KeyValuePairVO(EventBusUtil.CREATE_WALLET_SUCCESS,true))
                    }else{
                        AppManager.getAppManager().returnToActivity(MainActivity::class.java)
                    }
                }

            }

        }


        // bottom sheet
        val view: View = LayoutInflater.from(this).inflate(R.layout.layout_choose_account, null)
        bottomSheet = BottomSheet.Builder(this).contentView(view).build()
        view.findViewById<View>(R.id.close_img).setOnClickListener {
            bottomSheet.dismiss()
        }
        val rvList = view.findViewById<RecyclerView>(R.id.rv_list)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = BottomSheetList(this)

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