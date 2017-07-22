package com.a.quarter.presenter.publish;

import com.a.quarter.model.api.Api;
import com.a.quarter.model.bean.RegisterResponse;
import com.exa.framelib_rrm.retrofit.RetrofitHelper;
import com.exa.framelib_rrm.rx.RxBasePresenter;
import com.exa.framelib_rrm.rx.RxHelper;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by acer on 2017/7/22.
 * 发表文章页面对应的Presenter
 */
public class PublishArticlePresenter extends RxBasePresenter{

    //发表文章
    public void publish(HashMap<String, String> map){
        if(preCheck(true, null, map)){
            Observable<RegisterResponse> observable = RetrofitHelper
                    .createApi(Api.class)
                    .publishArticle(map);

            RxHelper.asyncGet(observable, null, this);
        }
    }


}
