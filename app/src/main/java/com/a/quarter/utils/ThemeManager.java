package com.a.quarter.utils;

import com.a.quarter.app.App;
import com.exa.framelib_rrm.base.view.view.BaseThemeManager;

/**
 * Created by acer on 2017/7/26.
 * 日夜间模式管理类
 * http://www.jb51.net/article/93852.htm
 */
public class ThemeManager extends BaseThemeManager{


    public static void switchThemeMode(){
        setThemeMode(getThemeMode() == ThemeMode.DAY ? ThemeMode.NIGHT : ThemeMode.DAY);
    }

    public static int getCurrentThemeRes(int dayResId) {
        return getCurrentThemeRes(App.getInstance(), dayResId);
    }

}
