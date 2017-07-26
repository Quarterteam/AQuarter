package com.a.quarter.presenter.login;

import com.a.quarter.model.api.Api;
import com.a.quarter.model.bean.login.LoginResponse;
import com.a.quarter.model.bean.login.RegisterResponse;
import com.a.quarter.model.bean.login.User;
import com.exa.framelib_rrm.retrofit.RetrofitHelper;
import com.exa.framelib_rrm.rx.RxBasePresenter;
import com.exa.framelib_rrm.rx.RxHelper;

import java.util.HashMap;

import io.reactivex.Observable;

/**
 * Created by acer on 2017/7/21.
 */
public class LoginPresenter extends RxBasePresenter{

    //登录
    public void login(String userPhone, String password){
        if(preCheck(true, null, userPhone, password)){
            Observable<LoginResponse> login = RetrofitHelper
                    .createApi(Api.class)
                    .login(userPhone, password);

            RxHelper.asyncGet(login, null, this);
        }
    }

    //注册
    public void register(User user){
        if(preCheck(true, null, user)){
            HashMap<String , String> map = new HashMap<String, String>();
            map.put("userName",user.userName);
            map.put("userHead",user.userHead);
            map.put("userPassword",user.userPassword);
            map.put("userPhone",user.userPhone);
            map.put("userSex",user.userSex);
            Observable<RegisterResponse> register = RetrofitHelper
                    .createApi(Api.class)
                    .register(map);

            RxHelper.asyncGet(register, null, this);
        }
    }

}
