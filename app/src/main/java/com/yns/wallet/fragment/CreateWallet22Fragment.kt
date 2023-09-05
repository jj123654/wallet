package com.yns.wallet.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yns.wallet.CreateStepImportActivity
import com.yns.wallet.R
import kotlin.random.Random

class CreateWallet22Fragment : Fragment(R.layout.fragment_create_wallet22) {

    private val line0: List<String> = listOf(
        "fine", "poverty", "satisfy"
    )
    private val line1: List<String> = listOf(
        "noise", "trial", "hazard"
    )
    private val line2: List<String> = listOf(
        "access", "ginger", "rocket"
    )
    private val line3: List<String> = listOf(
        "never", "squirrel", "trumpet"
    )
    private val indexArray = ArrayList<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        indexArray.add(Random.nextInt(0, 3))
        indexArray.add(Random.nextInt(0, 3) + 3)
        indexArray.add(Random.nextInt(0, 3) + 6)
        indexArray.add(Random.nextInt(0, 3) + 9)
        val removeIndex = Random.nextInt(0, 3)
        indexArray.removeAt(removeIndex)
    }

    private fun initView(view: View) {
        val rvList = view.findViewById<RecyclerView>(R.id.rv_list)
        rvList.layoutManager = GridLayoutManager(context, 3)
        rvList.adapter = WordListAdapter(view.context, indexArray)

        view.findViewById<View>(R.id.tv_import).setOnClickListener {
            startActivity(Intent(view.context, CreateStepImportActivity::class.java))
        }
    }

    class WordListAdapter(private val context: Context, private val indexArray: List<Int>) :
        RecyclerView.Adapter<WordListHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_word_index, parent, false)
            return WordListHolder(view)
        }

        override fun onBindViewHolder(holder: WordListHolder, position: Int) {
            holder.tvWord.text = (position + 1).toString()
            val isContains = indexArray.contains(position)
            holder.tvWord.isSelected = isContains
            holder.tvWord.textSize = if (isContains) {
                context.resources.getDimension(R.dimen.sp_13)
            } else {
                context.resources.getDimension(R.dimen.sp_10)
            }
        }

        override fun getItemCount(): Int = 12

    }

    class WordListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvWord = view.findViewById<TextView>(R.id.tv_word)
    }
}