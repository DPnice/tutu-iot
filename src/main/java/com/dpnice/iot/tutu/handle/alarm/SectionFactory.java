package com.dpnice.iot.tutu.handle.alarm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author DPnice
 * @date 2019-12-15 上午 3:11
 * <p>
 * 区间工厂
 */
public class SectionFactory {

    private static Map<SectionType, SectionComparison> map = new ConcurrentHashMap<>();

    public static SectionComparison getSectionComparisonByType(SectionType type) {
        return map.get(type);
    }

    public static void register(SectionType type, SectionComparison sectionComparison) {
        map.put(type, sectionComparison);
    }

}
