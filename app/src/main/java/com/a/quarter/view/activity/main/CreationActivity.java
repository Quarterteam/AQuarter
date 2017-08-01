package com.a.quarter.view.activity.main;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.utils.ActivityUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class CreationActivity extends BaseActivity {
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.creation_vedio)
    TextView creationVedio;
    @Bind(R.id.creation_text)
    TextView creationText;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_creation;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.tv_cancel, R.id.creation_vedio, R.id.creation_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.creation_vedio:
                ActivityUtils.jumpIn(this, VideoActivity.class);
                break;
            case R.id.creation_text:
                ActivityUtils.jumpIn(this, PublishArticleActivity.class);
                break;
        }
    }
}
