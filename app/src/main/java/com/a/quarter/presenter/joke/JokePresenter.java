package com.a.quarter.presenter.joke;

import com.a.quarter.model.api.Api;
import com.a.quarter.model.bean.joke.JokeBean;
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
    public void getDataFrom() {
        if (preCheck(true, null)) {
            Observable<JokeBean> jokeData = RetrofitHelper
                    .createApi(Api.class)
                    .jokeData();
            RxHelper.asyncGet(jokeData, null, this);
        }
    }
}
