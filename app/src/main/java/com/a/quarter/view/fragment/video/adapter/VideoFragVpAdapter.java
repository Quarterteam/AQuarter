package com.a.quarter.view.fragment.video.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class VideoFragVpAdapter extends FragmentStatePagerAdapter{
    private  ArrayList<Fragment> mListFrag;
    private ArrayList<String> mListTitle;

    public VideoFragVpAdapter(FragmentManager fm, ArrayList<Fragment> mListFrag, ArrayList<String> mListTitle) {
        super(fm);
        this.mListFrag = mListFrag;
        this.mListTitle = mListTitle;
    }

  //  CharSequence
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
