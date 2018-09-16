package com.lq.utils;

import com.lq.model.SysUserModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: LQ
 * @Date: 2018/9/16 21:01
 * @Description: LoginHolder
 */
public class LoginHolder {

    private static final ThreadLocal<SysUserModel> userHolder = new ThreadLocal<>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static void addUser(SysUserModel userModel) {
        userHolder.set(userModel);
    }

    public static void addRequest(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static SysUserModel getUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getRequest() {
        return requestHolder.get();
    }


    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }

}
