package com.a.quarter.view.adapter.video;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.utils.QQLoginShareUtils;
import com.a.quarter.view.activity.userpage.UserPageActivity;
import com.a.quarter.view.base.BaseActivity;
import com.a.quarter.view.view.ItemIjkPlayerView;
import com.dl7.player.media.IjkPlayerView;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.LogUtils;
import com.exa.framelib_rrm.utils.T;
import com.umeng.socialize.UMShareAPI;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
//import media.AndroidMediaController;
//import media.IjkVideoView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class VHotFragDetails extends BaseActivity implements ItemIjkPlayerView.OnPlayCircleClickListener {


    @Bind(R.id.hotdetails_return)
    TextView hotdetailsReturn;
    @Bind(R.id.hotdetails_love)
    RadioButton hotdetailsLove;
    @Bind(R.id.hotdetails_nolove)
    RadioButton hotdetailsNolove;
    @Bind(R.id.hotdetails_share)
    TextView hotdetailsShare;
    @Bind(R.id.hotdetails_user)
    TextView hotdetailsUser;
//    @Bind(R.id.IjkVideoView)
//    IjkVideoView mIjkVideoView;
    @Bind(R.id.hotdetails_video_name)
    TextView hotdetailsVideoName;
    @Bind(R.id.hotdetails_ListView)
    ListView hotdetailsListView;
    @Bind(R.id.hotdetails_comment)
    EditText hotdetailsComment;
    @Bind(R.id.hotdetails_send)
    Button hotdetailsSend;
//    private AndroidMediaController mMediaController;
    private String url = "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=22211&editionType=default&source=ucloud";
    private MediaMetadataRetriever mMetadataRetriever;
    private boolean mBackPressed;
    private ItemIjkPlayerView player;

    @Override
    protected int getContentViewId() {
        return R.layout.vhotfragdetails;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");


//        IjkMediaPlayer.loadLibrariesOnce(null);
//        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
//        // TODO: 显示播放进度
//        mMediaController = new AndroidMediaController(this, false);
//        mIjkVideoView.setMediaController(mMediaController);
//        // TODO: 设置播放地址
//        //Environment.getExternalStorageDirectory().getPath() + "/oppo.mp4"
//        Uri uri = Uri.parse("http://baobab.kaiyanapp.com/api/v1/playUrl?vid=22111&editionType=default&source=ucloud");
//        mIjkVideoView.setVideoURI(Uri.parse
//                (url));
//        mIjkVideoView.start();


        player = (ItemIjkPlayerView) findViewById(R.id.ijkPlayerView);
        //player.setOnPlayCircleClickListener(this);
        player.init(false)
              .setTitle("这是个跑马灯TextView，标题要足够长才会跑。-(゜ -゜)つロ 乾杯~")
              //.setSkipTip(1000*60*1)设置上次的播放进度的提示
              //.enableOrientation()//设置可自动旋转
              .enableDanmaku()//显示弹幕
              .setDanmakuSource(getResources().openRawResource(R.raw.bili))//设置弹幕资源
              //.setVideoSource(null, url, null, null, null)//设置不同清晰度的视频资源
              //.setMediaQuality(IjkPlayerView.MEDIA_QUALITY_MEDIUM);//设置当前选择的清晰度
            .setVideoPath(url);
        player.mPlayerThumb.setActualImageResource(R.mipmap.bg5);
        //player.mPlayerThumb.setImageResource(list.get(position).videoThumbResourceId);


    }

    @Override
    protected void initDatas() {

    }

    //在AndroidManifest.xml里设置VHotFragDetails
    //为android:configChanges="orientation|keyboardHidden|screenSize"
    //保证横竖屏切换的时候VHotFragDetails Activity不会销毁重建
    //重写onConfigurationChanged方法，执行横竖屏切换时需要进行的操作
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //LogUtils.i("newConfig.orientation="+newConfig.orientation);
        //if(newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){//横屏
        if(newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER){//横屏？
            T.showShort(this, "横屏了");
            //LogUtils.i("横屏了");
        }else if(newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){//竖屏
            T.showShort(this, "竖屏了");
        }
        player.configurationChanged(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPlayCircleClicked() {
//        player.setTitle("这是个跑马灯TextView，标题要足够长才会跑。-(゜ -゜)つロ 乾杯~")
////                .setSkipTip(1000*60*1)
//                .enableOrientation()
//                .enableDanmaku()
//                .setDanmakuSource(getResources().openRawResource(R.raw.bili))
//                //.setVideoSource(null, contentListBean.videoUri.getPath(), null, null, null)得到的是网址的后半段
//                //比如/videolib3/1611/28/GbgsL3639/SD/movie_index.m3u8: No such file or directory
//                .setVideoSource(null, url, null, null, null)
//                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_MEDIUM);
    }

    @OnClick({R.id.hotdetails_return, R.id.hotdetails_love, R.id.hotdetails_nolove, R.id.hotdetails_share, R.id.hotdetails_user,R.id.hotdetails_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hotdetails_return:// TODO: 返回键
                finish();
                break;
            case R.id.hotdetails_love:// TODO: 点赞
                break;
            case R.id.hotdetails_nolove:// TODO: 踩
                break;
            case R.id.hotdetails_share:// TODO: 分享
              {
                  String titele="视频标题";
                  String content="视频内容";
                  QQLoginShareUtils.setShare(url,titele,content,VHotFragDetails.this);
               }
                break;
            case R.id.hotdetails_user:// TODO: 用户
                ActivityUtils.jumpIn(this, UserPageActivity.class);
                break;
            case R.id.hotdetails_send:// TODO: 发表
                break;

        }
    }

    @OnClick(R.id.hotdetails_send)
    public void onClick() {
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        mBackPressed = true;
        if (player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (player.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        if (mBackPressed || !mIjkVideoView.isBackgroundPlayEnabled()) {
//            mIjkVideoView.stopPlayback();
//            mIjkVideoView.release(true);
//            mIjkVideoView.stopBackgroundPlay();
//        } else {
//            mIjkVideoView.enterBackground();
//        }
//        IjkMediaPlayer.native_profileEnd();

    }

    @Override
    protected void onPause() {
        if(player!=null){
            player.onPause();
        }
        super.onPause();
    }

//    @Override
//    protected void onResume() {
//        if(player!=null){
//            player.onResume();
//        }
//        super.onResume();
//    }

    @Override
    protected void onDestroy() {
        if(player!=null){
            player.onDestroy();
        }
        super.onDestroy();
    }
}
