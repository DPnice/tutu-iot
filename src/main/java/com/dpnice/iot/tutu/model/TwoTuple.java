package com.dpnice.iot.tutu.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DPnice
 */
@Getter
@Setter
public class TwoTuple<A, B> {
    public A key;

    public B value;

    public TwoTuple(A k, B v) {
        key = k;
        value = v;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

}
