package com.a.quarter.presenter.VHotFragDetails;

import com.a.quarter.model.api.Api;
import com.a.quarter.model.bean.DetailsPraiseBean;
import com.a.quarter.model.bean.video.DetailsCommemt;
import com.a.quarter.model.bean.video.DetailsTrample;
import com.exa.framelib_rrm.retrofit.RetrofitHelper;
import com.exa.framelib_rrm.rx.RxBasePresenter;
import com.exa.framelib_rrm.rx.RxHelper;

import io.reactivex.Observable;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class DetailsPresenter extends RxBasePresenter{

    // TODO: 点赞
    public void getDetailsPraise(){
        if(preCheck(true, null)) {
            Observable<DetailsPraiseBean> vhot = RetrofitHelper
                    .createApi(Api.class)
                    .detailsPraise();
            RxHelper.asyncGet(vhot, null, this);
        }
    }
    // TODO: 踩
    public void getdetailstrample(){
        if(preCheck(true, null)) {
            Observable<DetailsTrample> vhot = RetrofitHelper
                    .createApi(Api.class)
                    .detailstrample();
            RxHelper.asyncGet(vhot, null, this);
        }
    }
    // TODO: 评论
    public void getdetailsCommemt(){
        if(preCheck(true, null)) {
            Observable<DetailsCommemt> vhot = RetrofitHelper
                    .createApi(Api.class)
                    .detailsCommemt();
            RxHelper.asyncGet(vhot, null, this);
        }
    }

}
