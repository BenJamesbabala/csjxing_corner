package com.doucome.corner.biz.dcome.enums;

/**
 * ��DcUserExternalPfEnum.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-7-28 ����11:47:06
 */
public enum DcUserExternalPfEnum {
    QQ("qq");

    private DcUserExternalPfEnum(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
