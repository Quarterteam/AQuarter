package com.a.quarter.model.api;

import com.a.quarter.model.bean.DetailsPraiseBean;
import com.a.quarter.model.bean.joke.JokeBean;
import com.a.quarter.model.bean.login.ChangePwdResponse;
import com.a.quarter.model.bean.login.LoginResponse;
import com.a.quarter.model.bean.login.RegisterResponse;
import com.a.quarter.model.bean.login.VertifyCodeResponse;
import com.a.quarter.model.bean.main.EditSignResponse;
import com.a.quarter.model.bean.main.FindUserResponse;
import com.a.quarter.model.bean.main.MyFollowResponse;
import com.a.quarter.model.bean.video.DetailsCommemt;
import com.a.quarter.model.bean.video.DetailsTrample;
import com.a.quarter.model.bean.video.VHotBean;
import com.a.quarter.model.bean.video.VVcicinity;
import com.a.quarter.model.utils.Constants;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
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

    //用户修改个性签名
//    @GET(Constants.EDIT_SIGN)
//    Observable<EditSignResponse> editSign(@Query("userId") int userId, @Query("userSignature") String newSign);
    @POST(Constants.EDIT_SIGN)
    @FormUrlEncoded
    Observable<EditSignResponse> editSign(@Field("userId") int userId, @Field("userSignature") String newSign);

    //根据条件查询用户查询用
    @POST(Constants.FIND_USER_BY)
    Observable<FindUserResponse> findUserBy(@Query("value") String value);

    //5.用户添加关注接口
    @POST(Constants.ADD_CONCERN)
    @FormUrlEncoded
    Observable<String> addConcern(@Field("UserId") int UserId, @Field("Beuserid") int Beuserid);
    //段子接口 信息
    @GET(Constants.JOKE_URL)
    Observable<JokeBean> jokeData(@Query("currentpage") int currentpage);
    //段子点赞数
    @GET(Constants.JOKE_ADDNICE)
    Observable<String> jokeAddNice(@Query("nicekey") String nicekey);
    //段子转发数
    @GET(Constants.JOKE_ADDFORWARD)
    Observable<String> jokeAddForward(@Query("forwardkey") String forwardkey);
    //段子踩数
    @GET(Constants.JOKE_ADDBAD)
    Observable<String> jokeAddBad(@Query("badkey") String badkey);

    @POST(Constants.PUBLISH_VIDEO)
    Observable<String> publishVideo(@Body RequestBody Body);
}
