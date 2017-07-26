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
       复合动画：平移加渐变
        */
    public static AnimatorSet setJokeAnimShow(View view ,Float traStart, Float traEnd,Float rotStart, Float rotEnd ){
        ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", traStart, traEnd);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", rotStart, rotEnd);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translationX).with(alpha);
        animatorSet.setDuration(1000);
        return animatorSet;

    }
    /*
    双复合动画：使复合动画 同时播放
     */
    public static void getSetAnimator(AnimatorSet anim1,AnimatorSet anim2,AnimatorSet anim3){
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.play(anim1).with(anim2).with(anim3);
        animatorSet2.setDuration(1000);
        animatorSet2.start();

    }

}
