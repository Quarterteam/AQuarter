package com.a.quarter.model.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.view.View;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/22.
 */

public class AnimUtils {
    //计算当前控件位置
public static PointF getWei(View t){
    int[] location = new int[2];
    t.getLocationOnScreen(location);
    int x = location[0];
    int y = location[1];
    PointF pointF = new PointF(x, y);
    return pointF;
    //   System.out.println("图片各个角Left："+t.getLeft()+"Right："+t.getRight()+"Top："+t.getTop()+"Bottom："+t.getBottom());
}
    /*
        段子界面
        复合动画：平移加渐变
         */
    public static AnimatorSet setTransAlpha(View view, Float traStart, Float traEnd, Float alpStart, Float alpEnd) {
        view.setVisibility(View.VISIBLE);
        ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", traStart, traEnd);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", alpStart, alpEnd);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translationX).with(alpha);
        animatorSet.setDuration(800);
        return animatorSet;
    }

    /*
    段子界面
    双复合动画：使复合动画 同时播放
     */
    public static void getSetAnimator(AnimatorSet... anim) {
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.play(anim[0]).with(anim[1]).with(anim[2]);
        animatorSet2.setDuration(800);
        animatorSet2.start();
    }

    /*
      用户界面
     不同控件复合动画：渐变加旋转
      */
    public static void setTransRot(Float rotStart, Float rotEnd, Float alpStart, Float alpEnd, int visible, View... view) {

        ObjectAnimator alpha = ObjectAnimator.ofFloat(view[0], "alpha", alpStart, alpEnd);
        ObjectAnimator alpha2 = ObjectAnimator.ofFloat(view[1], "alpha", alpStart, alpEnd);
        ObjectAnimator alpha3 = ObjectAnimator.ofFloat(view[2], "alpha", alpStart, alpEnd);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view[3], "rotation", rotStart, rotEnd);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(alpha).with(alpha2).with(alpha3).with(rotation);
        animatorSet.setDuration(300);
        animatorSet.start();
        for (int i = 0; i < view.length - 1; i++) {
            view[i].setVisibility(visible);
        }


    }
    /*

    渐变
     */
    public static void setAlpha( Float alpStart, Float alpEnd, int visible, View... view) {

        ObjectAnimator alpha = ObjectAnimator.ofFloat(view[0], "alpha", alpStart, alpEnd);
        ObjectAnimator alpha2 = ObjectAnimator.ofFloat(view[1], "alpha", alpStart, alpEnd);
        ObjectAnimator alpha3 = ObjectAnimator.ofFloat(view[2], "alpha", alpStart, alpEnd);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(alpha).with(alpha2).with(alpha3);
        animatorSet.setDuration(500);
        animatorSet.start();
        for (int i = 0; i < view.length - 1; i++) {
            view[i].setVisibility(visible);
        }


    }
}
