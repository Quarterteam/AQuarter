package com.a.quarter.model.bean.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.a.quarter.app.App;

/**
 * Created by acer on 2017/7/21.
 * {"error_code":"200","user":{"userHead":"emmm","userId":6,"userName":"唐清如","userPassword":"111","userPhone":"13011196165","userSex":"女"}}
 */
public class User {

    public String userHead;
    public int userId;
    public String userName;
    public String userPassword;
    public String userPhone;
    public String userSex;
    public String userPasswordConfirm;

    public static boolean saveUserInfo(User user){
        SharedPreferences sp = App.getInstance().
                getSharedPreferences("userdata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userName",user.userName);
        editor.putInt("userId",user.userId);
        editor.putString("userHead",user.userHead);
        editor.putString("userPassword",user.userPassword);
        editor.putString("userPhone",user.userPhone);
        editor.putString("userSex",user.userSex);
        return editor.commit();
    }

    public static boolean clearUserInfo(){
        SharedPreferences sp = App.getInstance().
                getSharedPreferences("userdata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userName",null);
        editor.putInt("userId",-1);
        editor.putString("userHead",null);
        editor.putString("userPassword",null);
        editor.putString("userPhone",null);
        editor.putString("userSex",null);
        return editor.commit();
    }

    public static User getUserInfo(){
        SharedPreferences sp = App.getInstance().
                getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String username = sp.getString("userName",null);
        if(!TextUtils.isEmpty(username)){

            User user = new User();
            user.userName = username;
            user.userId = sp.getInt("userId",-1);
            user.userHead = sp.getString("userHead",null);
            user.userPassword = sp.getString("userPassword",null);
            user.userPhone = sp.getString("userPhone",null);
            user.userSex = sp.getString("userSex",null);

            return user;
        }

        return null;
    }

    public void reset() {
        userName = null;
        userId = -1;
        userHead = null;
        userPassword = null;
        userPhone = null;
        userSex = null;
    }
}
