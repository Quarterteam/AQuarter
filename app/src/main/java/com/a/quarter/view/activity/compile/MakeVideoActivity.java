package com.a.quarter.view.activity.compile;
//
//import android.Manifest;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.pm.PackageManager;
//import android.os.Build;
//import android.os.Environment;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v4.content.PermissionChecker;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.animation.AnimationUtils;
//import android.view.animation.RotateAnimation;
//import android.widget.ImageView;
//import android.widget.TextView;
//
import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;
//import com.exa.framelib_rrm.utils.LogUtils;
//import com.exa.framelib_rrm.utils.ScreenUtils;
//import com.exa.framelib_rrm.utils.StatusBarCompat;
//import com.exa.framelib_rrm.utils.T;
//import com.seu.as.MagicEngine;
//import com.seu.as.filter.helper.MagicFilterType;
//import com.seu.as.helper.SavePictureTask;
//import com.seu.as.utils.MagicParams;
//import com.seu.as.widget.MagicCameraView;
//import com.seu.cameraandimage.adapter.FilterAdapter;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
////拍照或录制视频页面，设置滤镜
public class MakeVideoActivity extends BaseActivity {
//    private RecyclerView mFilterListView;
//    private FilterAdapter mAdapter;
//    private MagicEngine magicEngine;
//    private boolean isRecording = false;
//    private final int MODE_PIC = 1;
//    private final int MODE_VIDEO = 2;
//    private int mode = MODE_PIC;
//
//    private ImageView btn_shutter;
//    private ImageView btn_mode;
//
//    private final MagicFilterType[] types = new MagicFilterType[]{
//            MagicFilterType.NONE,
//            MagicFilterType.FAIRYTALE,
//            MagicFilterType.SUNRISE,
//            MagicFilterType.SUNSET,
//            MagicFilterType.WHITECAT,
//            MagicFilterType.BLACKCAT,
//            MagicFilterType.SKINWHITEN,
//            MagicFilterType.HEALTHY,
//            MagicFilterType.SWEETS,
//            MagicFilterType.ROMANCE,
//            MagicFilterType.SAKURA,
//            MagicFilterType.WARM,
//            MagicFilterType.ANTIQUE,
//            MagicFilterType.NOSTALGIA,
//            MagicFilterType.CALM,
//            MagicFilterType.LATTE,
//            MagicFilterType.TENDER,
//            MagicFilterType.COOL,
//            MagicFilterType.EMERALD,
//            MagicFilterType.EVERGREEN,
//            MagicFilterType.CRAYON,
//            MagicFilterType.SKETCH,
//            MagicFilterType.AMARO,
//            MagicFilterType.BRANNAN,
//            MagicFilterType.BROOKLYN,
//            MagicFilterType.EARLYBIRD,
//            MagicFilterType.FREUD,
//            MagicFilterType.HEFE,
//            MagicFilterType.HUDSON,
//            MagicFilterType.INKWELL,
//            MagicFilterType.KEVIN,
//            MagicFilterType.LOMO,
//            MagicFilterType.N1977,
//            MagicFilterType.NASHVILLE,
//            MagicFilterType.PIXAR,
//            MagicFilterType.RISE,
//            MagicFilterType.SIERRA,
//            MagicFilterType.SUTRO,
//            MagicFilterType.TOASTER2,
//            MagicFilterType.VALENCIA,
//            MagicFilterType.WALDEN,
//            MagicFilterType.XPROII
//    };
//    private TextView tv_type;
//
    @Override
    protected int getContentViewId() {
        return R.layout.activity_use_camera;
    }
//
//    @Override
//    protected void setStatusBar() {
//        StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.translucent), true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            findViewById(R.id.rl_top).setPadding(0, ScreenUtils.getStatusHeight(this), 0, 0);
//        }
//    }
//
    @Override
    protected void initViews() {
//        MagicEngine.Builder builder = new MagicEngine.Builder();
//        MagicCameraView magicCameraView = (MagicCameraView) findViewById(R.id.glsurfaceview_camera);
//
//        magicEngine = builder.build(magicCameraView);
//
//        mFilterListView = (RecyclerView) findViewById(R.id.filter_listView);
//
//        btn_shutter = (ImageView)findViewById(R.id.btn_camera_shutter);
//        btn_mode = (ImageView)findViewById(R.id.btn_camera_mode);
//        tv_type = (TextView)findViewById(R.id.tv_type);
//
//        btn_shutter.setOnClickListener(btn_listener);
//        findViewById(R.id.btn_camera_switch).setOnClickListener(btn_listener);
//        btn_mode.setOnClickListener(btn_listener);
//        findViewById(R.id.btn_camera_beauty).setOnClickListener(btn_listener);
//        findViewById(R.id.tv_cancel).setOnClickListener(btn_listener);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mFilterListView.setLayoutManager(linearLayoutManager);
//
//        mAdapter = new FilterAdapter(this, types);
//        mFilterListView.setAdapter(mAdapter);
//        mAdapter.setOnFilterChangeListener(onFilterChangeListener);
    }

    @Override
    protected void initDatas() {

    }
//
//    private FilterAdapter.onFilterChangeListener onFilterChangeListener = new FilterAdapter.onFilterChangeListener(){
//
//        @Override
//        public void onFilterChanged(MagicFilterType filterType) {
//            magicEngine.setFilter(filterType);
//        }
//    };
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                           int[] grantResults) {
//        if (grantResults.length != 1 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            if(mode == MODE_PIC)
//                takePhoto();
//            else
//                takeVideo();
//        } else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        }
//    }
//
//    private View.OnClickListener btn_listener = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            int i = v.getId();
//            if (i == R.id.btn_camera_mode) {
//                switchMode();
//
//            } else if (i == R.id.btn_camera_shutter) {
//                if (PermissionChecker.checkSelfPermission(MakeVideoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                        == PackageManager.PERMISSION_DENIED) {
//                    ActivityCompat.requestPermissions(MakeVideoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                            v.getId());
//                } else {
//                    if (mode == MODE_PIC)
//                        takePhoto();
//                    else
//                        takeVideo();
//                }
//
//            } else if (i == R.id.btn_camera_filter) {
//                //showFilters();
//            } else if (i == R.id.btn_camera_switch) {
//                magicEngine.switchCamera();
//
//            } else if (i == R.id.btn_camera_beauty) {
//                new AlertDialog.Builder(MakeVideoActivity.this)
//                        .setSingleChoiceItems(new String[]{"关闭", "1", "2", "3", "4", "5"}, MagicParams.beautyLevel,
//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        magicEngine.setBeautyLevel(which);
//                                        dialog.dismiss();
//                                    }
//                                })
//                        .setTitle("设置美颜等级")
//                        .setNegativeButton("取消", null)
//                        .show();
//
//            } else if (i == R.id.btn_camera_closefilter) {
//
//            }else if (i == R.id.tv_cancel) {
//                finish();
//            }
//        }
//    };
//
//    private void switchMode(){
//        if(mode == MODE_PIC){
//            mode = MODE_VIDEO;
//            //btn_mode.setImageResource(R.drawable.icon_camera);
//            btn_mode.setImageResource(R.mipmap.xiang);
//            tv_type.setText("录制视频");
//        }else{
//            mode = MODE_PIC;
//            btn_mode.setImageResource(R.drawable.icon_video);
//            tv_type.setText("拍照");
//        }
//    }
//
//    private void takePhoto(){
//        //magicEngine.savePicture(getOutputMediaFile(),null);
//        LogUtils.i("开始保存图片");
//        magicEngine.savePicture(getOutputMediaFile(), new SavePictureTask.OnPictureSaveListener() {
//
//            private Runnable r;
//
//            @Override
//            public void onSaved(String s) {
//                LogUtils.i("已保存图片"+s);
//                if(r==null){
//                    r = new Runnable() {
//                        @Override
//                        public void run() {
//                            T.showShort(getApplicationContext(),"图片已保存");
//                        }
//                    };
//                }
//                runOnUiThread(r);
//            }
//
//        });
//    }
//
//    private void takeVideo(){
//        if(isRecording) {
//            magicEngine.stopRecord();
//            stopRecordingAnim();
//            LogUtils.i("结束录制视频，"+MagicParams.videoPath+"/"+MagicParams.videoName);
//        }else {
//
//            LogUtils.i("开始录制视频");
//            //设置录制的视频的保存路径和名称
//            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_DCIM), "MagicCamera");
//            boolean dirExists = false;
//            if (!mediaStorageDir.exists()) {
//                dirExists = mediaStorageDir.mkdirs();
//            }else{
//                dirExists = true;
//            }
//            if(dirExists){
//                MagicParams.videoPath = mediaStorageDir.getAbsolutePath();
//                LogUtils.i("setVideoPath "+mediaStorageDir.getAbsolutePath());
//                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINESE).format(new Date());
//                MagicParams.videoName = "VIDEO_" + timeStamp + ".mp4";
//                LogUtils.i("setVideoName "+MagicParams.videoName);
//                //视频路径和名称设置结束
//                //开始录制
//                magicEngine.startRecord();
//
//                startRecordingAnim();
//                isRecording = !isRecording;
//            }else{
//                T.showShort(getApplicationContext(), "无法指定视频保存路径");
//            }
//        }
//    }
//
//    private RotateAnimation recordingAnim;
//    private void startRecordingAnim() {
//        if(recordingAnim == null){
//            recordingAnim = (RotateAnimation)AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_video_recording);
//        }
//        btn_shutter.startAnimation(recordingAnim);
//    }
//
//    private void stopRecordingAnim() {
//        if(recordingAnim!=null){
//            recordingAnim.cancel();
//        }
//        btn_shutter.clearAnimation();
//    }
//
//    public File getOutputMediaFile() {
//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "MagicCamera");
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                return null;
//            }
//        }
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINESE).format(new Date());
//        File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
//                "IMG_" + timeStamp + ".jpg");
//
//        return mediaFile;
//    }
}
