package com.exa.framelib_rrm.rx;

import com.exa.framelib_rrm.base.model.http.tag.BaseTag;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by acer on 2017/7/16.
 * RxJava工具类
 */
public class RxHelper {

//    public static Observable sync(Observable observable) {
//        return observable
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    /**异步请求，指定线程*/
    public static Observable async(Observable observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**异步请求，指定线程和观察者*/
    public static <T> void asyncGet(Observable<T> observable, BaseTag tag, RxBasePresenter presenter) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSimpleObserver<T>(presenter, tag));
    }

    /**同步请求，指定线程和观察者*/
    public static <T> void syncGet(Observable<T> observable, BaseTag tag, RxBasePresenter presenter) {
        observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSimpleObserver<T>(presenter, tag));
    }

}
