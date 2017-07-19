package com.exa.framelib_rrm.base.model.exception;

/**
 * Created by acer on 2017/7/16.
 * 自定义异常基类
 */
public class BaseCustomException extends Exception{

    public BaseCustomException() {
    }

    public BaseCustomException(String detailMessage) {
        super(detailMessage);
    }
}
