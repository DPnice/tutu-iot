package com.dpnice.iot.tutu.handle.alarm;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author DPnice
 * @date 2019-12-15 上午 3:02
 * []
 */
@Service
public class SectionYY implements SectionComparison, InitializingBean {

    private SectionType type = SectionType.YY;

    /**
     * 判断是否告警
     *
     * @param alarmType 类型
     * @param left      left
     * @param right     right
     * @param value     value
     * @return CompareResult
     */
    @Override
    public CompareResult alarmCompare(AlarmType alarmType, double left, double right, double value) {
        CompareResult compareResult = new CompareResult();
        compareResult.setAlarmType(alarmType);
        boolean notAlarm = (value >= left && value <= right);
        if (notAlarm) {
            //不告警
            compareResult.setAlarm(false);
            return compareResult;
        } else {
            compareResult.setAlarm(true);
            //告警
            if (value < left) {
                compareResult.setLeftOrRight(false);
                compareResult.setValue(left);
            } else {
                compareResult.setLeftOrRight(true);
                compareResult.setValue(right);
            }
        }
        return compareResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SectionFactory.register(type, this);
    }

}
