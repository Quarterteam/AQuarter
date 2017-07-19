package com.exa.framelib_rrm.utils;

import android.util.Log;

import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.base.model.system.Constants;

/**
 * Created by acer on 2017/6/9.
 * 打印框架网络请求生命周期
 */
public class FrameLifeCircleLogUtils {
    
    public static final String TAG = "framelib_rrm";
    public static final String MSG = "MVP交互 生命周期----->";

    public static final boolean showLog = Constants.IS_DEBUG;

    public static void log(String methodName, BaseTag tag){
        if(showLog)
            Log.i(TAG, MSG+methodName+" "+(tag!=null?tag.tag:"no tag"));
    }

    public static void log(String methodName, BaseTag tag, String msg){
        if(showLog)
            Log.i(TAG, MSG+methodName+" "+(tag!=null?tag.tag:"no tag")+" "+msg);
    }

}
