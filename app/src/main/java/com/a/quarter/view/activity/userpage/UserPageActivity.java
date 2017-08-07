package com.a.quarter.view.activity.userpage;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.userpage.UserPageItemBean;
import com.a.quarter.presenter.userpage.UserPagePresenter;
import com.a.quarter.view.adapter.userpage.UserPageAdapter;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.rx.RxCallback;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/25.
 */

public class UserPageActivity extends BaseActivity<UserPagePresenter,UserPageActivity.UserPageCallBack> implements View.OnClickListener{
    @Bind(R.id.userpage_image_show)
    ImageView mIvShow;
    @Bind(R.id.userpage_image_back)
    ImageView mBack;
    @Bind(R.id.userpage_text_title) //Mc 跨足令
    TextView mTitleName;
    @Bind(R.id.userpage_image_share) //分享
    ImageView mShape;
    @Bind(R.id.userpage_image_comment)//评论
    ImageView mComment;
    @Bind(R.id.userpage_image_title)
    SimpleDraweeView mImageTitle;
    @Bind(R.id.userpage_Text_attention) //关注
    TextView mAttention;
    @Bind(R.id.userpage_text_fans)
     TextView mFans;
    @Bind(R.id.userpage_text_attention)
    TextView mAttentionNum;
    @Bind(R.id.userpage_Text_favour)//点赞数
    TextView mFavour;
    @Bind(R.id.userpage_Text_wokes)//作品数量
    TextView mWokes;
    @Bind(R.id.userpage_recycler)
    RecyclerView mRecyclerView;
    private boolean TextSelector=false;
    private UserPageAdapter mAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<UserPageItemBean> list=new ArrayList<>();

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

        for (int i = 0; i <2 ; i++) {
            UserPageItemBean userPageItemBean = new UserPageItemBean(false,false,false, false);
            list.add(userPageItemBean);
        }
        mAdapter.setData(list);
        mAdapter.notifyDataSetChanged();
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
                    mAttention.setTextColor(this.getResources().getColor(R.color.mainColor));
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

    static  class UserPageCallBack extends RxCallback<UserPageItemBean,UserPageActivity,BaseTag> {


        public UserPageCallBack(UserPageActivity host, Context mContext) {
            super(host, mContext);
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
        }


        @Override
        protected void onDealNextResponse(UserPageItemBean response, BaseTag tag) {

        }
    }
}
