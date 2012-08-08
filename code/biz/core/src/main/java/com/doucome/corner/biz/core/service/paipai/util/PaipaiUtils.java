package com.doucome.corner.biz.core.service.paipai.util;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.doucome.corner.biz.core.constant.Constant;

/**
 * 拍拍签名Util
 * @author shenjia.caosj 2012-8-7
 *
 */
public class PaipaiUtils {

	public static byte[] encryptHMAC(String data, String secret) throws IOException {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constant.CHARSET_UTF8), "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes(Constant.CHARSET_UTF8));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse.getMessage());
		}
		return bytes;
	}
	
	/**
	 * apiPath :去掉ServerUrl的Path
	 * apiPath="/item/addItem.xhtml "，对应的cmdid="item.addItem"
	 * @param requestURI
	 * @return
	 */
	public static String getCmdId(String apiPath) {
		byte[] uri = apiPath.getBytes();
		int posStart = 0;
		while (uri[posStart] == '/') { // 去掉开通的'/'
			posStart++;
		}
		int posEnd = posStart;
		int index = posStart;
		while (index < uri.length) { // 替换中间的'/'
			if (uri[index] == '/') {
				uri[index] = '.';
			} else if (uri[index] == '.') {
				posEnd = index;
			}
			index++;
		}
		return new String(uri, posStart, posEnd - posStart);
	}
}
