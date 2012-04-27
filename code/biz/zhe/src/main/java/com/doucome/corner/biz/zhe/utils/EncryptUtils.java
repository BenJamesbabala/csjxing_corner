package com.doucome.corner.biz.zhe.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author ze2200
 *
 */
public final class EncryptUtils {
	private static final Log log = LogFactory.getLog(EncryptUtils.class);
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static final String md5Encrypt(String str) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.reset();
			digest.update(str.getBytes());
		} catch (NoSuchAlgorithmException e) {
			log.error("get md5 messagedigest failed", e);
		}
		byte[] byteArray = digest.digest();
		StringBuilder result = new StringBuilder();
		for (byte temp : byteArray) {
			if (Integer.toHexString(0xFF & temp).length() == 1) {
				result.append("0").append(Integer.toHexString(0xFF & temp));  
			} else {
				result.append(Integer.toHexString(0xFF & temp));
			}
		}
		return result.toString();
	}
}
