package com.a.quarter.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.a.quarter.app.App;
import com.a.quarter.model.bean.main.SearchFriendBean;

import java.util.Map;

/**
 * Created by acer on 2017/8/10.
 * 搜索好友的搜索历史记录
 */
public class SearchFriendHistoryUtils {

    public static boolean saveHistory(String keyword, String iconUrl){
        Editor editor = getEditor();
        //editor.putString(keyword, keyword+","+(iconUrl!=null?iconUrl:""));
        //editor.putString(keyword+"_iconUrl", iconUrl);
        editor.putString(keyword, iconUrl!=null?iconUrl:"");//键是输入的关键字，值是头像地址（如果值是null的话，会保存失败）
        return editor.commit();
    }

    //保存的历史记录的顺序无法保证
    public static Map<String, ?> getAllHistorys(){
        return getSp().getAll();
    }

    public static boolean deleteHistory(String keyword){
        Editor editor = getEditor();
        editor.remove(keyword);
        //editor.remove(keyword+"_iconUrl");
        return editor.commit();
    }

    public static boolean deleteAllHistorys(){
        Editor editor = getEditor();
        editor.clear();
        return editor.commit();
    }

    private static SharedPreferences getSp(){
        return App.getInstance().getSharedPreferences("History_SearchFriend", Activity.MODE_PRIVATE);
    }

    private static Editor getEditor(){
        return App.getInstance().getSharedPreferences("History_SearchFriend", Activity.MODE_PRIVATE).edit();
    }

}
