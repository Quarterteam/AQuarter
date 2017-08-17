package com.a.quarter.view.activity.compile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.a.quarter.R;
import com.a.quarter.view.base.BaseActivity;
import com.a.videorecord.helper.SavePictureTask;
import com.exa.framelib_rrm.utils.ScreenUtils;
import com.exa.framelib_rrm.utils.StatusBarCompat;
import com.seu.cameraandimage.common.view.edit.filter.ImageEditFilterView;
import com.seu.magicfilter.display.MagicImageDisplay;
import com.seu.magicfilter.utils.SaveTask;

import java.io.File;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

//预览拍摄得照片
public class PicturePreviewActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_head)
    TextView tvHead;
    @Bind(R.id.tv_right)
    TextView tvRight;
//    @Bind(R.id.tv_next_step)
//    TextView tvNextStep;
    private MagicImageDisplay mMagicImageDisplay;
    private String picpath;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_picture_preview;
    }

    @Override
    protected void setStatusBar() {
        //设置状态栏为透明，并且使用状态栏所占空间
        StatusBarCompat.compat(this, ContextCompat.getColor(this, android.R.color.transparent), true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //如果可以使用状态栏所占的空间，左侧的SlidingMenu使用了状态栏所占的空间，
            //需要给右侧的主布局加上一个高度等于状态栏高度的paddingTop，让头部不被状态栏挡住
            findViewById(R.id.rl_head).setPadding(0, ScreenUtils.getStatusHeight(this), 0, 0);
        }
    }

    @Override
    protected void initViews() {
        tvBack.setOnClickListener(this);
        tvHead.setText("图片预览");
        tvRight.setText("存到本地");
//        tvBack.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.textsize_medium));
//        tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.textsize_medium));
        //获取传过来图片路径
        picpath = getIntent().getStringExtra("picpath");
        if (TextUtils.isEmpty(picpath)) {
            finish();
            return;
        }

        initMagicPreview();
        ImageEditFilterFragment mImageEditFilterFrag = new ImageEditFilterFragment(this, mMagicImageDisplay);
        //mImageEditFilterView.setOnHideListener(mOnHideListener);

        getFragmentManager().beginTransaction()
                .add(R.id.image_edit_fragment_container, mImageEditFilterFrag)
                .commit();

//        tvNextStep.setOnClickListener(this);
        tvRight.setOnClickListener(this);
    }

    private void initMagicPreview() {
        GLSurfaceView glSurfaceView = (GLSurfaceView) findViewById(R.id.glsurfaceview_image);
        mMagicImageDisplay = new MagicImageDisplay(this, glSurfaceView);

        Bitmap bitmap = BitmapFactory.decodeFile(picpath);
        mMagicImageDisplay.setImageBitmap(bitmap);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMagicImageDisplay != null)
            mMagicImageDisplay.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMagicImageDisplay != null)
            mMagicImageDisplay.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMagicImageDisplay != null)
            mMagicImageDisplay.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_back:
                //释放视频资源
                //关闭页面
                finish();
                break;

//            case R.id.tv_next_step:
////                //点击了下一步，跳转到发表页面，把图片地址传过去
////                Intent intent = new Intent(this, PublishVideoActivity.class);
////                intent.putExtra("picpath", picpath);
////                startActivity(intent);
//                break;

            case R.id.tv_right:
                //保存图片
                savePicture();
                break;

            default:
                break;
        }
    }

    private String picturePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/videokit/picture";
    private void savePicture() {
        tvRight.setEnabled(false);
        //指定照片的保存路径
        File file = new File(picturePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date d = new Date();
        String timestamp = String.valueOf(d.getTime());
        final File file2 = new File(picturePath+"/p"+timestamp+".jpg");
//        SavePictureTask.OnPictureSaveListener listener = new SavePictureTask.OnPictureSaveListener(){
//
//            @Override
//            public void onSaved(String result) {
//                Log.i("134123","result="+result);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tvRight.setEnabled(true);
//                        Toast.makeText(getApplicationContext(), "图片已保存在"+picturePath, Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//        };
//        SavePictureTask savePictureTask = new SavePictureTask(file2, listener, getApplicationContext(), -1);
//        savePictureTask.execute(mMagicImageDisplay);
        mMagicImageDisplay.savaImage(file2, new SaveTask.onPictureSaveListener() {

            @Override
            public void onSaved(String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvRight.setEnabled(true);
                        Toast.makeText(getApplicationContext(), "图片已保存在" + picturePath, Toast.LENGTH_LONG).show();
                    }
                });
            }

        });
    }

}
