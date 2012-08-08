package com.paipai.api.util;

public enum ReservedName {
	CHARSET("charset"),
	FORMAT("format"),
	PUREDATA("pureData"),
	DEBUG("debug"),

	UIN("uin"),
	SPID("spid"),
	TOKEN("token"),
	SKEY("skey"),
	SIGN("sign"),
	
	APP_OAUTH_ID("appOAuthID") ,
	ACCESS_TOKEN("accessToken") ,
	APP_OAUTH_KEY("appOAuthKey") ,
	
	TIMESTAMP("timeStamp"),
	
	RANDOM_VALUE("randomValue")
	
	;
	private String desc;
	private ReservedName(String desc) {
		this.desc = desc;
	}
	public String toString() {
		return desc;
	}
}
