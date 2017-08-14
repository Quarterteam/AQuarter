package com.a.quarter.presenter.main;

import com.a.quarter.model.api.Api;
import com.a.quarter.model.bean.main.EditSignResponse;
import com.a.quarter.model.bean.main.MyFollowResponse;
import com.exa.framelib_rrm.retrofit.RetrofitHelper;
import com.exa.framelib_rrm.rx.RxBasePresenter;
import com.exa.framelib_rrm.rx.RxHelper;

import io.reactivex.Observable;

/**
 * Created by acer on 2017/8/8.
 */
public class MainPresenter extends RxBasePresenter{

    public void editSign(String newSign, int userId){
        if(preCheck(true, null, newSign)){
            Observable<EditSignResponse> editSignResult = RetrofitHelper
                    .createApi(Api.class)
                    .editSign(userId, newSign);
            RxHelper.asyncGet(editSignResult, null, this);
        }
    }

}
