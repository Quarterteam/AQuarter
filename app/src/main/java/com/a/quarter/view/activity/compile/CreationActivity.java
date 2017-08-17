package com.a.quarter.view.activity.compile;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a.quarter.R;
import com.a.quarter.app.App;
import com.a.quarter.view.base.BaseActivity;
import com.a.videorecord.permission.PermissionsChecker;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.T;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 王 ：王万鹏
 * & 作用  ：  编译页面
 * & 思路  ：
 */

public class CreationActivity extends BaseActivity {
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.creation_vedio)
    TextView creationVedio;
    @Bind(R.id.creation_text)
    TextView creationText;

    //相机权限,录制音频权限,读写sd卡的权限,都为必须,缺一不可
    private static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int PERMISSION_REQUEST_CODE = 0;
    private static final String PACKAGE_URL_SCHEME = "package:";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_creation;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.tv_cancel, R.id.creation_vedio, R.id.creation_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel: // TODO: 退出此页面
                finish();
                break;
            case R.id.creation_vedio:// TODO: 视频
//                ActivityUtils.jumpIn(this, VideoActivity.class);
//                ActivityUtils.jumpIn(this, CameraActivity.class);

//                if(!App.isLogin()){
//                    T.showShort(getApplicationContext(),"未登录");
//                    return;
//                }

                PermissionsChecker mChecker = new PermissionsChecker(getApplicationContext());
                if (mChecker.lacksPermissions(PERMISSIONS)) {
                    ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
                }else{
                    //ActivityUtils.jumpIn(this, MakeVideoActivity.class);
                    ActivityUtils.jumpIn(this, VideoActivity.class);
                }

                break;
            case R.id.creation_text:// TODO: 段子
                ActivityUtils.jumpIn(this, PublishArticleActivity.class);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions
            , @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE){
            if(hasAllPermissionsGranted(grantResults)){
                ActivityUtils.jumpIn(this, VideoActivity.class);
            } else {
                showMissingPermissionDialog();
            }
        }
    }

    private boolean hasAllPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    private void showMissingPermissionDialog() {
        AlertDialog.Builder alertDialogBuilder;
        alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(getString(R.string.tips_permission_request_title))
                .setMessage(getString(R.string.tips_permission_request_content))
                .setPositiveButton(R.string.go_and_grant_permission, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startAppSettings();
                    }
                })
                .setNegativeButton(R.string.deny_and_quit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }

    private void startAppSettings() {
        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }

}
