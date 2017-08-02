package com.a.quarter.model.bean.collect;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/8/2.
 */

public class MyCollectItemBean {
    private boolean isVisibility;
    private boolean isAnimshow;
    private boolean isPlay;

    public MyCollectItemBean(boolean isVisibility, boolean isAnimshow, boolean isPlay) {
        this.isVisibility = isVisibility;
        this.isAnimshow = isAnimshow;
        this.isPlay = isPlay;
    }

    public boolean isVisibility() {
        return isVisibility;
    }

    public void setVisibility(boolean visibility) {
        isVisibility = visibility;
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
