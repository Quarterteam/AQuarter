package com.a.quarter.view.fragment.main;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a.quarter.R;
import com.a.quarter.model.bean.main.FollowListItemBean;
import com.a.quarter.view.adapter.main.FollowListAdapter;
import com.a.quarter.view.base.BaseFragment;
import com.a.quarter.view.base.LinearItemDecoration;
import com.exa.framelib_rrm.utils.TimeUtils;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by acer on 2017/7/24.
 */
public class FollowListFragment extends BaseFragment {

    @Bind(R.id.rv)
    RecyclerView rv;
    private ArrayList<FollowListItemBean> list;
    private FollowListAdapter adapter;

    private  String url;

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
        //设置关注列表数据
        for (int i = 0; i < 15; i++) {
            list.add(new FollowListItemBean("阿诺马斯丁"+i, "吃货们快戳我", TimeUtils.getStringTime(System.currentTimeMillis())));
        }
        adapter.notifyDataSetChanged();
    }

}
