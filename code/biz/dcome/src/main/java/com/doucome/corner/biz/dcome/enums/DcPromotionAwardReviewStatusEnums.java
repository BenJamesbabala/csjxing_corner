package com.doucome.corner.biz.dcome.enums;

/**
 * ÷–Ω±…Û∫À◊¥Ã¨
 * 
 * @author ze2200
 */
public enum DcPromotionAwardReviewStatusEnums {

    /**
     * Œ¥…Û∫À
     */
    UNVIEW("U"),
    /**
     * …Û∫À≥…π¶
     */
    SUCCESS("S"),

    /**
     * …Û∫À ß∞‹
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
