package com.exa.framelib_rrm.base.presenter;

import com.exa.framelib_rrm.base.model.exception.WrongParamException;
import com.exa.framelib_rrm.base.model.http.tag.BaseTag;
import com.exa.framelib_rrm.base.view.BaseIView;

/**
 * Created by acer on 2017/6/11.
 * 应该保证一个页面只使用一个presenter实例？
 */
public abstract class BasePresenter<T extends BaseIView> {

    private T mT;

    public void attacView(T t) {
        this.mT = t;
    }

    public T getMvpView() {
        return mT;
    }

    public void detachView() {
        this.mT = null;
    }

    /**
     * TODO 怎么让这个方法必须调用
     * @param checkEnvironment 是否检查网络状态或者其他
     * @param tag 标记，代表是哪个请求
     * @param params 需要的参数
     * */
    public boolean preCheck(boolean checkEnvironment, BaseTag tag, Object...params){
        if(checkEnvironment){
            Exception ex = mT.onCheckEnvironment(tag);
            //不同类型的Exception，比如没有连接网络时的Exception；如果返回值是null，代表一切正常
            if (ex != null) {
                mT.onError(ex, tag);
                return false;
            }
        }

        //检查参数的合法性，如果有参数不合法，返回值是需要提示的错误信息；如果参数都合法，返回值是null
        String errorMsg = mT.onCheckParamsLegality(tag, params);
        if (errorMsg != null) {
            mT.onError(new WrongParamException(errorMsg), tag);//提示参数错误原因，在框架的BaseCallback里吐司了
            return false;
        } else {//说明参数格式都正确
            mT.onRequestStart(tag);
        }
        return true;
    }

}
