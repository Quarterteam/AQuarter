package com.exa.framelib_rrm.base.view;

import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
/**
 * BaseTag的作用：
 * 当一个页面有多个接口需要请求时，可以通过该参数对象的tag属性进行区分；
 * 它的子类有PositionTag，在拥有BaseTag功能的基础上，还可以应对同一个页面上有多级列表的情况；
 *
 * 下面的方法代表使用MVP模式时，通过Presenter进行一次操作，从本次操作开始执行到本次操作执行结束，的基本生命周期。
 *
 * 可以用于网络请求，也可以用于本地耗时操作或非耗时操作。（具体实现参考example中的示例）
 * */
public interface BaseIView<R, TAG extends BaseTag> {

//    /**发起网络请求前，进行检查网络等操作*/
//    Exception onCheckEnvironment(TAG tag);
//    /**发起网络请求前，检查参数是否合法*/
//    String onCheckParamsLegality(TAG tag, Object...params);
//    /**发起网络请求前，即将发起网络请求，可以在这个方法里显示进度条等*/
//    void onRequestStart(TAG tag);
//    /**得到网络请求的结果*/
//    void onNextResponse(R response, TAG tag);
//    /**得到网络请求的进度，比如下载进度*/
//    void onProgressUpdate(long progress, TAG tag);//TODO 还完全没有处理
//    /**网络请求过程中发生错误，导致请求未成功*/
//    void onError(Throwable e, TAG tag);
//    /**本次网络请求结束，可以在这个方法里隐藏进度条等*/
//    void onRequestEnd(TAG tag);
//    /**退出本页面时，把一些对象置为空，防止内存泄漏*/
//    void onDestroy();

    /**发起网络请求前，进行检查网络等操作*/
    Exception onCheckEnvironment(TAG tag);
    /**执行本次实际的操作之前，检查参数是否合法*/
    String onCheckParamsLegality(TAG tag, Object...params);
    /**执行本次实际的操作之前，即将执行实际的操作，可以在这个方法里显示进度条等*/
    void onRequestStart(TAG tag);
    /**处理Presenter里本次操作得到的结果*/
    void onNextResponse(R response, TAG tag);
    /**处理当前进度，比如下载进度*/
    void onProgressUpdate(long progress, TAG tag);//TODO 还完全没有处理
    /**本次操作过程中出现错误，导致未成功，处理异常*/
    void onError(Throwable e, TAG tag);
    /**本次操作结束，可以在这个方法里隐藏进度条等*/
    void onRequestEnd(TAG tag);
    /**退出本页面时，把一些对象置为空或者进行其他处理，防止内存泄漏*/
    void onDestroy();

}
