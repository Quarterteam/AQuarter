package com.a.quarter.view.activity.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.app.App;
import com.a.quarter.model.bean.main.AddPicItemBean;
import com.a.quarter.model.bean.main.PublishArticleResponse;
import com.a.quarter.presenter.publish.PublishArticlePresenter;
import com.a.quarter.view.activity.login.NativeLoginActivity;
import com.a.quarter.view.adapter.main.AddPicAdapter;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.rx.RxCallback;
import com.exa.framelib_rrm.utils.T;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;

/*发表文章*/
public class PublishArticleActivity extends BaseActivity<PublishArticlePresenter, PublishArticleActivity.PublishArticleCallback> implements View.OnClickListener{

    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.tv_publish)
    TextView tvPublish;
    @Bind(R.id.et_input)
    EditText etInput;
    @Bind(R.id.rv)
    RecyclerView rv;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_publish_article;
    }

    @Override
    protected void initViews() {
        tvCancel.setOnClickListener(this);
        tvPublish.setOnClickListener(this);

        ArrayList<AddPicItemBean> list = new ArrayList<AddPicItemBean>();
        list.add(new AddPicItemBean(AddPicAdapter.TYPE_NORMAL));

        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.setAdapter(new AddPicAdapter(this, list));
    }

    @Override
    protected void initDatas() {
        bindPresenter(new PublishArticlePresenter(), new PublishArticleCallback(this, this));
    }

    // TODO:  点击 监听
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_cancel:
                if(tvPublish.isEnabled()){
                    finish();
                }else{
                    T.showShort(getApplicationContext(), "正在发表文章，请稍候...");
                }
                break;
            case R.id.tv_publish:
                boolean login = App.getInstance().isLogin();
                if(login){
                    T.showShort(this,"已登录");
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("是否登录");

                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(PublishArticleActivity.this, NativeLoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();

                    return;
                }
                T.showShort(getApplicationContext(), "发表文章");
                publish();
                break;
            default:
                break;
        }
    }

    //开始请求发表文章的接口
    private HashMap<String, String> map;
//    private String pictureName = null;
    private String pictureName = "1";
    private void publish() {
        if(map==null){
            map = new HashMap<String, String>();
        }
        map.clear();
        map.put("pictureName", pictureName);//图片名称
        map.put("pictureDescription", etInput.getText().toString().trim()) ;//图片描述
        map.put("pictureDescriptionVlue",3+"");
        map.put("pictureUserId", App.getInstance().getUser().userId+"");//用户id
        mPresenter.publish(map);
    }

    //检查参数
    private String checkPublishParams(HashMap<String, String> map) {
        String msg = null;
        if(TextUtils.isEmpty(map.get("pictureName"))){
            return "图片名称不能为空";
        }
        if(TextUtils.isEmpty(map.get("pictureDescription"))){
            return "图片描述不能为空";
        }
        return msg;
    }

    //发表文章的结果监听
    static class PublishArticleCallback extends RxCallback<PublishArticleResponse, PublishArticleActivity, BaseTag>{

        public PublishArticleCallback(PublishArticleActivity host, Context mContext) {
            super(host, mContext);
        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            return getHost().checkPublishParams((HashMap<String, String>)params[0]);
        }

        @Override
        public void onRequestStart(BaseTag tag) {
            getHost().tvPublish.setEnabled(false);
        }

        @Override
        public void onRequestEnd(BaseTag tag) {
            getHost().tvPublish.setEnabled(true);
        }

        @Override
        protected void onDealNextResponse(PublishArticleResponse response, BaseTag tag) {
            if(response.code == 200){
                T.showShort(mAppContext, "发表成功");
                getHost().finish();
            }else{
                T.showShort(mAppContext, "发表失败");
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(tvPublish.isEnabled()){
            super.onBackPressed();
        }else{
            T.showShort(getApplicationContext(), "正在发表文章，请稍候...");
        }
    }
}
