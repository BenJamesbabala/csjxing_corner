package com.doucome.corner.biz.core.utils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.exception.EncryptException;
import com.taobao.api.Constants;
import com.taobao.api.internal.util.TaobaoUtils;

public class TaobaoWidgetUtils {

	private static final Log log = LogFactory.getLog(TaobaoWidgetUtils.class);

	/**
	 * 
	 * @return
	 */
	public static String sign(String secret, String appKey, String timestamp)
			throws EncryptException {

		String message = secret + "app_key" + appKey + "timestamp" + timestamp
				+ secret;
		String encrypt;
		try {
			encrypt = byte2hex(encryptHMAC(message , secret)) ;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new EncryptException(e);
		}

		return encrypt;
	}

	/**
	 * 当前时间戳
	 * 
	 * @return
	 */
	public static String timestamp() {
		return timestamp(System.currentTimeMillis()) ;
	}
	
	public static String timestamp(long time) {
		String timestamp = String.valueOf(time);
		while (StringUtils.length(timestamp) < 13) {
			timestamp += "0";
		}
		return timestamp;
	}

	public static void main(String[] args) {
		System.out.println(timestamp());
	}

	private static byte[] encryptHMAC(String data, String secret) throws IOException {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse);
		}
		return bytes;
	}
	
	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}
}
