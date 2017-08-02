package com.a.quarter.view.fragment.joke;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.a.quarter.R;
import com.a.quarter.model.bean.joke.JokeBean;
import com.a.quarter.model.bean.joke.JokeItemBean;
import com.a.quarter.presenter.joke.JokePresenter;
import com.a.quarter.view.adapter.joke.JokeAdapter;
import com.a.quarter.view.base.BaseFragment;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.rx.RxCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 段子
 */
public class JokeFragment extends BaseFragment <JokePresenter,JokeFragment.JokeCallBack>{
    @Bind(R.id.joke_recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.iv_noNet)
    ImageView imageView;

    private JokeAdapter adapter;
    private List<JokeItemBean> list=new ArrayList<>();
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
        bindPresenter(new JokePresenter(),new JokeCallBack(JokeFragment.this,getActivity()));
        mPresenter.getDataFrom();
    }

   static  class JokeCallBack extends RxCallback<JokeBean,JokeFragment,BaseTag> {

        public JokeCallBack(JokeFragment host, Context mContext) {
            super(host, mContext);
        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            return null;
        }

        @Override
        public void onRequestStart(BaseTag tag) {
            if (getHost().list.size()>0){
                getHost().imageView.setVisibility(View.VISIBLE);
            }else{
                getHost().imageView.setVisibility(View.GONE);
            }
        }

        @Override
        public void onRequestEnd(BaseTag tag) {
        }

        @Override
        protected void onDealNextResponse(JokeBean response, BaseTag tag) {

            for (int i = 0; i <response.getCharacter().size() ; i++) {
                JokeItemBean jokeItemBean = new JokeItemBean(false,false,false,response.getCharacter().get(i));
                getHost().list.add(jokeItemBean);
            }
            getHost().adapter.setData(getHost().list);
            getHost().adapter.notifyDataSetChanged();
        }
    }
}
