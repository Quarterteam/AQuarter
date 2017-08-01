package com.a.quarter.view.fragment.recommend;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.a.quarter.R;

import media.IjkVideoView;

/**
 * Created by acer on 2017/7/31.
 */
public class MyIjkVideoView extends IjkVideoView{

    public MyIjkVideoView(Context context) {
        super(context);
        initMyVideoView(context);
    }

    public MyIjkVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMyVideoView(context);
    }

    public MyIjkVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMyVideoView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyIjkVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initMyVideoView(context);
    }

    public ImageView mIvThumb;
    public ImageView mIvStartIcon;
    private void initMyVideoView(Context context) {
        //视频缩略图显示控件
        mIvThumb = new ImageView(context);
        mIvThumb.setImageResource(R.mipmap.bg6);
        mIvThumb.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(mIvThumb, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        //开始播放的图标
        mIvStartIcon = new ImageView(context);
        mIvStartIcon.setImageResource(R.mipmap.video_icon);
        LayoutParams params = new LayoutParams(100, 100);
        params.gravity = Gravity.CENTER;
        addView(mIvStartIcon, params);
    }

}
