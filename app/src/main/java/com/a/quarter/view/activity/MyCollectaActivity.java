package com.a.quarter.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.view.adapter.userpage.UserPageAdapter;
import com.a.quarter.view.base.BaseActivity;

import butterknife.Bind;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/28.
 */

public class MyCollectaActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.tv_back)
    TextView mtvBack;
    @Bind(R.id.tv_head)
    TextView mTvHead;
    @Bind(R.id.tv_right)//删除
    TextView mTvRight;
    @Bind(R.id.collect_recycler)
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_mycollect;
    }

    @Override
    protected void initViews() {
        mtvBack.setOnClickListener(this);
        mTvRight.setOnClickListener(this);
        mTvHead.setText("我的收藏");
        mTvRight.setText("删除");
    }

    @Override
    protected void initDatas() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        UserPageAdapter userPageAdapter = new UserPageAdapter(this);
        recyclerView.setAdapter(userPageAdapter);
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()){
          case R.id.tv_back:
              finish();
              break;
           case R.id.tv_right:
          break;
      }
    }
}
