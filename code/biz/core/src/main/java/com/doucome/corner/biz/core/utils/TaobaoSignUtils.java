package com.doucome.corner.biz.core.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Encoder;

import com.doucome.corner.biz.core.exception.EncryptException;

/**
 * taobao util
 * 
 * @author langben 2012-3-21
 * 
 */
public class TaobaoSignUtils {
	
	private static final Log log = LogFactory.getLog(TaobaoSignUtils.class) ;

	/**
	 * 
	 * 签名运算
	 * 
	 * @param parameter
	 * @param secret
	 * @return
	 * 
	 * @throws EncryptException
	 * 
	 * 
	 */

	private static String sign(String parameter, String secret) throws EncryptException {
		// 对参数+密钥做MD5运算
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage(), e);
			throw new EncryptException(e);
		}
		byte[] digest = md.digest((parameter + secret).getBytes());
		// 对运算结果做BASE64运算并加密
		BASE64Encoder encode = new BASE64Encoder();
		String sign = encode.encode(digest);
		return sign ;

	}

	/**
	 * 
	 * 验证签名
	 * 
	 * @param sign
	 * @param parameter 注意，这个parameter并不就是上面的top_paramater，而是指的待签名验证的参数，即上面的top_appkey+top_parameter+top_session
	 * @param secret
	 * @return
	 * @throws EncryptException
	 */

	public static boolean validateSign(String sign, String parameter,String secret) throws EncryptException {
		String signedStr = sign(parameter, secret) ;
		return sign != null && parameter != null && secret != null&& sign.equals(signedStr);
		// 注意，这个parameter并不就是上面的top_paramater，而是指的待签名验证的参数，即上面的top_appkey+top_parameter+top_session
	}

	/**
	 * 
	 * 把经过BASE64编码的字符串转换为Map对象
	 * @param str
	 * @return
	 * @throws Exception
	 */

	public static Map<String, String> convertBase64StringtoMap(String parameter) {
		if (StringUtils.isBlank(parameter)){
			return null;
		}
		String keyvalues = null;
		try {
			keyvalues = new String(Base64.decodeBase64(URLDecoder.decode(parameter,"utf-8").getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(),e) ;
		}
		String[] keyvalueArray = keyvalues.split("\\&");
		Map<String, String> map = new HashMap<String, String>();
		for (String keyvalue : keyvalueArray) {
			String[] s = keyvalue.split("\\=");
			if (s == null || s.length != 2){
				return null;
			}
			map.put(s[0], s[1]);
		}
		return map;

	}

	/**
	 * 
	 * 验证TOP回调地址的签名是否合法。要求所有参数均为已URL反编码的。
	 * 
	 * @param topParams
	 *            TOP私有参数（未经BASE64解密）
	 * 
	 * @param topSession
	 *            TOP私有会话码
	 * 
	 * @param topSign
	 *            TOP回调签名
	 * 
	 * @param appKey
	 *            应用公钥
	 * 
	 * @param appSecret
	 *            应用密钥
	 * 
	 * @param appSecret
	 *            应用密钥
	 * 
	 * @return 验证成功返回true，否则返回false
	 * 
	 * @throws NoSuchAlgorithmException
	 * 
	 * @throws IOException
	 */

	public static boolean verifyTopResponse(String topParams,
			String topSession, String topSign,

			String appKey, String appSecret) throws NoSuchAlgorithmException,
			IOException {

		StringBuilder result = new StringBuilder();

		MessageDigest md5 = MessageDigest.getInstance("MD5");

		result.append(appKey).append(topParams).append(topSession)
				.append(appSecret);

		byte[] bytes = md5.digest(result.toString().getBytes("UTF-8"));

		BASE64Encoder encoder = new BASE64Encoder();

		return encoder.encode(bytes).equals(topSign);

	}
}
