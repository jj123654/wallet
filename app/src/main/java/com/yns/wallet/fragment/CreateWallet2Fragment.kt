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
import com.yns.wallet.R
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.databinding.FragmentCreateWallet2Binding

class CreateWallet2Fragment : BaseFragment<FragmentCreateWallet2Binding>() {

    private val wordList: List<String> = listOf(
        "fine", "poverty", "satisfy",
        "noise", "trial", "hazard",
        "access", "ginger", "rocket",
        "never", "squirrel", "trumpet"
    )

    override fun initView(view: View, savedInstanceState: Bundle?) {
        val vPopBg = view.findViewById<View>(R.id.v_pop_bg)
        val llPopLayout = view.findViewById<View>(R.id.ll_pop_layout)
        view.findViewById<View>(R.id.tv_view_mnemonic).setOnClickListener {
            vPopBg.visibility = View.GONE
            llPopLayout.visibility = View.GONE
        }

        view.findViewById<View>(R.id.tv_confirm).setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fl_content, CreateWallet22Fragment())
            }
        }

        val rvList = view.findViewById<RecyclerView>(R.id.rv_list)
        rvList.layoutManager = GridLayoutManager(context, 3)
        rvList.adapter = WordListAdapter(view.context, wordList)
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