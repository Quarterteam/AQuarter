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

    @Override
    protected boolean onDealNextResponse(R response, TAG tag) {
        //FrameLifeCircleLogUtils.log("onDealNextResponse RxCallback", tag);
        //需要在子类里重写该方法，完成数据的传递，但是返回值不需要在子类里修改
        return true;//返回true，是因为需要让onRequestEnd方法在下面的onComplete中调用
    }

    public void onComplete(TAG tag) {
        FrameLifeCircleLogUtils.log("onComplete", tag);
        onRequestEnd(tag);//这个方法改为在这里调用了，是为了适应Observer的生命周期；BaseCallback中是在onNextResponse方法里调用的
    }

}
