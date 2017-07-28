package com.a.quarter.view.activity.login;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.model.bean.login.VertifyCodeResponse;
import com.a.quarter.presenter.login.FindPwdPresenter;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.rx.RxCallback;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.T;
import com.exa.framelib_rrm.utils.TextFormatUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindPwdActivity extends BaseActivity<FindPwdPresenter, FindPwdActivity.FindPwdCallBack> implements View.OnClickListener {

    private static final byte TAG_GET_VERTIFY_CODE = 1;
    private static final byte TAG_NEXT_STEP = 2;

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.tv_get_vertify_code)
    TextView tvGetVertifyCode;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_vertify_code)
    EditText etVertifyCode;
    @Bind(R.id.btn_next)
    Button btnNext;
    @Bind(R.id.tv_visitor_login)
    TextView tvVisitorLogin;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_find_pwd;
    }

    @Override
    protected void initViews() {
        tvRight.setText("已有账号？");
        tvRight.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tvGetVertifyCode.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        tvVisitorLogin.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        bindPresenter(new FindPwdPresenter(), new FindPwdCallBack(this, getApplicationContext()));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();//TODO
                break;
            case R.id.tv_right:
                ActivityUtils.jumpIn(this, NativeLoginActivity.class);
                finish();
                break;
            case R.id.tv_get_vertify_code:
                T.showShort(getApplicationContext(), "获取验证码");
                getVertifyCode();
                break;
            case R.id.btn_next:
                T.showShort(getApplicationContext(), "下一步");
                nextStep();
                break;
            case R.id.tv_visitor_login:
                T.showShort(getApplicationContext(), "游客登录");
                break;

            default:
                break;
        }
    }

    //获取验证码
    private void getVertifyCode() {
        //mPresenter.getVertifyCode(new BaseTag(TAG_GET_VERTIFY_CODE), etPhone.getText().toString().trim());
    }

    //下一步，更改密码
    private void nextStep() {
        //mPresenter.nextStep(new BaseTag(TAG_NEXT_STEP), etPhone.getText().toString().trim(), etVertifyCode.getText().toString().trim());
        ActivityUtils.jumpIn(this, ChangePwdActivity.class);
    }

    static class FindPwdCallBack extends RxCallback<Object, FindPwdActivity, BaseTag> {

        public FindPwdCallBack(FindPwdActivity host, Context mContext) {
            super(host, mContext);
        }

        @Override
        protected void onDealNextResponse(Object response, BaseTag tag) {
            if(tag.tag == TAG_GET_VERTIFY_CODE){
                //TODO
                VertifyCodeResponse r = (VertifyCodeResponse)response;

            }else if(tag.tag == TAG_NEXT_STEP){
                //TODO
                String str = (String)response;

            }
        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            if(tag.tag == TAG_GET_VERTIFY_CODE){
                return TextFormatUtils.isPhone((String)params[0]);
            }else if(tag.tag == TAG_NEXT_STEP){
                String phoneCheck = TextFormatUtils.isPhone((String)params[0]);
                if(phoneCheck!=null){
                    return phoneCheck;
                }
                String vertifyCode = (String)params[1];
                if(TextUtils.isEmpty(vertifyCode)){
                    return "请输入验证码";
                }
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
