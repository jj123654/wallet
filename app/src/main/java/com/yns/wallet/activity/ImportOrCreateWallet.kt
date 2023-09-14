package com.yns.wallet.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.base.KeyValuePairVO
import com.yns.wallet.databinding.ActivityImportCreateBinding
import com.yns.wallet.util.EventBusUtil

class ImportOrCreateWallet : BaseActivity<ActivityImportCreateBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {


        viewBinding.apply {
            tvImport.setOnClickListener {
                var intent = Intent(this@ImportOrCreateWallet,ImportActivity::class.java)
                intent.putExtra("isFirstLoad",true)
                startActivity(intent)
            }
            tvCreate.setOnClickListener {
                startActivity(CreateActivity::class.java)
            }
        }

    }

    override fun isRegisterEventBus(): Boolean {
        return true
    }

    override fun onEventBusMsgReceived(keyValuePairVO: KeyValuePairVO?) {
        super.onEventBusMsgReceived(keyValuePairVO)
        if(keyValuePairVO?.key == EventBusUtil.CREATE_WALLET_SUCCESS){
            startActivity(MainActivity::class.java)
            finish()
        }
    }


}
