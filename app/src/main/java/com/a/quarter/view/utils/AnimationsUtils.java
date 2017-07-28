package com.a.quarter.view.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IntegerRes;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.exa.framelib_rrm.utils.LogUtils;
import com.exa.framelib_rrm.utils.T;

import java.io.FileFilter;


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
        LogUtils.i("setAnimationSet1");
        LogUtils.i("million="+million+", view="+view+", Vis="+view.getVisibility()+", tfrom="+tfrom+", tto="+tto+", afrom="+afrom+", ato="+ato);
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(view, "translationX", tfrom, tto);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(view, "alpha", afrom, ato);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(moveIn).with(fadeInOut);
        animSet.setDuration(million);
        animSet.start();
        LogUtils.i("setAnimationSet2");
    }



}
