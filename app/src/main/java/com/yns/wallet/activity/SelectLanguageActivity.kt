package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.adapter.LanguageListAdapter
import com.yns.wallet.adapter.TransactionListAdapter
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.LanguageBean
import com.yns.wallet.bean.TransactionRecord
import com.yns.wallet.databinding.ActivitySelectLanguageBinding
import com.yns.wallet.util.LanguageType
import com.yns.wallet.util.LanguageUtils
import com.yns.wallet.util.restartApp
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class SelectLanguageActivity:BaseActivity<ActivitySelectLanguageBinding>() {

    private val languageListAdapter: LanguageListAdapter by lazy {
        LanguageListAdapter(this@SelectLanguageActivity, LanguageUtils.getInstance().languageList).apply {
            setOnItemClickListener { adapter, view, position ->
                if(!data[position].isSelected){
                    refreshData()
                    LanguageUtils.getInstance().updateLanguage(this@SelectLanguageActivity,data[position].languageLocale?.language);
                    restartApp(this@SelectLanguageActivity)
                }
            }
        }
    }

    var list:MutableList<LanguageBean> = ArrayList()

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            recyclerView.layoutManager = WrapContentLinearLayoutManager(this@SelectLanguageActivity, LinearLayoutManager.VERTICAL,false)
            recyclerView.addItemDecoration(SpacesItemDecoration(QMUIDisplayHelper.dp2px(this@SelectLanguageActivity, 20), QMUIDisplayHelper.dp2px(this@SelectLanguageActivity, 20)))
            recyclerView.adapter = languageListAdapter
        }

        languageListAdapter.data.forEach {
            if(it.languageLocale?.language == LanguageUtils.getInstance().language){
                it.isSelected = true
            }
        }
        languageListAdapter.refreshData()

    }
}