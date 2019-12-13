package com.dpnice.iot.tutu.model.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * rest page result
 *
 * @param <T> data type
 * @author DPnice
 */
@Getter
@Setter
public class RestPageResult<T> implements Serializable {

    private static final long serialVersionUID = -8048577763828650575L;

    private int code;
    private String errmsg;
    private int total;
    private int pageSize;
    private int pageNo;
    private T data;

    public RestPageResult() {
    }

    public RestPageResult(int code, String errcode, int total, int pageNo, int pageSize, T data) {
        this.code = code;
        this.total = total;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.data = data;
    }

    public RestPageResult(int code, String errcode, String errmsg) {
        this.code = code;
        this.errmsg = errmsg;
    }

}
