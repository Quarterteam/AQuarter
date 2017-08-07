package com.a.quarter.model.bean.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.a.quarter.app.App;

/**
 * Created by acer on 2017/7/21.
 * {"code":500，"message", "用户名或密码有误登陆失败！"}
 * {"code":"200","user":{"userHead":"打开疯狂的","userId":6,"userName":"小明","userPassword":"111","userPhone":"13011196165","userSex":"男"}}
 */
public class User {

    public String loginType;
    public String userHead;
    //public String userId;
    public int userId;
    public String userName;
    public String userPassword;
    public String userPhone;
    public String userSex;
    public String userPasswordConfirm;
    public String expiration;//过期时间（qq登录）

    public static boolean saveUserInfo(User user){
        SharedPreferences sp = App.getInstance().
                getSharedPreferences("userdata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loginType",user.loginType);
        editor.putString("userName",user.userName);
        editor.putInt("userId",user.userId);
        editor.putString("userHead",user.userHead);
        //editor.putString("userPassword",user.userPassword);
        editor.putString("userPhone",user.userPhone);
        editor.putString("userSex",user.userSex);
        editor.putString("expiration",user.expiration);
        return editor.commit();
    }

    public static boolean clearUserInfo(){
        SharedPreferences sp = App.getInstance().
                getSharedPreferences("userdata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("loginType",null);
        editor.putString("userName",null);
        editor.putInt("userId",-1);
        editor.putString("userHead",null);
        //editor.putString("userPassword",null);
        editor.putString("userPhone",null);
        editor.putString("userSex",null);
        editor.putString("expiration",null);
        return editor.commit();
    }

    public static User getUserInfo(){
        SharedPreferences sp = App.getInstance().
                getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String username = sp.getString("userName",null);
        if(!TextUtils.isEmpty(username)){

            User user = new User();
            user.userName = username;
            user.loginType = sp.getString("loginType", null);
            user.userId = sp.getInt("userId",-1);
            user.userHead = sp.getString("userHead",null);
            //user.userPassword = sp.getString("userPassword",null);
            user.userPhone = sp.getString("userPhone",null);
            user.userSex = sp.getString("userSex",null);
            user.expiration = sp.getString("expiration",null);

            return user;
        }

        return null;
    }

    public void reset() {
        loginType = null;
        userName = null;
        userId = -1;
        userHead = null;
        userPassword = null;
        userPhone = null;
        userSex = null;
        expiration = null;
    }
}
