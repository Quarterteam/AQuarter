package com.a.quarter.view.fragment.recommend;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by acer on 2017/7/29.
 * 第三方控件banner，根据资源id加载本地图片
 */
public class BannerFrescoImageLoader implements ImageLoaderInterface<ImageView> {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setImageResource((Integer) path);
    }

    @Override
    public ImageView createImageView(Context context) {
        //Log.i("LocalImageLoader", "createImageView new");
        return new ImageView(context);
    }

}
