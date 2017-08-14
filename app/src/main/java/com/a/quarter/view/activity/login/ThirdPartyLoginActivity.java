package com.a.quarter.view.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a.quarter.R;
import com.a.quarter.app.App;
import com.a.quarter.model.bean.login.User;
import com.a.quarter.utils.QQLoginShareUtils;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.NetUtils;
import com.exa.framelib_rrm.utils.T;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*第三方登录页面*/
public class ThirdPartyLoginActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_other_login)
    TextView tvOtherLogin;
    @Bind(R.id.ll_qq)
    LinearLayout llQq;
    @Bind(R.id.ll_wechat)
    LinearLayout llWechat;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_third_party_login;
    }

    @Override
    protected void initViews() {
        ivBack.setOnClickListener(this);
        tvOtherLogin.setOnClickListener(this);
        llQq.setOnClickListener(this);
        llWechat.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {

    }

    //@OnClick({R.id.iv_back, R.id.ll_qq, R.id.ll_wechat, R.id.tv_other_login})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                if(!isLogining()) {
                    finish();
                }
                break;

            case R.id.ll_qq:
                if (NetUtils.isConnected()) {
                    if(!isLogining()) {
                        llQq.setEnabled(false);
                        initListenerIfNeed();
                        QQLoginShareUtils.qqLogin(this, umAuthListener);
                    }
                } else {
                    T.showShort(getApplicationContext(), "没有连接网络！");
                }
                break;

            case R.id.ll_wechat:
                if(!isLogining()) {
                    T.showShort(getApplicationContext(), "该功能暂时未开通");
                }
                //UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                break;

            case R.id.tv_other_login:
                ActivityUtils.jumpForResult(1, this, NativeLoginActivity.class);
                break;
            default:
                break;
        }
    }

    private boolean isLogining() {
        if(!llQq.isEnabled() || !llWechat.isEnabled()){
            T.showShort(getApplicationContext(), "正在进行登录，请稍候...");
            return true;
        }
        return false;
    }

    private UMAuthListener umAuthListener;

    private void initListenerIfNeed() {
        if (umAuthListener == null) {
            umAuthListener = new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA platform) {
                    //授权开始的回调
                }

                @Override
                public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                    llQq.setEnabled(true);
                    Toast.makeText(getApplicationContext(), "授权成功", Toast.LENGTH_SHORT).show();
//                    Set<Map.Entry<String, String>> entries = data.entrySet();
//                    for (Map.Entry<String, String> m : entries) {
//                        Log.i("12312", m.getKey() + " = " + m.getValue());
//                    }

//                    name：name（6.2以前用screen_name）
//                    用户id（openid）：uid
//                    accesstoken: accessToken （6.2以前用access_token）
//                    过期时间：expiration （6.2以前用expires_in）
//                    性别：gender
//                    头像：iconurl（6.2以前用profile_image_url）
//                    是否黄钻：is_yellow_year_vip
//                    黄钻等级：yellow_vip_level
//                    城市：city
//                    省份：province
                    if (data != null) {
                        //保存用户信息
                        User user = new User();
                        if(platform==SHARE_MEDIA.QQ){
                            user.loginType = "qq";
                        }else if(platform==SHARE_MEDIA.WEIXIN){
                            user.loginType = "weixin";
                        }else{
                            user.loginType = "other";
                        }
                        user.userName = data.get("name");
//                        user.userPassword = ;
//                        user.userId = data.get("uid");
//                        user.userPhone = data.get("");
                        user.userSex = data.get("gender");
                        user.userHead = data.get("iconurl");
                        user.expiration = data.get("expiration");
                        App.getInstance().saveUserInfo(user);
                        //返回到主页面，并通知已登录成功
                        setResult(1);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "未获取到用户信息！", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                    llQq.setEnabled(true);
                    //Toast.makeText(getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancel(SHARE_MEDIA platform, int action) {
                    llQq.setEnabled(true);
                    Toast.makeText(getApplicationContext(), "授权取消", Toast.LENGTH_SHORT).show();
                }
            };
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //登录成功后，从手机登录页面返回
        if (requestCode == 1 && resultCode == 1) {
            setResult(1);
            finish();
        }
        //qq登录
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

}
