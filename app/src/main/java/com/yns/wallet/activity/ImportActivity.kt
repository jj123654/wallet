package com.yns.wallet.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.qmuiteam.qmui.kotlin.onClick
import com.qmuiteam.qmui.util.QMUIKeyboardHelper
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityImportBinding
import com.yns.wallet.util.showToast
import com.yns.wallet.widget.CommonCenterDialog


class ImportActivity : BaseActivity<ActivityImportBinding>() {

    var isFirstLoad = false

    override fun initView(root: View, savedInstanceState: Bundle?) {
        isFirstLoad = intent.getBooleanExtra("isFirstLoad",false)

        viewBinding.apply {
            bgLayout.onClick {
                if(QMUIKeyboardHelper.isKeyboardVisible(this@ImportActivity)){
                    QMUIKeyboardHelper.hideKeyboard(inputEdit)
                }
            }

            pasteTv.onClick {
                inputEdit.setText(pastePrivateKey())
            }

            tvImport.onClick {
                if(inputEdit.text.toString().isNullOrEmpty()){
                    showToast(getString(R.string.please_enter_name))
                }else {
                    if(inputEdit.text.toString().contains(" ")){
                        walletViewModel.hasHDWallet {
                            //测试代码
                            walletViewModel.saveMenomic(inputEdit.text.toString()) {
                                    gotoImport2(false)
                            }
                            //正式代码，后续记得打开
//                            if(it){
//                                CommonCenterDialog(this@ImportActivity).showCenterSingleBtnDialog(getString(R.string.has_hd_wallet_tips),null){
//                                }
//                            }else{
//                                walletViewModel.saveMenomic(inputEdit.text.toString()) {
//                                    gotoImport2(false)
//                                }
//                            }
                        }
                    }else{
                        gotoImport2(true)
                    }

                }

            }
        }

    }

    private fun gotoImport2(privateKeyOrMemomic:Boolean){
        var intent = Intent(this@ImportActivity,Import2Activity::class.java)
        intent.putExtra("isFirstLoad",isFirstLoad)
        intent.putExtra("privateKeyOrMemomic",privateKeyOrMemomic)
        intent.putExtra("privateKey",viewBinding.inputEdit.text.toString())
        startActivity(intent)

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
