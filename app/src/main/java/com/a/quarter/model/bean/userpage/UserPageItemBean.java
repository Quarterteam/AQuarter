package com.a.quarter.model.bean.userpage;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/8/2.
 */

public class UserPageItemBean {
    private boolean isAnimshow;//动画展开
    private boolean isPlay;

    public UserPageItemBean(boolean isAnimshow, boolean isPlay) {
        this.isAnimshow = isAnimshow;
        this.isPlay = isPlay;
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
}
