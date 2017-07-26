package com.a.quarter.view.activity.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.login.RegisterResponse;
import com.a.quarter.model.bean.login.User;
import com.a.quarter.presenter.login.LoginPresenter;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.rx.RxCallback;
import com.exa.framelib_rrm.utils.T;
import com.exa.framelib_rrm.utils.TextFormatUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<LoginPresenter, RegisterActivity.RegisterCallback> implements View.OnClickListener {
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
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.btn_register)
    Button btnRegister;
    @Bind(R.id.rBtn_sex_male)
    RadioButton rBtnSexMale;
    @Bind(R.id.rBtn_sex_female)
    RadioButton rBtnSexFemale;

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
        //关联Presenter和callback
        bindPresenter(new LoginPresenter(), new RegisterCallback(this, getApplicationContext()));
    }

    @OnClick({R.id.btn_register, R.id.iv_back})
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
        if (!btnRegister.isEnabled()) {
            T.showShort(this, "正在进行注册，请稍候...");
            return true;
        }
        return false;
    }

    //开始注册
    private User tempUser;
    private void register() {
        if (tempUser == null) {
            tempUser = new User();
        }
        tempUser.reset();
        tempUser.userHead = "";
        tempUser.userName = etUsername.getText().toString().trim();
        tempUser.userPassword = etPassword.getText().toString().trim();
        tempUser.userPasswordConfirm = etPasswordConfirm.getText().toString().trim();
        tempUser.userPhone = etPhone.getText().toString().trim();
        if(rBtnSexMale.isChecked()){
            tempUser.userSex = "男";
        }else{
            tempUser.userSex = "女";
        }
        mPresenter.register(tempUser);
    }

    //校验注册时需要的参数的格式是否正确
    private String checkRegisterParams(User user){
        if(user != null){
            String msg = TextFormatUtils.isUsername(user.userName);
            if(msg!=null){
                return msg;
            }
            msg = TextFormatUtils.isPassword(user.userPassword);
            if(msg!=null){
                return msg;
            }
            msg = TextFormatUtils.isConfirmPassword(user.userPassword, user.userPasswordConfirm);
            if(msg!=null){
                return msg;
            }
            msg = TextFormatUtils.isPhone(user.userPhone);
            if(msg!=null){
                return msg;
            }
            if(TextUtils.isEmpty(user.userSex)){
                return "请选择性别";
            }
        }else{
            return "参数为空";
        }
        return null;
    }

    //注册的结果监听
    static class RegisterCallback extends RxCallback<RegisterResponse, RegisterActivity, BaseTag> {

        public RegisterCallback(RegisterActivity host, Context mContext) {
            super(host, mContext);
        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            return getHost().checkRegisterParams((User)params[0]);
        }

        @Override
        public void onRequestStart(BaseTag tag) {
            //开始注册后，让注册按钮不可点击
            getHost().btnRegister.setEnabled(false);
            getHost().btnRegister.setText("正在注册...");
        }

        @Override
        public void onRequestEnd(BaseTag tag) {
            //请求结束后，让注册按钮可点击
            getHost().btnRegister.setEnabled(true);
            getHost().btnRegister.setText("注册");
        }

        @Override
        protected void onDealNextResponse(RegisterResponse response, BaseTag tag) {
            if(response.code == 200){
                getHost().btnRegister.setEnabled(false);
                T.showShort(mAppContext, "注册成功！");
                //关闭本页面
                Intent intent = new Intent();
                intent.putExtra("phone", getHost().tempUser.userPhone);
                getHost().setResult(1, intent);
                getHost().finish();
            }else{
                T.showShort(mAppContext, "注册失败！");
            }
        }
    }

}
