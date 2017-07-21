package com.a.quarter.view.fragment.video;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 视频
 */
public class VideoFragment extends BaseFragment {

    @Bind(R.id.video_TabLayout)
    TabLayout videoTabLayout;
    @Bind(R.id.video_FrameLayout)
    FrameLayout videoFrameLayout;

    @Override
    protected int getContentViewId() {
        return R.layout.frag_video;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.video_TabLayout)
    public void onClick() {
    }
}
