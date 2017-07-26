package com.a.quarter.model.utils;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.a.quarter.view.adapter.main.TabViewPagerAda;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 类的作用：TabLayout的工具类
 * 类的思路：
 * 时间：2017/7/21
 * 作者：王亚迪
 */

public class TabLayoutUtils {

    public static void setAda(final TabLayout tab, final ViewPager viewpager, FragmentManager manager, ArrayList<Fragment> datas, ArrayList<String> titles)
    {

        for (int i = 0; i < titles.size(); i++) {
            tab.addTab(tab.newTab().setText(titles.get(i)));
        }

        //创建ViewPager的适配器
        TabViewPagerAda pagerAda = new TabViewPagerAda(manager, datas, titles);
        //加入title选择和被选择的颜色,前面为未点击的颜色,后面为点击后的颜色
        tab.setTabTextColors(Color.BLACK, Color.parseColor("#03A9F4"));
        //设置适配器
        viewpager.setAdapter(pagerAda);
        //将tablayout与viewpager建立关系
        tab.setupWithViewPager(viewpager);
        // //设置适配器
        // viewpager.setAdapter(pagerAda);
        //设置TabLayout下划线的长度
        tab.post(new Runnable() {
            @Override
            public void run() {
                //setIndicator(tab,60,60);
                setIndicator(tab, 25);
            }
        });
    }

    public static void solveConflict(final ViewPager viewpager){
        //防止ViewPager和侧滑冲突，比如推荐Fragment与slidingmenu
        viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (viewpager.getCurrentItem()!=0)
                {
                    //viewPager的上级控件不拦截所以只滑动ViewPager,不会滑动SildingMenu
                    viewpager.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
    }

//    //设置TabLayout下划线的长度
//    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
//        Class<?> tabLayout = tabs.getClass();
//        Field tabStrip = null;
//        try {
//            tabStrip = tabLayout.getDeclaredField("mTabStrip");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        tabStrip.setAccessible(true);
//        LinearLayout llTab = null;
//        try {
//            llTab = (LinearLayout) tabStrip.get(tabs);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
//        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
//
//        for (int i = 0; i < llTab.getChildCount(); i++) {
//            View child = llTab.getChildAt(i);
//            child.setPadding(0, 0, 0, 0);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
//            params.leftMargin = left;
//            params.rightMargin = right;
//            child.setLayoutParams(params);
//            child.invalidate();
//        }
//    }

    /**
     * @param tabs TabLayout
     * @param offset 一个tab上的指示器线条长度需要比TextView上的文字的总宽度多出的一侧的距离
     * */
    public static void setIndicator(TabLayout tabs, int offset) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        }

        //手动测量所有TextView上所有文字占的总宽度
        int totalTextWidth = 0;
        View child;
        LinearLayout.LayoutParams params = null;
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < llTab.getChildCount(); i++) {
            child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 0);
            child.setLayoutParams(params);
            child.measure(w, w);
            //totalWidth += child.getWidth();
            totalTextWidth += child.getMeasuredWidth();
        }

        //设置offset不能为负值，否则文字会显示不全
        if(offset<0){
            offset = 0;
        }

        //剩余的宽度
        int availableWidth = llTab.getWidth() - totalTextWidth;
        //所有padding占的宽度
        int totalPadding = llTab.getChildCount()*2*offset;

        //平均每个tab上的TextView应该设置的padding
        int padding = 0;
        //平均每个tab上的TextView应该设置的margin
        int margin = 0;
        //LogUtils.i("totalPadding="+totalPadding+"，availableWidth="+availableWidth);
        if(totalPadding >= availableWidth){//如果加上padding之后，超出了屏幕的总宽度，就修改padding为可用的最大值，margin设置为0
            padding = availableWidth/llTab.getChildCount()/2;
            margin = 0;
        }else{//否则，设置padding为传进来的参数里指定的宽度，设置margin为剩余可用宽度的平均值
            padding = offset;
            margin = (availableWidth - totalPadding)/llTab.getChildCount()/2;
        }
        //改变每个tab上的TextView的padding和margin的值
        for (int i = 0; i < llTab.getChildCount(); i++) {
            child = llTab.getChildAt(i);
            child.setPadding(padding, 0, padding, 0);
            params = (LinearLayout.LayoutParams)child.getLayoutParams();
            params.leftMargin = margin;
            params.rightMargin = margin;
            //child.invalidate();
        }
        //重绘画面
        llTab.invalidate();
    }
}
