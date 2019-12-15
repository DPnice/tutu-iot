package com.dpnice.iot.tutu.handle.alarm;

import org.springframework.stereotype.Service;

/**
 * @author DPnice
 * @date 2019-12-15 上午 2:47
 */
@Service
public class AlarmContext {

    public CompareResult isAlarm(AlarmType alarmType, String section, double value) {
        CompareResult result = null;
        section = section.trim();
        double left, right;
        String substring = section.substring(1, section.length() - 1);
        String[] split = substring.split(",");
        left = Double.parseDouble(split[0].trim());
        right = Double.parseDouble(split[1].trim());

        //判断使用哪个策略
        if (section.startsWith("[")) {
            if (section.endsWith("]")) {
                result = SectionFactory.getSectionComparisonByType(SectionType.YY).alarmCompare(alarmType,
                        left, right, value);
            } else if (section.endsWith(")")) {
                result = SectionFactory.getSectionComparisonByType(SectionType.YN).alarmCompare(alarmType,
                        left, right, value);
            }
        } else if (section.startsWith("(")) {
            if (section.endsWith(")")) {
                result = SectionFactory.getSectionComparisonByType(SectionType.NN).alarmCompare(alarmType,
                        left, right, value);
            } else if (section.endsWith("]")) {
                result = SectionFactory.getSectionComparisonByType(SectionType.NY).alarmCompare(alarmType,
                        left, right, value);
            }
        }

        return result;

    }


}
