package com.doucome.corner.biz.dcome.enums;

/**
 * �״̬.
 * 
 * @author ze2200
 */
public enum DcPromotionStatusEnum {
    /**
     * ��ͨ��δ����
     */
    NORMAL("N"),
    /**
     * �ر�
     */
    DISABLE("D"), 
    /**
     * �������ѿ���
     */
    ENDING("E"),
    UNKNOWN(null);

    private String value;

    private DcPromotionStatusEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static DcPromotionStatusEnum getInstance(String status) {
        try {
            return Enum.valueOf(DcPromotionStatusEnum.class, status);
        } catch (Exception e) {
            for (DcPromotionStatusEnum tempStatus : values()) {
                if (tempStatus.getValue().equals(status)) {
                    return tempStatus;
                }
            }
        }
        return UNKNOWN;
    }
}
