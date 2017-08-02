package com.a.quarter.view.activity.login;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.app.App;
import com.a.quarter.model.bean.login.LoginResponse;
import com.a.quarter.presenter.login.LoginPresenter;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.rx.RxCallback;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.T;
import com.exa.framelib_rrm.utils.TextFormatUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**原生登录页面*/
public class NativeLoginActivity extends BaseActivity<LoginPresenter, NativeLoginActivity.LoginCallback> implements View.OnClickListener{
    @Bind(R.id.et_username)
    EditText etName;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.tv_right)
    TextView tvRight;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_native_login;
    }

    @Override
    protected void initViews() {
        //((TextView)findViewById(R.id.tv_right)).setText("注册账号");
        tvRight.setText("注册账号");
        tvRight.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        //关联presenter和callback
        bindPresenter(new LoginPresenter(), new LoginCallback(this, getApplicationContext()));
    }

    @OnClick({R.id.tv_visitor_login, R.id.btn_login, R.id.iv_back, R.id.tv_go_find_pwd})
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_visitor_login:
                T.showShort(getApplicationContext(), "游客登录");
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.iv_back:
                if(!isLogining()){
                    finish();
                }
                break;
            case R.id.tv_right:
                if(!isLogining()) {
                    ActivityUtils.jumpForResult(1, this, NativeRegisterActivity.class);
                }
                break;
            case R.id.tv_go_find_pwd:
                if(!isLogining()) {
                    ActivityUtils.jumpIn(this, FindPwdActivity.class);
                }
                break;
            default:
                break;
        }
    }

    private boolean isLogining() {
        if(!btnLogin.isEnabled()){
            T.showShort(this, "正在进行登录，请稍候...");
            return true;
        }
        return false;
    }

    private void login() {
        //进行登录
        mPresenter.login(etName.getText().toString().trim(), etPassword.getText().toString().trim());
    }

    //校验用户名和密码是否正确
    private String checkLoginParam(String username, String password) {
        String msg = TextFormatUtils.isUsername(username);
        if(msg!=null){
            return msg;
        }
        msg = TextFormatUtils.isPassword(password);
        if(msg!=null){
            return msg;
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        if(!isLogining()){
            super.onBackPressed();
        }
    }

    //登录结果的监听
    static class LoginCallback extends RxCallback<LoginResponse, NativeLoginActivity, BaseTag> {

        public LoginCallback(NativeLoginActivity host, Context mContext) {
            super(host, mContext);
        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            //检查登录时需要的参数的格式是否正确
            return getHost().checkLoginParam((String)params[0], (String)params[1]);
        }

        @Override
        public void onRequestStart(BaseTag tag) {
            //开始登录后，让登录按钮不可点击
            getHost().btnLogin.setEnabled(false);
            getHost().btnLogin.setText("正在登录...");
        }

        @Override
        protected void onDealNextResponse(LoginResponse response, BaseTag tag) {
            if("200".equals(response.code)){
                T.showShort(mAppContext, "登录成功！");
                App.getInstance().saveUserInfo(response.user);


                //关闭本页面
                getHost().setResult(1);
                getHost().finish();
            }else{
                T.showShort(mAppContext, "登录失败！");
                App.getInstance().clearUserInfo();
            }
        }

        @Override
        public void onRequestEnd(BaseTag tag) {
            //请求结束后，让登录按钮可点击
            getHost().btnLogin.setEnabled(true);
            getHost().btnLogin.setText("登录");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //注册成功后，从注册页面返回
        if(requestCode == 1 && resultCode == 1){
            if(data!=null){
                //显示回传过来的手机号
                etName.setText(data.getStringExtra("phone"));
            }
        }
    }

}
