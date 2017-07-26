package com.a.quarter.view.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.a.quarter.R;
import com.exa.framelib_rrm.utils.LogUtils;

import java.lang.reflect.Field;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class TabUnderlineUtil {
//    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
//        Class<?> tabLayout = tabs.getClass();
//        Field tabStrip = null;
//        try {
//            tabStrip = tabLayout.getDeclaredField("mTabStrip");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//
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

//    /**
//     * @param tabs TabLayout
//     * @param offset 指示器线条长度需要比一个TextView上的文字的总宽度多出的距离
//     * */
//    public static void setIndicator(TabLayout tabs, int offset) {
//        Class<?> tabLayout = tabs.getClass();
//        Field tabStrip = null;
//        try {
//            tabStrip = tabLayout.getDeclaredField("mTabStrip");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//
//        tabStrip.setAccessible(true);
//        LinearLayout llTab = null;
//        try {
//            llTab = (LinearLayout) tabStrip.get(tabs);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//        //手动测量所有文字加上offset占的总宽度
//        int totalWidth = 0;
//        View child;
//        LinearLayout.LayoutParams params = null;
//        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//        for (int i = 0; i < llTab.getChildCount(); i++) {
//            child = llTab.getChildAt(i);
//            child.setPadding(offset, 0, offset, 0);
//            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 0);
//            child.setLayoutParams(params);
//            child.measure(w, w);
//            //totalWidth += child.getWidth();
//            totalWidth += (child.getMeasuredWidth() + offset*2);
//        }
//
//        //得到每个TextView应该设置的平均margin
//        int margin = (llTab.getWidth() - totalWidth)/(llTab.getChildCount()*2);
//
//        //如果margin为负值，表示不需要设置margin
//        if(margin<=0){
//            return;
//        }
//        //改变每个TextView的margin值
//        for (int i = 0; i < llTab.getChildCount(); i++) {
//            child = llTab.getChildAt(i);
//            params = (LinearLayout.LayoutParams)child.getLayoutParams();
//            params.leftMargin = margin;
//            params.rightMargin = margin;
//            //child.invalidate();
//        }
//        //重绘画面
//        llTab.invalidate();
//    }

    /**
     * @param tabs TabLayout
     * @param offset 指示器线条长度需要比一个TextView上的文字的总宽度多出的距离
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
        }

        //手动测量所有TextView上所有文字占的总宽度
        int totalTextWidth = 0;
        View child;
        LinearLayout.LayoutParams params = null;
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < llTab.getChildCount(); i++) {
            child = llTab.getChildAt(i);
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 0);
            child.setLayoutParams(params);
            child.measure(w, w);
            //totalWidth += child.getWidth();
//            totalWidth += (child.getMeasuredWidth() + offset*2);
            totalTextWidth += child.getMeasuredWidth();
        }

        //剩余的宽度
        int availableWidth = llTab.getWidth() - totalTextWidth;
        //所有padding占的宽度
        int totalPadding = llTab.getChildCount()*2*offset;


        int padding = 0;
        //得到每个TextView应该设置的平均margin
        int margin = 0;
        if(totalPadding >= availableWidth){//如果加上padding之后，超出了屏幕的总宽度，就修改padding为可用的最大值，margin设置为为0
            padding = availableWidth/llTab.getChildCount()/2;
            margin = 0;
        }else{//否则，设置padding为指定的宽度。设置margin为剩余可用宽度的平均值
            padding = offset;
            margin = (availableWidth - totalPadding)/llTab.getChildCount()/2;
        }
        //改变每个TextView的padding和margin的值
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
