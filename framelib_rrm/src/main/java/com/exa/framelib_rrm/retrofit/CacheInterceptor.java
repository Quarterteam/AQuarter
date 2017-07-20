package com.exa.framelib_rrm.retrofit;

import android.util.Log;

import com.exa.framelib_rrm.utils.NetUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by acer on 2017/7/18.
 * 缓存拦截器
 * http://blog.csdn.net/u010286855/article/details/52608485
 *
 * 两种缓存：
 * 一、无论有无网络我们都去获取缓存的数据（我们会设置一个缓存时间，在某一段时间内（例如60S）去获取缓存数据。超过60S我们就去网络重新请求数据）
 * 二、有网络的时候我们就去直接获取网络上面的数据。当没有网络的时候我们就去缓存获取数据。
 *
 * 这里用的是第二种方式
 */
public class CacheInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
//        if(NetUtils.isConnected()){
//            return chain.proceed(chain.request())
//                    .newBuilder()
//                    .removeHeader("pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
//                    .removeHeader("Cache-Control")
//                    .header("Cache-Control","max-age=60")
//                    .build();
//        }else{//没有网络的话，就去缓存里面取数据
//            Request request = chain.request()
//                    .newBuilder()
//                    .cacheControl(CacheControl.FORCE_CACHE)
//                    //.cacheControl(CacheControl.CACHED_ELSE_NETWORK)
//                    .build();
//            return chain.proceed(request);
//        }

        Request request = chain.request();//获取请求
        //这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上面的数据，要是没有网络的话我么就去缓存里面取数据
        if(!NetUtils.isConnected()){
            request = request.newBuilder()
                    //这个的话内容有点多啊，大家记住这么写就是只从缓存取，想要了解这个东西我等下在
                    // 给大家写连接吧。大家可以去看下，获取大家去找拦截器资料的时候就可以看到这个方面的东西反正也就是缓存策略。
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Log.d("CacheInterceptor","no network");
        }
        Response originalResponse = chain.proceed(request);
        if(NetUtils.isConnected()){
            //这里大家看点开源码看看.header .removeHeader做了什么操作很简答，就是的加字段和减字段的。
            //String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder()
                    //这里设置的为0就是说不进行缓存，我们也可以设置缓存时间
                    .header("Cache-Control", "public, max-age=" + 0)
                    .removeHeader("Pragma")
                    .build();
        }else{
            int maxTime = 4*24*60*60;
            return originalResponse.newBuilder()
                    //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                    .header("Cache-Control", "public, only-if-cached, max-stale="+maxTime)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
