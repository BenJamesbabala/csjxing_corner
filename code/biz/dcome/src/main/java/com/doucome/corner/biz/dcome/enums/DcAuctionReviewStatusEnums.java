package com.doucome.corner.biz.dcome.enums;

public enum DcAuctionReviewStatusEnums {

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

    private DcAuctionReviewStatusEnums(String status){
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
    public static DcAuctionReviewStatusEnums getInstance(String status) {
        try {
            return valueOf(DcAuctionReviewStatusEnums.class, status);
        } catch (Exception e) {
            for (DcAuctionReviewStatusEnums statusEnum : values()) {
                if (statusEnum.getStatus().equalsIgnoreCase(status)) {
                    return statusEnum;
                }
            }
        }
        return UNKNOWN;
    }
}

