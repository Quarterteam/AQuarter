package com.a.quarter.view.fragment.recommend;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * Created by acer on 2017/7/29.
 * 第三方控件banner，使用Fresco加载本地资源图片
 */
public class BannerFrescoImageLoader implements ImageLoaderInterface<SimpleDraweeView> {

    @Override
    public void displayImage(Context context, Object path, SimpleDraweeView sdv) {
        if(path instanceof Integer){
            sdv.setActualImageResource((Integer) path);//资源图片
        }else if(path instanceof String){
            sdv.setImageURI((String)path);
        }else{
            sdv.setImageURI((Uri)path);
        }
    }

    @Override
    public SimpleDraweeView createImageView(Context context) {
        //Log.i("LocalImageLoader", "createImageView new");
        return new SimpleDraweeView(context);
    }

}
