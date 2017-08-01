package com.a.quarter.model.bean.login;

/**
 * Created by acer on 2017/7/21.
 *
 * 成功
 * {"code":"200","user":{"userHead":"","userId":7,"userName":"test","userPassword":"123","userPhone":"15910488412","userSex":"男"}}
 *
 * 参数为空时
 * {
 "timestamp": 1500699040948,
 "status": 500,
 "error": "Internal Server Error",
 "exception": "org.mybatis.spring.MyBatisSystemException",
 "message": "nested exception is org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 15",
 "path": "/user/addLogin"
 }

 失败
 {"code":"500"}
 */
public class LoginResponse {
    public String code;
    public String message;
    public User user;
}
