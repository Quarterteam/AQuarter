package com.a.quarter.model.bean.main;

import com.a.quarter.view.adapter.main.SearchFriendListAdapter;

/**
 * Created by acer on 2017/7/25.
 * 搜索好友的历史记录，条目对应的实体类
 * "userHead":"aaa","userId":0,"userName":"aa","userPhone":"123456","userSex":"男"
 */
public class SearchFriendBean extends MultiTypeItemBean{

    public String userHead;
    public int userId = -1;
    public String userName;
    public String userPhone;
    public String userSex;
    public boolean isAlreadyFollowed;//是否已经关注过

//    public SearchFriendInterest(String name, String info) {
//        super(SearchFriendListAdapter.TYPE_INTEREST);
//        this.name = name;
//        this.info = info;
//    }
}
