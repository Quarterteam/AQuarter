package com.a.quarter.view.fragment.recommend;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.a.quarter.R;
import com.a.quarter.model.bean.recommend.ItemBean;
import com.a.quarter.view.adapter.recommend.HotAdapter;
import com.a.quarter.view.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;

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
    @Bind(R.id.scroller)
    ScrollView scroller;
    private float scaleY;

    @Override
    protected int getContentViewId() {
        return R.layout.frag_hot;
    }

    @Override
    protected void initViews() {

    }

    @Override
    public void onResume() {
        super.onResume();
//        viewpager.setFocusable(true);
    scroller.smoothScrollTo(0,0);
    }

    @Override
    protected void initDatas() {
        recyclerview.setLayoutManager(new LinearLayoutManager(mActivity));

        HotAdapter hotAdapter = new HotAdapter(mActivity);
        recyclerview.setAdapter(hotAdapter);

        ArrayList<ItemBean> strings = new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            strings.add(new ItemBean("2017-6-01 12:45",true));
        }

        hotAdapter.setData(strings);
        hotAdapter.notifyDataSetChanged();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden){
            scaleY = scroller.getScaleY();

            Log.i("hidden",scaleY+"");
        }else {
            scroller.smoothScrollTo(0, (int) scaleY);
        }
    }
}
