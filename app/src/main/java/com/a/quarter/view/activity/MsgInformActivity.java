package com.a.quarter.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;
import com.a.quarter.view.fragment.msginform.MsgFragment;
import com.a.quarter.view.fragment.msginform.PrivateLetterFragment;

import butterknife.Bind;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/25.
 */

public class MsgInformActivity extends BaseActivity implements View.OnClickListener ,TabLayout.OnTabSelectedListener {
    @Bind(R.id.tv_back)
    TextView mBack;
    @Bind(R.id.tv_head)
    TextView mHead;
    @Bind(R.id.msg_table)
    TabLayout tablayout;
    @Bind(R.id.msg_tv_del)
    TextView mDelete;
    @Bind(R.id.msg_frame)
    FrameLayout mFrameLayout;
    private MsgFragment mMsgFragment;
    private PrivateLetterFragment mPrivateLetterFragment;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_msg_inform;
    }

    @Override
    protected void initViews() {
        initFragment();
        mHead.setText("消息通知");
        mBack.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        tablayout.setOnTabSelectedListener(this);

        tablayout.addTab(tablayout.newTab().setText("消息"));
        tablayout.addTab(tablayout.newTab().setText("私信"));


    }

    private void initFragment() {
        FragmentManager  manager = getSupportFragmentManager();
        FragmentTransaction  transaction = manager.beginTransaction();
        mMsgFragment = new MsgFragment();
        mPrivateLetterFragment = new PrivateLetterFragment();
        transaction.add(R.id.msg_frame, mMsgFragment).add(R.id.msg_frame, mPrivateLetterFragment);
        transaction.show(mMsgFragment).hide(mPrivateLetterFragment);
        transaction.commit();
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
          case R.id.tv_back:
              finish();
              break;
            case R.id.msg_tv_del:
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        if (tab.getText().equals("消息")){
            mDelete.setVisibility(View.VISIBLE);
            FragmentManager  manager = getSupportFragmentManager();
            FragmentTransaction  transaction = manager.beginTransaction();
            transaction.show(mMsgFragment).hide(mPrivateLetterFragment);
            transaction.commit();
        }
        if (tab.getText().equals("私信")){
            mDelete.setVisibility(View.GONE);
            FragmentManager  manager = getSupportFragmentManager();
            FragmentTransaction  transaction = manager.beginTransaction();
            transaction.show(mPrivateLetterFragment).hide(mMsgFragment);
            transaction.commit();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
