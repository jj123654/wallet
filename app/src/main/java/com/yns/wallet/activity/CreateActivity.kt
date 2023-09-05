package com.yns.wallet.activity

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.yns.wallet.R
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.databinding.ActivityCreateBinding
import com.yns.wallet.fragment.CreateWalletFragment

class CreateActivity : BaseActivity<ActivityCreateBinding>() {

    override fun initView(root: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val bundle = bundleOf("index" to 0)
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CreateWalletFragment>(R.id.fl_content, args = bundle)
            }
        }
    }


}
