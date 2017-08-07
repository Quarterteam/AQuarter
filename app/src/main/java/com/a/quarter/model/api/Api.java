package com.a.quarter.model.api;

import com.a.quarter.model.bean.DetailsPraiseBean;
import com.a.quarter.model.bean.joke.JokeBean;
import com.a.quarter.model.bean.login.ChangePwdResponse;
import com.a.quarter.model.bean.login.LoginResponse;
import com.a.quarter.model.bean.login.RegisterResponse;
import com.a.quarter.model.bean.login.VertifyCodeResponse;
import com.a.quarter.model.bean.main.MyFollowResponse;
import com.a.quarter.model.bean.video.DetailsCommemt;
import com.a.quarter.model.bean.video.DetailsTrample;
import com.a.quarter.model.bean.video.VHotBean;
import com.a.quarter.model.bean.video.VVcicinity;
import com.a.quarter.model.utils.Constants;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @POST(Constants.GET_VERTIFY_CODE)
    @FormUrlEncoded
    Observable<VertifyCodeResponse> getVertifyCode(@Field("phone") String phone);

    @POST(Constants.NEXT_STEP)
    @FormUrlEncoded
    Observable<String> nextStep(@Field("phone") String phone, @Field("vertify_code") String vertifyCode);

    @POST(Constants.CHANGE_PASSWORD)
    @FormUrlEncoded
    Observable<ChangePwdResponse> changePwd(@Field("password") String pwd);
    //段子
    @GET(Constants.JOKEURL)
    Observable<JokeBean> jokeData();



    @GET(Constants.VIDEO_HOT)  // TODO: 视频中 热门
    Observable<VHotBean> VHotFrag();

    @GET(Constants.VIDEO_HOT)  // TODO: 视频中 附近
    Observable<VVcicinity> vcicinity();

    @GET(Constants.DETAILS_PRAISE)  // TODO: 详情 赞
    Observable<DetailsPraiseBean> detailsPraise();

    @GET(Constants.DETAILS_TRAMPLE)  // TODO: 详情 踩
    Observable<DetailsTrample> detailstrample();

    @GET(Constants.DETAILS_COMMENT)  // TODO: 详情 评论
    Observable<DetailsCommemt> detailsCommemt();

    //我的关注
    @GET(Constants.MY_FOLLOW)
    Observable<MyFollowResponse> getMyFollowList(@Query("Userid") int Userid);


}
