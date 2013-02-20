package com.doucome.corner.biz.dcome.enums;

/**
 * ��Ʒ����״̬
 * 
 * @author ze2200
 */
public enum DcPromotionAwardSendStatusEnums {

    /**
     * δ����
     */
    UNSEND("U"),
    /**
     * ���ͳɹ�
     */
    SUCCESS("S"),

    /**
     * ����ʧ��
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
