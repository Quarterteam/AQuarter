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

    @POST
    Observable<String> simplePost(@Url String url, @FieldMap Map<String, String> map);

    @GET
    Observable<String> simpleGet(@Url String url);

}
