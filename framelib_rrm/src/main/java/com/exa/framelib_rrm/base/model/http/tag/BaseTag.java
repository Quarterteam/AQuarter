package com.exa.framelib_rrm.base.model.http.tag;

/**
 * Created by acer on 2017/7/16.
 * 用于标记Presenter里哪个方法调用的Callback的方法
 */
public class BaseTag {

    public static final byte TAG_INVALID = -1;
    public byte tag;

    public BaseTag(byte tag) {
        this.tag = tag;
    }

    public BaseTag() {
        this.tag = TAG_INVALID;
    }

}
