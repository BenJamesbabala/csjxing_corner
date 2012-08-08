package com.doucome.corner.biz.zhe.utils;

import org.apache.commons.lang.StringUtils;


public final class DcStringUtils {
	
	public static String[] split(String strs, String splitChar) {
		if (StringUtils.isEmpty(strs)) {
			return new String[0];
		}
		if (splitChar == null) {
			return new String[] {strs};
		}
		return strs.split(splitChar);
	}
	
	public static <T> String concat(T[] objs) {
		if (objs == null || objs.length == 0) {
			return "";
		}
		StringBuilder tempBuilder = new StringBuilder();
		for (T temp: objs) {
			if (temp != null) {
				tempBuilder.append(temp.toString()).append(",");
			}
		}
		if (tempBuilder.length() > 0) {
			return tempBuilder.substring(0, tempBuilder.length() - 1);
		}
		return tempBuilder.toString();
	}
}
