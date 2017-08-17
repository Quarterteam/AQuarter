package com.a.quarter.view.activity.compile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.a.quarter.R;
import com.a.quarter.utils.DrawableUtils;
import com.a.quarter.view.base.BaseActivity;
import com.a.videorecord.helper.SavePictureTask;
import com.a.videorecord.utils.CameraUtils;
import com.a.videorecord.videorecord.CameraPreview;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.ScreenUtils;
import com.exa.framelib_rrm.utils.StatusBarCompat;
import com.exa.framelib_rrm.utils.T;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 王 ：王万鹏
 * & 作用  ：
 * & 思路  ：
 *
 *
 * chenzhihui on 2016/08/18
 * 由于Camera在SDK 21的版本被标为Deprecated,建议使用新的Camera2类来实现
 * 但是由于Camera2这个类要求minSDK大于21,所以依旧使用Camera这个类进行实现
 */

public class VideoActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = VideoActivity.class.getSimpleName();
    private static final int FOCUS_AREA_SIZE = 500;
    @Bind(R.id.camera_preview)
    LinearLayout cameraPreview;
    @Bind(R.id.buttonQuality)
    TextView buttonQuality;
    @Bind(R.id.textChrono)
    Chronometer textChrono;
    @Bind(R.id.chronoRecordingImage)
    ImageView chronoRecordingImage;
    @Bind(R.id.listOfQualities)
    ListView listOfQualities;
    @Bind(R.id.button_change_camera)
    CheckBox buttonChangeCamera;
    @Bind(R.id.button_change_mode)
    CheckBox buttonChangeMode;
    @Bind(R.id.button_capture)
    ImageView buttonCapture;
    @Bind(R.id.buttonFlash)
    CheckBox buttonFlash;
    @Bind(R.id.tv_type)
    TextView tvType;
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.more_options)
    ImageView ivMoreOptions;
    @Bind(R.id.ll_more_options)
    LinearLayout llMoreOptions;
    private Camera mCamera;
    private CameraPreview mPreview;
    private MediaRecorder mediaRecorder;
    private String url_file;
    private static boolean cameraFront = false;
    private static boolean flash = false;
    private long countUp;
    private int videoQuality = CamcorderProfile.QUALITY_480P;
    private boolean isVideoMode = true;//false拍照模式，true录像模式
    private String videoPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/videokit/video";
    private String picturePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/videokit/picture";

    public static void startActivityForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, VideoActivity.class);
        ActivityCompat.startActivityForResult(activity, intent, requestCode, null);
    }

    @Override
    protected int getContentViewId() {
        //return R.layout.activity_video;
        return R.layout.activity_make_video;
    }

    @Override
    protected void initViews() {
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initialize();
    }

    @Override
    protected void initDatas() {

    }

    public void onResume() {
        super.onResume();
        if (!hasCamera(getApplicationContext())) {
            //这台设备没有发现摄像头
            Toast.makeText(getApplicationContext(), R.string.dont_have_camera_error
                    , Toast.LENGTH_SHORT).show();
            //setResult(MActivity.RESULT_CODE_FOR_RECORD_VIDEO_FAILED);
            releaseCamera();
            releaseMediaRecorder();
            finish();
        }
        if (mCamera == null) {
            releaseCamera();
            final boolean frontal = cameraFront;

            int cameraId = findFrontFacingCamera();
            if (cameraId < 0) {
                //前置摄像头不存在
                switchCameraListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(VideoActivity.this, R.string.dont_have_front_camera, Toast.LENGTH_SHORT).show();
                    }
                };

                //尝试寻找后置摄像头
                cameraId = findBackFacingCamera();
                if (flash) {
                    mPreview.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    //buttonFlash.setImageResource(R.mipmap.flashlight_off);
                }
            } else if (!frontal) {
                cameraId = findBackFacingCamera();
                if (flash) {
                    mPreview.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    //buttonFlash.setImageResource(R.mipmap.flashlight_on);
                }
            }

            mCamera = Camera.open(cameraId);
//            mCamera.setPreviewCallback(new Camera.PreviewCallback() {
//                @Override
//                public void onPreviewFrame(byte[] data, Camera camera) {
//
//                }
//            });
            mPreview.refreshCamera(mCamera);
            reloadVideoQualities(cameraId);

        }
    }

    private int findFrontFacingCamera() {
        mCameraId = CameraUtils.findFrontFacingCamera();
        if(mCameraId!=-1){
            cameraFront = true;
        }
        return mCameraId;
    }

    private int findBackFacingCamera() {
        mCameraId = CameraUtils.findBackFacingCamera();
        if(mCameraId!=-1){
            cameraFront = false;
        }
        return mCameraId;
    }

    //点击对焦
    public void initialize() {
        mPreview = new CameraPreview(VideoActivity.this, mCamera);
        cameraPreview.addView(mPreview);
        buttonCapture.setOnClickListener(captrureListener);
        buttonChangeCamera.setOnClickListener(switchCameraListener);
        buttonQuality.setOnClickListener(qualityListener);
        buttonFlash.setOnClickListener(flashListener);
        cameraPreview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        focusOnTouch(event);
                    } catch (Exception e) {
                        Log.i(TAG, getString(R.string.fail_when_camera_try_autofocus, e.toString()));
                        //do nothing
                    }
                }
                return true;
            }
        });

        buttonChangeMode.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

        //初始化图标，并设置图标的大小
        DrawableUtils.scaleDrawableTop(this, R.drawable.selector_rewardcamera, buttonChangeCamera, 2.5f);
        DrawableUtils.scaleDrawableTop(this, R.drawable.selector_bg_checkbox_flash, buttonFlash, 2.5f);
        DrawableUtils.scaleDrawableTop(this, R.mipmap.ic_launcher, buttonQuality, 2.5f);

        ivMoreOptions.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_change_mode:
                isVideoMode = buttonChangeMode.isChecked();
                if(isVideoMode){
                    tvType.setText("录像");
                }else{
                    tvType.setText("拍照");
                }
                break;
            case R.id.tv_cancel:
                if(!recording){
                    finish();
                }else{
                    T.showShort(getApplicationContext(), "正在录制视频");
                }
                break;
            case R.id.more_options:
                if(llMoreOptions.getVisibility()==View.VISIBLE){
                    llMoreOptions.setVisibility(View.GONE);
                    if(listOfQualities.getVisibility()==View.VISIBLE){
                        listOfQualities.setVisibility(View.GONE);
                    }
                }else{
                    llMoreOptions.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }

    //reload成像质量
    private void reloadVideoQualities(int idCamera) {
        SharedPreferences prefs = getSharedPreferences("RECORDING", Context.MODE_PRIVATE);

        videoQuality = prefs.getInt("QUALITY", CamcorderProfile.QUALITY_480P);

        changeVideoQuality(videoQuality);

        final ArrayList<String> list = new ArrayList<String>();

        //int maxQualitySupported = CamcorderProfile.QUALITY_480P;
        int maxQualitySupported = CamcorderProfile.QUALITY_QVGA;

        if (CamcorderProfile.hasProfile(idCamera, CamcorderProfile.QUALITY_QVGA)) {
            list.add("240p");
            maxQualitySupported = CamcorderProfile.QUALITY_QVGA;
        }
        if (CamcorderProfile.hasProfile(idCamera, CamcorderProfile.QUALITY_480P)) {
            list.add("480p");
            maxQualitySupported = CamcorderProfile.QUALITY_480P;
        }
        if (CamcorderProfile.hasProfile(idCamera, CamcorderProfile.QUALITY_720P)) {
            list.add("720p");
            maxQualitySupported = CamcorderProfile.QUALITY_720P;
        }
        if (CamcorderProfile.hasProfile(idCamera, CamcorderProfile.QUALITY_1080P)) {
            list.add("1080p");
            maxQualitySupported = CamcorderProfile.QUALITY_1080P;
        }
        if (CamcorderProfile.hasProfile(idCamera, CamcorderProfile.QUALITY_2160P)) {
            list.add("2160p");
            maxQualitySupported = CamcorderProfile.QUALITY_2160P;
        }

        if (!CamcorderProfile.hasProfile(idCamera, videoQuality)) {
            videoQuality = maxQualitySupported;
            updateButtonText(maxQualitySupported);
        }

        /*final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);*/
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                R.layout.layout_camera_resolution_ratio_list,R.id.tv, list);
        listOfQualities.setAdapter(adapter);

        listOfQualities.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);

                buttonQuality.setText(item);

                if (item.equals("240p")) {
                    changeVideoQuality(CamcorderProfile.QUALITY_QVGA);
                } else if (item.equals("480p")) {
                    changeVideoQuality(CamcorderProfile.QUALITY_480P);
                } else if (item.equals("720p")) {
                    changeVideoQuality(CamcorderProfile.QUALITY_720P);
                } else if (item.equals("1080p")) {
                    changeVideoQuality(CamcorderProfile.QUALITY_1080P);
                } else if (item.equals("2160p")) {
                    changeVideoQuality(CamcorderProfile.QUALITY_2160P);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    listOfQualities.animate().setDuration(200).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    listOfQualities.setVisibility(View.GONE);
                                }
                            });
                } else {
                    listOfQualities.setVisibility(View.GONE);
                }
            }

        });

    }

    View.OnClickListener qualityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!recording) {
                if(listOfQualities.getVisibility() == View.GONE){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        listOfQualities.setVisibility(View.VISIBLE);
                        listOfQualities.animate().setDuration(200).alpha(95)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                    }

                                });
                    } else {
                        listOfQualities.setVisibility(View.VISIBLE);
                    }
                }else{
                    listOfQualities.setVisibility(View.GONE);
                }
            }
        }
    };

    //闪光灯
    View.OnClickListener flashListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!recording && !cameraFront) {
                if (flash) {
                    flash = false;
                    //buttonFlash.setImageResource(R.mipmap.flashlight_off);
                    setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                } else {
                    flash = true;
                    //buttonFlash.setImageResource(R.mipmap.flashlight_on);
                    setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                }
            }
        }
    };

    //切换前置后置摄像头
    View.OnClickListener switchCameraListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!recording) {
                int camerasNumber = Camera.getNumberOfCameras();
                if (camerasNumber > 1) {
                    releaseCamera();
                    chooseCamera();
                } else {
                    //只有一个摄像头不允许切换
                    Toast.makeText(getApplicationContext(), R.string.only_have_one_camera
                            , Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private int mCameraId = -1;
    //选择摄像头
    public void chooseCamera() {
        if (cameraFront) {
            //当前是前置摄像头
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                // open the backFacingCamera
                // set a picture callback
                // refresh the preview
                mCamera = Camera.open(cameraId);
                // mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);
                reloadVideoQualities(cameraId);
            }
        } else {
            //当前为后置摄像头
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                // open the backFacingCamera
                // set a picture callback
                // refresh the preview
                mCamera = Camera.open(cameraId);
                if (flash) {
                    flash = false;
                    //buttonFlash.setImageResource(R.mipmap.ic_flash_off_white);
                    mPreview.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                }
                // mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera);//修改camera分辨率
                reloadVideoQualities(cameraId);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    //检查设备是否有摄像头
    private boolean hasCamera(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    boolean recording = false;
    View.OnClickListener captrureListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(!isVideoMode){//如果是拍照模式
                takePicture();
                return;
            }

            //如果是录像模式
            if (recording) {
                //如果正在录制点击这个按钮表示录制完成
                mediaRecorder.stop(); //停止
                stopChronometer();
                //buttonCapture.setImageResource(R.mipmap.player_record);
                stopRecordingAnim();
                changeRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                releaseMediaRecorder();
                Toast.makeText(VideoActivity.this, R.string.video_captured, Toast.LENGTH_SHORT).show();
                recording = false;
//                Intent intent = new Intent();
//                intent.putExtra(MainActivity.INTENT_EXTRA_VIDEO_PATH, url_file);
//                setResult(MainActivity.RESULT_CODE_FOR_RECORD_VIDEO_SUCCEED, intent);
                releaseCamera();
                releaseMediaRecorder();
                //finish();
                //跳转到视频预览页面，传入视频的保存路径
                Intent intent = new Intent(VideoActivity.this, VideoPreviewActivity.class);
                intent.putExtra("videopath", url_file);
                startActivity(intent);
            } else {
                //准备开始录制视频
                if (!prepareMediaRecorder()) {
                    Toast.makeText(VideoActivity.this, getString(R.string.camera_init_fail), Toast.LENGTH_SHORT).show();
//                    setResult(MainActivity.RESULT_CODE_FOR_RECORD_VIDEO_FAILED);
                    releaseCamera();
                    releaseMediaRecorder();
                    finish();
                }
                startRecordingAnim();
                //开始录制视频
                runOnUiThread(new Runnable() {
                    public void run() {
                        // If there are stories, add them to the table
                        try {
                            mediaRecorder.start();
                            startChronometer();
                            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                                changeRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            } else {
                                changeRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                            }
                            //buttonCapture.setImageResource(R.mipmap.player_stop);
                        } catch (final Exception ex) {
                            Log.i("---", "Exception in thread");
//                            setResult(MainActivity.RESULT_CODE_FOR_RECORD_VIDEO_FAILED);
                            releaseCamera();
                            releaseMediaRecorder();
                            finish();
                        }
                    }
                });
                recording = true;
            }
        }
    };

    private RotateAnimation recordingAnim;
    private void startRecordingAnim() {
        if(recordingAnim == null){
            recordingAnim = (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_video_recording);
        }
        buttonCapture.startAnimation(recordingAnim);
    }

    private void stopRecordingAnim() {
        if(recordingAnim!=null){
            recordingAnim.cancel();
        }
        buttonCapture.clearAnimation();
    }

    private void changeRequestedOrientation(int orientation) {
        setRequestedOrientation(orientation);
    }

    private void releaseMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            mCamera.lock();
        }
    }

    private boolean prepareMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mCamera.unlock();
        mediaRecorder.setCamera(mCamera);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (cameraFront) {
                mediaRecorder.setOrientationHint(270);
            } else {
                mediaRecorder.setOrientationHint(90);
            }
        }

        mediaRecorder.setProfile(CamcorderProfile.get(videoQuality));
//        File file = new File("/mnt/sdcard/videokit");
        File file = new File(videoPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date d = new Date();
        String timestamp = String.valueOf(d.getTime());
//        url_file = Environment.getExternalStorageDirectory().getPath() + "/videoKit" + timestamp + ".mp4";
//        url_file = "/mnt/sdcard/videokit/in.mp4";
//        url_file = "/mnt/sdcard/videokit/" + timestamp + ".mp4";
        url_file = videoPath+"/in.mp4";

        File file1 = new File(url_file);
        if (file1.exists()) {
            file1.delete();
        }

        mediaRecorder.setOutputFile(url_file);

//        https://developer.android.com/reference/android/media/MediaRecorder.html#setMaxDuration(int) 不设置则没有限制
//        mediaRecorder.setMaxDuration(CameraConfig.MAX_DURATION_RECORD); //设置视频文件最长时间 60s.
//        https://developer.android.com/reference/android/media/MediaRecorder.html#setMaxFileSize(int) 不设置则没有限制
//        mediaRecorder.setMaxFileSize(CameraConfig.MAX_FILE_SIZE_RECORD); //设置视频文件最大size 1G

        try {
            mediaRecorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            releaseMediaRecorder();
            return false;
        }
        return true;

    }

//    汇总Android视频录制中常见问题
//    http://www.jb51.net/article/77261.htm
//    // BEGIN_INCLUDE (configure_media_recorder)
//    mMediaRecorder = new MediaRecorder();
//
//    // Step 1: Unlock and set camera to MediaRecorder
//    mCamera.unlock();
//    mMediaRecorder.setCamera(mCamera);
//    mMediaRecorder.setOrientationHint(90);
//
//    // Step 2: Set sources
//    mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION);
//    mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
//
//    // Step 3: Set a Camera Parameters
//    mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
//    /* Fixed video Size: 640 * 480*/
//    mMediaRecorder.setVideoSize(640, 480);
//    /* Encoding bit rate: 1 * 1024 * 1024*/
//    mMediaRecorder.setVideoEncodingBitRate(1 * 1024 * 1024);
//    mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
//    mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
//
//    // Step 4: Set output file
//    mMediaRecorder.setMaxFileSize(maxFileSizeInBytes);
//    mMediaRecorder.setOutputFile(videoFilePath);
//    // END_INCLUDE (configure_media_recorder)
//
//    // Set MediaRecorder ErrorListener
//    mMediaRecorder.setOnErrorListener(this);

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    //修改录像质量
    private void changeVideoQuality(int quality) {
        SharedPreferences prefs = getSharedPreferences("RECORDING", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("QUALITY", quality);
        editor.commit();
        this.videoQuality = quality;
        updateButtonText(quality);
    }

    private void updateButtonText(int quality) {
        if (quality == CamcorderProfile.QUALITY_QVGA)
            buttonQuality.setText("240p");
        if (quality == CamcorderProfile.QUALITY_480P)
            buttonQuality.setText("480p");
        if (quality == CamcorderProfile.QUALITY_720P)
            buttonQuality.setText("720p");
        if (quality == CamcorderProfile.QUALITY_1080P)
            buttonQuality.setText("1080p");
        if (quality == CamcorderProfile.QUALITY_2160P)
            buttonQuality.setText("2160p");
    }


    private class StableArrayAdapter extends ArrayAdapter<String> {
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

//        public StableArrayAdapter(Context context, int textViewResourceId,
//                                  List<String> objects) {
//            super(context, textViewResourceId, objects);
//            for (int i = 0; i < objects.size(); ++i) {
//                mIdMap.put(objects.get(i), i);
//            }
//        }

        public StableArrayAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
            super(context, resource, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }



        @Override
        public boolean hasStableIds() {
            return true;
        }
    }

    //闪光灯
    public void setFlashMode(String mode) {
        try {
            if (getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)
                    && mCamera != null
                    && !cameraFront) {

                mPreview.setFlashMode(mode);
                mPreview.refreshCamera(mCamera);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), R.string.changing_flashLight_mode,
                    Toast.LENGTH_SHORT).show();
        }
    }

    //计时器
    private void startChronometer() {
        textChrono.setVisibility(View.VISIBLE);
        final long startTime = SystemClock.elapsedRealtime();
        textChrono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer arg0) {
                countUp = (SystemClock.elapsedRealtime() - startTime) / 1000;
                if (countUp % 2 == 0) {
                    chronoRecordingImage.setVisibility(View.VISIBLE);
                } else {
                    chronoRecordingImage.setVisibility(View.INVISIBLE);
                }

                String asText = String.format("%02d", countUp / 60) + ":" + String.format("%02d", countUp % 60);
                textChrono.setText(asText);
            }
        });
        textChrono.start();
    }

    private void stopChronometer() {
        textChrono.stop();
        chronoRecordingImage.setVisibility(View.INVISIBLE);
        textChrono.setVisibility(View.INVISIBLE);
    }

    public static void reset() {
        flash = false;
        cameraFront = false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (recording) {
            mediaRecorder.stop();
            if (textChrono != null && textChrono.isActivated())
                textChrono.stop();
            releaseMediaRecorder();
            recording = false;
//            File mp4 = new File(url_file);
//            if (mp4.exists() && mp4.isFile()) {
//                mp4.delete();
//            }
        }
//        setResult(MActivity.RESULT_CODE_FOR_RECORD_VIDEO_CANCEL);
        releaseCamera();
        releaseMediaRecorder();
        finish();
        return super.onKeyDown(keyCode, event);
    }

    private void focusOnTouch(MotionEvent event) {
        if (mCamera != null) {
            Camera.Parameters parameters = mCamera.getParameters();
            if (parameters.getMaxNumMeteringAreas() > 0) {
                Rect rect = calculateFocusArea(event.getX(), event.getY());
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
                meteringAreas.add(new Camera.Area(rect, 800));
                parameters.setFocusAreas(meteringAreas);
                mCamera.setParameters(parameters);
                mCamera.autoFocus(mAutoFocusTakePictureCallback);
            } else {
                mCamera.autoFocus(mAutoFocusTakePictureCallback);
            }
        }
    }

    private Rect calculateFocusArea(float x, float y) {
        int left = clamp(Float.valueOf((x / mPreview.getWidth()) * 2000 - 1000).intValue(), FOCUS_AREA_SIZE);
        int top = clamp(Float.valueOf((y / mPreview.getHeight()) * 2000 - 1000).intValue(), FOCUS_AREA_SIZE);
        return new Rect(left, top, left + FOCUS_AREA_SIZE, top + FOCUS_AREA_SIZE);
    }

    private int clamp(int touchCoordinateInCameraReper, int focusAreaSize) {
        int result;
        if (Math.abs(touchCoordinateInCameraReper) + focusAreaSize / 2 > 1000) {
            if (touchCoordinateInCameraReper > 0) {
                result = 1000 - focusAreaSize / 2;
            } else {
                result = -1000 + focusAreaSize / 2;
            }
        } else {
            result = touchCoordinateInCameraReper - focusAreaSize / 2;
        }
        return result;
    }

    private Camera.AutoFocusCallback mAutoFocusTakePictureCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            if (success) {
                // do something...
                Log.i("tap_to_focus", "success!");
            } else {
                // do something...
                Log.i("tap_to_focus", "fail!");
            }
        }
    };

    //////////////////////
    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    //拍照
    private Camera.Parameters parameters;
    private Camera.Size pictureOldSize;
    private Camera.Size foundSize;
    public void takePicture(){
        if(mCamera == null){
            return;
        }
        buttonCapture.setEnabled(false);
        //指定照片的保存路径
        File file = new File(picturePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date d = new Date();
        String timestamp = String.valueOf(d.getTime());
        final File file2 = new File(picturePath+"/p"+timestamp+".jpg");
        SavePictureTask.OnPictureSaveListener listener = new SavePictureTask.OnPictureSaveListener(){

            @Override
            public void onSaved(String result) {
                Log.i("134123","result="+result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        buttonCapture.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "图片已保存在"+picturePath, Toast.LENGTH_LONG).show();

                        //跳转到图片预览页面
//                        ActivityUtils.jumpIn(VideoActivity.this, PicturePreviewActivity.class);
                        Intent intent = new Intent(VideoActivity.this, PicturePreviewActivity.class);
                        intent.putExtra("picpath", file2.getAbsolutePath());
                        VideoActivity.this.startActivity(intent);

                    }//保存的照片偏了90度，为什么？
                });
            }

        };
        final SavePictureTask savePictureTask = new SavePictureTask(file2, listener, getApplicationContext(), mCameraId);
        //int rotation = mCamera.getCameraInfo(1, null);

        //尽量控制拍的照片的分辨率跟录制视频的分辨率相同
        //获取现在的录制视频分辨率的宽
        int quality = -1;
        if(videoQuality == CamcorderProfile.QUALITY_QVGA){
            quality = 240;
        }else if(videoQuality == CamcorderProfile.QUALITY_480P){
            quality = 480;
        }else if(videoQuality == CamcorderProfile.QUALITY_720P){
            quality = 720;
        }else if(videoQuality == CamcorderProfile.QUALITY_1080P){
            quality = 1080;
        }else if(videoQuality == CamcorderProfile.QUALITY_2160P){
            quality = 2160;
        }else{
            //如果是其他情况
        }

        foundSize = null;
        parameters = null;
        pictureOldSize = null;
        if(quality!=-1){
            //找到摄像头支持的拍摄的照片的分辨率中有没有现在的录制视频的分辨率，
            //如果有的话，就把该分辨率指定为拍照的分辨率，否则拍照得到的图片分辨率默认是很大的
            parameters = mCamera.getParameters();
            pictureOldSize = parameters.getPictureSize();
            List<Camera.Size> list = parameters.getSupportedPictureSizes();
            Camera.Size tempSize = null;
            for (int i = 0; i <list.size() ; i++) {
                //Log.i("134123", list.get(i).width+"x"+list.get(i).height);
                tempSize = list.get(i);
                if(tempSize.height == quality){
                    foundSize = tempSize;
                    break;
                }
            }
            if(foundSize!=null){
                parameters.setPictureSize(foundSize.width, foundSize.height);
                mCamera.setParameters(parameters);
            }
        }

        mCamera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                mCamera.stopPreview();

                if(foundSize!=null){
                    //如果改变过拍照的分辨率，恢复成原来默认的分辨率
                    parameters.setPictureSize(pictureOldSize.width, pictureOldSize.height);
                    mCamera.setParameters(parameters);
                }

                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                if (bitmap != null) {
                    savePictureTask.execute(bitmap);
                    bitmap = null;
                }
                mCamera.startPreview();
            }
        });

        //后置摄像头，拍照后，保存的图片会是逆时针旋转了90度的
        //前置摄像头，拍照后，保存的图片也是顺时针旋转了90度的
        //使用相同的方法处理之后，一个是正确的，一个不正确
    }

}
