package com.yns.wallet

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.yns.wallet.fragment.CreateWalletFragment

class CreateActivity : BaseActivity() {

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_create)

        if (savedInstanceState == null) {
            val bundle = bundleOf("index" to 0)
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CreateWalletFragment>(R.id.fl_content, args = bundle)
            }
        }
    }

}
