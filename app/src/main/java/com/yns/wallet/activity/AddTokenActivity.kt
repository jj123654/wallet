package com.yns.wallet.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yns.wallet.R
import com.yns.wallet.adapter.TokenListAdapter
import com.yns.wallet.adapter.WalletListAdapter
import com.yns.wallet.api.WalletApi
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.TokenBean
import com.yns.wallet.databinding.ActivityAddTokenBinding
import com.yns.wallet.widget.bottomsheet.BottomSheet
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class AddTokenActivity : BaseActivity<ActivityAddTokenBinding>() {

    var list:MutableList<TokenBean> = ArrayList()

    private val tokenListAdapter: TokenListAdapter by lazy {
        TokenListAdapter(this@AddTokenActivity, mutableListOf()).apply {
            setOnItemChildClickListener{ adapter, view, position->
                when(view.id){

                }
            }
        }
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            ivBack.setOnClickListener { finish() }

            rvList.layoutManager = WrapContentLinearLayoutManager(this@AddTokenActivity)
            rvList.adapter = tokenListAdapter
        }

        if(list==null||list.size<=0) {
            list.add(TokenBean(0,"Account-1","0xsadtr234",true))
            list.add(TokenBean(0,"Account-2","0xsadtr234",false))
            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
            list.add(TokenBean(0,"Account-3","0xsadtr234",false))
            list.add(TokenBean(0,"Account-3","0xsadtr234",false))


            tokenListAdapter.addData(list)
        }

        WalletApi.getPopularToken()

    }

}
