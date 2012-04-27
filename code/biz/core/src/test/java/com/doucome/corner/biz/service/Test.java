package com.doucome.corner.biz.service;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.utils.JacksonHelper;

public class Test {

	public static void main(String[] args) {
		LuckydrawResponse<Boolean> e = new LuckydrawResponse<Boolean>() ;
		e.setResponseData(false) ;
		String s = JacksonHelper.toJSON(e) ;
		System.out.println(s);
		String sss = "{\"responseData\":false,\"errorCode\":null}" ;
		LuckydrawResponse<Boolean> r = JacksonHelper.fromJSON(sss, LuckydrawResponse.class) ;
		System.out.println(r.getResponseData());
	}

	public static class LuckydrawResponse<T> {
		private T responseData ;
		private String errorCode;

		public LuckydrawResponse(){
			
		}
		public boolean isSuccess() {
			return StringUtils.isBlank(errorCode);
		}

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public T getResponseData() {
			return responseData;
		}

		public void setResponseData(T responseData) {
			this.responseData = responseData;
		}
			
	}
}
