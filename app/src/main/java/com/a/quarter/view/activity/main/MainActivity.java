package com.a.quarter.view.activity.main;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.app.App;
import com.a.quarter.model.bean.login.User;
import com.a.quarter.view.activity.MsgInformActivity;
import com.a.quarter.view.activity.configure.SlidingmenuToActivity;
import com.a.quarter.view.activity.login.ThirdPartyLoginActivity;
import com.a.quarter.utils.DrawableUtils;
import com.a.quarter.utils.SlidingMenuUtils;
import com.a.quarter.view.base.BaseActivity;
import com.a.quarter.view.fragment.joke.JokeFragment;
import com.a.quarter.view.fragment.recommend.RecommendFragment;
import com.a.quarter.view.fragment.video.VideoFragment;

import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.ScreenUtils;
import com.exa.framelib_rrm.utils.StatusBarCompat;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.Bind;
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
    private View mHead;
    //private View mRoot;

    @Override
    protected int getContentViewId() {
//        return R.layout.activity_main;
        return R.layout.slidingmenu_wraper;
    }

    @Override
    protected void setStatusBar() {
        mHead = findViewById(R.id.main_head);
        //设置状态栏为透明，并且使用状态栏所占空间
        StatusBarCompat.compat(this, ContextCompat.getColor(this, android.R.color.transparent), true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //如果可以使用状态栏所占的空间，左侧的SlidingMenu使用了状态栏所占的空间，
            //需要给右侧的主布局加上一个高度等于状态栏高度的paddingTop，让头部不被状态栏挡住
            mHead.setPadding(0, ScreenUtils.getStatusHeight(this), 0, 0);
        }
    }

    @Override
    protected void initViews() {
        //mRoot = findViewById(R.id.rl_root);
        //初始化底部导航
        initRadioButton();
        radioGroupNav.setOnCheckedChangeListener(this);
        radioButtonRecommend.setChecked(true);

        //初始化侧滑菜单
        slidingMenuUtils = new SlidingMenuUtils();
        slidingMenu = slidingMenuUtils.initSlidingMenu(this, this);
        slidingMenuUtils.initDrawables();
        if (App.isLogin()) {
            User user = App.getUser();
            slidingMenuUtils.tvUserName.setText(user.userName);
            slidingMenuUtils.ivUserIcon.setImageResource(R.mipmap.user_icon);
            if ("男".equals(user.userSex)) {
                slidingMenuUtils.ivSexIcon.setImageResource(R.mipmap.ic_launcher);
            } else {
                slidingMenuUtils.ivSexIcon.setImageResource(R.mipmap.user_icon);
            }
            ivLeft.setImageResource(R.mipmap.user_icon);
        } else {
            slidingMenuUtils.tvUserName.setText("点击头像登录");
            slidingMenuUtils.ivUserIcon.setImageResource(R.mipmap.default_no_avatar);
            slidingMenuUtils.ivSexIcon.setImageDrawable(null);
            ivLeft.setImageResource(R.mipmap.default_no_avatar);
        }

        //发表文章
        findViewById(R.id.iv_right).setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.iv_left:
                if (slidingMenu != null) {
                    slidingMenu.toggle();
                }
                break;
            case R.id.iv_right:
//                if(App.isLogin()){
                ActivityUtils.jumpIn(this, PublishArticleActivity.class);
//                }else{
//                    T.showShort(getApplicationContext(), "没有登录");
//                }
                break;
            case R.id.tv_my_follow:
                ActivityUtils.jumpIn(this, MyFollowActivity.class);

                break;
            case R.id.tv_search_friend:
                ActivityUtils.jumpIn(this, SearchFriendActivity.class);
                break;
            case R.id.tv_my_collection:
            case R.id.tv_my_work:
                setIntent("mywork");
            case R.id.tv_settings:
                setIntent("setting");
                if (slidingMenu != null) {
                    slidingMenu.toggle();
                }
                break;
            case R.id.tv_msg_notify:

                ActivityUtils.jumpIn(this, MsgInformActivity.class);
                break;
            case R.id.iv_user_icon:
//                if(!App.isLogin()){
                ActivityUtils.jumpForResult(1, this, ThirdPartyLoginActivity.class);
//                }else{TODO 跳转到个人中心页面
//                    T.showShort(getApplicationContext(), "已登录");
//                }
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

    public void setIntent(String tag) {
        Intent intent = new Intent(this, SlidingmenuToActivity.class);
        intent.putExtra("tag", tag);
        startActivity(intent);

//        finish();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //登录成功后，从登录页面返回
        if (requestCode == 1 && resultCode == 1) {
            User user = App.getUser();
            slidingMenuUtils.tvUserName.setText(user.userName);
            slidingMenuUtils.ivUserIcon.setImageResource(R.mipmap.user_icon);
            if ("男".equals(user.userSex)) {
                slidingMenuUtils.ivSexIcon.setImageResource(R.mipmap.ic_launcher);
            } else {
                slidingMenuUtils.ivSexIcon.setImageResource(R.mipmap.female);
            }
            ivLeft.setImageResource(R.mipmap.user_icon);
        }


    }//13567890550

}
