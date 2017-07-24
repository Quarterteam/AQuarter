package com.exa.framelib_rrm.retrofit;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by acer on 2017/7/16.
 */
//因为方法返回值需要的泛型不一样，所以无法写成一两个方法统一代替所有接口对应的方法
public interface ApiService {

    /**GET请求，无参数，返回类型为json字符串*/
    @GET
    Observable<String> get(@Url String url);

    /**POST请求，无参数，返回类型为json字符串*/
    @POST
    Observable<String> post(@Url String url);

    /**POST请求，有参数，返回类型为son字符串*/
    @POST
    Observable<String> postWithParams(@Url String url, @FieldMap Map<String, String> map);

}
