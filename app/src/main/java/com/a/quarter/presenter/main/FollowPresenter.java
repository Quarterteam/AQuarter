package com.a.quarter.presenter.main;

import com.a.quarter.model.api.Api;
import com.a.quarter.model.bean.login.LoginResponse;
import com.a.quarter.model.bean.main.FindUserResponse;
import com.a.quarter.model.bean.main.MyFollowResponse;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.retrofit.RetrofitHelper;
import com.exa.framelib_rrm.rx.RxBasePresenter;
import com.exa.framelib_rrm.rx.RxHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import io.reactivex.Observable;

/**
 * Created by acer on 2017/8/4.
 * 我的关注
 */
public class FollowPresenter extends RxBasePresenter {

    //如果是第三方登录，id怎么办？
    //获取我的关注列表
    public void getMyFollowList(int userId){
        if(preCheck(true, null, userId)){
            Observable<MyFollowResponse> myFollow = RetrofitHelper
                    .createApi(Api.class)
                    .getMyFollowList(userId);
            RxHelper.asyncGet(myFollow, null, this);
        }
    }

    //6.查询用户信息接口（根据条件查询用户查询用户）
    public void findUserByKeyword(BaseTag tag, String keyword){
        if(preCheck(true, tag, keyword)){
            try {
                String encodedKeyWord = URLEncoder.encode(keyword, "UTF-8");//防止中文会乱码，导致请求未成功的问题出现
                Observable<FindUserResponse> findUserBy = RetrofitHelper
                        .createApi(Api.class)
                        //.findUserBy(keyword);
                        .findUserBy(encodedKeyWord);
                RxHelper.asyncGet(findUserBy, tag, this);
            } catch (UnsupportedEncodingException e) {
                //e.printStackTrace();
                getMvpView().onError(e, tag);
            }
        }
    }

    //5.用户添加关注接口
    public void addConcern(BaseTag tag, int userId, int beuserid){
        if(preCheck(true, tag, userId, beuserid)){
            Observable<String> myFollow = RetrofitHelper
                    .createApi(Api.class)
                    .addConcern(userId, beuserid);
            RxHelper.asyncGet(myFollow, tag, this);
        }
    }

}
