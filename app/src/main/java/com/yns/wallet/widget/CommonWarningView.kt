package com.yns.wallet.widget

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.yns.wallet.R

class CommonWarningView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = -1
) : FrameLayout(context, attrs, defStyleAttr) {
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.item_title, null, false)
        addView(view)
        val array = context.obtainStyledAttributes(attrs, R.styleable.CommonWarningView)
//        val titleText = array.getString(R.styleable.CommonWarningView_title_text)
//        val contentText = array.getString(R.styleable.CommonWarningView_content_text)
//        val buttonText = array.getString(R.styleable.CommonWarningView_button_text)
//        val title = findViewById<TextView>(R.id.tv_pop_title)
//        val content = findViewById<TextView>(R.id.tv_pop_content)
//        val button = findViewById<TextView>(R.id.tv_view_mnemonic)
//
//        button.setOnClickListener {
//            view.visibility = if(view.visibility==View.VISIBLE) View.GONE else View.VISIBLE
//        }
//        if (!TextUtils.isEmpty(titleText)) {
//            title.text = titleText
//        }
//        if (!TextUtils.isEmpty(contentText)) {
//            content.text = contentText
//        }
//        if (!TextUtils.isEmpty(buttonText)) {
//            button.text = buttonText
//        }

        array.recycle()
    }

    fun setTitle(title:String){
        val titleView = findViewById<TextView>(R.id.tv_title)
        titleView.text = title
    }

    fun setRightImg(rightIconId:Int){
        val imgRight = findViewById<ImageView>(R.id.img_right)
        imgRight.visibility = View.VISIBLE
        imgRight.setImageResource(rightIconId)
    }
}