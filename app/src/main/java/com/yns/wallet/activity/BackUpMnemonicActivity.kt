package com.yns.wallet.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.api.WalletApi
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.BackUpRecordModel
import com.yns.wallet.databinding.ActivityAboutUsBinding
import com.yns.wallet.databinding.ActivityBackUpMnemonicBinding
import com.yns.wallet.fragment.CreateWallet22Fragment
import com.yns.wallet.fragment.CreateWallet2Fragment
import com.yns.wallet.util.ViewModelUtils.lazyViewModel
import com.yns.wallet.viewmodel.BackUpViewModel
import com.yns.wallet.widget.CommonCenterDialog

class BackUpMnemonicActivity : BaseActivity<ActivityBackUpMnemonicBinding>() {

    var wordList = mutableListOf<String>()

    var menomics:String?=null

    private val backUpViewModel: BackUpViewModel by lazyViewModel()

    override fun initView(root: View, savedInstanceState: Bundle?) {

        var backUpRecordModel = BackUpRecordModel(WalletApi.BACKUP_TYPE.MENOMIC
            ,walletViewModel.currentWalletLiveData.value?.name
            ,walletViewModel.currentWalletLiveData.value?.address
            ,System.currentTimeMillis())
        backUpViewModel.addBackUpRecord(backUpRecordModel){

        }

        viewBinding.apply {
            topLayout.tvViewMnemonic.onClick {
                tipsLayout.visibility = View.GONE
            }

            tvScanBtn.onClick {
                CommonCenterDialog(this@BackUpMnemonicActivity).showCenterQRDialog("",getString(R.string.mnemonic_qr_code),"","",null,null)
            }

            tvConfirm.onClick {
                var intent = Intent(this@BackUpMnemonicActivity,BackUpMnemonic2Activity::class.java)
                intent.putExtra("menomic",menomics)
                startActivity(intent)
                finish()
            }

            menomics = WalletApi.createMenomic()
            menomics?.let {
                it?.split(" ")?.forEach {menomic->
                    wordList.add(menomic)
                }
            }

            rvList.layoutManager = GridLayoutManager(this@BackUpMnemonicActivity, 3)
            rvList.adapter = WordListAdapter(this@BackUpMnemonicActivity, wordList)

        }
    }

    class WordListAdapter(private val context: Context, private val wordList: List<String>) :
        RecyclerView.Adapter<WordListHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_word, parent, false)
            return WordListHolder(view)
        }

        override fun onBindViewHolder(holder: WordListHolder, position: Int) {
            holder.tvWord.text = wordList[position]
        }

        override fun getItemCount(): Int = wordList.size

    }

    class WordListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvWord = view.findViewById<TextView>(R.id.tv_word)
    }

}
