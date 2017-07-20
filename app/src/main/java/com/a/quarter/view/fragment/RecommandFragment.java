package com.a.quarter.view.fragment;

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

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class RecommandFragment extends BaseFragment {

    @Bind(R.id.recommand_frgament_tablayout)
    TabLayout recommandFrgamentTablayout;
    @Bind(R.id.recommad_framlayout)
    FrameLayout recommadFramlayout;

    @Override
    protected int getContentViewId() {
        return R.layout.recommand_fragment;
    }

    @Override
    protected void initViews() {
        recommandFrgamentTablayout.addTab(recommandFrgamentTablayout.newTab().setText("热门"));
        recommandFrgamentTablayout.addTab(recommandFrgamentTablayout.newTab().setText("关注"));

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

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment like bottom ... and run LayoutCreator again
//        View view = View.inflate(getActivity(), R.layout.frag_layout, null);
//        return view;
//    }
}
