package com.doucome.corner.biz.core.qq.enums;

public enum QqGenderEnums {

    Male("M"), Female("F"), UnKnow("U");

    private QqGenderEnums(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
