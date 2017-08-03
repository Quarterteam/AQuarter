package com.a.quarter.view.fragment.slidingmenu;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.a.quarter.R;
import com.a.quarter.view.adapter.mywork.VgAdapter;
import com.a.quarter.view.base.BaseFragment;
import com.a.quarter.view.fragment.mywork.LoadedFragment;
import com.a.quarter.view.fragment.mywork.LocalFragment;

import java.util.ArrayList;

import butterknife.Bind;

import static com.a.quarter.utils.TabUnderlineUtil.setIndicator;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class MyWorkFragment extends BaseFragment {

    @Bind(R.id.mywork_tablayout)
    TabLayout tablayout;
    @Bind(R.id.mywork_vg)
    ViewPager vg;
    private LocalFragment localFragment;
    private LoadedFragment loadedFragment;
    private ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<String> titleList = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.frag_mywork;
    }

    @Override
    protected void initViews() {

        titleList.add("本地作品");
        titleList.add("已上传");

        localFragment = new LocalFragment();
        loadedFragment = new LoadedFragment();
        list.add(localFragment);
        list.add(loadedFragment);

        VgAdapter adapter = new VgAdapter(
                getFragmentManager(),mActivity);
        vg.setAdapter(adapter);


        //为适配器添加数据
        adapter.setFragData(list);
        adapter.setTitleData(titleList);
        //刷新适配器
        adapter.notifyDataSetChanged();

       //将tablayout和viewpager连接起来
        tablayout.setupWithViewPager(vg);

       //tablayout的监听
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("本地作品")) {
                    vg.setCurrentItem(0, false);
                } else {
                    vg.setCurrentItem(1, false);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void initDatas() {

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

}

