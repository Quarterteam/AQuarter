package com.a.quarter.model.bean.main;

import java.util.ArrayList;

/**
 * Created by acer on 2017/8/4.
 * 搜索好友
 * {"code":500}
 * {"code":"200","user":[{"userHead":"aaa","userId":0,"userName":"aa","userPhone":"123456","userSex":"男"}]}
 */
public class FindUserResponse {

    public String code;
//    public ArrayList<User> user;
    public ArrayList<SearchFriendBean> user;

}
