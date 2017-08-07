package com.a.quarter.view.fragment.main;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a.quarter.R;
import com.a.quarter.app.App;
import com.a.quarter.model.bean.main.FollowListItemBean;
import com.a.quarter.model.bean.main.MyFollowResponse;
import com.a.quarter.presenter.main.MyFollowPresenter;
import com.a.quarter.view.adapter.main.FollowListAdapter;
import com.a.quarter.view.base.BaseFragment;
import com.a.quarter.view.base.LinearItemDecoration;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.rx.RxCallback;
import com.exa.framelib_rrm.utils.T;
import com.exa.framelib_rrm.utils.TimeUtils;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by acer on 2017/7/24.
 */
public class FollowListFragment extends BaseFragment<MyFollowPresenter, FollowListFragment.MyFollowCallback> {

    @Bind(R.id.rv)
    RecyclerView rv;
    private ArrayList<FollowListItemBean> list;
    private FollowListAdapter adapter;

    private String url;

    @Override
    protected int getContentViewId() {
        return R.layout.frag_follow_list;
    }

    @Override
    protected void initViews() {

        //初始化关注列表控件
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<FollowListItemBean>();
        LinearItemDecoration itemDecoration = new LinearItemDecoration(LinearItemDecoration.VERTICAL_LIST);
        itemDecoration.setDividerColor(Color.GRAY);
        itemDecoration.setDividerHeight(1);
        rv.addItemDecoration(itemDecoration);
        rv.setAdapter(adapter = new FollowListAdapter(getActivity(), list));

    }

    @Override
    protected void initDatas() {

//        bindPresenter(new MyFollowPresenter(), new MyFollowCallback(this, getActivity().getApplicationContext()));
//        mPresenter.getMyFollowList(App.getUser().userId);

        //设置关注列表数据
        for (int i = 0; i < 15; i++) {
            list.add(new FollowListItemBean("阿诺马斯丁"+i, "吃货们快戳我", TimeUtils.getStringTime(System.currentTimeMillis())));
        }
        adapter.notifyDataSetChanged();
    }


    static class MyFollowCallback extends RxCallback<MyFollowResponse, FollowListFragment, BaseTag>{

        public MyFollowCallback(FollowListFragment host, Context mContext) {
            super(host, mContext);
        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            if(((Integer)params[0])==-1){
                return "userId无效";
            }
            return null;
        }

        @Override
        public void onRequestStart(BaseTag tag) {

        }

        @Override
        protected void onDealNextResponse(MyFollowResponse response, BaseTag tag) {
            if("200".equals(response.code)){
                //TODO
            }else{
                T.showShort(mAppContext, "获取失败");
            }
        }

        @Override
        public void onRequestEnd(BaseTag tag) {

        }
    }

}
