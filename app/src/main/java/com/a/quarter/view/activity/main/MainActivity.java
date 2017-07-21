package com.a.quarter.view.activity.main;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.view.activity.login.LoginActivity;
import com.a.quarter.view.base.BaseActivity;
import com.a.quarter.view.fragment.joke.JokeFragment;
import com.a.quarter.view.fragment.recommend.RecommendFragment;
import com.a.quarter.view.fragment.video.VideoFragment;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.DensityUtils;
import com.exa.framelib_rrm.utils.LogUtils;
import com.exa.framelib_rrm.utils.ScreenUtils;
import com.exa.framelib_rrm.utils.StatusBarCompat;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @Bind(R.id.radioGroupNav)
    public RadioGroup radioGroupNav;
    @Bind(R.id.radioButtonRecommend)
    public RadioButton radioButtonRecommend;
    @Bind(R.id.radioButtonJoke)
    public RadioButton radioButtonJoke;
    @Bind(R.id.radioButtonVideo)
    public RadioButton radioButtonVideo;
    @Bind(R.id.tv_title)
    public TextView tvTitle;
    @Bind(R.id.iv_left)
    public ImageView ivLeft;

    private RecommendFragment recommendFragment;
    private JokeFragment jokeFragment;
    private VideoFragment videoFragment;
    private SlidingMenu slidingMenu;
    private SlidingMenuUtils slidingMenuUtils;

    @Override
    protected int getContentViewId() {
        //return R.layout.activity_main;
        return R.layout.slidingmenu_wraper;
    }

    @Override
    protected void setStatusBar() {
        //设置状态栏为透明，并且使用状态栏所占空间
        StatusBarCompat.compat(this, ContextCompat.getColor(this, android.R.color.transparent), true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            //如果可以使用状态栏所占的空间，左侧的SlidingMenu使用了状态栏所占的空间，
            //需要给右侧的主布局加上一个高度等于状态栏高度的paddingTop，让头部不被状态栏挡住
            findViewById(R.id.rl_root).setPadding(0, ScreenUtils.getStatusHeight(this), 0, 0);
        }
    }

    @Override
    protected void initViews() {
        initRadioButton();
        radioGroupNav.setOnCheckedChangeListener(this);
        radioButtonRecommend.setChecked(true);

        slidingMenuUtils = new SlidingMenuUtils();
        slidingMenu = slidingMenuUtils.initSlidingMenu(this, this);
        slidingMenuUtils.initDrawables();
    }

    private void initRadioButton() {
        DrawableUtils.scaleDrawableTop(this, R.drawable.selector_icon_main_nav_recommend, radioButtonRecommend);
        DrawableUtils.scaleDrawableTop(this, R.drawable.selector_icon_main_nav_joke, radioButtonJoke);
        DrawableUtils.scaleDrawableTop(this, R.drawable.selector_icon_main_nav_video, radioButtonVideo);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.iv_left)
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_left:
                if(slidingMenu!=null){
                    slidingMenu.toggle();
                }
                break;
            case R.id.tv_my_follow:
            case R.id.tv_my_collection:
            case R.id.tv_my_work:
            case R.id.tv_settings:
            case R.id.tv_search_friend:
            case R.id.tv_msg_notify:
                if(slidingMenu!=null){
                    slidingMenu.toggle();
                }
                break;
            case R.id.iv_user_icon:
                ActivityUtils.jumpIn(this, LoginActivity.class);
                break;
            default:
                break;
        }
    }

    private FragmentTransaction t;
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        t = getSupportFragmentManager().beginTransaction();
        switch (checkedId) {
            case R.id.radioButtonRecommend:
                showFrag(0);
                hideFrag(1);
                hideFrag(2);
                break;

            case R.id.radioButtonJoke:
                showFrag(1);
                hideFrag(0);
                hideFrag(2);
                break;

            case R.id.radioButtonVideo:
                showFrag(2);
                hideFrag(0);
                hideFrag(1);
                break;

            default:
                break;
        }
        t.commit();
    }

    private void showFrag(int i) {
        switch (i) {
            case 0:
                if (recommendFragment == null) {
                    recommendFragment = new RecommendFragment();
                    t.add(R.id.frag_container, recommendFragment, "recommend");
                }
                tvTitle.setText("推荐");
                t.show(recommendFragment);
                break;
            case 1:
                if (jokeFragment == null) {
                    jokeFragment = new JokeFragment();
                    t.add(R.id.frag_container, jokeFragment, "joke");
                }
                tvTitle.setText("段子");
                t.show(jokeFragment);
                break;
            case 2:
                if (videoFragment == null) {
                    videoFragment = new VideoFragment();
                    t.add(R.id.frag_container, videoFragment, "video");
                }
                tvTitle.setText("视频");
                t.show(videoFragment);
                break;
            default:
                break;
        }
    }

    private void hideFrag(int i) {
        switch (i) {
            case 0:
                if (recommendFragment != null) {
                    t.hide(recommendFragment);
                }
                break;
            case 1:
                if (jokeFragment != null) {
                    t.hide(jokeFragment);
                }
                break;
            case 2:
                if (videoFragment != null) {
                    t.hide(videoFragment);
                }
                break;
            default:
                break;
        }
    }

}
