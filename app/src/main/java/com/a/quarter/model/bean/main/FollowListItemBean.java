package com.a.quarter.model.bean.main;

/**
 * Created by acer on 2017/7/24.
 */
public class FollowListItemBean {

    public String imgUrl;
    public String name;
    public String info;
    public String time;

    public FollowListItemBean(String name, String info, String time) {
        this.name = name;
        this.info = info;
        this.time = time;
    }
}
