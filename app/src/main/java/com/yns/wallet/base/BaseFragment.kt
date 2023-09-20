package com.yns.wallet.base

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.yns.wallet.R
import com.yns.wallet.viewmodel.WalletViewModel
import com.yns.wallet.util.globalViewModel
import java.lang.Exception
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding> : DialogFragment() {

    private var vb: VB? = null
    val viewBinding: VB get() = vb!!
    protected val walletViewModel: WalletViewModel by globalViewModel()

    /**
     * 是否dialog
     */
    open val isDialog: Boolean = false

    open val fullScreenDialog: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showsDialog = isDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vb ?: findView(container).apply {
            initView(vb!!.root, savedInstanceState)
        }

        if (vb != null && vb!!.root != null) {
            (vb!!.root.parent as ViewGroup?)?.removeView(vb!!.root)

        }
        return vb!!.root

    }


    private fun findView(container: ViewGroup?): ViewBinding {
        //利用反射，调用指定ViewBinding中的inflate方法填充视图
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val method = clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        method.isAccessible = true
        vb = method.invoke(null, layoutInflater, container, false) as VB
        return vb!!
    }

    override fun onDestroy() {
        super.onDestroy()
        vb = null
    }


    abstract fun initView(root: View, savedInstanceState: Bundle?)


    fun startActivity(activity: Class<out Activity>) {
        startActivity(Intent(getActivity(), activity))
    }

    fun startActivityForResult(activity: Class<out Activity>, requestCode: Int) {
        startActivityForResult(Intent(getActivity(), activity), requestCode);
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (isDialog) {
            return AppCompatDialog(requireContext(), R.style.Dialog)
        }
        return super.onCreateDialog(savedInstanceState)
    }

    fun add(manager: FragmentManager, tag: String?) {
        try {
            val clazz = Fragment::class.java
            val f = clazz.getField("mShownByMe")
            f.set(this, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val ft = manager.beginTransaction()
        ft.setReorderingAllowed(true)
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }

    fun add(manager: FragmentManager) {
        add(manager, this.javaClass.name)
    }


    override fun onStart() {
        super.onStart()
        if (isDialog) {
            dialog?.window?.let {
                it.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            }
        }
    }


    /**
     * 移除自己
     */
    fun removeSelf() {
        if (isDialog) {
            dismissAllowingStateLoss()
        } else {
            fragmentManager?.beginTransaction()?.remove(this)?.commitAllowingStateLoss()
        }
    }
}