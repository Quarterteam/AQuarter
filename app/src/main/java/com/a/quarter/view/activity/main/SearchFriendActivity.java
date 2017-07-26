package com.a.quarter.view.activity.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.main.MultiTypeItemBean;
import com.a.quarter.model.bean.main.SearchFriendHistory;
import com.a.quarter.model.bean.main.SearchFriendInterest;
import com.a.quarter.view.adapter.main.SearchFriendListAdapter;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.utils.T;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//搜索好友页面
public class SearchFriendActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.rv)
    RecyclerView rv;
    private ArrayList<MultiTypeItemBean> list;
    private SearchFriendListAdapter adapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search_friend;
    }

    @Override
    protected void initViews() {
        tvHead.setText("搜索好友");
        //初始化列表控件
        rv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MultiTypeItemBean>();
        adapter = new SearchFriendListAdapter(this, list);
        rv.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {
        //初始化数据
        list.add(new MultiTypeItemBean(SearchFriendListAdapter.TYPE_HISTORY_TITLE));
        for (int i = 0; i < 5; i++) {
            list.add(new SearchFriendHistory("历史记录"+i));
        }
        list.add(new MultiTypeItemBean(SearchFriendListAdapter.TYPE_INTEREST_TITLE));
        for (int i = 0; i < 5; i++) {
            list.add(new SearchFriendInterest("感兴趣的用户"+i, "我爱美食"));
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.iv_back, R.id.tv_cancel, R.id.iv_search})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_cancel:
                T.showShort(getApplicationContext(), "取消");
                break;

            case R.id.iv_search:
                T.showShort(getApplicationContext(), "搜索");
                break;

            default:
                break;
        }
    }
}
