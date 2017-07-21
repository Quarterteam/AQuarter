package com.a.quarter.view.fragment.video;

import android.support.v7.widget.RecyclerView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseFragment;

import butterknife.Bind;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class VHotFragment extends BaseFragment{
    @Bind(R.id.vhot_RecyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected int getContentViewId() {
        return R.layout.vhotfrag_video;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

}
