package com.exa.framelib_rrm.rx;

import com.exa.framelib_rrm.base.presenter.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by dom4j on 2017/3/7.
 * 基于Rx的Presenter封装 更好的控制订阅的声明周期
 * 防止在Activity被finish之后请求才完成，可能会出现的空指针异常
 *
 * 在BaseObserver中用到了本类里关于Disposable的方法
 *
 * 应该保证一个页面只使用一个presenter实例
 */
public abstract class RxBasePresenter<T extends RxCallback> extends BasePresenter<T> {

    protected CompositeDisposable mComposite;

    protected void clearDisposables(){
        if(mComposite != null){
            mComposite.clear();
        }
        //FrameLifeCircleLogUtils.i("停止所有的请求");
    }

    public void addDisposable(Disposable d){
        if(d == null){
            return;
        }
        if(mComposite == null){
            mComposite = new CompositeDisposable();
        }
        //FrameLifeCircleLogUtils.i("请求入列");
        mComposite.add(d);
    }

    public void removeDisposable(Disposable d){
        if(d!=null && mComposite != null){
            //FrameLifeCircleLogUtils.i("移除");
            mComposite.remove(d);
        //    d.dispose();
        }
    }

    @Override
    public void detachView() {
        clearDisposables();
        mComposite = null;
        super.detachView();
    }
}
