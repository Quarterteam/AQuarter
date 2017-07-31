package com.a.quarter.model.bean.recommend;

import android.net.Uri;

/**
 * Created by acer on 2017/7/29.
 * 首页热门推荐，RecyclerView的条目对应的实体类
 */
public class ContentListBean {

    public int type;//条目类型
    public boolean isOpen;//是否已展开屏蔽，复制链接，举报三个图标
    public boolean isHearted;//是否已..
    public boolean isStared;//是否已收藏
//    public String videoTitle;
//    public String videoMediumUrl;
//    public String videoHdUrl;
    public int videoThumbResourceId;
    public Uri videoUri;
    public int imgResourceId;

    public int userIconResourceId;
    public String userName;
    public String time;
    public String content;
    public int heartCount;
    public int starCount;
    public int transmitCount;
    public String commentCount;

    public String commentUserName1;
    public String commentUserName2;
    public String commentContent1;
    public String commentContent2;

    public boolean isGoodComment;

    public ContentListBean(int type) {
        this.type = type;
    }

//    public ContentListBean video(String title, String videoUrl, String videoHdUrl) {
//        this.videoTitle = title;
//        this.videoMediumUrl = videoUrl;
//        this.videoHdUrl = videoHdUrl;
//        this.thumb = R.mipmap.banner3;
//        return this;
//    }
    public ContentListBean video(Uri videoUri, int videoThumbResourceId) {
        this.videoUri = videoUri;
        this.videoThumbResourceId = videoThumbResourceId;
        return this;
    }

    public ContentListBean image(int imgResourceId) {
        this.imgResourceId = imgResourceId;
        return this;
    }

    public ContentListBean(int type, boolean isHearted, boolean isStared, int userIconResourceId, String userName, String time, String content, int heartCount, int starCount, int transmitCount, String commentCount, String commentUserName1, String commentContent1, String commentUserName2, String commentContent2, boolean isGoodComment) {
        this.type = type;
        this.isHearted = isHearted;
        this.isStared = isStared;
        this.userIconResourceId = userIconResourceId;
        this.userName = userName;
        this.time = time;
        this.content = content;
        this.heartCount = heartCount;
        this.starCount = starCount;
        this.transmitCount = transmitCount;
        this.commentCount = commentCount;
        this.commentUserName1 = commentUserName1;
        this.commentUserName2 = commentUserName2;
        this.commentContent1 = commentContent1;
        this.commentContent2 = commentContent2;
        this.isGoodComment = isGoodComment;
    }
}
