package com.yns.wallet.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityImportBinding
import com.yns.wallet.util.showToast


class ImportActivity : BaseActivity<ActivityImportBinding>() {

    var isFirstLoad = false

    override fun initView(root: View, savedInstanceState: Bundle?) {
        isFirstLoad = intent.getBooleanExtra("isFirstLoad",false)

        viewBinding.apply {
            pasteTv.onClick {
                inputEdit.setText(pastePrivateKey())
            }

            tvImport.onClick {
                if(inputEdit.text.toString().isNullOrEmpty()){
                    showToast(getString(R.string.please_enter_name))
                }else {
                    var intent = Intent(this@ImportActivity,Import2Activity::class.java)
                    intent.putExtra("isFirstLoad",isFirstLoad)
                    intent.putExtra("privateKey",inputEdit.text.toString())
                    startActivity(intent)
                }

            }
        }

    }


    private fun pastePrivateKey():String{
        val clipboardManager: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (null != clipboardManager) {
            // 获取剪贴板的剪贴数据集
            val clipData: ClipData? = clipboardManager.primaryClip
            if (null != clipData && clipData.itemCount > 0) {
                // 从数据集中获取（粘贴）第一条文本数据
                val item = clipData.getItemAt(0)
                if (null != item) {
                    return item.text.toString()
                }
            }
        }
        return ""
    }

}
