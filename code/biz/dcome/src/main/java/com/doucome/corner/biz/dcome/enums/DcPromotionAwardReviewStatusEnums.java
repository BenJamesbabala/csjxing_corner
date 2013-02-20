package com.doucome.corner.biz.dcome.enums;

/**
 * �н����״̬
 * 
 * @author ze2200
 */
public enum DcPromotionAwardReviewStatusEnums {

    /**
     * δ���
     */
    UNVIEW("U"),
    /**
     * ��˳ɹ�
     */
    SUCCESS("S"),

    /**
     * ���ʧ��
     */
    FAIL("F") ,
    
    UNKNOWN("")
    
    ; 

    private String status;

    private DcPromotionAwardReviewStatusEnums(String status){
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
    public static DcPromotionAwardReviewStatusEnums getInstance(String status) {
        try {
            return valueOf(DcPromotionAwardReviewStatusEnums.class, status);
        } catch (Exception e) {
            for (DcPromotionAwardReviewStatusEnums statusEnum : values()) {
                if (statusEnum.getStatus().equalsIgnoreCase(status)) {
                    return statusEnum;
                }
            }
        }
        return UNKNOWN;
    }
}
