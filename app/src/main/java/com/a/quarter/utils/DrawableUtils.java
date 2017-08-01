package com.a.quarter.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * 作者：卡卡
 * 链接：https://www.zhihu.com/question/26248104/answer/50517954
 * 来源：知乎
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class DrawableUtils {

    //设置RadioButton的DrawableTop，并根据文字大小控制图片的大小
    public static void scaleDrawableTop(Context context, int id, RadioButton rBtn){
        //Drawable drawable = context.getResources().getDrawable(R.mipmap.ic_launcher);方法已过时
        int width = (int)rBtn.getTextSize()*2;
        Drawable drawableTop = ContextCompat.getDrawable(context, id);
        //drawableTop.setBounds(0, 0, drawable.getIntrinsicWidth() / 2, drawable.getIntrinsicHeight() / 2);
        drawableTop.setBounds(0, 0, width, width);//设置图片宽度和高度为2个字的宽度
        Drawable[] drawables = rBtn.getCompoundDrawables();
        rBtn.setCompoundDrawables(drawables[0], drawableTop, drawables[1], drawables[2]);
    }

    //设置TextView的DrawableLeft，并根据文字大小控制图片的大小
    public static void scaleDrawableLeft(Context context, int id, TextView tv){
        int width = (int)(tv.getTextSize()*1.5f);
        Drawable drawableLeft = ContextCompat.getDrawable(context, id);
        drawableLeft.setBounds(0, 0, width, width);//设置图片宽度和高度为1.5个字的宽度
        Drawable[] drawables = tv.getCompoundDrawables();
        tv.setCompoundDrawables(drawableLeft, drawables[1], drawables[2], drawables[3]);
    }

    //设置TextView的DrawableTop，并根据文字大小控制图片的大小
    public static void scaleDrawableTop(Context context, int id, TextView tv){
        int width = (int)tv.getTextSize()*2;
        Drawable drawableTop = ContextCompat.getDrawable(context, id);
        drawableTop.setBounds(0, 0, width, width);//设置图片宽度和高度为2个字的宽度
        Drawable[] drawables = tv.getCompoundDrawables();
        tv.setCompoundDrawables(drawables[0], drawableTop, drawables[2], drawables[3]);
    }

}
