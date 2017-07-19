package com.exa.framelib_rrm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by acer on 2017/7/17.
 */
public class TimeUtils {

    public static String getStringTime(long time){
        return SimpleDateFormat
                .getDateTimeInstance(DateFormat.SHORT,DateFormat.MEDIUM, Locale.CHINA)
                .format(new Date());
    }

//	public static String getStringTime(long time){
//        //DateFormat sdf = SimpleDateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL,Locale.CHINA);
//        DateFormat sdf = SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.MEDIUM,Locale.CHINA);
//        //sdf.("yyyy-MM-dd hh:ss",Locale.CHINA);
//		return sdf.format(new Date(time));
//	}

    /**
     * 获取当前时间的毫秒值
     */
    public static long getTimeInMillis(){
        //return Calendar.getInstance().getTimeInMillis();
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间的简短形式
     */
    public static String getCurrentTimeShort(){
        return SimpleDateFormat
                .getTimeInstance(DateFormat.SHORT,Locale.CHINA)
                .format(new Date());
    }

    public static String getHourAndMinute(){
        SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getTimeInstance(DateFormat.SHORT,Locale.CHINA);
        sdf.applyPattern("HH:mm");
        return sdf.format(new Date());
    }

}
