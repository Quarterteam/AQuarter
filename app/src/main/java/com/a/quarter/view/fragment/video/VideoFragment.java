package com.a.quarter.view.fragment.video;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseFragment;
import com.a.quarter.view.fragment.video.adapter.VideoFragVpAdapter;

import java.util.ArrayList;

import butterknife.Bind;

import static com.a.quarter.view.utils.TabUnderlineUtil.setIndicator;

/**
 * 视频
 */
public class VideoFragment extends BaseFragment {

    @Bind(R.id.video_TabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.video_ViewPager)
    ViewPager mViewPager;

    private ArrayList<String> mListTitle=new ArrayList<>();
    private ArrayList<Fragment> mListFrag=new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.frag_video;
    }

    @Override
    protected void initViews() {
        mListTitle.add("热门");
        mListTitle.add("附近");
        // TODO:  遍历 listtitle 集合 将title 添加经 TabLayou 中
        for (int i = 0; i < mListTitle.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mListTitle.get(i)));
        }

        //添加 Fragment
        mListFrag.add(new VHotFragment());
        mListFrag.add(new VVicinityFragment());

        VideoFragVpAdapter mAdapter = new VideoFragVpAdapter(getFragmentManager(), mListFrag, mListTitle);

        //给ViewPager设置适配器
       mViewPager.setAdapter(mAdapter);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
    }

    @Override
    protected void initDatas() {

    }
    @Override
    public void onStart() {
        super.onStart();
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mTabLayout, 60, 60);
            }
        });
    }
}
