package com.yns.wallet.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB:ViewBinding>: Fragment() {

    private var vb: VB?=null
    val viewBinding :VB get() = vb!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vb?:findView(container).apply {
            initView(vb!!.root, savedInstanceState)
        }

        if(vb!=null&&vb!!.root!=null){
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
}