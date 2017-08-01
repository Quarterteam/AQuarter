package com.a.quarter.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.suke.widget.SwitchButton;

/**
 * Created by acer on 2017/7/20.
 */
public class SlidingMenuUtils implements SwitchButton.OnCheckedChangeListener {

    private ImageView ivBg;
    private TextView tvEditSign;
    public ImageView ivUserIcon;
    public TextView tvUserName;
    public ImageView ivSexIcon;

    private Context context;
    private TextView tvMyFollow;
    private TextView tvMyCollection;
    private TextView tvMsgNotify;
    private TextView tvSearchFriend;
    private TextView tvMyWork;
    private TextView tvSettings;
    private CheckedTextView tvLightMode;
    private SwitchButton switchButton;

    public SlidingMenu initSlidingMenu(Activity activity, final View.OnClickListener onClickListener) {
        this.context = activity.getApplicationContext();
        int pixels = activity.getResources().getDisplayMetrics().widthPixels;
        final SlidingMenu slidingMenu = (SlidingMenu)activity.findViewById(R.id.slidingmenulayout);
        //改为在xml里定义的方式，是为了实现既能使用状态栏的空间，又能自由设置状态栏的颜色。
        //因为SlidingMenu属于自定义控件，对于这样的控件是整个Activity第一个控件的情况，沉浸效果无法实现，
        //所以在slidingmenu_wraper.xml里添加一个系统规定的可以支持沉浸效果的TextView作为第一个控件。
        //(参考 http://blog.csdn.net/zf19921020/article/details/46840383
        // 和   http://blog.csdn.net/lmj623565791/article/details/36677279)

        //从左边滑出
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置滑出后屏幕的剩余宽度
        slidingMenu.setBehindOffset(pixels/4);

        //slidingMenu.attachToActivity(HomeActivity.this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.include_main_slide);
        tvMyFollow = (TextView)slidingMenu.findViewById(R.id.tv_my_follow);
        tvMyFollow.setOnClickListener(onClickListener);
        tvMyCollection = (TextView)slidingMenu.findViewById(R.id.tv_my_collection);
        tvMyCollection.setOnClickListener(onClickListener);
        tvMsgNotify = (TextView)slidingMenu.findViewById(R.id.tv_msg_notify);
        tvMsgNotify.setOnClickListener(onClickListener);
        tvSearchFriend = (TextView)slidingMenu.findViewById(R.id.tv_search_friend);
        tvSearchFriend.setOnClickListener(onClickListener);
        tvMyWork = (TextView)slidingMenu.findViewById(R.id.tv_my_work);
        tvMyWork.setOnClickListener(onClickListener);
        tvSettings = (TextView)slidingMenu.findViewById(R.id.tv_settings);
        tvSettings.setOnClickListener(onClickListener);

//        slidingMenu.findViewById(R.id.iv_bg).setOnClickListener(onClickListener);
//        slidingMenu.findViewById(R.id.tv_edit_sign).setOnClickListener(onClickListener);
//        slidingMenu.findViewById(R.id.iv_user_icon).setOnClickListener(onClickListener);
//        slidingMenu.findViewById(R.id.tv_user_name).setOnClickListener(onClickListener);
//        slidingMenu.findViewById(R.id.iv_sex_icon).setOnClickListener(onClickListener);

        this.ivBg = (ImageView)slidingMenu.findViewById(R.id.iv_bg);
        this.tvEditSign = (TextView)slidingMenu.findViewById(R.id.tv_edit_sign);
        this.ivUserIcon = (ImageView)slidingMenu.findViewById(R.id.iv_user_icon);
        this.tvUserName = (TextView)slidingMenu.findViewById(R.id.tv_user_name);
        this.ivSexIcon = (ImageView)slidingMenu.findViewById(R.id.iv_sex_icon);
        this.tvLightMode = (CheckedTextView)slidingMenu.findViewById(R.id.tv_light_mode);
        this.switchButton = (SwitchButton)slidingMenu.findViewById(R.id.switchButton);

        tvEditSign.setOnClickListener(onClickListener);
        ivUserIcon.setOnClickListener(onClickListener);
        switchButton.setOnCheckedChangeListener(this);

        slidingMenu.setOffsetFadeDegree(0.4f);

        return slidingMenu;
    }

    public void initDrawables(){
        DrawableUtils.scaleDrawableLeft(context, R.drawable.selector_icon_main_slide_myfollow, tvMyFollow);
        DrawableUtils.scaleDrawableLeft(context, R.drawable.selector_icon_main_slide_mycollect, tvMyCollection);
        DrawableUtils.scaleDrawableLeft(context, R.drawable.selector_icon_main_slide_msgnotify, tvMsgNotify);
        DrawableUtils.scaleDrawableLeft(context, R.drawable.selector_icon_main_slide_searchfriend, tvSearchFriend);
        DrawableUtils.scaleDrawableTop(context, R.drawable.selector_icon_main_slide_mywork, tvMyWork);
        DrawableUtils.scaleDrawableTop(context, R.drawable.selector_icon_main_slide_settings, tvSettings);
        DrawableUtils.scaleDrawableLeft(context, R.drawable.selector_icon_main_slide_light_mode, tvLightMode);
    }

    @Override
    public void onCheckedChanged(SwitchButton view, boolean isChecked) {
        tvLightMode.setChecked(isChecked);
        if(isChecked){
            tvLightMode.setText("夜间模式");
        }else{
            tvLightMode.setText("日间模式");
        }
        //切换背景颜色
        //ThemeManager.switchThemeMode();
    }
}
