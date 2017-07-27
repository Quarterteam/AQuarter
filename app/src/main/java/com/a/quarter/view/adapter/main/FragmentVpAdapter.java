package com.a.quarter.view.adapter.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * 我的关注ViewPager的适配器，结合TabLayout时使用
 */

public class FragmentVpAdapter extends FragmentStatePagerAdapter{

    private  ArrayList<Fragment> mListFrag;
    private ArrayList<String> mListTitle;

    public FragmentVpAdapter(FragmentManager fm, ArrayList<Fragment> mListFrag, ArrayList<String> mListTitle) {
        super(fm);
        this.mListFrag = mListFrag;
        this.mListTitle = mListTitle;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListTitle.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mListFrag.get(position);
    }

    @Override
    public int getCount() {
        return mListFrag.size();
    }

}
