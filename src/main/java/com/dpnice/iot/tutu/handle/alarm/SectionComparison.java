package com.dpnice.iot.tutu.handle.alarm;

/**
 * @author DPnice
 * @date 2019-12-15 上午 2:50
 * 区间策略接口
 */
public interface SectionComparison {

    /**
     * 判断是否告警
     * @param alarmType 类型
     * @param left left
     * @param right right
     * @param value value
     * @return CompareResult
     */
    CompareResult alarmCompare(AlarmType alarmType,double left, double right, double value);
}
