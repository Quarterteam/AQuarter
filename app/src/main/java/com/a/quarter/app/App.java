package com.a.quarter.app;

import com.a.quarter.model.bean.login.User;
import com.a.quarter.model.utils.Constants;
import com.exa.framelib_rrm.app.BaseApp;
import com.exa.framelib_rrm.retrofit.RetrofitHelper;
import com.exa.framelib_rrm.utils.LogUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by acer on 2017/7/21.
 */
public class App extends BaseApp {
    {
        PlatformConfig.setQQZone("1106087531", "QhOqaUeYytKEeeBB");
    }
    //用户信息
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitHelper.init(Constants.BASE_URL);
        //获取用户信息
        user = User.getUserInfo();
        //LogUtils.i("userid="+user.userId);//userid=113 15910488415
        //TODO QQ配置
        UMShareAPI.get(this);
        // TODO: 初始化Fresco
        Fresco.initialize(this);
    }

    //获取本身的实例
    public static App getInstance(){
        return (App)getInstance0();
    }

    //判断是否已登录
    public static boolean isLogin(){
        return getInstance().user!=null;
    }

    //保存用户信息
    public void saveUserInfo(User user) {
        if(user!=null && User.saveUserInfo(user)){
            this.user = user;
        }else{
            this.user = null;
        }
    }

    //清空用户信息
    public void clearUserInfo() {
        if(isLogin()){
            User.clearUserInfo();
            this.user = null;
        }
    }

    //获取用户对象
    public static User getUser() {
        return getInstance().user;
    }

}
