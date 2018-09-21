package com.lq.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: LQ
 * @Date: 2018/9/10 22:20
 * @Description:
 */
public enum ErrorCode {

    PARAM_MISS(10001, "缺少参数"),
    PARAM_ERROR(10002, "参数错误"),
    PARAM_INVALIDED(10003, "参数非法"),
    EMAIL_EXIST(10004, "邮箱已存在"),
    PHONE_EXIST(10005, "电话已存在"),
    USER_NOT_EXIST(10006,"用户不存在"),
    USER_FROZI(10007,"用户被冻结，请联系管理员"),
    DEPT_REPEAT(10008,"部门名重复"),
    DELETE_ERROR1(10009,"此部门下已有数据 删除失败"),
    USERNAME_EXIST(10010,"用户名已存在"),

    ;

    private Integer code;
    private String msg;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
