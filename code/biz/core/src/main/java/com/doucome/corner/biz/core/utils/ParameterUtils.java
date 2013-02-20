package com.doucome.corner.biz.core.utils;

/**
 * ����������.
 * @author ze2200
 *
 */
public final class ParameterUtils {
	
	/**
	 * �ж�id�Ƿ���Ч.
	 * @param id
	 */
	public static boolean isLongIdValid(Long id) {
		return id != null && id >= 0;
	}
	
	/**
	 * �ж�id�Ƿ���Ч.
	 * @param id
	 */
	public static boolean isIntIdValid(Integer id) {
		return id != null && id >= 0;
	}
}
