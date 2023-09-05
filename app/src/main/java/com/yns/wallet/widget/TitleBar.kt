package com.yns.wallet.widget

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.yns.wallet.R

class TitleBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = -1
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.item_title, null, false)
        addView(view)
        val array = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        val iconId = array.getInteger(R.styleable.TitleBar_bar_icon, -1)
        val iconView = findViewById<ImageView>(R.id.iv_back)

        iconView.setOnClickListener {
            if (context is Activity) {
                context.finish()
            }
        }
        if (iconId != -1) {
            iconView.setImageResource(iconId)
        }
        val string = array.getString(R.styleable.TitleBar_bar_title)
        if (!TextUtils.isEmpty(string)) {
            val titleView = findViewById<TextView>(R.id.tv_title)
            titleView.text = string
        }
        array.recycle()
    }
}