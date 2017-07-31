package com.a.quarter.view.fragment.msginform;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a.quarter.R;
import com.a.quarter.view.adapter.msginform.MsgListAdapter;
import com.a.quarter.view.base.BaseFragment;

import butterknife.Bind;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/27.
 */

public class MsgFragment extends BaseFragment {
    @Bind(R.id.msg_inform_recycler)
    RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MsgListAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.frag_msg;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new MsgListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }
}
