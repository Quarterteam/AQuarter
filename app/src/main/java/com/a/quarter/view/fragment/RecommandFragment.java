package com.a.quarter.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseFragment;
import com.exa.framelib_rrm.app.App;
import com.exa.framelib_rrm.utils.FragmentUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class RecommandFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    @Bind(R.id.recommand_frgament_tablayout)
    TabLayout rFrgamentTablayout;
    @Bind(R.id.recommad_framlayout)
    FrameLayout rFramlayout;
    private HotofRecommandFragment hotFragment;
    private FocuseofRecommandFragment focuseFragment;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<Fragment> hideFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_recommand;
    }

    @Override
    protected void initViews() {
        rFrgamentTablayout.addTab(rFrgamentTablayout.newTab().setText("热门"));
        rFrgamentTablayout.addTab(rFrgamentTablayout.newTab().setText("关注"));

        rFrgamentTablayout.setOnTabSelectedListener(this);

        setDefaultFragment();


    }

    private void setDefaultFragment() {
        fragmentList = new ArrayList<>();
        hideFragment = new ArrayList<>();

        hotFragment = new HotofRecommandFragment();
        focuseFragment = new FocuseofRecommandFragment();
        fragmentList.add(hotFragment);
        fragmentList.add(focuseFragment);

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentUtils.setDefaultFragment(manager,R.id.recommad_framlayout, fragmentList);

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

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
if (tab.getText().equals("关注")){
    FragmentManager manager = getActivity().getSupportFragmentManager();

    hideFragment.clear();
    hideFragment.add(hotFragment);
    FragmentUtils.hideAndShow(manager,hideFragment,focuseFragment);


}else {
    FragmentManager manager = getActivity().getSupportFragmentManager();

    hideFragment.clear();
    hideFragment.add(focuseFragment);
    FragmentUtils.hideAndShow(manager,hideFragment,hotFragment);
}
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
