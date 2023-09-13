package com.yns.wallet.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.R
import com.yns.wallet.adapter.AccountListAdapter
import com.yns.wallet.adapter.LanguageListAdapter
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.AccountBean
import com.yns.wallet.bean.LanguageBean
import com.yns.wallet.bean.TokenBean
import com.yns.wallet.databinding.ActivityAccountBinding
import com.yns.wallet.util.LanguageUtils
import com.yns.wallet.util.restartApp
import com.yns.wallet.widget.CommonCenterDialog
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class AccountActivity : BaseActivity<ActivityAccountBinding>() {

    private val accountListAdapter: AccountListAdapter by lazy {
        AccountListAdapter(this@AccountActivity, mutableListOf()).apply {
            setOnItemClickListener { adapter, view, position ->
                if(!data[position].isSelected){
                    refreshData(position)
                }
            }
        }
    }

    var list:MutableList<AccountBean> = ArrayList()

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            tvCreate.setOnClickListener {
                CommonCenterDialog(this@AccountActivity).showPswEditDialog{
                    var intent = Intent(this@AccountActivity,CreateStepImportActivity::class.java)
                    intent.putExtra("isFirstLoad",false)
                    startActivity(CreateStepImportActivity::class.java)
                }

            }

            tvImport.setOnClickListener {
                startActivity(ImportActivity::class.java)
            }

            rvList.layoutManager = WrapContentLinearLayoutManager(this@AccountActivity, LinearLayoutManager.VERTICAL,false)
            rvList.addItemDecoration(SpacesItemDecoration(QMUIDisplayHelper.dp2px(this@AccountActivity, 20), QMUIDisplayHelper.dp2px(this@AccountActivity, 20)))
            rvList.adapter = accountListAdapter
        }

        if(list==null||list.size<=0) {
            list.add(AccountBean(0,"Account-1","0xsadtr234",true))
            list.add(AccountBean(0,"Account-2","0xsadtr234",false))
            list.add(AccountBean(0,"Account-3","0xsadtr234",false))

            accountListAdapter.addData(list)
        }

    }




}
