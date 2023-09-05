package com.yns.wallet.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yns.wallet.R
import com.yns.wallet.getStatusBarHeight

class MineFragment : Fragment(R.layout.fragment_mine) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adjustStatusBar(view)
    }

    private fun adjustStatusBar(view: View) {
        val statusBar = view.findViewById<View>(R.id.v_status_bar)
        if (statusBar != null) {
            statusBar.layoutParams.height = getStatusBarHeight(view.context)
        }
    }
}
