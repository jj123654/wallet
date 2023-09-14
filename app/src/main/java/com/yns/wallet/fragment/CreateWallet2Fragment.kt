package com.yns.wallet.fragment

import android.content.Context
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
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.databinding.FragmentCreateWallet2Binding
import com.yns.wallet.widget.CommonCenterDialog

class CreateWallet2Fragment : BaseFragment<FragmentCreateWallet2Binding>() {

    var wordList = mutableListOf<String>()

    var menomic:String?=null
    var password:String?=null

    companion object {
        fun newInstance(menomic: String,password: String): CreateWallet2Fragment {
            val arguments = Bundle()
            arguments.putString("menomic", menomic)
            arguments.putString("password", password)
            val createWallet2Fragment = CreateWallet2Fragment()
            createWallet2Fragment.arguments = arguments
            return createWallet2Fragment
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        viewBinding.apply {
            topLayout.tvViewMnemonic.onClick {
                tipsLayout.visibility = View.GONE
            }

            tvConfirm.onClick {
                parentFragmentManager.commit {
                    replace(
                        R.id.fl_content,
                        CreateWallet22Fragment.newInstance(menomic?:"",password?:"")
                    )
                }
            }
            password = arguments?.getString("password")
            menomic = arguments?.getString("menomic")
            menomic?.split(" ")?.forEach { menomic ->
                wordList.add(menomic)
            }
            tvScanBtn.onClick {
                CommonCenterDialog(activity).showCenterQRDialog(
                    menomic,
                    getString(R.string.mnemonic_qr_code),
                    "",
                    "",
                    null,
                    null
                )
            }



            rvList.layoutManager = GridLayoutManager(context, 3)
            rvList.adapter = WordListAdapter(view.context, wordList)
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