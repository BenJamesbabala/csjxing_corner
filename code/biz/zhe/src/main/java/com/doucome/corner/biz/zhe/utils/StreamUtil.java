package com.doucome.corner.biz.zhe.utils;

import java.io.Closeable;

public class StreamUtil {
	/**
	 * 
	 * @param closeable
	 * @return
	 */
	public static boolean close(Closeable closeable) {
		if (closeable == null) {
			return true;
		}
		try {
			closeable.close();
		} catch (Throwable e) {
			
		}
		return true;
	}
}
