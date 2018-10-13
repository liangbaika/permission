package com.lq.enums;

/**
 * @Auther: LQ
 * @Date: 2018/10/13 13:40
 * @Description: 日志类型
 */
public enum LogType {
    DEPT(1, "部门"),
    USER(2, "用户"),
    ACLMODULE(3, "权限模块"),
    ACL(4, "权限点"),
    ROLE(5, "角色"),
    ROLE_ACL(6, "角色与权限点"),
    ROLE_USER(7, "角色与用户");

    private String msg;
    private Integer code;

    LogType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
