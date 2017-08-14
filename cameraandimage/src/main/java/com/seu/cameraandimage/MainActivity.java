package com.seu.cameraandimage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.seu.cameraandimage.activity.CameraActivity;
import com.seu.cameraandimage.image.ImageActivity;

//public class MainActivity extends Activity {
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PermissionChecker.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.CAMERA },
                            REQUEST_CODE_PERMISSION);
                } else {
                    startActivity(REQUEST_CODE_PERMISSION);
                }
            }
        });
        findViewById(R.id.button_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(-1);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                                     int[] grantResults) {
        if (grantResults.length != 1 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivity(REQUEST_CODE_PERMISSION);
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void startActivity(int i) {
        switch (i) {
            case REQUEST_CODE_PERMISSION:
                startActivity(new Intent(this, CameraActivity.class));
                break;
            case -1:
                startActivity(new Intent(this, ImageActivity.class));
                break;
            default:
                break;
        }
    }
}
