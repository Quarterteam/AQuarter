package com.exa.framelib_rrm.utils;

import android.text.TextUtils;

import com.exa.framelib_rrm.app.App;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验文本格式是否正确
 */
public class TextFormatUtils {

    public static final String REGEX_USERNAME = "^[a-z0-9_-]{3,16}$";
    //    public static final String REGEX_PASSWORD = "^[a-z0-9_-]{6,18}$";
    public static final String REGEX_PASSWORD = "^[a-z0-9_-]{3,18}$";
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static boolean isUsername(String username){
        if(TextUtils.isEmpty(username)){
            T.showShort(App.getInstance().getApplicationContext(),"用户名不能为空！");
            return false;
        }
        Pattern regex = Pattern.compile(REGEX_USERNAME);
        Matcher matcher = regex.matcher(username);
        if(matcher.matches()){
            return true;
        }else{
            T.showShort(App.getInstance().getApplicationContext(),"用户名格式不正确，请输入3-16位字母或数字！");
            return false;
        }
    }

    private static boolean isLetterOrDigits(String string) {
        boolean flag = false;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isLowerCase(string.charAt(i))
                    || Character.isUpperCase(string.charAt(i))
                    || Character.isDigit(string.charAt(i))) {
                flag = true;
            } else {
                flag = false;
                return flag;
            }
        }
        return flag;

    }

    public static boolean isPassword(String password){
        if(TextUtils.isEmpty(password)){
            T.showShort(App.getInstance().getApplicationContext(),"密码不能为空！");
            return false;
        }
        LogUtils.i("password="+password);

        Pattern regex = Pattern.compile(REGEX_PASSWORD);
        Matcher matcher = regex.matcher(password);
        if(matcher.matches()){
            return true;
        }else{
            T.showShort(App.getInstance().getApplicationContext(), "密码格式不正确，请输入6-18位字母或数字！");
            return false;
        }
    }

    public static boolean isEmail(String email) {
        if(TextUtils.isEmpty(email)){
            T.showShort(App.getInstance().getApplicationContext(), "邮箱不能为空！");
            return false;
        }
        Pattern regex = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = regex.matcher(email);
        if(matcher.matches()){
            return true;
        }else{
            T.showShort(App.getInstance().getApplicationContext(), "邮箱格式不正确！");
            return false;
        }
    }

    public static boolean isConfirmPassword(String password, String passwordConfirm) {
        if(TextUtils.isEmpty(passwordConfirm)){
            T.showShort(App.getInstance().getApplicationContext(),"确认密码不能为空！");
            return false;
        }
        if(!passwordConfirm.equals(password)){
            T.showShort(App.getInstance().getApplicationContext(),"前后密码不一致！");
            return false;
        }
        return true;
    }

    public static boolean isUrlAddress(String url) {
        if (url.contains("http") || url.contains("www.") || url.contains(".com") || url.contains(".cn")) {
            return true;
        }
        return false;
    }

}
