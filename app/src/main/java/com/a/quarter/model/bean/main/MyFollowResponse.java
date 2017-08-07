package com.a.quarter.model.bean.main;

import com.a.quarter.model.bean.login.User;

import java.util.ArrayList;

/**
 * Created by acer on 2017/8/4.
 * 我的关注
 * {"code":500}
 * {"code":"200","user":[{"userHead":"aaa","userId":0,"userName":"aa","userPhone":"123456","userSex":"男"}]}
 */
public class MyFollowResponse {

    public String code;
    public ArrayList<User> user;

}
