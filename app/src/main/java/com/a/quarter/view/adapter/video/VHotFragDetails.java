package com.a.quarter.view.adapter.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;
import media.AndroidMediaController;
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
    media.IjkVideoView mIjkVideoView;
    @Bind(R.id.hotdetails_video_name)
    TextView hotdetailsVideoName;
    @Bind(R.id.hotdetails_ListView)
    ListView hotdetailsListView;
    @Bind(R.id.hotdetails_comment)
    EditText hotdetailsComment;
    private AndroidMediaController mMediaController;

    @Override
    protected int getContentViewId() {
        return R.layout.vhotfragdetails;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        Log.e("key-------",key);

        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        // TODO: 显示播放进度
        mMediaController = new AndroidMediaController(this, false);
        mIjkVideoView.setMediaController(mMediaController);
        // TODO: 设置播放地址
        mIjkVideoView.setVideoURI(Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/oppo.mp4"));
        mIjkVideoView.start();

    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.hotdetails_return, R.id.hotdetails_love, R.id.hotdetails_nolove, R.id.hotdetails_share, R.id.hotdetails_user, R.id.hotdetails_video_name})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hotdetails_return:
                    finish();
                break;
            case R.id.hotdetails_love:
                CheckBox cb = (CheckBox) view;
                boolean checked = cb.isChecked();
                if (checked){

                }else {

                }
                break;
            case R.id.hotdetails_nolove:
                break;
            case R.id.hotdetails_share:
                break;
            case R.id.hotdetails_user:
                break;
            case R.id.hotdetails_video_name:
                break;
        }
    }

}
