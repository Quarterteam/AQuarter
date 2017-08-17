package com.a.quarter.presenter.joke;

import com.a.quarter.model.api.Api;
import com.a.quarter.model.bean.joke.JokeBean;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.retrofit.RetrofitHelper;
import com.exa.framelib_rrm.rx.RxBasePresenter;
import com.exa.framelib_rrm.rx.RxHelper;

import io.reactivex.Observable;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/31.
 */

public class JokePresenter extends RxBasePresenter {
    //段子数据
    public void getDataFrom(BaseTag tag, int currentpage) {
        if (preCheck(true,tag)) {
            Observable<JokeBean> jokeData = RetrofitHelper
                    .createApi(Api.class)
                    .jokeData(currentpage);
            RxHelper.asyncGet(jokeData, tag, this);
        }
    }
    //段子点赞
    public void getDataFromNice(BaseTag tag,String nicekey) {
        if (preCheck(true,tag)) {
            Observable<String> addNice = RetrofitHelper
                    .createApi(Api.class)
                    .jokeAddNice(nicekey);
            RxHelper.asyncGet(addNice, tag, this);
        }
    }
    //段子踩
    public void getDataFormBad(BaseTag tag,String badkey) {
        if (preCheck(true, null)) {
            Observable<String> addBad = RetrofitHelper
                    .createApi(Api.class)
                    .jokeAddBad(badkey);
            RxHelper.asyncGet(addBad, tag, this);
        }
    }
    //段子转发
    public void getDataFormForward(BaseTag tag,String forwardkey) {
        if (preCheck(true, null)) {
            Observable<String> addForward = RetrofitHelper
                    .createApi(Api.class)
                    .jokeAddForward(forwardkey);
            RxHelper.asyncGet(addForward, tag, this);
        }
    }
}
