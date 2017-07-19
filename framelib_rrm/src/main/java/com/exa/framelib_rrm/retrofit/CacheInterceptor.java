package com.exa.framelib_rrm.retrofit;

import com.exa.framelib_rrm.utils.NetUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by acer on 2017/7/18.
 * 缓存拦截器
 *
 * 两种缓存：
 * 一、无论有无网络我们都去获取缓存的数据（我们会设置一个缓存时间，在某一段时间内（例如60S）去获取缓存数据。超过60S我们就去网络重新请求数据）
 * 二、有网络的时候我们就去直接获取网络上面的数据。当没有网络的时候我们就去缓存获取数据。
 */
public class CacheInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        if(NetUtils.isConnected()){
            return chain.proceed(chain.request())
                    .newBuilder()
                    .removeHeader("pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .removeHeader("Cache-Control")
                    .header("Cache-Control","max-age=60")
                    .build();
        }else{//没有网络的话，就去缓存里面取数据
            Request request = chain.request()
                    .newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    //.cacheControl(CacheControl.CACHED_ELSE_NETWORK)
                    .build();
            return chain.proceed(request);
        }
    }

}
