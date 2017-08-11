package com.a.quarter.view.fragment.main;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a.quarter.R;
import com.a.quarter.model.bean.main.FollowListItemBean;
import com.a.quarter.model.bean.main.MyFollowResponse;
import com.a.quarter.presenter.main.FollowPresenter;
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
public class FollowListFragment extends BaseFragment<FollowPresenter, FollowListFragment.HotFollowCallback> {

    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.srl)
    SwipeRefreshLayout srl;
    private ArrayList<FollowListItemBean> list;
    private FollowListAdapter adapter;

    private String url;
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;

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

        srl.setColorSchemeResources(R.color.mainColor, R.color.mainAdd);
        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                if (App.isLogin()) {
//                    mPresenter.getMyFollowList(App.getUser().userId);
//                } else {
//                    T.showShort(getActivity(), "未登录！");
//                    srl.setRefreshing(false);
//                }

                //SystemClock.sleep(2000);
                srl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //设置关注列表数据
                        for (int i = 0; i < 5; i++) {
                            list.add(new FollowListItemBean("阿诺马斯丁" + i, "吃货们快戳我", TimeUtils.getStringTime(System.currentTimeMillis())));
                        }
                        adapter.notifyDataSetChanged();
                        srl.setRefreshing(false);
                    }
                }, 2000);
            }
        };
        srl.setOnRefreshListener(onRefreshListener);
    }

    @Override
    protected void initDatas() {

//        //设置关注列表数据
//        for (int i = 0; i < 15; i++) {
//            list.add(new FollowListItemBean("阿诺马斯丁" + i, "吃货们快戳我", TimeUtils.getStringTime(System.currentTimeMillis())));
//        }
//        adapter.notifyDataSetChanged();


//        bindPresenter(new MyFollowPresenter(), new MyFollowCallback(this, getActivity().getApplicationContext()));
//        mPresenter.getMyFollowList(App.getUser().userId);
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(true);
                onRefreshListener.onRefresh();
            }
        });
    }

    static class HotFollowCallback extends RxCallback<MyFollowResponse, FollowListFragment, BaseTag> {

        public HotFollowCallback(FollowListFragment host, Context mContext) {
            super(host, mContext);
        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            if (((Integer) params[0]) == -1) {
                return "userId无效";
            }
            return null;
        }

        @Override
        public void onRequestStart(BaseTag tag) {

        }

        @Override
        protected void onDealNextResponse(MyFollowResponse response, BaseTag tag) {
            if ("200".equals(response.code)) {
                //TODO
            } else {
                T.showShort(mAppContext, "获取失败");
            }
        }

        @Override
        public void onRequestEnd(BaseTag tag) {

        }
    }

}
