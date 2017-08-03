package com.a.quarter.view.activity.configure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;
import com.a.quarter.view.fragment.slidingmenu.MyWorkFragment;
import com.a.quarter.view.fragment.slidingmenu.SettingFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class SlidingmenuToActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.iv_left)
    SimpleDraweeView ivLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.text_back)
    TextView textBack;
    @Bind(R.id.slidTo_container_fram)
    FrameLayout slidToContainerFram;
    private SettingFragment settFragment;
    private FragmentTransaction transaction;
    private MyWorkFragment myWorkFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_configure;
    }

    @Override
    protected void initViews() {
        textBack.setVisibility(View.VISIBLE);
        ivRight.setVisibility(View.INVISIBLE);

        ivLeft.setVisibility(View.INVISIBLE);

        textBack.setOnClickListener(this);
        Intent intent = getIntent();
        String tag = intent.getStringExtra("tag");


        Log.i("tag", tag);
        FragmentManager fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();


        if (tag.equals("setting")) {
            tvTitle.setText("设置");
            showFrag(0);
        } else {
            tvTitle.setText("我的作品");
            showFrag(1);
        }

        transaction.commit();


    }

    @Override
    protected void initDatas() {


    }


    private void showFrag(int i) {
        switch (i) {
            case 0:
                if (settFragment == null) {
                    settFragment = new SettingFragment();
                    transaction.add(R.id.slidTo_container_fram, settFragment, "settFragment");
                }
//                tvTitle.setText("推荐");
                transaction.show(settFragment);
                break;
            case 1:
                if (myWorkFragment == null) {
                    myWorkFragment = new MyWorkFragment();
                    transaction.add(R.id.slidTo_container_fram, myWorkFragment, "myWorkFragment");
                }
//                tvTitle.setText("段子");
                transaction.show(myWorkFragment);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_back:
                finish();
                break;
        }
    }
}
