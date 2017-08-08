package com.a.quarter.view.fragment.recommend;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseFragment;

import butterknife.Bind;

import static com.a.quarter.utils.TabUnderlineUtil.setIndicator;

/**
 * 推荐
 */
public class RecommendFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    @Bind(R.id.recommand_frgament_tablayout)
    TabLayout tablayout;
    @Bind(R.id.recommad_framlayout)
    FrameLayout framlayout;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private HotFragment hotFragment;
    private FocuseFragment focuseFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.frag_recommend;
    }

    @Override
    protected void initViews() {
        manager = getActivity().getSupportFragmentManager();

        tablayout.addTab(tablayout.newTab().setText("热门"));
        tablayout.addTab(tablayout.newTab().setText("关注"));

        FragmentTransaction transaction = manager.beginTransaction();
        if (hotFragment == null) {
            hotFragment = new HotFragment();
        }
        transaction.add(R.id.recommad_framlayout, hotFragment);
        transaction.commit();
        tablayout.getTabAt(0).select();

        tablayout.setOnTabSelectedListener(this);

    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        transaction = manager.beginTransaction();
        if (tab.getText().equals("热门")) {
            showFrag(0);
            hideFrag(1);
        } else {
            showFrag(1);
            hideFrag(0);
        }

        transaction.commit();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    private void showFrag(int i) {
        switch (i) {
            case 0:
                if (hotFragment == null) {
                    hotFragment = new HotFragment();
                    transaction.add(R.id.recommad_framlayout, hotFragment, "hotFragment");
                }
                transaction.show(hotFragment);
                break;
            case 1:
                if (focuseFragment == null) {
                    focuseFragment = new FocuseFragment();
                    transaction.add(R.id.recommad_framlayout, focuseFragment, "focuseFragment");
                }
                transaction.show(focuseFragment);
                break;
            default:
                break;
        }
    }

    private void hideFrag(int i) {
        switch (i) {
            case 0:
                if (hotFragment != null) {
                    transaction.hide(hotFragment);
                }
                break;
            case 1:
                if (focuseFragment != null) {
                    transaction.hide(focuseFragment);
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        tablayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tablayout, 60, 60);
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        //如果本Fragment的显示隐藏状态切换了，并且内部的hotFragment正处于显示状态的话，
        //把Fragment的状态变化传递给内部的hotFragment，
        //当切换到的是隐藏状态时，暂停内部hotFragment正在播放的视频或轮播图
        //当切换到的是显示状态时，重新开始播放内部hotFragment上的轮播图
        if(hotFragment!=null && !hotFragment.isHidden()){
            hotFragment.onHiddenChanged(hidden);
        }

        //原理同上
        if(focuseFragment!=null && !focuseFragment.isHidden()){
            focuseFragment.onHiddenChanged(hidden);
        }
        super.onHiddenChanged(hidden);
    }

}
