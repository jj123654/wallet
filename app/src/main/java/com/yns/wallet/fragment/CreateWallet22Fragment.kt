package com.yns.wallet.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.activity.CreateStepImportActivity
import com.yns.wallet.R
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.databinding.FragmentCreateWallet22Binding
import com.yns.wallet.util.showToast
import kotlin.random.Random

class CreateWallet22Fragment : BaseFragment<FragmentCreateWallet22Binding>() {

    var line1: MutableList<TextView> = mutableListOf()
    var line2: MutableList<TextView> = mutableListOf()
    var line3: MutableList<TextView> = mutableListOf()

    private val indexArray = ArrayList<Int>()

    private val wordList = mutableListOf<String>()

    companion object {
        fun newInstance(it:String): CreateWallet22Fragment {
            val arguments = Bundle()
            arguments.putString("menomic",it)
            val createWallet22Fragment = CreateWallet22Fragment()
            createWallet22Fragment.arguments = arguments
            return createWallet22Fragment
        }
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        val menomics = arguments?.getString("menomic")
        menomics?.let {
            it?.split(" ")?.forEach {menomic->
                wordList.add(menomic)
            }
        }

        indexArray.add(Random.nextInt(0, 4))
        indexArray.add(Random.nextInt(0, 4) + 4)
        indexArray.add(Random.nextInt(0, 4) + 8)

        viewBinding.apply {
            tvChooseIndex1.text = String.format(getString(R.string.choose_index_1),(indexArray[0]+1).toString())
            tvChooseIndex2.text = String.format(getString(R.string.choose_index_1),(indexArray[1]+1).toString())
            tvChooseIndex3.text = String.format(getString(R.string.choose_index_1),(indexArray[2]+1).toString())

            line1.add(tvChooseIndex11)
            line1.add(tvChooseIndex12)
            line1.add(tvChooseIndex13)

            line2.add(tvChooseIndex21)
            line2.add(tvChooseIndex22)
            line2.add(tvChooseIndex23)

            line3.add(tvChooseIndex31)
            line3.add(tvChooseIndex32)
            line3.add(tvChooseIndex33)


            rvList.layoutManager = GridLayoutManager(context, 3)
            rvList.adapter = WordListAdapter(view.context, indexArray)


            tvImport.onClick{
                if(line1.firstOrNull{textView -> textView.isSelected }?.text.toString() != wordList[indexArray[0]]
                    ||line2.firstOrNull{textView -> textView.isSelected }?.text.toString() != wordList[indexArray[1]]
                    ||line3.firstOrNull{textView -> textView.isSelected }?.text.toString() != wordList[indexArray[2]]){
                    showToast(getString(R.string.word_order_wrong_tips))
                    return@onClick
                }
                var intent = Intent(activity,CreateStepImportActivity::class.java)
                intent.putExtra("isFirstLoad",true)
                startActivity(CreateStepImportActivity::class.java)
            }

            line1[0].onClick {
                setSelected(line1,0)
            }
            line1[1].onClick {
                setSelected(line1,1)
            }
            line1[2].onClick {
                setSelected(line1,2)
            }
            line2[0].onClick {
                setSelected(line2,0)
            }
            line2[1].onClick {
                setSelected(line2,1)
            }
            line2[2].onClick {
                setSelected(line2,2)
            }
            line3[0].onClick {
                setSelected(line3,0)
            }
            line3[1].onClick {
                setSelected(line3,1)
            }
            line3[2].onClick {
                setSelected(line3,2)
            }

        }

        val list1 = mutableListOf<String>()
        val list2 = mutableListOf<String>()
        val list3 = mutableListOf<String>()

        list1.add(wordList[indexArray[0]])
        list2.add(wordList[indexArray[1]])
        list3.add(wordList[indexArray[2]])

        var linshilist1 = mutableListOf<String>()
        linshilist1.addAll(wordList.subList(0,4))
        linshilist1.removeAt(indexArray[0])
        linshilist1.shuffle()
        list1.addAll(linshilist1.take(2))
        list1.shuffle()

        var linshilist2 = mutableListOf<String>()
        linshilist2.addAll(wordList.subList(4,8))
        linshilist2.removeAt(indexArray[1]-4)
        linshilist2.shuffle()
        list2.addAll(linshilist2.take(2))
        list2.shuffle()

        var linshilist3 = mutableListOf<String>()
        linshilist3.addAll(wordList.subList(8,12))
        linshilist3.removeAt(indexArray[2]-8)
        linshilist3.shuffle()
        list3.addAll(linshilist3.take(2))
        list3.shuffle()

        for(i in 0 until 3){
            line1[i].text = list1[i]
            line2[i].text = list2[i]
            line3[i].text = list3[i]
        }



    }

    private fun setSelected(line:MutableList<TextView>,position:Int){
        for(i in 0 until line.size){
            line[i].isSelected = position==i
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