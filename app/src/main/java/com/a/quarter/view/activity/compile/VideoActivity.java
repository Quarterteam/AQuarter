package com.a.quarter.view.activity.compile;

import android.view.View;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class VideoActivity extends BaseActivity {
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.xiang)
    TextView xiang;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_video;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }



    @OnClick({R.id.tv_cancel, R.id.xiang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.xiang:
                break;
        }
    }
}
