package com.yns.wallet

import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

fun adjustStatusBarMargin(view: View) {
    val params = view.layoutParams as ConstraintLayout.LayoutParams
    params.topMargin = getStatusBarHeight(view.context) + params.topMargin
}

fun getStatusBarHeight(context: Context): Int {
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        return context.resources.getDimensionPixelSize(resourceId)
    }
    return 0
}

fun addEyeViewLogic(view: View, eyeViewId: Int, pwdViewId: Int, src: String) {
    val tvBalanceView = view.findViewById<TextView>(eyeViewId)
    val eyeView = view.findViewById<ImageView>(pwdViewId)
    eyeView.setOnClickListener {
        if (eyeView.isSelected) {
            tvBalanceView.text = "***"
        } else {
            tvBalanceView.text = src
        }
        eyeView.isSelected = !eyeView.isSelected
    }
}

fun addEditEyeViewLogic(view: View, eyeViewId: Int, pwdViewId: Int) {
    val etView = view.findViewById<EditText>(pwdViewId)
    val eyeView = view.findViewById<ImageView>(eyeViewId)
    eyeView.setOnClickListener {
        if (eyeView.isSelected) {
            etView.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            etView.inputType = InputType.TYPE_CLASS_TEXT
        }
        eyeView.isSelected = !eyeView.isSelected
    }
}

fun showDevToast(context: Context) {
    Toast.makeText(context, "开发中", Toast.LENGTH_SHORT).show()
}


open class EditTextWatch(editText: EditText) {

    private var text: String = ""
    fun getSrcText() = text

    init {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (text.contains("**")) {
                    return;
                }
                text = s.toString()
            }
        })
    }
}
