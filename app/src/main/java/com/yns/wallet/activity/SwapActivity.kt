package com.yns.wallet.activity

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.qmuiteam.qmui.util.QMUIDisplayHelper
import com.yns.wallet.R
import com.yns.wallet.adapter.BottomSheetChooseTokenListAdapter
import com.yns.wallet.adapter.SwapListAdapter
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.SwapRecordBean
import com.yns.wallet.bean.TokenModel
import com.yns.wallet.databinding.ActivitySwapBinding
import com.yns.wallet.databinding.LayoutChooseTokenBinding
import com.yns.wallet.fragment.ConfirmLoopFragment
import com.yns.wallet.util.ViewModelUtils.lazyViewModel
import com.yns.wallet.util.onClick
import com.yns.wallet.viewmodel.SwapViewModel
import com.yns.wallet.widget.bottomsheet.BottomSheet
import com.yns.wallet.widget.decoration.SpacesItemDecoration
import com.yns.wallet.widget.decoration.WrapContentLinearLayoutManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.math.BigDecimal

class SwapActivity : BaseActivity<ActivitySwapBinding>() {

    private val swapViewModel:SwapViewModel by lazyViewModel()

    private val handler = Handler()
    private var delayedRunnable: Runnable = Runnable {
        getSwapInfo()
    }

    private val swapListAdapter: SwapListAdapter by lazy {
        SwapListAdapter(this@SwapActivity, mutableListOf()).apply{
        }
    }

    var recordList: MutableList<SwapRecordBean> = ArrayList()

    private lateinit var bottomSheet: BottomSheet

    private var leftTokenBean: TokenModel?=null
    private var rightTokenBean: TokenModel?=null
    private var fromOrTo = true
    private var leftPosition = 0
    private var rightPosition = 0

    private val chooseTokenListAdapter: BottomSheetChooseTokenListAdapter by lazy {
        BottomSheetChooseTokenListAdapter(this@SwapActivity, mutableListOf()).apply {
            setOnItemClickListener { adapter, view, position ->
                if(getCurrentSelect()!=position){
                    bottomSheet?.let { bottomSheet.dismiss() }
                    if(fromOrTo){
                        leftPosition = position
                    }else{
                        rightPosition = position
                    }
                    showCurrentToken()
                    viewBinding.header.fromEt.text = viewBinding.header.fromEt.text
                }

            }
        }
    }

    override fun initView(root: View, savedInstanceState: Bundle?) {
        swapViewModel.swapInfoLiveData.observe(this){
            dismissProgress()
            viewBinding.header.apply {
                toEt.setText("${it.rate}")
                rateTv.text = "${fromEt.text} ${leftTokenBean?.name} = ${it.rate} ${rightTokenBean?.name}"
                feeTv.text = "${it.fee} ${leftTokenBean?.name}"
                priceImpactTv.text = "${it.priceImpact}%"
                minReceiveTv.text = "${it.minReceive}"
            }
        }

        swapViewModel.swapRecordLiveData.observe(this){
            dismissProgress()
            swapListAdapter.setList(it)
        }

        viewBinding.apply {
            recyclerView.layoutManager = WrapContentLinearLayoutManager(
                this@SwapActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            recyclerView.addItemDecoration(
                SpacesItemDecoration(
                    QMUIDisplayHelper.dp2px(
                        this@SwapActivity,
                        20
                    ), QMUIDisplayHelper.dp2px(this@SwapActivity, 15)
                )
            )
            recyclerView.adapter = swapListAdapter
            swapListAdapter.setEmptyView(R.layout.common_empty_view)
        }
        val header = viewBinding.header
//        if (recordList.size <= 0) {
//            for (i in 0..11) {
//                recordList.add(SwapRecordBean())
//            }
//
//            swapListAdapter.setList(recordList)
//        }
        header.rlFromLayout.onClick {
            fromOrTo = true
            chooseTokenListAdapter.refreshData(leftPosition)
            bottomSheet.show()
        }
        header.rlToLayout.onClick {
            fromOrTo = false
            chooseTokenListAdapter.refreshData(rightPosition)
            bottomSheet.show()
        }

        header.fromEt.doAfterTextChanged {
            handler.removeCallbacks(delayedRunnable)
            if(it.toString().isNullOrEmpty()){
                header.toEt.setText("")
                header.tvSwap.isEnabled = false
                header.tokenInfoLayout.visibility = View.GONE
            }else{
                header.tvSwap.isEnabled = true
                header.tokenInfoLayout.visibility = View.VISIBLE
                handler.postDelayed(delayedRunnable, 500)
            }
        }

        header.tvSwap.onClick {
            ConfirmLoopFragment.newInstance(false,rightTokenBean?.address,viewBinding.header.toEt.text.toString(),rightTokenBean?.address,rightTokenBean).add(supportFragmentManager)
        }

        loadTokenBottomSheetData()

        getSwapRecord()
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
        chooseTokenListAdapter.setEmptyView(R.layout.common_empty_view)
        var list:MutableList<TokenModel> = ArrayList()
        list.clear()
        walletViewModel.tokenLiveData.value?.forEach {
            if (it.name == "TRX") {
                list.add(0, it)
            } else {
                list.add(it)
            }
        }

        chooseTokenListAdapter.setList(list)


        leftPosition = list.indexOfFirst { it.name=="YNS" }
        fromOrTo = true
        showCurrentToken()
        rightPosition = list.indexOfFirst { it.name=="USDT" }
        fromOrTo = false
        showCurrentToken()

    }


    private fun showCurrentToken(){
        if(fromOrTo){
            if(leftPosition<=chooseTokenListAdapter.data.size-1) {
                leftTokenBean = chooseTokenListAdapter.data[leftPosition]
                chooseTokenListAdapter.refreshData(leftPosition)
            }

        }else{
            if(rightPosition<=chooseTokenListAdapter.data.size-1) {
                rightTokenBean = chooseTokenListAdapter.data[rightPosition]
                chooseTokenListAdapter.refreshData(rightPosition)
            }
        }


        viewBinding.header.apply {
            if(fromOrTo){
                leftTokenBean?.let {
                    ivMineIcon.load(it.imageUrl){
                        placeholder(R.mipmap.account_default_photo)
                    }
                    ivMineName.text = it.name
                    tvLeftBalance.text = it.balance.toString()
                    fromImg.load(it.imageUrl){
                        placeholder(R.mipmap.account_default_photo)
                    }
                    fromName.text = it.name
                }
            }else{
                rightTokenBean?.let {
                    ivMineIconTo.load(it.imageUrl){
                        placeholder(R.mipmap.account_default_photo)
                    }
                    ivMineNameTo.text = it.name
                    tvRightBalance.text = it.balance.toString()

                }
            }

            fromToTv.text = "${leftTokenBean?.name}/${rightTokenBean?.name}"
        }


    }


    private fun getSwapInfo(){
        showProgress()
        swapViewModel.getSwapInfoFromTokenAddress(leftTokenBean?.address,rightTokenBean?.address,viewBinding.header.fromEt.text.toString())

    }

    private fun getSwapRecord(){
        showProgress()
        swapViewModel.getSwapRecordList(leftTokenBean?.address)
    }

}
