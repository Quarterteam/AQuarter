package com.exa.framelib_rrm.utils;

import android.util.Log;

/**
 * Created by acer on 2017/6/9.
 */
public class LogUtils {
    
    public static final String TAG = "framelib_rrm";
    public static final String MSG = "----->";

    public static void e(Throwable tr){
        Log.e(TAG, MSG, tr);
    }

    public static void i(Throwable tr){
        Log.i(TAG, MSG, tr);
    }

    public static void d(Throwable tr){
        Log.d(TAG, MSG, tr);
    }

    public static void v(Throwable tr){
        Log.v(TAG, MSG, tr);
    }

    public static void w(Throwable tr){
        Log.w(TAG, MSG, tr);
    }

    public static void wtf(Throwable tr){
        Log.wtf(TAG, MSG, tr);
    }

    ///////

    public static void e(String msg){
        Log.e(TAG, msg);
    }

    public static void i(String msg){
        Log.i(TAG, msg);
    }

    public static void d(String msg){
        Log.d(TAG, msg);
    }

    public static void v(String msg){
        Log.v(TAG, msg);
    }

    public static void w(String msg){
        Log.w(TAG, msg);
    }

    public static void wtf(String msg){
        Log.wtf(TAG, msg);
    }

    //////

    public static void e(String msg, Throwable tr){
        Log.e(TAG, MSG + msg, tr);
    }

    public static void i(String msg, Throwable tr){
        Log.i(TAG, MSG + msg, tr);
    }

    public static void d(String msg, Throwable tr){
        Log.d(TAG, MSG + msg, tr);
    }

    public static void v(String msg, Throwable tr){
        Log.v(TAG, MSG + msg, tr);
    }

    public static void w(String msg, Throwable tr){
        Log.w(TAG, MSG + msg, tr);
    }

    public static void wtf(String msg, Throwable tr){
        Log.wtf(TAG, MSG + msg, tr);
    }
}
