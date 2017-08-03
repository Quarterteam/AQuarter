package com.a.quarter.view.adapter.mywork;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class VgAdapter extends FragmentPagerAdapter {

    Context context;
    ArrayList<Fragment> list=new ArrayList<>();
    ArrayList<String> titleList=new ArrayList<>();

    public VgAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
    }

    public void setFragData(ArrayList<Fragment> fragDatas){
        if (fragDatas!=null){
            list.addAll(fragDatas);
        }
    }
    public void setTitleData(ArrayList<String> titleDatas){
        if (titleDatas!=null){
            titleList.addAll(titleDatas);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("fragment",list.get(position).getTag()+"----");
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

}
