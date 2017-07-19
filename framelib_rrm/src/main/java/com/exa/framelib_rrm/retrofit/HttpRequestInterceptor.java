package com.exa.framelib_rrm.retrofit;

import android.os.Build;

import com.exa.framelib_rrm.BuildConfig;
import com.exa.framelib_rrm.utils.FrameLifeCircleLogUtils;
import com.exa.framelib_rrm.utils.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by acer on 2017/6/13.
 * 日志拦截器
 * 打印正在请求的接口url
 */
public class HttpRequestInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain
                .request()
                .newBuilder();

//        if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
//            builder.addHeader("Connection", "close");//为了防止请求过于频繁时出现错误，引起程序崩溃
//        }
        Request request = builder.build();
        if(BuildConfig.DEBUG){
            LogUtils.i("发送请求："+request.url()+"，connection："+chain.connection()+"，headers："+request.headers());
        }
        return chain.proceed(request);// java.net.SocketTimeoutException: failed to connect to /192.168.56.1 (port 80) after 10000ms
    }//这里抛出了异常，会在onError方法里接收到
}
