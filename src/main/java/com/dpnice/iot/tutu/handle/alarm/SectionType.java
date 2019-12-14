package com.dpnice.iot.tutu.handle.alarm;

/**
 * @author DPnice
 * @date 2019-12-15 上午 3:11
 */
public enum SectionType {
    /**
     * 区间枚举
     */
    YY("[]"), NN("()"), YN("[)"), NY("(]");

    private String value;

    SectionType(String value) {
        this.value = value;
    }
    public String value() {
        return this.value;
    }

}
