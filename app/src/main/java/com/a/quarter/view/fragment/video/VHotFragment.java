package com.a.quarter.view.fragment.video;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseFragment;
import com.a.quarter.view.adapter.video.VHotFragmentAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import media.AndroidMediaController;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class VHotFragment extends BaseFragment{
    @Bind(R.id.vhot_RecyclerView)
    RecyclerView mRecyclerView;
    private ArrayList<Integer> list=new ArrayList<>();
    private AndroidMediaController mMediaController;

    @Override
    protected int getContentViewId() {
        return R.layout.vhotfrag_video;
    }

    @Override
    protected void initViews() {
        // RecycleView 使用的什么样的布局方式
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        // TODO: 添加集合
        for (int i = 0; i < 10; i++) {
           //int ic_launcher = R.mipmap.ic_launcher;
            list.add(R.mipmap.ic_launcher);
        }

        // TODO: 设置适配器
        VHotFragmentAdapter adapter = new VHotFragmentAdapter(getActivity(),list);
        mRecyclerView.setAdapter(adapter);// No adapter attached; skipping layout
         adapter.notifyDataSetChanged();
    }

    @Override
    protected void initDatas() {

    }

}
