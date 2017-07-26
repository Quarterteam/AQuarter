package com.a.quarter.model.bean.recommend;

/**
 * desc:
 * author:吴晓芳
 * date:
 */

public class ItemBean {
    private  String name;
    private  Boolean isCheck;

    public ItemBean(String name, Boolean isCheck) {
        this.name = name;
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }
}
