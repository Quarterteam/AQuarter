package com.exa.framelib_rrm.base.model.exception;

/**
 * Created by acer on 2017/7/16.
 * 自定义的参数错误异常，参数是指调用Presenter的使用到Callback的方法时传入的参数
 */
public class WrongParamException extends BaseCustomException{

    public WrongParamException(String detailMessage) {
        super(detailMessage);
    }

//    public WrongParamException() {
//        this("参数不正确");
//    }
}
