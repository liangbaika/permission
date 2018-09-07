package com.lq.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.HashMap;
import java.util.Map;

/**
 * The response envelope to envelope the response data and make it as the
 * standard data
 *
 * @param <T>
 */
@JsonInclude(Include.NON_EMPTY)
public class ResponseEnvelope<T> {
    private T data;
    private boolean status;
    private String msg;

    public ResponseEnvelope() {
        this((T) null, false);
    }

    public ResponseEnvelope(T data) {
        this(data, false);
    }

    public ResponseEnvelope(T data, boolean status) {
        this.data = data;
        this.status = status;
    }

    public ResponseEnvelope(T data, boolean status, String msg) {
        this.msg = msg;
        this.data = data;
        this.status = status;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", data);
        map.put("status", status);
        map.put("msg", msg);
        return map;
    }

    public ResponseEnvelope(boolean status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }


    public boolean isStatus() {
        return status;
    }
}
