package com.dpnice.iot.tutu.model.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * rest result class
 *
 * @param <T> data type
 * @author DPnice
 */
@Setter
@Getter
public class RestResult<T> implements Serializable {

    private static final long serialVersionUID = 6095433538316185017L;

    private int code;
    private String errmsg;
    private T data;

    public RestResult(int code, String errmsg, T data) {
        this.code = code;
        this.errmsg = errmsg;
        this.data = data;
    }

    public RestResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public RestResult(int code) {
        this.code = code;
    }

}
