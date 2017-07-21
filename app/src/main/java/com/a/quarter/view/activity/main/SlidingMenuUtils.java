package com.a.quarter.view.activity.main;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.a.quarter.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by acer on 2017/7/20.
 */
public class SlidingMenuUtils {

    public static SlidingMenu initSlidingMenu(Activity activity, final View.OnClickListener onClickListener) {
        int pixels = activity.getResources().getDisplayMetrics().widthPixels;
        int slidingmenuWidth = activity.getResources().getDimensionPixelOffset(R.dimen.main_slide_width);
        //final SlidingMenu slidingMenu = new SlidingMenu(this);
        final SlidingMenu slidingMenu = (SlidingMenu)activity.findViewById(R.id.slidingmenulayout);
        //改为在xml里定义的方式，是为了实现既能使用状态栏的空间，又能自由设置状态栏的颜色。
        //因为SlidingMenu属于自定义控件，对于这样的控件是整个Activity第一个控件的情况，沉浸效果无法实现，
        //所以在slidingmenu_wraper.xml里添加一个系统规定的可以支持沉浸效果的TextView作为第一个控件。
        //(参考 http://blog.csdn.net/zf19921020/article/details/46840383
        // 和   http://blog.csdn.net/lmj623565791/article/details/36677279)

        //从左边滑出
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置住屏幕滑出的宽度
        //slidingMenu.setBehindOffset(pixels / 3);
        if(pixels - slidingmenuWidth>0){
            slidingMenu.setBehindOffset(pixels - slidingmenuWidth);
        }else{
            slidingMenu.setBehindOffset(pixels / 3);
        }
        //slidingMenu.attachToActivity(HomeActivity.this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setMenu(R.layout.include_main_slide);
        slidingMenu.findViewById(R.id.tv_my_follow).setOnClickListener(onClickListener);
        slidingMenu.findViewById(R.id.tv_my_collection).setOnClickListener(onClickListener);
        slidingMenu.findViewById(R.id.tv_msg_notify).setOnClickListener(onClickListener);
        slidingMenu.findViewById(R.id.tv_search_friend).setOnClickListener(onClickListener);
        slidingMenu.findViewById(R.id.tv_my_work).setOnClickListener(onClickListener);
        slidingMenu.findViewById(R.id.tv_settings).setOnClickListener(onClickListener);
        slidingMenu.setOffsetFadeDegree(0.4f);

        return slidingMenu;
    }

}
