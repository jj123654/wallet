package com.yns.wallet.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yns.wallet.AboutUsActivity
import com.yns.wallet.R
import com.yns.wallet.getStatusBarHeight

class MineFragment : Fragment(R.layout.fragment_mine) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adjustStatusBar(view)
        initView(view)
    }

    private fun initView(view: View) {
        view.findViewById<View>(R.id.v_about_bg).setOnClickListener {
            startActivity(Intent(view.context, AboutUsActivity::class.java))
        }
    }

    private fun adjustStatusBar(view: View) {
        val statusBar = view.findViewById<View>(R.id.v_status_bar)
        if (statusBar != null) {
            statusBar.layoutParams.height = getStatusBarHeight(view.context)
        }
    }
}
