package com.a.quarter.presenter.publish;

import com.a.quarter.model.api.Api;
import com.a.quarter.model.bean.login.RegisterResponse;
import com.exa.framelib_rrm.retrofit.RetrofitHelper;
import com.exa.framelib_rrm.rx.RxBasePresenter;
import com.exa.framelib_rrm.rx.RxHelper;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 *
 */
public class PublishVideoPresenter extends RxBasePresenter{

    //发表视频
    public void publishVideo(String mediaDescription, int mediaUserId, File videoFile){
        if(preCheck(true, null)){
            //构建body
            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("mediaDescription", mediaDescription)
                    .addFormDataPart("mediaDictionaryValue", 2+"")
                    .addFormDataPart("mediaUserId", mediaUserId+"")
                    .addFormDataPart("file", videoFile.getName(), RequestBody.create(MediaType.parse("video/*"), videoFile))
                    .build();
            Observable<String> observable = RetrofitHelper
                    .createApi(Api.class)
                    .publishVideo(requestBody);

            RxHelper.asyncGet(observable, null, this);
        }
    }


}
