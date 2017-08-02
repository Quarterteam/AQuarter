package com.a.quarter.utils;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/8/1.
 */

public class FrescoCircleUtils {
    public static void setImageViewCircle(SimpleDraweeView icon, Uri uri){
        DraweeController draweeController2 = Fresco.newDraweeControllerBuilder().setUri(Uri.parse("http://169.254.1.100/ic_ss.jpg")).setAutoPlayAnimations(true).build();
        icon.setController(draweeController2);
        RoundingParams mRoundParams2 = RoundingParams.fromCornersRadius(7f);
        mRoundParams2.setRoundAsCircle(true);//圆圈 - 设置roundAsCircle为true
        // mRoundParams2.setBorder(Color.parseColor("#ffffff"),3);//显示边框的颜色
        //mRoundParams2.setOverlayColor(Color.parseColor("#ffffff"));//用来绘制非圆角颜色
        icon.getHierarchy().setRoundingParams(mRoundParams2);
    }
}
