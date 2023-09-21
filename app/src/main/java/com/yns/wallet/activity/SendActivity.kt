package com.yns.wallet.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.adapter.BottomSheetChooseTokenListAdapter
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.TokenBean
import com.yns.wallet.bean.TokenModel
import com.yns.wallet.databinding.ActivitySendBinding
import com.yns.wallet.fragment.ConfirmLoopFragment
import com.yns.wallet.util.showToast
import com.yns.wallet.widget.bottomsheet.BottomSheet
import java.math.BigDecimal

class SendActivity : BaseActivity<ActivitySendBinding>() {

    private lateinit var bottomSheet: BottomSheet

    private var currentTokenBean:TokenModel?=null

    private val chooseTokenListAdapter: BottomSheetChooseTokenListAdapter by lazy {
        BottomSheetChooseTokenListAdapter(this@SendActivity, mutableListOf()).apply {
            setOnItemClickListener { adapter, view, position ->
                if(getCurrentSelect()!=position){
                    bottomSheet?.let { bottomSheet.dismiss() }
                    showCurrentToken(position)
                }

            }
        }
    }

    var list:MutableList<TokenModel> = ArrayList()

    override fun initView(root: View, savedInstanceState: Bundle?) {



        walletViewModel.currentWalletLiveData.observe(this) {
            viewBinding.ivMineName.text = it.name
            viewBinding.ivMineHash.text = it.address
        }

        viewBinding.apply {
            ivMineName2.onClick {
                bottomSheet.show()
            }

            ivMineMax.onClick {
                etBalance.setText(currentTokenBean?.balance.toString())
            }

            tvConfirm.onClick {
                if(etPwd.text.toString().isNullOrEmpty()){
                    showToast(getString(R.string.enter_or_paste_the_address))
                    return@onClick
                }
                if(etBalance.text.toString().isNullOrEmpty()){
                    showToast(getString(R.string.please_enter_amount))
                    return@onClick
                }
                if(BigDecimal(etBalance.text.toString()).compareTo(currentTokenBean?.balance)==1){
                    showToast(getString(R.string.should_less_than_balance))
                    return@onClick
                }
                if(BigDecimal(etBalance.text.toString()).compareTo(BigDecimal("0.000001"))==-1){
                    showToast(getString(R.string.should_more_than_0_000001))
                    return@onClick
                }
                ConfirmLoopFragment.newInstance(true,if(currentTokenBean?.name=="TRX") "" else currentTokenBean?.address,etBalance.text.toString(),etPwd.text.toString(),currentTokenBean).add(supportFragmentManager)

            }
            ivMineName2.text = currentTokenBean?.name

        }

        loadTokenBottomSheetData()

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

        list.clear()
        walletViewModel.tokenLiveData.value?.forEach {
            if (it.name == "TRX") {
                list.add(0, it)
            } else {
                list.add(it)
            }
        }

        chooseTokenListAdapter.addData(list)

        showCurrentToken(0)
    }


    private fun showCurrentToken(position:Int){
        if(position<=chooseTokenListAdapter.data.size-1) {
            currentTokenBean = chooseTokenListAdapter.data[position]
        }
        chooseTokenListAdapter.refreshData(position)

        viewBinding.apply {
            currentTokenBean?.let {
                ivMineIcon2.load(it.imageUrl) {
                    placeholder(R.mipmap.account_default_photo)
                }
                ivMineName2.text = it?.name
                ivMineHash2.text = it?.name
                ivMineWallet.text = "${it?.balance.toString()} ${it.name}"
                etBalance.setText("")
            }
        }

    }


}
