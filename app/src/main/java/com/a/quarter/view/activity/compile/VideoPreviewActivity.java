package com.a.quarter.view.activity.compile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;
import com.a.quarter.view.view.ItemIjkPlayerView;
import com.exa.framelib_rrm.utils.ScreenUtils;
import com.exa.framelib_rrm.utils.StatusBarCompat;

import butterknife.Bind;

//预览录制的视频
public class VideoPreviewActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ijkPlayerView)
    ItemIjkPlayerView ijkPlayerView;
    @Bind(R.id.tv_next_step)
    TextView tvNextStep;
    private String videopath;
    private Bitmap videoThumbBitmap;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_video_preview;
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
        //初始化控件
        tvBack.setOnClickListener(this);
        tvHead.setText("视频预览");
        tvRight.setText("存到本地");
//        tvBack.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.textsize_medium));
//        tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.textsize_medium));

        //获取传过来视频路径
        videopath = getIntent().getStringExtra("videopath");
        if (TextUtils.isEmpty(videopath)) {
            finish();
            return;
        }
        //初始化播放器
        ijkPlayerView.init(true)//隐藏全屏按钮
                .enableDanmaku(false)//不显示弹幕
                .setVideoPath(videopath);
        //视频缩略图
        //从API 10开始新增一类MediaMetadataRetriever可以用来获取媒体文件的信息
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(videopath);
        videoThumbBitmap = mmr.getFrameAtTime();
        ijkPlayerView.mPlayerThumb.setImageBitmap(videoThumbBitmap);

        tvNextStep.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {

    }

//    @Override
//    protected void onResume() {
//        if(ijkPlayerView!=null){
//            ijkPlayerView.onResume();
//        }
//        super.onResume();
//    }

    @Override
    public void onBackPressed() {
        if (ijkPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (ijkPlayerView.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        if(ijkPlayerView!=null){
            ijkPlayerView.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(ijkPlayerView!=null){
            ijkPlayerView.onDestroy();
        }
        if(videoThumbBitmap!=null){
            videoThumbBitmap.recycle();
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_back:
                //释放视频资源
                //关闭页面
                finish();
                break;

            case R.id.tv_next_step:
                //点击了下一步，跳转到发表页面，把视频地址传过去
                Intent intent = new Intent(VideoPreviewActivity.this, PublishVideoActivity.class);
                intent.putExtra("videopath", videopath);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
