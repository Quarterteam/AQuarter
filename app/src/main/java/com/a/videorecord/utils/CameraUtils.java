package com.a.videorecord.utils;

import android.hardware.Camera;

/**
 * Created by acer on 2017/8/16.
 */
public class CameraUtils {

    /**
     * 找前置摄像头,没有则返回-1
     *
     * @return cameraId
     */
    public static int findFrontFacingCamera() {
        int cameraId = -1;
        //获取摄像头个数
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                //cameraFront = true;
                break;
            }
        }
        //mCameraId = cameraId;
        return cameraId;
    }

    /**
     * 找后置摄像头,没有则返回-1
     *
     * @return cameraId
     */
    public static int findBackFacingCamera() {
        int cameraId = -1;
        //获取摄像头个数
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
//                cameraFront = false;
                break;
            }
        }
//        mCameraId = cameraId;
        return cameraId;
    }

}
