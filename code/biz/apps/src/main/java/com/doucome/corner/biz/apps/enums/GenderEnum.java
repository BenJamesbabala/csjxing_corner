package com.doucome.corner.biz.apps.enums;

/**
 * ��DcGenderEnum��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-7-28 ����11:47:06
 */
public enum GenderEnum {
    Male("M"), Female("F"), UnKnow("U");

    private GenderEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
