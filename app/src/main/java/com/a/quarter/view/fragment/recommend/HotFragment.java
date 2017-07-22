package com.a.quarter.view.fragment.recommend;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class HotFragment extends BaseFragment {
    @Bind(R.id.recommand_fragment_viewpager)
    ViewPager viewpager;
    @Bind(R.id.recommand_fragment_linearlayout)
    LinearLayout linearlayout;
    @Bind(R.id.recommand_fragemnt_recyclerview)
    RecyclerView recyclerview;

    
    @Override
    protected int getContentViewId() {
        return R.layout.frag_hot;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

}
