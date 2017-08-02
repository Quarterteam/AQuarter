package com.a.quarter.presenter.video;

import com.a.quarter.model.api.Api;
import com.a.quarter.model.bean.video.VHotBean;
import com.exa.framelib_rrm.retrofit.RetrofitHelper;
import com.exa.framelib_rrm.rx.RxBasePresenter;
import com.exa.framelib_rrm.rx.RxHelper;

import io.reactivex.Observable;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class VHotPresenter extends RxBasePresenter{

    // TODO: 热门
    public  void    Vhot(){
        if(preCheck(true, null)) {
            Observable<VHotBean> vhot = RetrofitHelper
                    .createApi(Api.class)
                    .VHotFrag();
            RxHelper.asyncGet(vhot, null, this);
        }
    }

    // TODO: 附近
    public  void vcicinity(){

        if(preCheck(true, null)) {
            Observable<VHotBean> login = RetrofitHelper
                    .createApi(Api.class)
                    .VHotFrag();

            RxHelper.asyncGet(login, null, this);
        }
    }
}
