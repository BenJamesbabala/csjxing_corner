package com.doucome.corner.biz.dcome.enums;

/**
 * 类DcGenderEnum的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-7-28 下午11:47:06
 */
public enum DcGenderEnum {
    Male("M"), Female("F"), UnKnow("U");

    private DcGenderEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
