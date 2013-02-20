package com.doucome.corner.biz.core.utils;

/**
 * 参数工具类.
 * @author ze2200
 *
 */
public final class ParameterUtils {
	
	/**
	 * 判断id是否有效.
	 * @param id
	 */
	public static boolean isLongIdValid(Long id) {
		return id != null && id >= 0;
	}
	
	/**
	 * 判断id是否有效.
	 * @param id
	 */
	public static boolean isIntIdValid(Integer id) {
		return id != null && id >= 0;
	}
}
