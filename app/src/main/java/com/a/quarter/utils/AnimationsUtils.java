package com.a.quarter.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;


/**
 * desc:
 * author:吴晓芳
 * date:translationX
 */

public class AnimationsUtils {

    private static Animation animation;

    public static void setTraAnimation(View view,String action,float from,float to,int millions) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, action, from, to);
        animator.setDuration(millions);
        animator.start();

    }
    public static Animation  getRotaAnimation(Context context,int animationId,View view) {

        RotateAnimation animation= (RotateAnimation) AnimationUtils.loadAnimation(context,animationId);
        view.startAnimation(animation);

        return animation;

    }
    public static void setAnimationSet(int million, View view, float tfrom, float tto, float afrom, float ato) {

        ObjectAnimator moveIn = ObjectAnimator.ofFloat(view, "translationX", tfrom, tto);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(view, "alpha", afrom, ato);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(moveIn).with(fadeInOut);
        animSet.setDuration(million);
        animSet.start();

    }



}
