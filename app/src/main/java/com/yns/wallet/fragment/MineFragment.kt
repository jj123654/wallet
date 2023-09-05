package com.yns.wallet.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yns.wallet.activity.AboutUsActivity
import com.yns.wallet.R
import com.yns.wallet.base.BaseFragment
import com.yns.wallet.databinding.FragmentMineBinding
import com.yns.wallet.util.getStatusBarHeight

class MineFragment : BaseFragment<FragmentMineBinding>() {

    override fun initView(view: View, savedInstanceState: Bundle?) {
        val statusBar = view.findViewById<View>(R.id.v_status_bar)
        if (statusBar != null) {
            statusBar.layoutParams.height = getStatusBarHeight(view.context)
        }
        view.findViewById<View>(R.id.v_about_bg).setOnClickListener {
            startActivity(Intent(view.context, AboutUsActivity::class.java))
        }
    }

}
