package com.exa.framelib_rrm.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by acer on 2017/6/10.
 */
public class ActivityUtils{

    public static void jumpIn(Activity ac, Class<?> clazz){
        Intent intent = new Intent(ac, clazz);
        ac.startActivity(intent);
    }

    public static void jumpIn(Fragment frag, Class<?> clazz){
        Intent intent = new Intent(frag.getActivity(), clazz);
        frag.startActivity(intent);
    }

    public static void jumpForResult(int requestCode, Activity ac, Class<?> clazz){
        Intent intent = new Intent(ac, clazz);
        ac.startActivityForResult(intent, requestCode);
    }

    public static void jumpForResult(int requestCode, Fragment frag, Class<?> clazz){
        Intent intent = new Intent(frag.getActivity(), clazz);
        frag.startActivityForResult(intent, requestCode);
    }

    //关闭软键盘
    public static void closeKeyBoardIfNeed(Activity ac){
        View v = ac.getCurrentFocus();
        if(v!=null && v instanceof EditText){
            InputMethodManager ims = (InputMethodManager) ac.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(ims.isActive()) {
                ims.hideSoftInputFromWindow(ac.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    //关闭软键盘
    public static void closeKeyBoard(Activity ac, EditText et){
        InputMethodManager ims = (InputMethodManager) ac.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(ims.isActive()) {
            ims.hideSoftInputFromWindow(et.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}
