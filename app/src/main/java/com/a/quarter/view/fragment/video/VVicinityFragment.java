package com.a.quarter.view.fragment.video;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a.quarter.R;
import com.a.quarter.view.adapter.video.VVicinityFragmentAdapter;
import com.a.quarter.view.base.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class VVicinityFragment extends BaseFragment {
    @Bind(R.id.vvicinity_RecyclerView)
    RecyclerView mRecyclerView;
    private ArrayList<Integer> list=new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.vvicinityfrag_video;
    }

    @Override
    protected void initViews() {
        // RecycleView 使用的什么样的布局方式
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        // TODO: 添加集合
        for (int i = 0; i < 10; i++) {

            list.add(R.mipmap.ic_launcher);
        }

        // TODO: 设置适配器
        VVicinityFragmentAdapter adapter = new VVicinityFragmentAdapter(getActivity(),list);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initDatas() {

    }
}
