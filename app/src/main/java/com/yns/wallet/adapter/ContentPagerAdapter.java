package com.yns.wallet.adapter;

import android.annotation.SuppressLint;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ContentPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> tabFragments;
    List<String>tabIndicators;

    public ContentPagerAdapter(FragmentManager fm, List<Fragment> tabFragments) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabFragments = tabFragments;
    }

    public ContentPagerAdapter(FragmentManager fm, List<Fragment> tabFragments,List<String>tabIndicators) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabFragments = tabFragments;
        this.tabIndicators = tabIndicators;
    }

    @Override
    public Fragment getItem(int position) {
        return tabFragments.get(position);
    }

    @Override
    public int getCount() {
        return tabFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(tabIndicators==null||tabIndicators.size()<=0){
            return null;
        }
        return tabIndicators.get(position);
    }
}

