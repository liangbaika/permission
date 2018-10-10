package com.lq.enums;

/**
 * @Auther: LQ
 * @Date: 2018/10/10 23:08
 * @Description:
 */
public enum RoleTypeEnum {

    //1 超级管理员 2 普通管理员 3 职工 4 老板 5 其他 （可拓展）
    SuperAdmin(1, "超级管理员"),
    Admin(2, "普通管理员"),
    Employee(3, "职工"),
    Boss(4, "老板"),
    Other(5, "其他");

    private String msg;
    private Integer code;
    RoleTypeEnum(Integer code, String msg) {
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
