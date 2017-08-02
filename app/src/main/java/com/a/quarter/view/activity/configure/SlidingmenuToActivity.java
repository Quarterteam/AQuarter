package com.a.quarter.view.activity.configure;

import android.content.Intent;
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
import com.exa.framelib_rrm.base.view.view.CircleImageView;

import butterknife.Bind;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class SlidingmenuToActivity extends BaseActivity {


    @Bind(R.id.iv_left)
    CircleImageView ivLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.text_back)
    TextView textBack;
    @Bind(R.id.slidTo_container_fram)
    FrameLayout container;
    private SettingFragment settFragment;
    private FragmentTransaction transaction;
    private MyWorkFragment myWorkFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_configure;
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        String tag = newIntent.getStringExtra("tag");
        Log.i("alidingmenuto", tag);
    }

    @Override
    protected void initViews() {
        textBack.setVisibility(View.VISIBLE);
        tvTitle.setText("设置");
        ivLeft.setVisibility(View.INVISIBLE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        showFrag(0);
//        hideFrag(0);
        showFrag(1);
        hideFrag(1);
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

    private void hideFrag(int i) {
        switch (i) {
            case 0:
                if (settFragment != null) {
                    transaction.hide(settFragment);
                }
                break;
            case 1:
                if (myWorkFragment != null) {
                    transaction.hide(myWorkFragment);
                }
                break;

            default:
                break;


        }
    }


}
