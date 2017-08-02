package com.a.quarter.model.bean.joke;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/8/1.
 */

public class JokeItemBean {
    private boolean isRigth;
    private boolean isfollow;
    private boolean iscomment;
    private JokeBean.Characterp jokeBean;

    public JokeItemBean(boolean isRigth, boolean isfollow, boolean iscomment, JokeBean.Characterp jokeBean) {
        this.isRigth = isRigth;
        this.isfollow = isfollow;
        this.iscomment = iscomment;
        this.jokeBean = jokeBean;
    }

    public boolean isRigth() {
        return isRigth;
    }

    public void setRigth(boolean rigth) {
        isRigth = rigth;
    }

    public boolean isfollow() {
        return isfollow;
    }

    public void setIsfollow(boolean isfollow) {
        this.isfollow = isfollow;
    }

    public boolean iscomment() {
        return iscomment;
    }

    public void setIscomment(boolean iscomment) {
        this.iscomment = iscomment;
    }

    public JokeBean.Characterp getJokeBean() {
        return jokeBean;
    }

    public void setJokeBean(JokeBean.Characterp jokeBean) {
        this.jokeBean = jokeBean;
    }
}
