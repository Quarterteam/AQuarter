package com.exa.framelib_rrm.rx;

import android.content.Context;

import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.base.view.BaseCallback;
import com.exa.framelib_rrm.utils.FrameLifeCircleLogUtils;

/**
 * Created by acer on 2017/7/17.
 * 适用于RxJava的回调类
 * <Response, Host>
 */
public abstract class RxCallback<R, H, TAG extends BaseTag> extends BaseCallback<R, H, TAG> {

    public RxCallback(H host, Context mContext) {
        super(host, mContext);
    }

    public void onSubscribe(TAG tag) {
        FrameLifeCircleLogUtils.log("onSubscribe", tag);
    }

    public void onComplete(TAG tag) {
        FrameLifeCircleLogUtils.log("onComplete", tag);
    }

}
