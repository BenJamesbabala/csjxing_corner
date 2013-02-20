package com.doucome.corner.biz.core.sms.model;

import org.apache.commons.lang.StringUtils;

/**
 * 短信 业务类型 Enum

 * @author langben 
 * @date 2011-1-24 
 *
 */
public enum SmsBusinessEnums {

	
	
	/**
	 * 点点折返现短信
	 */
	DDZ_SETTLE_SMS("ddz_settle_sms","点点折返现短信") ,

	DDZ_JFB_SETTLE_SMS("ddz_jfb_settle_sms" , "点点折集分宝结算") ,
	
	DDZ_DELAY_SMS("ddz_delay_sms","点点折大额延迟短信") ,
	
	/**
	 * UNKNOW
	 */
	UNKNOW("unknow" , "UNKNOW") ,
	;
		
	public static SmsBusinessEnums toBusinessEnum(String smsBusinessId){
		SmsBusinessEnums[] smsBusinessEnums = SmsBusinessEnums.values() ;
		for(SmsBusinessEnums smsBusinessEnum : smsBusinessEnums){
			if(StringUtils.equals(smsBusinessEnum.getValue(), smsBusinessId)){
				return smsBusinessEnum ;
			}
		}
		return UNKNOW ;
	}
	
	private String value;
	
	private String description;
    
    private SmsBusinessEnums(String value , String description){
        this.value = value;
        this.description = description ;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getDescription(){
    	return this.description ;
    }
}
