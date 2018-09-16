package com.lq.enums;

/**
 * @Auther: LQ
 * @Date: 2018/9/16 21:42
 * @Description:
 */
public enum DataUseful {

    USEFUL(1, "有效"),
    NOUSEFUL(-1, "冻结"),;

    private Integer code;
    private String msg;

    DataUseful(Integer code, String msg) {
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
