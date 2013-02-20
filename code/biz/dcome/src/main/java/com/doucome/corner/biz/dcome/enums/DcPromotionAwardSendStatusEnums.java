package com.doucome.corner.biz.dcome.enums;

/**
 * 奖品发送状态
 * 
 * @author ze2200
 */
public enum DcPromotionAwardSendStatusEnums {

    /**
     * 未发送
     */
    UNSEND("U"),
    /**
     * 发送成功
     */
    SUCCESS("S"),

    /**
     * 发送失败
     */
    FAIL("F") ,
    
    UNKNOWN("") ,
    ;

    private String status;

    private DcPromotionAwardSendStatusEnums(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     * @return
     */
    public static DcPromotionAwardSendStatusEnums getInstance(String status) {
        try {
            return valueOf(DcPromotionAwardSendStatusEnums.class, status);
        } catch (Exception e) {
            for (DcPromotionAwardSendStatusEnums statusEnum : values()) {
                if (statusEnum.getStatus().equalsIgnoreCase(status)) {
                    return statusEnum;
                }
            }
        }
        return UNKNOWN;
    }
}
