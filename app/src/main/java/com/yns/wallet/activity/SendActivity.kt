package com.yns.wallet.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.adapter.BottomSheetChooseTokenListAdapter
import com.yns.wallet.adapter.SelectTokenListAdapter
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.TokenBean
import com.yns.wallet.databinding.ActivitySendBinding
import com.yns.wallet.fragment.ConfirmLoopFragment
import com.yns.wallet.widget.bottomsheet.BottomSheet

class SendActivity : BaseActivity<ActivitySendBinding>() {

    private lateinit var bottomSheet: BottomSheet

    private var currentTokenBean:TokenBean?=null

    private val chooseTokenListAdapter: BottomSheetChooseTokenListAdapter by lazy {
        BottomSheetChooseTokenListAdapter(this@SendActivity, mutableListOf()).apply {
            setOnItemClickListener { adapter, view, position ->
                if(!data[position].isSelected){
                    refreshData(position)
                    currentTokenBean = data[position]
                    viewBinding.ivMineName2.text = currentTokenBean?.name
                    bottomSheet?.let { bottomSheet.dismiss() }
                }

            }
        }
    }

    var list:MutableList<TokenBean> = ArrayList()

    override fun initView(root: View, savedInstanceState: Bundle?) {

        loadTokenBottomSheetData()


        viewBinding.apply {
            ivMineName2.onClick {
                bottomSheet.show()
            }
            tvConfirm.onClick {
                ConfirmLoopFragment().show(supportFragmentManager)
            }
            ivMineName2.text = currentTokenBean?.name

        }


    }

    private fun loadTokenBottomSheetData(){
        // bottom sheet
        val view: View = LayoutInflater.from(this).inflate(R.layout.layout_choose_token, null)
        bottomSheet = BottomSheet.Builder(this).contentView(view).build()
        view.findViewById<View>(R.id.close_img).setOnClickListener {
            bottomSheet.dismiss()
        }
        val rvList = view.findViewById<RecyclerView>(R.id.rv_list)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = chooseTokenListAdapter

        if(list==null||list.size<=0) {
            currentTokenBean = TokenBean(0,"TRX","0xsadtr234",true)
            list.add(currentTokenBean!!)
            list.add(TokenBean(0,"YNS","0xsadtr234",false))
            list.add(TokenBean(0,"USDT","0xsadtr234",false))

            chooseTokenListAdapter.addData(list)
        }
    }



}
