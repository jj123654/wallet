package com.yns.wallet.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.imageLoader
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.luck.picture.lib.utils.ToastUtils
import com.yns.wallet.R
import com.yns.wallet.activity.MainActivity
import com.yns.wallet.base.BaseApplication
import java.text.SimpleDateFormat
import java.util.*

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


/**
 * 扩展onClick方法，防止双击
 * @param onClickListener 监听器
 * @param doubleClickInterval 防止双击的间隔毫秒，默认1秒，小于0则不检测双击
 */
fun View.onClick(doubleClickInterval: Long = 1000L, onClickListener: View.OnClickListener?) {
    if (onClickListener == null) {
        setOnClickListener(null)
        return
    }
    if (doubleClickInterval <= 0) {
        setOnClickListener(onClickListener)
        return
    }
    setOnClickListener {
        //取出上一次点击的时间戳
        val lastClick = this.getTag(R.id.no_double_click) as? Long ?: 0
        if (System.currentTimeMillis() - lastClick <= doubleClickInterval) {
            return@setOnClickListener
        }
        //记录本次点击的时间戳
        this.setTag(R.id.no_double_click, System.currentTimeMillis())
        onClickListener.onClick(it)
    }

}

/**
 * 显示toast ，在中间
 */
fun showToast(toast: CharSequence?) {
    toast ?: return
    Toast.makeText(BaseApplication.globalContext(), toast, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.CENTER, 0, 0)
    }.show()
}

fun restartApp(context: Context) {
    val LaunchIntent = Intent(context, MainActivity::class.java)
    LaunchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    context.startActivity(LaunchIntent)
}


fun String.copyToClipboard(context: Context) {
    val clip = context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
    clip?.let {
        it.setPrimaryClip(ClipData.newPlainText("copy", this))
        ToastUtils.showToast(context, context.getString(R.string.copy_success))
    }
}


inline fun <reified T : ViewModel> globalViewModel(): Lazy<T> {
    return lazy {
        ViewModelProvider(BaseApplication.globalContext()).get(T::class.java)
    }
}

/**
 * 加载glide
 */
@JvmOverloads
fun ImageView.loadUrl(
    url: Any?,
    defaultIcon: Int = -1, errorIcon: Int = defaultIcon,
    vararg transform: Transformation
) {
    val u = when (url) {
        is Int -> {
            Uri.parse("android.resource://${context.packageName}/$url")
        }

        else -> {
            url
        }
    }
    this.load(u, this.context.imageLoader) {
        this.crossfade(true)
        if (transform.isNotEmpty()) {
            transformations(*transform)
        }
        if (defaultIcon != -1) {
            placeholder(defaultIcon)
        }
        if (errorIcon != -1) {
            error(defaultIcon)
        }
    }
}


/**
 * 加载圆形
 */
fun ImageView.loadCircle(url: String?, defaultIcon: Int = -1) {
    loadUrl(url, defaultIcon, transform = arrayOf(CircleCropTransformation()))
}

/**
 * 加载圆角
 */
fun ImageView.loadRoundCorner(url: String?, defaultIcon: Int = -1, radius: Float) {
    loadUrl(
        url,
        defaultIcon,
        transform = arrayOf(CircleCropTransformation(), RoundedCornersTransformation(radius))
    )
}

fun formatTimeString(time:Long):String{
    val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    val date = Date(time)

    return dateFormat.format(date)
}

fun getNowTime():String =
    SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date())