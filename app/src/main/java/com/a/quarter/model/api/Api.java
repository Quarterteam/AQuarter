package com.a.quarter.model.api;

import com.a.quarter.model.bean.login.LoginResponse;
import com.a.quarter.model.bean.login.RegisterResponse;
import com.a.quarter.model.utils.Constants;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by acer on 2017/7/21.
 */
public interface Api {

    @POST(Constants.LOGIN)
    @FormUrlEncoded
    Observable<LoginResponse> login(@Field("userPhone") String phone, @Field("userPassword") String password);

    @POST(Constants.REGISTER)
    @FormUrlEncoded
    Observable<RegisterResponse> register(@FieldMap HashMap<String, String> map);
    //java.lang.IllegalArgumentException: @FieldMap parameters can only be used with form encoding. (parameter #1)

    @POST(Constants.PUBLISH_ARTICLE)
    @FormUrlEncoded
    Observable<RegisterResponse> publishArticle(@FieldMap HashMap<String, String> map);

}
