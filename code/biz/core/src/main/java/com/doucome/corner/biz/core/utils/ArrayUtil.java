package com.doucome.corner.biz.core.utils;

public final class ArrayUtil {
	/**
	 * 
	 * @param <T>
	 * @param objects
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public static <T> boolean batchSetField(T[] objects, String fieldName, Object value) {
		if (objects == null || objects.length == 0) {
			return true;
		}
		boolean result = false;
		for (T temp : objects) {
			result = ReflectUtils.setField(temp, fieldName, value);
		}
		return result;
	}
}
