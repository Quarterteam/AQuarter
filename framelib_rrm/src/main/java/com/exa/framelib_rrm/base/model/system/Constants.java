package com.exa.framelib_rrm.base.model.system;

import com.exa.framelib_rrm.BuildConfig;

/**
 * Created by acer on 2017/7/17.
 * 一些常量
 */
public class Constants {

    //网络请求默认超时时间
    public static final int DEFAILT_TIMEOUT = 10;

    //log相关
    public static final String LOG_TAG = "framelib_rrm";
    public static boolean IS_DEBUG = BuildConfig.DEBUG;

    //是否缓存了网络数据
    public static boolean cacheNet = true;//RetrofitHelper使用了缓存拦截器，所以这里设置为true
}
