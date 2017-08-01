package com.a.quarter.view.iview;

import com.exa.framelib_rrm.base.view.BaseIView;

/**
 * 类的作用：
 * 实现思路 ：
 * auther:  郭鸽鸽
 * date ： 2017/7/31.
 */

public interface JokeView<T> extends BaseIView{
    public void callbachData(T t);
}
