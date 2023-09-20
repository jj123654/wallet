package com.yns.wallet.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.yns.wallet.R
import com.yns.wallet.viewmodel.WalletViewModel
import com.yns.wallet.util.*
import com.yns.wallet.widget.CommonCenterDialog
import org.greenrobot.eventbus.Subscribe
import java.lang.reflect.ParameterizedType


abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var viewBinding: VB
    protected val walletViewModel: WalletViewModel by globalViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!beforeSetContentView()) {
            // 1. 使内容区域全屏
//        WindowCompat.setDecorFitsSystemWindows(window, true)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            // 2. 设置 System bar 透明
            window.statusBarColor = Color.TRANSPARENT
        }

        AppManager.getAppManager().addActivity(this)
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        //利用反射，调用指定ViewBinding中的inflate方法填充视图
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments[0] as Class<VB>
                val method = clazz.getMethod("inflate", LayoutInflater::class.java)
                method.isAccessible = true
                viewBinding = method.invoke(null, layoutInflater) as VB
                setContentView(viewBinding.root)
                initView(viewBinding.root, savedInstanceState)
                initObserver()
            } catch (e: Exception) {
            }
        }
        adjustStatusBar()
    }

    abstract fun initView(root: View, savedInstanceState: Bundle?)

    //livedata监听
    open fun initObserver() {}

    private fun adjustStatusBar() {
        val statusBar = findViewById<View>(R.id.v_status_bar)
        if (statusBar != null) {
            statusBar.layoutParams.height = getStatusBarHeight(this)
        }
    }

    //部分页面有特殊需求的
    open fun beforeSetContentView(): Boolean {
        return false
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected open fun isRegisterEventBus(): Boolean {
        return false
    }

    @Subscribe
    open fun onEventBusReceive(keyValuePairVO: KeyValuePairVO?) {
        if (keyValuePairVO == null) {
            return
        }
        onEventBusMsgReceived(keyValuePairVO)
    }

    /**
     * 如果需要调用eventbus，则重写isRegisterEventBus返回true，并且重写该方法即可
     * eventBus的key建议写到EventBusUtils里
     *
     * @param keyValuePairVO
     */
    open fun onEventBusMsgReceived(keyValuePairVO: KeyValuePairVO?) {}

    override fun onDestroy() {
        super.onDestroy()
        AppManager.getAppManager().finishActivity(this);
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this)
        }
    }

    fun startActivity(activity: Class<out Activity>) {
        startActivity(Intent(this@BaseActivity, activity))
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LanguageUtils.attachBaseContext(newBase))
        //app杀进程启动后会调用Activity attachBaseContext
        //app杀进程启动后会调用Activity attachBaseContext
        LanguageUtils.getInstance().setConfiguration(newBase)
    }


    private var dialog: Dialog? = null
    fun showProgress() {
        if(dialog == null){
            dialog = Dialog(this,R.style.Dialog).apply {
                setContentView(
                    LayoutInflater.from(this@BaseActivity).inflate(R.layout.view_loading, null)
                )
                setCancelable(false)
                setCanceledOnTouchOutside(false)
            }
        }
        if(!dialog!!.isShowing){
            dialog!!.show()
        }
    }

    fun dismissProgress() {
        if(dialog!=null&&dialog!!.isShowing){
            dialog?.dismiss()
        }
    }

    fun showVerifyPasswordDialog(callback:(String) -> Unit){
        CommonCenterDialog(this@BaseActivity).showPswEditDialog{
            walletViewModel.checkPassword(it){result->
                if(result){
                    callback(it)
                }else{
                    showToast(getString(R.string.password_is_wrong))
                }
            }
        }

    }
}