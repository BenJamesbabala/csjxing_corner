package com.doucome.corner.biz.core.utils;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.enums.OutCodeEnums;

public class OutCodeUtils {
	
	/**
	 * 生成outCode
	 * @param accountId
	 * @return 
	 */
	public static String encodeOutCode(String outCodeData , OutCodeEnums type) {
		if(StringUtils.length(outCodeData) > 11){
			throw new IllegalArgumentException("outdata : " + outCodeData + " is too long .") ;
		}
		return type.getName() + outCodeData ;
	}
	
	/**
	 * 根据outCode 返回数据
	 * @param outCode
	 * @return
	 */
	public static OutCode decodeOutCode(String outCode) {
		if(StringUtils.isBlank(outCode)){
			return new OutCode(null , OutCodeEnums.UNKNOWN) ;
		}
		String typeString = StringUtils.substring(outCode, 0 , 1) ;
		OutCodeEnums type = OutCodeEnums.toOutCodeEnums(typeString) ;
		String outCodeData = null ;
		if(type == OutCodeEnums.UNKNOWN){
			outCodeData = outCode ;
		} else {
			int strlen = StringUtils.length(outCode) ;
			outCodeData = StringUtils.substring(outCode, 1 , strlen) ;
		}
		
		return new OutCode(outCodeData, type) ;
	}
	
	public static class OutCode {
		
		public OutCode(String outCode , OutCodeEnums type){
			this.outCode = outCode ;
			this.type = type ;
		}
		
		private String outCode ;
		
		private OutCodeEnums type ;

		public String getOutCode() {
			return outCode;
		}

		public void setOutCode(String outCode) {
			this.outCode = outCode;
		}

		public OutCodeEnums getType() {
			return type;
		}

		public void setType(OutCodeEnums type) {
			this.type = type;
		}
		
		
	}
}
