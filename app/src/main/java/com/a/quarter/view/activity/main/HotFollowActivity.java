package com.a.quarter.view.activity.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.utils.TabLayoutUtils;
import com.a.quarter.view.base.BaseActivity;
import com.a.quarter.view.fragment.main.FollowListFragment;
import com.exa.framelib_rrm.utils.T;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class HotFollowActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.vp)
    ViewPager mViewPager;
    @Bind(R.id.iv_toolbar)
    ImageView ivToolbar;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_hot_follow;
    }

    @Override
    protected void initViews() {
        //设置标题
        tvHead.setText("热门关注");
        //设置搜索图标
        ivToolbar.setImageResource(R.mipmap.search_friend);
        ivToolbar.setBackgroundResource(R.drawable.selector_bg_transparent_graytranslucent);
        ivToolbar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initDatas() {
        setUpTabVp();
    }

    //初始化TabLayout和ViewPager
    private void setUpTabVp() {
        //初始化title数据
        ArrayList<String> mTitleList = new ArrayList<String>();
        mTitleList.add("新闻");
        mTitleList.add("爆笑");
        mTitleList.add("感人");
        mTitleList.add("美食");
        mTitleList.add("网红");
        mTitleList.add("颜值");

        //添加 Fragment
        ArrayList<Fragment> mFragList = new ArrayList<Fragment>();
        for (int i = 0; i < mTitleList.size(); i++) {
            mFragList.add(new FollowListFragment());
        }

        //关联TabLayout和Viewpager
        TabLayoutUtils.setAda(mTabLayout, mViewPager, getSupportFragmentManager(), mFragList, mTitleList);
        //设置tab下划线长度
        TabLayoutUtils.setIndicator(mTabLayout, 0);
    }

    @OnClick({R.id.iv_back, R.id.iv_toolbar})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_toolbar:
                T.showShort(getApplicationContext(), "搜索");
                break;
            default:
                break;
        }
    }

}
