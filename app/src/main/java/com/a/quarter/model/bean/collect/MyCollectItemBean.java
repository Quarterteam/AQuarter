package com.a.quarter.model.bean.collect;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/8/2.
 */

public class MyCollectItemBean {
    private boolean isDelVisibility;
    private boolean isAnimshow;
    private boolean isPlay;
    private boolean isLike;
    private boolean isCollect;

    public MyCollectItemBean(boolean isDelVisibility, boolean isAnimshow, boolean isPlay, boolean isLike, boolean isCollect) {
        this.isDelVisibility = isDelVisibility;
        this.isAnimshow = isAnimshow;
        this.isPlay = isPlay;
        this.isLike = isLike;
        this.isCollect = isCollect;
    }

    public boolean isDelVisibility() {
        return isDelVisibility;
    }

    public void setDelVisibility(boolean delVisibility) {
        isDelVisibility = delVisibility;
    }

    public boolean isAnimshow() {
        return isAnimshow;
    }

    public void setAnimshow(boolean animshow) {
        isAnimshow = animshow;
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setPlay(boolean play) {
        isPlay = play;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }
}
