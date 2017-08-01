package com.a.quarter.view.activity.login;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.login.ChangePwdResponse;
import com.a.quarter.model.bean.login.VertifyCodeResponse;
import com.a.quarter.presenter.login.FindPwdPresenter;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.base.view.view.PasswordEditText;
import com.exa.framelib_rrm.rx.RxCallback;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.T;
import com.exa.framelib_rrm.utils.TextFormatUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/*更改密码页面*/
public class ChangePwdActivity extends BaseActivity<FindPwdPresenter, ChangePwdActivity.ChangePwdCallBack> implements View.OnClickListener {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.et_pwd)
    PasswordEditText etPwd;
    @Bind(R.id.et_pwd_confirm)
    PasswordEditText etPwdConfirm;
    @Bind(R.id.btn_done)
    Button btnDone;
    @Bind(R.id.tv_visitor_login)
    TextView tvVisitorLogin;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_pwd;
    }

    @Override
    protected void initViews() {
        tvRight.setText("已有账号？");
        tvRight.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        tvVisitorLogin.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        bindPresenter(new FindPwdPresenter(), new ChangePwdCallBack(this, getApplicationContext()));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();//TODO if is doing request, don't finish
                break;
            case R.id.tv_right:
                ActivityUtils.jumpIn(this, NativeLoginActivity.class);
                finish();
                break;
            case R.id.btn_done:
                T.showShort(getApplicationContext(), "完成");
                changePwd();
                break;
            case R.id.tv_visitor_login:
                T.showShort(getApplicationContext(), "游客登录");
                break;

            default:
                break;
        }
    }

    private void changePwd() {
        //mPresenter.changePwd(TextFormatUtils.getTrimedText(etPwd), TextFormatUtils.getTrimedText(etPwdConfirm));
    }

    static class ChangePwdCallBack extends RxCallback<ChangePwdResponse, ChangePwdActivity, BaseTag> {

        public ChangePwdCallBack(ChangePwdActivity host, Context mContext) {
            super(host, mContext);
        }

        @Override
        protected void onDealNextResponse(ChangePwdResponse response, BaseTag tag) {
            //TODO 修改密码的返回结果

        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            String msg = TextFormatUtils.isPassword((String)params[0]);
            if(msg!=null){
                return msg;
            }
            msg = TextFormatUtils.isConfirmPassword((String)params[0], (String)params[1]);
            if(msg!=null){
                return msg;
            }
            return null;
        }

        @Override
        public void onRequestStart(BaseTag tag) {

        }

        @Override
        public void onRequestEnd(BaseTag tag) {

        }
    }

}
