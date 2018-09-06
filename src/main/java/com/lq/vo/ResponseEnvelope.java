package com.lq.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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



    public T getData() {
        return data;
    }



    public boolean isStatus() {
        return status;
    }
}
