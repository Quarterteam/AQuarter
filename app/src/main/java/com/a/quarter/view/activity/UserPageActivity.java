package com.a.quarter.view.activity;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.view.adapter.userpage.UserPageAdapter;
import com.a.quarter.view.base.BaseActivity;

import butterknife.Bind;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/25.
 */

public class UserPageActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.userpage_Text_attention)
    TextView mAttention;
    @Bind(R.id.userpage_recycler)
    RecyclerView mRecyclerView;
    @Bind(R.id.userpage_image_back)
    ImageView mBack;
    @Bind(R.id.userpage_image_share)
    ImageView mShape;
    @Bind(R.id.userpage_image_comment)
    ImageView mComment;
    private boolean TextSelector=false;
    private UserPageAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_userpage;
    }

    @Override
    protected void initViews() {
        mAttention.setOnClickListener(this);
        mBack.setOnClickListener(this);

    }

    @Override
    protected void initDatas() {
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new UserPageAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.userpage_image_back:
                finish();
                break;
            case R.id.userpage_Text_attention:
                if (TextSelector){
                    mAttention.setBackgroundResource(R.drawable.icon_attention_default);
                    mAttention.setText("+关注");
                    mAttention.setTextColor(Color.BLUE);
                    TextSelector=false;
                }else{
                    mAttention.setBackgroundResource(R.drawable.icon_attention_check);
                    mAttention.setText("已关注");
                    mAttention.setTextColor(Color.WHITE);
                    TextSelector=true;
                }
                break;
        }
    }
}
