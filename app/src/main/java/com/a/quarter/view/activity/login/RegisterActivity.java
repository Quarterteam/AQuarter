package com.a.quarter.view.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.iv_toolbar)
    ImageView ivToolbar;
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.et_password_confirm)
    EditText etPasswordConfirm;
    @Bind(R.id.et_email)
    EditText etEmail;
    @Bind(R.id.btn_register)
    Button btnRegister;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {
        tvHead.setText("注 册");
    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.btn_register, R.id.btn_login, R.id.iv_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                if (!isRegistering()) {
                    finish();
                }
                break;
            case R.id.btn_register:
                register();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (!isRegistering()) {
            super.onBackPressed();
        }
    }

    private boolean isRegistering() {
        if(!btnRegister.isEnabled()){
            T.showShort(this, "正在进行注册，请稍候...");
            return true;
        }
        return false;
    }

    private void register() {
//        if(NetUtils.isConnected()){
//            String username = etName.getText().toString().trim();
//            if(!TextFormatUtils.isUsername(username)){
//                return;
//            }
//
//            String password = etPassword.getText().toString().trim();
//            if(!TextFormatUtils.isPassword(password)){
//                return;
//            }
//
//            String passwordConfirm = etPasswordConfirm.getText().toString().trim();
//            if(!TextFormatUtils.isConfirmPassword(password, passwordConfirm)){
//                return;
//            }
//
//            String email = etEmail.getText().toString().trim();
//            if(!TextFormatUtils.isEmail(email)){
//                return;
//            }
//
//            ActivityUtils.closeKeyBoardIfNeed(this);
//            btnRegister.setEnabled(false);
//            btnRegister.setText("注册中...");
//            initPresenterIfNeed();
//            up.register(username, password, passwordConfirm, email, Constants.CLIENT);
//        }else{
//            T.showShort(this, "无法联网，请检查网络连接！");
//        }

    }

    private void initPresenterIfNeed() {
//        if(up==null){
//            up = new UserPresenter();
//            registerListener = new RegisterListener(this);
//            registerListener.bind(up);
//        }
    }

}
