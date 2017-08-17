package com.a.quarter.model.utils;

/**
 * Created by acer on 2017/7/21.
 */
public class Constants {

    public static final String BASE_URL = "http://192.168.1.100/quarter/";

    public static final String LOGIN = "user/addLogin";//登录 POST
    public static final String REGISTER = "user/addUser";//注册 POST
    public static final String PUBLISH_ARTICLE = "picture/pictureUpload";//发表文章 POST
    public static final String GET_VERTIFY_CODE = "";//获取验证码
    public static final String NEXT_STEP = "";//获取验证码后的下一步
    public static final String CHANGE_PASSWORD = "";//更改密码
    public static final String VIDEO_HOT = "media/showMedia";//视频  热点

    public static final String DETAILS_PRAISE = "picture/AddNice";//详情 点赞

    public static final String DETAILS_TRAMPLE = "picture/AddBad";//详情 踩

    public static final String DETAILS_COMMENT = "picture/AddComment";//详情  评论

    public static final String JOKEURL="character/select_character";

    public static final String MY_FOLLOW="user/myFollow";//我的关注
    public static final String EDIT_SIGN="user/upUsersign";//用户修改个性签名
    public static final String FIND_USER_BY="user/findUserBy";//根据条件查询用户查询用
    public static final String ADD_CONCERN="user/addConcern";//5.用户添加关注接口
    public static final String PUBLISH_VIDEO="media/uploadMedia";//13.用户上传视频的接口
}
