package com.a.quarter.view.adapter.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.utils.QQLoginShareUtils;
import com.a.quarter.view.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import media.AndroidMediaController;
import media.IjkVideoView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 */

public class VHotFragDetails extends BaseActivity {


    @Bind(R.id.hotdetails_return)
    TextView hotdetailsReturn;
    @Bind(R.id.hotdetails_love)
    CheckBox hotdetailsLove;
    @Bind(R.id.hotdetails_nolove)
    CheckBox hotdetailsNolove;
    @Bind(R.id.hotdetails_share)
    TextView hotdetailsShare;
    @Bind(R.id.hotdetails_user)
    TextView hotdetailsUser;
    @Bind(R.id.IjkVideoView)
    IjkVideoView mIjkVideoView;
    @Bind(R.id.hotdetails_video_name)
    TextView hotdetailsVideoName;
    @Bind(R.id.hotdetails_ListView)
    ListView hotdetailsListView;
    @Bind(R.id.hotdetails_comment)
    EditText hotdetailsComment;
    @Bind(R.id.hotdetails_send)
    Button hotdetailsSend;
    private AndroidMediaController mMediaController;
    private  String url="http://baobab.kaiyanapp.com/api/v1/playUrl?vid=22111&editionType=default&source=ucloud";

    @Override
    protected int getContentViewId() {
        return R.layout.vhotfragdetails;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        Log.e("key", key);

        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        // TODO: 显示播放进度
        mMediaController = new AndroidMediaController(this, false);
        mIjkVideoView.setMediaController(mMediaController);
        // TODO: 设置播放地址
        //Environment.getExternalStorageDirectory().getPath() + "/oppo.mp4"
        Uri uri = Uri.parse("http://baobab.kaiyanapp.com/api/v1/playUrl?vid=22111&editionType=default&source=ucloud");
        mIjkVideoView.setVideoURI(Uri.parse
                (url));
        mIjkVideoView.start();

    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.hotdetails_return, R.id.hotdetails_love, R.id.hotdetails_nolove, R.id.hotdetails_share, R.id.hotdetails_user,R.id.hotdetails_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hotdetails_return://// TODO: 返回键
                finish();
                break;
            case R.id.hotdetails_love:// TODO: 喜欢
                CheckBox cb = (CheckBox) view;
                boolean checked = cb.isChecked();
                if (checked) {

                } else {

                }
                break;
            case R.id.hotdetails_nolove:// TODO: 不喜欢
                break;
            case R.id.hotdetails_share:// TODO: 分享
              {
                  String titele="视频标题";
                  String content="视频内容";
                  QQLoginShareUtils.setShare(url,titele,content,VHotFragDetails.this);
               }
                break;
            case R.id.hotdetails_user:// TODO: 用户
                break;
            case R.id.hotdetails_send:// TODO: 发表
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.hotdetails_send)
    public void onClick() {
    }
}
