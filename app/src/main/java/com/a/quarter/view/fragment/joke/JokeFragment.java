package com.a.quarter.view.fragment.joke;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a.quarter.R;
import com.a.quarter.view.adapter.joke.JokeAdapter;
import com.a.quarter.view.base.BaseFragment;

import butterknife.Bind;

/**
 * 段子
 */
public class JokeFragment extends BaseFragment{
    @Bind(R.id.joke_recycler)
    RecyclerView mRecyclerView;
    private JokeAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected int getContentViewId() {
        return R.layout.frag_joke;
    }

    @Override
    protected void initViews() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new JokeAdapter(getActivity());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {

    }

}
