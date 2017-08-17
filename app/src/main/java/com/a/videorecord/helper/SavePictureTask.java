package com.a.videorecord.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SavePictureTask extends AsyncTask<Bitmap, Integer, String>{

	private final int cameraId;
	private Context context;
	private OnPictureSaveListener onPictureSaveListener;
	private File file;

	public SavePictureTask(File file, OnPictureSaveListener listener, Context context, int cameraId){
		this.onPictureSaveListener = listener;
		this.file = file;
		this.context = context;
		this.cameraId = cameraId;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(final String result) {
		if(result != null)
			MediaScannerConnection.scanFile(context,
	                new String[] {result}, null,
	                new MediaScannerConnection.OnScanCompletedListener() {
	                    @Override
	                    public void onScanCompleted(final String path, final Uri uri) {
	                        if (onPictureSaveListener != null)
                                onPictureSaveListener.onSaved(result);
	                    }
            	});
	}

	@Override
	protected String doInBackground(Bitmap... params) {
		if(file == null)
			return null;

//		// 下面的方法主要作用是把图片转一个角度，也可以放大缩小等
//		Matrix m = new Matrix();
//		int width = params[0].getWidth();
//		int height = params[0].getHeight();
//		m.setRotate(90); // 旋转angle度
//		params[0] = Bitmap.createBitmap(params[0], 0, 0, width, height,
//				m, true);// 重新生成图片

//		android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
//		android.hardware.Camera.getCameraInfo(0, info);//0代表后置摄像头
//		//Bitmap bitmap = rotate(realImage, info.orientation);
//		if(info.facing == Camera.CameraInfo.CAMERA_FACING_BACK){//后置摄像头
//			Log.i("134123", "前置摄像头");
//			Matrix m = new Matrix();
//			int width = params[0].getWidth();
//			int height = params[0].getHeight();
////			m.setRotate(-info.orientation); // 旋转angle度 90度
//			m.setRotate(180); // 旋转angle度 90度
//			params[0] = Bitmap.createBitmap(params[0], 0, 0, width, height, m, true);// 重新生成图片
//		}else{//后置摄像头
//			Log.i("134123", "后置摄像头");
//			Matrix m = new Matrix();
//			int width = params[0].getWidth();
//			int height = params[0].getHeight();
//			m.setRotate(info.orientation); // 旋转angle度 90度
//			params[0] = Bitmap.createBitmap(params[0], 0, 0, width, height, m, true);// 重新生成图片
//		}

		if(cameraId!=-1){
			Camera.CameraInfo info = new Camera.CameraInfo();
			Camera.getCameraInfo(cameraId, info);
			if(info.orientation!=0){
				Matrix m = new Matrix();
				int width = params[0].getWidth();
				int height = params[0].getHeight();
				m.setRotate(info.orientation);
				// 旋转angle度（需要旋转是因为params[0]对应的是原始数据，而看到的是旋转过的，
				// 所以想要让保存的图片跟看到的方向一样，就需要把原始数据也跟看到的旋转一样的角度，
				// 通过info.orientation获得的就是看到的画面旋转过的角度
				// 为什么看到的画面是旋转过的，因为系统默认就是横屏，0度，我们的效果是竖屏，
				// 所以想要拍摄的视频看起来正确的话，需要旋转90度，
				// 不进行处理的话，保存的照片是原始数据的方向，与通过摄像头实时看到的方向会不一样
				// ）
				params[0] = Bitmap.createBitmap(params[0], 0, 0, width, height, m, true);// 重新生成图片
			}
		}
		return saveBitmap(params[0]);
	}
	
	private String saveBitmap(Bitmap bitmap) {
		if (file.exists()) {
			file.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
			bitmap.recycle();
			return file.toString();
		} catch (FileNotFoundException e) {
		   e.printStackTrace();
		} catch (IOException e) {
		   e.printStackTrace();
		}
		return null;
	}
	
	public interface OnPictureSaveListener{
		void onSaved(String result);
	}
}
