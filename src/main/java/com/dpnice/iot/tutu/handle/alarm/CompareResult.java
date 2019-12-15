package com.dpnice.iot.tutu.handle.alarm;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DPnice
 * @date 2019-12-15 下午 1:25
 */
@Getter
@Setter
public class CompareResult {

    private AlarmType alarmType;

    private boolean alarm;

    /**
     * left false
     * right true
     */
    private boolean leftOrRight;
    private double value;

    @Override
    public String toString() {
        String msg;
        switch (alarmType) {
            case H:
                if (!leftOrRight) {
                    msg = "湿度低于 " + value;
                } else {
                    msg = "湿度高于 " + value;
                }
                break;
            case T:
                if (!leftOrRight) {
                    msg = "温度低于 " + value;
                } else {
                    msg = "温度高于 " + value;
                }
                break;
            default:
                msg="";
        }
        return msg;
    }
}
