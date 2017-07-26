package com.a.quarter.view.activity.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.LogUtils;
import com.exa.framelib_rrm.utils.T;

import butterknife.OnClick;

/*第三方登录页面*/
public class ThirdPartyLoginActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected int getContentViewId() {
        return R.layout.activity_third_party_login;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.iv_back, R.id.ll_qq, R.id.ll_wechat, R.id.tv_other_login})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
                break;

            case R.id.ll_qq:
                T.showShort(getApplicationContext(), "QQ登录");
                break;

            case R.id.ll_wechat:
                T.showShort(getApplicationContext(), "微信登录");
                break;

            case R.id.tv_other_login:
                ActivityUtils.jumpForResult(1, this, NativeLoginActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //登录成功后，从手机登录页面返回
        if(requestCode == 1 && resultCode == 1){
            setResult(1);
            finish();
        }
    }
}
