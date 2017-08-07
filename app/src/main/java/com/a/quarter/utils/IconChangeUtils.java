package com.a.quarter.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * 类的作用：用于点击 item中 如 评论 ；图片的改变 ，和评论数的加减
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/8/3.
 */

public class IconChangeUtils {
    //数量的增加，图片的改变
   public static void setIconChangeCheck(Context context, TextView textView, int imageId){
       Drawable top = context.getResources().getDrawable(imageId);
       textView.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
       String str = textView.getText().toString();
       int num = Integer.parseInt(str)+1;
       textView.setText("" + num);
   }
    //数量的减少 图片的改变
    public static void setIconChangeDefault(Context context,TextView textView,int imageId){
        Drawable top = context.getResources().getDrawable(imageId);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
        String str = textView.getText().toString();
        int num = Integer.parseInt(str)-1;
        textView.setText("" + num);
    }
//数量的增加
    public static void getNumAdd(TextView text){
        String str = text.getText().toString();
        int num = Integer.parseInt(str)+1;
        text.setText("" + num);
    }
    //数量的减少
    public static void getNumReduce(TextView text){
        String str = text.getText().toString();
        int num = Integer.parseInt(str) - 1;
        text.setText("" + num);
    }
}
