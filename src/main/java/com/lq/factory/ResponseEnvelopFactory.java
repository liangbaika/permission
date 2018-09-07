package com.lq.factory;


import com.lq.vo.ResponseEnvelope;

public class ResponseEnvelopFactory {
    private ResponseEnvelopFactory() {
    }

    public static ResponseEnvelope success() {
        ResponseEnvelope responseEnvelope = new ResponseEnvelope(true);
        return responseEnvelope;
    }

    public static ResponseEnvelope success(Object data) {
        ResponseEnvelope responseEnvelope = new ResponseEnvelope(data, true);
        return responseEnvelope;
    }

    public static ResponseEnvelope success(Object data, boolean status) {
        ResponseEnvelope responseEnvelope = new ResponseEnvelope(data, status);
        return responseEnvelope;
    }

    public static ResponseEnvelope fail() {
        return success(null, false);
    }

    public static ResponseEnvelope fail(Object data) {
        return success(data, false);
    }

    public static ResponseEnvelope fail(String msg) {
        return new ResponseEnvelope(null, false, msg);
    }
}
