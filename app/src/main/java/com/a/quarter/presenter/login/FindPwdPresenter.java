package com.a.quarter.presenter.login;

import com.a.quarter.model.api.Api;
import com.a.quarter.model.bean.login.ChangePwdResponse;
import com.a.quarter.model.bean.login.VertifyCodeResponse;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.retrofit.RetrofitHelper;
import com.exa.framelib_rrm.rx.RxBasePresenter;
import com.exa.framelib_rrm.rx.RxHelper;

import io.reactivex.Observable;

/**
 * Created by acer on 2017/7/28.
 * 找回密码页面
 */
public class FindPwdPresenter extends RxBasePresenter {

    //获取验证码
    public void getVertifyCode(BaseTag tag, String phone){
        if(preCheck(true, tag, phone)){
            //TODO
            Observable<VertifyCodeResponse> o = RetrofitHelper.createApi(Api.class).getVertifyCode(phone);
            RxHelper.asyncGet(o, tag, this);
        }
    }

    //获取验证码后，下一步
    public void nextStep(BaseTag tag, String phone, String vertifyCode){
        if(preCheck(true, tag, phone, vertifyCode)){
            //TODO
            Observable<String> o = RetrofitHelper.createApi(Api.class).nextStep(phone, vertifyCode);
            RxHelper.asyncGet(o, tag, this);
        }
    }

    //更改密码
    public void changePwd(String pwd, String pwdConfirm) {
        if(preCheck(true, null, pwd, pwdConfirm)){
            //TODO
            Observable<ChangePwdResponse> o = RetrofitHelper.createApi(Api.class).changePwd(pwd);
            RxHelper.asyncGet(o, null, this);
        }
    }
}
