package com.dpnice.iot.tutu.handle.alarm;

/**
 * @author DPnice
 * @date 2019-12-15 下午 2:03
 */
public enum AlarmType {

    /**
     * 告警类型
     */
    T("温度"), H("湿度"), W("水量");

    private String value;

    AlarmType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
