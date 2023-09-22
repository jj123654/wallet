package com.yns.wallet.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.load
import com.qmuiteam.qmui.kotlin.onClick
import com.yns.wallet.R
import com.yns.wallet.adapter.ContentPagerAdapter
import com.yns.wallet.base.BaseActivity
import com.yns.wallet.bean.TokenModel
import com.yns.wallet.bean.TokenTransferTransactionModel
import com.yns.wallet.databinding.ActivityAboutUsBinding
import com.yns.wallet.databinding.ActivityAssetsBinding
import com.yns.wallet.fragment.AssetsFragment
import com.yns.wallet.fragment.TransactionRecordFragment
import com.yns.wallet.util.onClick
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

class AssetsActivity : BaseActivity<ActivityAssetsBinding>() {

    var tokenModel: TokenModel?=null

    private val tabFragments: MutableList<Fragment> = ArrayList()

    private var tabList: MutableList<String> = mutableListOf()

    override fun initView(root: View, savedInstanceState: Bundle?) {
        initIndicator()
    }

    private fun initIndicator() {
        tokenModel = intent.getParcelableExtra("tokenModel")
        viewBinding.apply {
            tokenModel?.name?.let { titleBar.setTitle(it) }

            priceTv.text = "$ ${tokenModel?.usdPrice}"
            iconImg.load(tokenModel?.imageUrl){
                placeholder(R.mipmap.account_default_photo)
            }
            balanceTv.text = tokenModel?.balance.toString()
            balance2Tv.text = "${tokenModel?.trxAmount} TRX"
            balance3Tv.text = "$ ${tokenModel?.usd}"

            hideSmallTransactionLayout.onClick{
                checkRb.isSelected = !checkRb.isSelected
            }
        }
        tabList.add(getString(R.string.all))
        tabList.add(getString(R.string.transfer))
        tabList.add(getString(R.string.receive))
        for(i in 0 until tabList.size) {
            tabFragments.add(AssetsFragment.newInstance(i))
        }
        viewBinding.viewPager.adapter =
            ContentPagerAdapter(
                supportFragmentManager,
                tabFragments
            )

        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = false
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return tabList.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView = SimplePagerTitleView(context)
                simplePagerTitleView.text = tabList[index]
                simplePagerTitleView.normalColor =
                    resources.getColor(R.color.transaction_blue_tv_color)
                simplePagerTitleView.selectedColor =
                    resources.getColor(R.color.transaction_blue_color)
                simplePagerTitleView.setOnClickListener {
                    viewBinding.viewPager.currentItem = index
                }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                indicator.lineHeight =
                    UIUtil.dip2px(context, 3.0).toFloat()
                indicator.roundRadius =
                    UIUtil.dip2px(context, 5.0).toFloat()
                indicator.setColors(resources.getColor(R.color.transaction_blue_color))
                return indicator
            }
        }
        viewBinding.magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(viewBinding.magicIndicator, viewBinding.viewPager)
        viewBinding.hideSmallTransactionLayout.onClick {
            it.isSelected = !it.isSelected
        }
    }

}
