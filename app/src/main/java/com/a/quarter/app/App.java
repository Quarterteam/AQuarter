package com.a.quarter.app;

import com.a.quarter.model.bean.login.User;
import com.a.quarter.model.utils.Constants;
import com.exa.framelib_rrm.app.BaseApp;
import com.exa.framelib_rrm.retrofit.RetrofitHelper;

/**
 * Created by acer on 2017/7/21.
 */
public class App extends BaseApp {

    //用户信息
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitHelper.init(Constants.BASE_URL);

        //获取用户信息
        user = User.getUserInfo();
    }

    //获取本身的实例
    public static App getInstance(){
        return (App)getInstance0();
    }

    //判断是否已登录
    public static boolean isLogin(){
        return getInstance().user!=null;
    }

    public void saveUserInfo(User user) {
        if(user!=null && User.saveUserInfo(user)){
            this.user = user;
        }else{
            this.user = null;
        }
    }

    public void clearUserInfo() {
        if(isLogin()){
            User.clearUserInfo();
            this.user = null;
        }
    }

    public static User getUser() {
        return getInstance().user;
    }

}
