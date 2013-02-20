package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author langben 2013-1-13
 *
 */
public enum DcExchangeApproveStatusEnums {

	/**
	 * �ȴ����
	 */
	APPROVED_PENDING("P") ,
	
	/**
	 * ���ͨ��
	 */
	APPROVED("A") ,
	
	/**
	 * ���δͨ��
	 */
	DISAPPROVED("D") ,
	
	/**
	 * �Ѿ�����
	 */
	SENDED("S") ,
	
	/**
	 * ȡ��
	 */
	CANCEL("C") ,
	
	/**
	 * ʧ��
	 */
	FAILED("F") ,
	
	UNKNOWN("") ,
	;
	
	DcExchangeApproveStatusEnums(String value){
		this.value = value ;
	}
	
	private String value ;

	public String getValue() {
		return value;
	}
	
	private static Map<String,DcExchangeApproveStatusEnums> map = new HashMap<String,DcExchangeApproveStatusEnums>() ;
	
	static {
		for(DcExchangeApproveStatusEnums e : DcExchangeApproveStatusEnums.values()){
			map.put(e.getValue(), e) ;
		}
	}
	
	public static DcExchangeApproveStatusEnums get(String value) {
		
		DcExchangeApproveStatusEnums iot = map.get(value) ;
		if(iot == null){
			return UNKNOWN ;
		}
		
		return iot ;
	}

}
