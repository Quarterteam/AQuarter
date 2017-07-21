package com.a.quarter.view.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.app.App;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.NetUtils;
import com.exa.framelib_rrm.utils.T;
import com.exa.framelib_rrm.utils.TextFormatUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**登录页面*/
public class LoginActivity extends BaseActivity implements View.OnClickListener{
    //    private UserPresenter up;
    @Bind(R.id.et_username)
    EditText etName;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;
//    private LoginResultLisenter loginListener;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        ((TextView)findViewById(R.id.tv_head)).setText("登 录");
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.tv_goRegister, R.id.btn_login, R.id.iv_back})
    @Override
    public void onClick(View v) {
//        super.onClick(v);
        switch(v.getId()){
            case R.id.tv_goRegister:
                ActivityUtils.jumpForResult(1, this, RegisterActivity.class);
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.iv_back:
                if(!isLogging()){
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(!isLogging()){
            super.onBackPressed();
        }
    }

    private boolean isLogging() {
        if(!btnLogin.isEnabled()){
            T.showShort(this, "正在进行登录，请稍候...");
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //注册成功后，从注册页面返回
        if(requestCode ==1 && resultCode==1){
            btnLogin.setText("已登录");
            btnLogin.setEnabled(false);
            setResult(1);
            finish();
        }
    }

    private void login() {
//        if(NetUtils.isConnected()){
//            String username = etName.getText().toString().trim();
//            String password = etPassword.getText().toString().trim();
//            if(checkLegality(username, password)){
//                ActivityUtils.closeKeyBoardIfNeed(this);
//                btnLogin.setEnabled(false);
//                btnLogin.setText("登录中...");
//                initPresenterIfNeed();
//                up.login(username,password, Constants.CLIENT);
//            }
//        }else{
//            T.showShort(this,"无法联网，请检查网络连接！");
//        }
    }

    private void initPresenterIfNeed(){
//        if(up==null){
//            up = new UserPresenter();
//            //up.attacView(new UserListener());
//            loginListener = new LoginResultLisenter(this);
//            loginListener.bind(up);
//        }
    }

    private boolean checkLegality(String username, String password) {
        if(!TextFormatUtils.isUsername(username)){
            return false;
        }
        if(!TextFormatUtils.isPassword(password)){
            return false;
        }
        return true;
    }
}
