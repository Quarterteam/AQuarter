package com.a.quarter.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.a.quarter.R;
import com.exa.framelib_rrm.app.BaseApp;
import com.exa.framelib_rrm.base.presenter.BasePresenter;
import com.exa.framelib_rrm.base.view.BaseCallback;
import com.exa.framelib_rrm.utils.ActivityUtils;
import com.exa.framelib_rrm.utils.StatusBarCompat;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by acer on 2017/7/17.
 * 需要做的有：
 * Activity的添加和移除；
 * 头部布局统一管理；
 * Prsenter和Callback的关联和解除关联；
 * 继承AutoLayoutActivity，用于屏幕适配；
 * 设置状态栏颜色和是否使用状态栏所占空间；
 */
public abstract class BaseActivity<P extends BasePresenter, C extends BaseCallback> extends AutoLayoutActivity {

    protected P mPresenter;
    protected C mCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        //设置状态栏
        setStatusBar();

        //添加Activity到集合中
        BaseApp.getInstance0().addActivity(this);
        initViews();
        initDatas();
    }

    protected void setStatusBar() {
        StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.mainColor), false);
    }

    /**获取本页面的布局id*/
    protected abstract int getContentViewId();

    /**初始化控件*/
    protected abstract void initViews();

    /**初始化数据*/
    protected abstract void initDatas();

    /**
     * 关联Prsenter和Callback，
     * 不一定每个页面都需要请求网络，所以可以由子类选择是否调用这个方法
     * */
    protected void bindPresenter(P presenter, C callback) {
        mPresenter = presenter;
        mCallback = callback;
        mPresenter.attacView(mCallback);
    }

    @Override
    protected void onDestroy() {
        //解除Prsenter和Callback关联，避免内存泄漏
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (mCallback != null) {
            mCallback.onDestroy();
        }
        ButterKnife.unbind(this);
        //把Activity从集合中移除
        BaseApp.getInstance0().removeActivity(this);
        //如果有软键盘的话，关闭软键盘
        ActivityUtils.closeKeyBoardIfNeed(this);
        super.onDestroy();
    }

}
