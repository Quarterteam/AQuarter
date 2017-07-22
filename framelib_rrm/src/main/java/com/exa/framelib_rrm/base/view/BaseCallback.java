package com.exa.framelib_rrm.base.view;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.exa.framelib_rrm.base.model.exception.WrongParamException;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.base.model.system.Constants;
import com.exa.framelib_rrm.utils.FrameLifeCircleLogUtils;
import com.exa.framelib_rrm.utils.LogUtils;
import com.exa.framelib_rrm.utils.NetUtils;
import com.exa.framelib_rrm.utils.T;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

//每个页面只使用一个Callback,用tag区分是哪个请求的回调
//<Response, Host, TAG>
public abstract class BaseCallback<R, H, TAG extends BaseTag> implements BaseIView<R, TAG> {

    private WeakReference<H> host;//代表所在的Activity或者Fragment等，TODO 使用WeakReference合适吗？会不会导致出现空指针异常？
    protected Context mAppContext;
    //这里的mAppContext是ApplicationContext，不能用来显示对话框等需要依附于Activity的控件；
    //如果需要在请求开始时弹出对话框，可以在Activity或Fragment里写好弹出对话框的方法，showLoadingDialog()，
    //然后在使用onRequestStart()方法里使用getHost().showLoadingDialog()

    public BaseCallback(H host, Context mContext){
        this.host = new WeakReference<H>(host);
        this.mAppContext = mContext.getApplicationContext();
    }

    public H getHost(){
        return host.get();
    }

    @Override
    public Exception onCheckEnvironment(TAG tag) {
        FrameLifeCircleLogUtils.log("onCheckEnvironment", tag);
        if(!Constants.cacheNet && !NetUtils.isConnected(mAppContext)){//!Constants.cacheNet，因为RetrofitHelper里使用了缓存拦截器，所以此时这里先不检查网络状态
            return new NetworkErrorException();
        }
        return null;
    }

    @Override
    public final void onNextResponse(R response, TAG tag) {
        //FrameLifeCircleLogUtils.i("返回的数据为："+response.toString());
        //FrameLifeCircleLogUtils.log("onNextResponse BaseCallback", tag);

        onRequestEnd(tag);

        if(response!=null){
            onDealNextResponse(response, tag);
        }else{
            T.showShort(mAppContext, "获取到的结果为null");
//            throw new RuntimeException("获取到的结果为null");
        }

    }

    /**处理得到的结果*/
    protected abstract void onDealNextResponse(R response, TAG tag);

    @Override
    public void onProgressUpdate(long progress, TAG tag) {
        FrameLifeCircleLogUtils.log("onProgressUpdate", tag);
    }

    @Override
    public void onError(Throwable e, TAG tag) {
        FrameLifeCircleLogUtils.log("onError", tag);
        e.printStackTrace();
        if(e instanceof ConnectException){
            onRequestEnd(tag);
            T.showShort(mAppContext, "无法连接服务器！");
        }else if(e instanceof NetworkErrorException){
            onRequestEnd(tag);
            T.showShort(mAppContext, "请检查网络连接是否中断了！");
        }else if(e instanceof SocketTimeoutException){
            onRequestEnd(tag);
            T.showShort(mAppContext, "连接超时！");
        }else if(e instanceof WrongParamException){//此时还没有调用过onRequestStart方法，所以不应该调用onRequestEnd方法
            T.showShort(mAppContext, e.getMessage());
            //在这里统一吐司提示了参数错误的原因，开发时在框架之外只需在onCheckParamsLegality方法返回需要提示的字符串
            //如果参数都正确，返回null就行
        }else if(e instanceof HttpException){//retrofit2.adapter.rxjava2.HttpException: HTTP 504 Unsatisfiable Request (only-if-cached)
            onRequestEnd(tag);
            LogUtils.i("errorMsg="+e.getMessage());
            T.showShort(mAppContext, "请求未成功！");
        }else{
            onRequestEnd(tag);
            T.showShort(mAppContext, "请求未成功！");
        }

    }

    @Override
    public void onDestroy(){
        FrameLifeCircleLogUtils.log("onDestroy", null);
        mAppContext = null;
        host.clear();
        host = null;
    }

}
