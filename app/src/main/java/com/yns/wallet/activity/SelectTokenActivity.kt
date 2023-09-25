package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.adapter.SelectTokenListAdapter
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.TokenBean
import com.yns.wallet.databinding.ActivitySelectTokenBinding
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class SelectTokenActivity:BaseActivity<ActivitySelectTokenBinding>() {

    private val selectTokenListAdapter: SelectTokenListAdapter by lazy {
        SelectTokenListAdapter(this@SelectTokenActivity, mutableListOf()).apply {
            setOnItemClickListener { adapter, view, position ->
                if(!data[position].isSelected){
                    refreshData(position)
                }
            }
        }
    }

    var list:MutableList<TokenBean> = ArrayList()

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            recyclerView.layoutManager = WrapContentLinearLayoutManager(this@SelectTokenActivity, LinearLayoutManager.VERTICAL,false)
            recyclerView.addItemDecoration(SpacesItemDecoration(QMUIDisplayHelper.dp2px(this@SelectTokenActivity, 20), QMUIDisplayHelper.dp2px(this@SelectTokenActivity, 20)))
            recyclerView.adapter = selectTokenListAdapter
        }

        if(list==null||list.size<=0) {
            list.add(TokenBean(0,"Account-1","0xsadtr234",true))
            list.add(TokenBean(0,"Account-2","0xsadtr234",false))
            list.add(TokenBean(0,"Account-3","0xsadtr234",false))

            selectTokenListAdapter.addData(list)
        }
    }
}