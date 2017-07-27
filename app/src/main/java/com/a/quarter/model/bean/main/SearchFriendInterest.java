package com.a.quarter.model.bean.main;

import com.a.quarter.view.adapter.main.SearchFriendListAdapter;

/**
 * Created by acer on 2017/7/25.
 * 搜索好友的历史记录，条目对应的实体类
 */
public class SearchFriendInterest extends MultiTypeItemBean{

    public String imgUrl;
    public String name;
    public String info;

    public SearchFriendInterest(String name, String info) {
        super(SearchFriendListAdapter.TYPE_INTEREST);
        this.name = name;
        this.info = info;
    }
}
