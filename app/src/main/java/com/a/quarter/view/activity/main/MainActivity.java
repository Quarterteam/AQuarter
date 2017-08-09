package com.a.quarter.view.activity.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.app.App;
import com.a.quarter.model.bean.login.User;
import com.a.quarter.model.bean.main.EditSignResponse;
import com.a.quarter.presenter.main.MainPresenter;
import com.a.quarter.utils.DialogUtils;
import com.a.quarter.utils.DrawableUtils;
import com.a.quarter.utils.SlidingMenuUtils;
import com.a.quarter.view.activity.compile.CreationActivity;
import com.a.quarter.view.activity.configure.SlidingmenuToActivity;
import com.a.quarter.view.activity.login.ThirdPartyLoginActivity;
import com.a.quarter.view.activity.msginform.MsgInformActivity;
import com.a.quarter.view.activity.mycollect.MyCollectActivity;
import com.a.quarter.view.base.BaseActivity;
import com.a.quarter.view.fragment.joke.JokeFragment;
import com.a.quarter.view.fragment.recommend.RecommendFragment;
import com.a.quarter.view.fragment.video.VideoFragment;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.rx.RxCallback;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.ScreenUtils;
import com.exa.framelib_rrm.utils.StatusBarCompat;
import com.exa.framelib_rrm.utils.T;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter, MainActivity.MainCallback> implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

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
    //public ImageView ivLeft;
    public SimpleDraweeView ivLeft;

    private RecommendFragment recommendFragment;
    private JokeFragment jokeFragment;
    private VideoFragment videoFragment;
    private SlidingMenu slidingMenu;
    private SlidingMenuUtils slidingMenuUtils;
    private View mHead;
    //private View mRoot;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
        //return R.layout.slidingmenu_wraper;
        //改成还是使用new SlidingMenu()的方式创建侧滑菜单，可以减少一层布局
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
        if (App.isLogin()) {//已登录
            //显示用户信息
            showUserInfo(App.getUser());
        } else {//未登录
            slidingMenuUtils.tvUserName.setText("点击头像登录");
            slidingMenuUtils.ivUserIcon.setActualImageResource(R.mipmap.default_no_avatar);
            ivLeft.setActualImageResource(R.mipmap.default_no_avatar);
            slidingMenuUtils.ivSexIcon.setImageDrawable(null);
        }

        //发表文章
        findViewById(R.id.iv_right).setOnClickListener(this);
    }

    private void showUserInfo(User user) {
        //显示用户名
        slidingMenuUtils.tvUserName.setText(user.userName);
        //显示头像
        if(!TextUtils.isEmpty(user.userHead)){
            slidingMenuUtils.ivUserIcon.setImageURI(user.userHead);
            ivLeft.setImageURI(user.userHead);
        }else{
            slidingMenuUtils.ivUserIcon.setActualImageResource(R.mipmap.default_no_avatar);
            ivLeft.setActualImageResource(R.mipmap.default_no_avatar);
        }
        //显示性别图标
        if ("男".equals(user.userSex)) {
            slidingMenuUtils.ivSexIcon.setImageResource(R.mipmap.ic_launcher);
        } else {
            slidingMenuUtils.ivSexIcon.setImageResource(R.mipmap.female);
        }
        //显示个性签名
        slidingMenuUtils.tvEditSign.setText(user.userSignature);
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
            case R.id.iv_right: // TODO: 编译
//                if(App.isLogin()){
                ActivityUtils.jumpIn(this, CreationActivity.class);
//                }else{
//                    T.showShort(getApplicationContext(), "没有登录");
//                }
                break;
            case R.id.tv_my_follow:
                //if(App.isLogin()){
                    ActivityUtils.jumpIn(this, MyFollowActivity.class);
//                }else{
//                    T.showShort(getApplicationContext(), "未登录");
//                }
                break;
            case R.id.tv_search_friend:
                ActivityUtils.jumpIn(this, SearchFriendActivity.class);
                break;
            case R.id.tv_my_collection:
                ActivityUtils.jumpIn(this, MyCollectActivity.class);
                break;
            case R.id.tv_my_work:
                setIntent("mywork");

                break;
            case R.id.tv_settings:
                setIntent("setting");
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
            case R.id.tv_edit_sign:
                if(!App.isLogin()){
                    ActivityUtils.jumpForResult(1, this, ThirdPartyLoginActivity.class);
                }else{
                    //T.showShort(getApplicationContext(), "已登录");
                    //TODO 显示编辑个性签名的对话框？
                    initPresenterInNeed();
                    if(dialog==null){
                        dialog = DialogUtils.getSignEditDialog(this, mPresenter);
                    }else{
                        dialog.show();
                    }
                    dialog.etSign.setText(App.getUser().userSignature);
                }
                break;
            default:
                break;
        }
    }

    private void initPresenterInNeed() {
        if(mPresenter==null){
            bindPresenter(new MainPresenter(), new MainCallback(this, getApplicationContext()));
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

        //finish();
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
            //显示用户信息
            showUserInfo(App.getUser());
        }

    }//13567890550

    private DialogUtils.SignEditDialog dialog;

    //// TODO: 2017/8/8 正在请求网络时，dialog上的button禁止点击
    static class MainCallback extends RxCallback<EditSignResponse, MainActivity, BaseTag>{
        private String newSign;

        public MainCallback(MainActivity host, Context mContext) {
            super(host, mContext);
        }

        @Override
        protected void onDealNextResponse(EditSignResponse response, BaseTag tag) {
            if("200!message:个性签名修改成功!!!!".equals(response.code)){
                getHost().dialog.dismiss();
                getHost().slidingMenuUtils.tvEditSign.setText(newSign);
                App.getUser().saveUserSignature(newSign);
                T.showShort(mAppContext, response.code);
            }else{
                T.showShort(mAppContext, response.code);
            }
        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            newSign = params.length>0 ? (String)params[0]:null;
            if(TextUtils.isEmpty(newSign)){
                return "还没输入个性签名";
            }
            return null;
        }

        @Override
        public void onRequestStart(BaseTag tag) {
            getHost().dialog.btnOk.setEnabled(false);
        }

        @Override
        public void onRequestEnd(BaseTag tag) {
            getHost().dialog.btnOk.setEnabled(true);
        }
    }
}
