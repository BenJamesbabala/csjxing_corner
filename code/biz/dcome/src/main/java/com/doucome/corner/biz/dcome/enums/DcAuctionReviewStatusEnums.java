package com.doucome.corner.biz.dcome.enums;

public enum DcAuctionReviewStatusEnums {

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

