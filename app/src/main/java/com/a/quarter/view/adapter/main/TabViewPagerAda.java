package com.a.quarter.view.adapter.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 类的作用：
 * 类的思路：
 * 时间：2017/7/20
 * 作者：王亚迪
 */

public class TabViewPagerAda extends FragmentPagerAdapter {

    private ArrayList<Fragment> datas;
    //private String[] titles;
    private ArrayList<String> titles;

    public TabViewPagerAda(FragmentManager fm, ArrayList<Fragment> datas, ArrayList<String> titles) {
        super(fm);
        this.datas=datas;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    //tablayout和viewpager结合之后,创建底部title使用的方法
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
