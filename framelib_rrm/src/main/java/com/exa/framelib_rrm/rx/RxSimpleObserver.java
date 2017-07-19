package com.exa.framelib_rrm.rx;

import com.exa.framelib_rrm.base.model.http.tag.BaseTag;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by acer on 2017/7/16.
 * 简单的RxJava观察者
 *
 * 关于Observer重用的问题？
 */
public class RxSimpleObserver<T> implements Observer<T> {

    private RxBasePresenter<RxCallback> presenter;
    private Disposable d;
    private BaseTag tag;

    public RxSimpleObserver(RxBasePresenter<RxCallback> presenter, BaseTag tag) {
        this.presenter = presenter;
        this.tag = tag;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        presenter.addDisposable(d);
        presenter.getMvpView().onSubscribe(tag);
        //FrameLifeCircleLogUtils.i("RxSimpleObserver onSubscribe");
    }

    @Override
    public void onNext(T t) {
        presenter.getMvpView().onNextResponse(t, tag);
        //FrameLifeCircleLogUtils.i("RxSimpleObserver onNext：" + t);
    }

    @Override
    public void onError(Throwable e) {
        //FrameLifeCircleLogUtils.e("RxSimpleObserver onError", e);
        presenter.removeDisposable(d);
        presenter.getMvpView().onError(e, tag);
        presenter = null;
        d = null;
        tag = null;
    }

    @Override
    public void onComplete() {
        presenter.getMvpView().onComplete(tag);
        presenter.removeDisposable(d);
        presenter = null;
        d = null;
        tag = null;
        //FrameLifeCircleLogUtils.i("RxSimpleObserver onComplete");
    }
}
