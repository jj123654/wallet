package com.yns.wallet.activity

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.adapter.DiscoveryHistoryListAdapter
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityAboutUsBinding
import com.yns.wallet.databinding.ActivitySearchDiscoveryBinding
import com.yns.wallet.io.SpUtil
import com.yns.wallet.util.showToast
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager

class SearchDiscoveryActivity : BaseActivity<ActivitySearchDiscoveryBinding>() {

    var historyList:MutableList<String> ?= null

    private val discoveryHistoryListAdapter:DiscoveryHistoryListAdapter by lazy{
        DiscoveryHistoryListAdapter(mutableListOf()).apply{
            setOnItemChildClickListener{ adapter,view,position->
                if(view.id == R.id.link_tv){
                    gotoWebView(discoveryHistoryListAdapter.data[position])
                } else if (view.id == R.id.close_img) {
                    historyList?.removeAt(position)
                    discoveryHistoryListAdapter.removeAt(position)
                    SpUtil.putList(SpUtil.DISCOVERY_LIST, historyList)
                }
            }
//            setOnItemClickListener{
//                    adapter,view,position->
//                gotoWebView(discoveryHistoryListAdapter.data[position])
//            }
        }
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        historyList = SpUtil.getList(SpUtil.DISCOVERY_LIST,String::class.java)
        if(historyList.isNullOrEmpty()){
            historyList = mutableListOf()
        }

        viewBinding.apply {
            searchEt.requestFocus()
            searchEt.setOnEditorActionListener(object : TextView.OnEditorActionListener{
                override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event != null && KeyEvent.KEYCODE_ENTER == event.keyCode && KeyEvent.ACTION_DOWN == event.action) {
                        if(searchEt.text.toString().isEmpty()){
                            showToast(getString(R.string.please_enter_name))
                            return true
                        }
                        gotoWebView(viewBinding.searchEt.text.toString())
                        return true
                    }
                    return false
                }

            })


            cancelTv.onClick {
                finish()
            }

            recyclerView.layoutManager = WrapContentLinearLayoutManager(
                this@SearchDiscoveryActivity
            )
            discoveryHistoryListAdapter.setList(historyList)
            recyclerView.adapter = discoveryHistoryListAdapter

        }


    }

    private fun gotoWebView(url:String){
        if(historyList?.firstOrNull { it==url }.isNullOrEmpty()){
            historyList?.add(url)
            discoveryHistoryListAdapter.addData(url)
            SpUtil.putList(SpUtil.DISCOVERY_LIST,historyList)
        }
        var intent = Intent(this@SearchDiscoveryActivity,WebViewActivity::class.java)
        intent.putExtra("url",url)
        startActivity(intent)
    }

}
