package com.a.quarter.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import java.util.Map;
import java.util.Set;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class QQLoginShareUtils {
    private static Context context;
    static SimpleDraweeView draweeView;
    static Button login;
    static SharedPreferences sp;

    public QQLoginShareUtils(){
        this.context = context;
        this.draweeView = draweeView;
        this.login = login;
        this.sp = sp;
    }


    private  static UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(context, "Authorize 成功", Toast.LENGTH_SHORT).show();
            Set<String> strings = data.keySet();
            SharedPreferences.Editor edit = sp.edit();
            for (String string : strings) {
                // 设置头像
                String touxiang = data.get("profile_image_url");
                // 设置昵称
                String nicheng = data.get("screen_name");
                edit.putString("头像",touxiang);
                edit.putString("昵称",nicheng);
                edit.putBoolean("状态",true);
                draweeView.setImageURI(Uri.parse(touxiang));
                login.setText(nicheng);
                edit.commit();
            }
        }
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( context, "Authorize 失败", Toast.LENGTH_SHORT).show();
            Log.e("---------------------","失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( context, "Authorize 取消", Toast.LENGTH_SHORT).show();
            Log.e("---------------------","取消");
        }
    };

    public  UMAuthListener getumAuthListener(){
        return umAuthListener;
    }


    // TODO: 链接分享
    public static void setShare(String url, String title, String Description,Context con){

        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        //   web.setThumb(thumb);  //缩略图
        web.setDescription(Description);//描述
        new ShareAction((Activity) con)
               //
                .setDisplayList( SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE)
                .withMedia(web)
                . setCallback(shareListener)//回调监听器
                .open();
    }
    private static UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(context,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(context,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(context,"取消了",Toast.LENGTH_LONG).show();

        }
    };

    /**
     *  下面的  方法 在点击事件中调用
     */
    //   UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);
//     TODO: 使用方法 如
//    boolean aTrue = sh.getBoolean("状态", false);
//        if (aTrue){
//        String string = sh.getString("昵称", "");
//        String im = sh.getString("头像", "");
//        draweeView.setImageURI(Uri.parse(im));
//        login.setText(string);
//    }else {
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UMShareAPImedia.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, umAuthListener);
//            }
//        });
//    }

    /**
     * 将此方法 放入要调用 它的类中
     * @param url
     * @param title
     * @param Description
     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//    }

    /**
     * 友盟QQ登录，获取用户资料授权
     * */
    public static void qqLogin(Activity ac, UMAuthListener umAuthListener) {
        UMShareAPI.get(ac).getPlatformInfo(ac, SHARE_MEDIA.QQ, umAuthListener);
    }

}
