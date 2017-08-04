package com.a.quarter.view.activity.welcome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.a.quarter.R;
import com.a.quarter.view.activity.main.MainActivity;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.autolayout.utils.AutoUtils;

//启动页
//public class WelcomeActivity extends AppCompatActivity {
public class WelcomeActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final View root = findViewById(R.id.rl_root);
        final View ll = findViewById(R.id.ll);
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        MaskImageView iv = (MaskImageView) findViewById(R.id.iv_entering_logo);
        //AutoUtils.autoSize(iv);
        iv.setAnimListener(new MaskImageView.AnimationListener() {
            @Override
            public void onAnimEnd() {
                ll.setVisibility(View.GONE);
                root.setBackgroundResource(R.mipmap.welcome_bg);
                progressBar.setVisibility(View.VISIBLE);

                root.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        ActivityUtils.jumpIn(WelcomeActivity.this, MainActivity.class);
                        finish();
                    }
                }, 3000);// TODO: 2017/7/26 修改时间3000
            }
        });
        iv.startAnim();
    }

}
