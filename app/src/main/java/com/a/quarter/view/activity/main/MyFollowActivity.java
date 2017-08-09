package com.a.quarter.view.activity.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.app.App;
import com.a.quarter.model.bean.login.User;
import com.a.quarter.model.bean.main.FollowListItemBean;
import com.a.quarter.model.bean.main.MyFollowResponse;
import com.a.quarter.presenter.main.MyFollowPresenter;
import com.a.quarter.view.adapter.main.FollowListAdapter;
import com.a.quarter.view.base.BaseActivity;
import com.a.quarter.view.base.LinearItemDecoration;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.rx.RxCallback;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.T;
import com.exa.framelib_rrm.utils.TimeUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的关注页面
 */
public class MyFollowActivity extends BaseActivity<MyFollowPresenter, MyFollowActivity.MyFollowCallback> implements View.OnClickListener {

    //    @Bind(R.id.iv_back)
//    ImageView ivBack;
    @Bind(R.id.tv_head)
    TextView tvHead;
    //    @Bind(R.id.tabLayout)
//    TabLayout mTabLayout;
//    @Bind(R.id.vp)
//    ViewPager mViewPager;
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.srl)
    SwipeRefreshLayout srl;
    @Bind(R.id.tv_empty_view)
    TextView tvEmptyView;
    private ArrayList<FollowListItemBean> list;
    private FollowListAdapter adapter;
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_follow;
    }

    @Override
    protected void initViews() {
        //设置标题
        tvHead.setText("我的关注");

//        //初始化TabLayout和ViewPager
//        ArrayList<String> mTitleList = new ArrayList<String>();
//        mTitleList.add("全部");
//        mTitleList.add("爆笑");
//        mTitleList.add("感人");
//        mTitleList.add("美食");
//        mTitleList.add("网红");
//        mTitleList.add("颜值");
//        for (int i = 0; i < mTitleList.size(); i++) {
//            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(i)));
//        }
//
//        //添加 Fragment
//        ArrayList<Fragment> mFragList = new ArrayList<Fragment>();
//        for (int i = 0; i < mTitleList.size(); i++) {
//            mFragList.add(new FollowListFragment());
//        }
//
//        FragmentVpAdapter mAdapter = new FragmentVpAdapter(getSupportFragmentManager(), mFragList, mTitleList);
//
//        //给ViewPager设置适配器
//        mViewPager.setAdapter(mAdapter);
//        //将TabLayout和ViewPager关联起来。
//        mTabLayout.setupWithViewPager(mViewPager);
//
//        TabLayoutUtils.setIndicator(mTabLayout, 0);

        //初始化关注列表控件
        rv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<FollowListItemBean>();
        LinearItemDecoration itemDecoration = new LinearItemDecoration(LinearItemDecoration.VERTICAL_LIST);
        itemDecoration.setDividerColor(Color.GRAY);
        itemDecoration.setDividerHeight(1);
        rv.addItemDecoration(itemDecoration);
        rv.setAdapter(adapter = new FollowListAdapter(this, list));

        srl.setColorSchemeResources(R.color.mainColor, R.color.mainAdd);
        onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (App.isLogin()) {
                    mPresenter.getMyFollowList(App.getUser().userId);
                } else {
                    T.showShort(MyFollowActivity.this, "未登录！");
                    srl.setRefreshing(false);
                }
            }
        };
        srl.setOnRefreshListener(onRefreshListener);
    }

    @Override
    protected void initDatas() {
        bindPresenter(new MyFollowPresenter(), new MyFollowCallback(this, getApplicationContext()));
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(true);
                onRefreshListener.onRefresh();
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                //T.showShort(getApplicationContext(), "热门关注");
                ActivityUtils.jumpIn(this, HotFollowActivity.class);
                break;
            default:
                break;
        }
    }

    static class MyFollowCallback extends RxCallback<MyFollowResponse, MyFollowActivity, BaseTag> {

        public MyFollowCallback(MyFollowActivity host, Context mContext) {
            super(host, mContext);
        }

        @Override
        protected void onDealNextResponse(MyFollowResponse response, BaseTag tag) {
            if ("200".equals(response.code)) {
                getHost().list.clear();
                if (response.user != null) {
                    FollowListItemBean bean = null;
                    for (User u : response.user) {
                        bean = new FollowListItemBean();
                        bean.imgUrl = u.userHead;
                        bean.info = "吃货们快戳我";
                        bean.name = u.userName;
                        bean.time = TimeUtils.getStringTime(System.currentTimeMillis());
                        getHost().list.add(bean);
                    }
                }
                getHost().adapter.notifyDataSetChanged();
                if (getHost().list.isEmpty()) {
                    getHost().showEmptyView(true);
                }else{
                    getHost().showEmptyView(false);
                }
            } else {
                T.showShort(mAppContext, "数据获取失败！");
            }
        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            return null;
        }

        @Override
        public void onRequestStart(BaseTag tag) {

        }

        @Override
        public void onRequestEnd(BaseTag tag) {
            getHost().srl.setRefreshing(false);
        }
    }

    private void showEmptyView(boolean show) {
        if(show){
            if(tvEmptyView.getVisibility()!=View.VISIBLE){
                tvEmptyView.setVisibility(View.VISIBLE);
            }
        }else{
            if(tvEmptyView.getVisibility()!=View.GONE){
                tvEmptyView.setVisibility(View.GONE);
            }
        }
    }
}
