package com.a.quarter.view.activity.compile;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.app.App;
import com.a.quarter.presenter.publish.PublishVideoPresenter;
import com.a.quarter.utils.DialogUtils;
import com.a.quarter.view.activity.login.NativeLoginActivity;
import com.a.quarter.view.base.BaseActivity;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.rx.RxCallback;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.ScreenUtils;
import com.exa.framelib_rrm.utils.StatusBarCompat;
import com.exa.framelib_rrm.utils.T;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

//13.用户上传视频的接口
public class PublishVideoActivity extends BaseActivity<PublishVideoPresenter, PublishVideoActivity.PublishVideoCallback> implements View.OnClickListener {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.iv_video_thumb)
    ImageView ivVideoThumb;
    @Bind(R.id.tv_publish)
    TextView tvPublish;
    @Bind(R.id.et_video_introduction)
    EditText etVideoIntroduction;
    private Bitmap videoThumbBitmap;
    private String videopath;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_publish_video;
    }

    @Override
    protected void setStatusBar() {
        //设置状态栏为透明，并且使用状态栏所占空间
        StatusBarCompat.compat(this, ContextCompat.getColor(this, android.R.color.transparent), true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //如果可以使用状态栏所占的空间，给头部加上一个高度等于状态栏高度的paddingTop，让头部不被状态栏挡住
            findViewById(R.id.rl_head).setPadding(0, ScreenUtils.getStatusHeight(this), 0, 0);
        }
    }

    @Override
    protected void initViews() {
        tvBack.setOnClickListener(this);
        tvHead.setText("发布");
        tvRight.setText(null);
        videopath = getIntent().getStringExtra("videopath");
        if (TextUtils.isEmpty(videopath)) {
            return;
        }
        //视频缩略图
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(videopath);
        videoThumbBitmap = mmr.getFrameAtTime();
        ivVideoThumb.setImageBitmap(videoThumbBitmap);
        tvPublish.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        bindPresenter(new PublishVideoPresenter(), new PublishVideoCallback(this,this));
    }

    @Override
    protected void onDestroy() {
        if (videoThumbBitmap != null) {
            videoThumbBitmap.recycle();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_publish:
                //开始上传视频
                showPublishAlertDialog();

                break;
            case R.id.tv_back:
                if(!tvPublish.isEnabled()){
                    showCancelAlertDialog();
                }else{
                    finish();
                }
                break;
            default:
                break;
        }
    }

    private void showPublishAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("即将上传视频，请注意流量，确定上传吗？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                publishVideo();
            }
        });
        builder.show();
    }

    private void showCancelAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("正在上传视频，退出本页面将会取消上传，确定退出吗？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }

    private void publishVideo() {
        //检查是否已登录
        if(!App.isLogin()){
            //ActivityUtils.jumpIn(this, NativeLoginActivity.class);
            T.showShort(this,"未登录！");
            return;
        }
        //检查参数
        String mediaDescription = etVideoIntroduction.getText().toString().trim();
        int mediaUserId = App.getUser().userId;
        File videoFile = new File(videopath);
        mPresenter.publishVideo(mediaDescription, mediaUserId, videoFile);
    }

    static class PublishVideoCallback extends RxCallback<String, PublishVideoActivity, BaseTag>{

        public PublishVideoCallback(PublishVideoActivity host, Context mContext) {
            super(host, mContext);
        }

        @Override
        protected void onDealNextResponse(String response, BaseTag tag) {
            if(response.contains("\"code\":\"200\"")){
                T.showShort(mAppContext,"视频上传成功！");
                getHost().finish();
            }else{
                T.showShort(mAppContext, ""+response);
            }
        }

        @Override
        public String onCheckParamsLegality(BaseTag tag, Object... params) {
            return null;
        }

        @Override
        public void onRequestStart(BaseTag tag) {
            getHost().tvPublish.setEnabled(false);
            getHost().tvPublish.setText("正在上传中，请稍候...");
        }

        @Override
        public void onRequestEnd(BaseTag tag) {
            getHost().tvPublish.setEnabled(true);
            getHost().tvPublish.setText("发布");
        }
    }

}
