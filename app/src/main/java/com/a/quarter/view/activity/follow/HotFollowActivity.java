package com.a.quarter.view.activity.follow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;
import com.a.quarter.view.fragment.RecommandFragment;
import com.exa.framelib_rrm.utils.FragmentUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HotFollowActivity extends BaseActivity {

    @Bind(R.id.activity_framlayout)
    FrameLayout activityFramlayout;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_hot_follow;
    }

    @Override
    protected void initViews() {

        FragmentManager manager = getSupportFragmentManager();
        ArrayList<Fragment> fragments = new ArrayList<>();
        RecommandFragment rFragment = new RecommandFragment();
        fragments.add(rFragment);
        FragmentUtils.setDefaultFragment(manager,R.id.activity_framlayout,fragments);

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
