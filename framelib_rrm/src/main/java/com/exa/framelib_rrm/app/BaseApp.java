package com.exa.framelib_rrm.app;

import android.app.Activity;
import android.app.Application;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by acer on 2017/7/18.
 * 需要时，在项目的清单文件中注册
 *
 * 本类的静态实例;
 * Activity管理，用于一键退出;
 */
public class BaseApp extends Application{

    private static BaseApp instance;
    private ArrayList<Activity> activitys;//使用ArrayList好，还是使用HashSet好？
    private Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        instance = this;
    }

    //方便在任何地方得到本类实例，能够使用ApplicationContext或者调用本类里方法
    public static BaseApp getInstance0(){
        return instance;
    }

    public void addActivity(Activity ac){
        if(activitys==null){
            activitys = new ArrayList<Activity>();
        }
        activitys.add(ac);
    }

    public void removeActivity(Activity ac){
        if(activitys!=null){
            activitys.remove(ac);
        }
    }

    public void exit(){
        if(activitys!=null){
            for (Activity ac : activitys) {
                ac.finish();
            }
            activitys.clear();
            activitys = null;
        }
        /**
         * 绕过Activity 生命周期  强制关闭
         */
        android.os.Process.killProcess(android.os.Process.myPid());
        //System.exit(0);
    }

    @Override
    public void onTerminate() {
        if(activitys!=null && !activitys.isEmpty()){
            exit();
        }
        instance = null;
        super.onTerminate();
    }

    public static Gson getGson(){
        if(instance.gson==null){
            instance.gson = new Gson();
        }
        return instance.gson;
    }

}
