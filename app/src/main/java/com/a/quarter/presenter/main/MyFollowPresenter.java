package com.a.quarter.presenter.main;

import com.a.quarter.model.api.Api;
import com.a.quarter.model.bean.login.LoginResponse;
import com.a.quarter.model.bean.main.MyFollowResponse;
import com.exa.framelib_rrm.retrofit.RetrofitHelper;
import com.exa.framelib_rrm.rx.RxBasePresenter;
import com.exa.framelib_rrm.rx.RxHelper;

import io.reactivex.Observable;

/**
 * Created by acer on 2017/8/4.
 * 我的关注
 */
public class MyFollowPresenter extends RxBasePresenter {

    //如果是第三方登录，id怎么办？
    public void getMyFollowList(int userId){
        if(preCheck(true, null, userId)){
            Observable<MyFollowResponse> myFollow = RetrofitHelper
                    .createApi(Api.class)
                    .getMyFollowList(userId);
            RxHelper.asyncGet(myFollow, null, this);
        }
    }

}
