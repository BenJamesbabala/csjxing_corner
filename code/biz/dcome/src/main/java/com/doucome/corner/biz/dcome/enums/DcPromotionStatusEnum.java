package com.doucome.corner.biz.dcome.enums;

/**
 * 活动状态.
 * 
 * @author ze2200
 */
public enum DcPromotionStatusEnum {
    /**
     * 普通，未开奖
     */
    NORMAL("N"),
    /**
     * 关闭
     */
    DISABLE("D"), 
    /**
     * 结束，已开奖
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
