package com.doucome.corner.biz.core.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Encoder;

/**
 * taobao util
 * 
 * @author shenjia.caosj 2012-3-21
 * 
 */
public class TaobaoSignV2Utils {

	private static final Log log = LogFactory.getLog(TaobaoSignV2Utils.class);

	public static void main(String[] args) throws Exception {

		String topParams = URLDecoder.decode(
				"bmljaz3kuK3mlofmtYvor5VuaWNrJnRzPTEzMzc4MjU0NDM2NzY%3D",
				"UTF-8");

		String topSign = URLDecoder.decode("kxQT%2F6j7eblJLORflcz9qw%3D%3D",
				"UTF-8");

		String appSecret = "28dbdd21127f438a59db0cb9f8f620f6";

		// ��У��ǩ��
		boolean success = verifyTopResponse(topParams, topSign, appSecret);
		if (success) {
			// �ٽ�������
			System.out.println(convertBase64StringtoMap(topParams));

			// TODO ʵ��ʹ��ʱ������У��ʱ���������ʱ�����5��������Ϊ��Ч

		}

	}

	/**
	 * 
	 * ��֤TOP�ص���ַ��ǩ���Ƿ�Ϸ���Ҫ�����в�����Ϊ��URL������ġ�
	 * 
	 * 
	 * 
	 * @param topParams
	 *            TOP˽�в�����δ��BASE64���ܣ�
	 * 
	 * @param topSign
	 *            TOP�ص�ǩ��
	 * 
	 * @param appSecret
	 *            Ӧ����Կ
	 * 
	 * @return ��֤�ɹ�����true�����򷵻�false
	 * 
	 * @throws NoSuchAlgorithmException
	 * 
	 * @throws IOException
	 */

	public static boolean verifyTopResponse(String topParams, String topSign, String appSecret)
		throws NoSuchAlgorithmException, IOException {

		StringBuilder result = new StringBuilder();

		MessageDigest md5 = MessageDigest.getInstance("MD5");

		result.append(topParams).append(appSecret);

		byte[] bytes = md5.digest(result.toString().getBytes("UTF-8"));

		BASE64Encoder encoder = new BASE64Encoder();

		return encoder.encode(bytes).equals(topSign);

	}

	/**
	 * 
	 * �Ѿ���BASE64������ַ���ת��ΪMap����
	 * 
	 * 
	 * 
	 * @param str
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */

	public static Map<String, String> convertBase64StringtoMap(String str) {

		if (str == null)

			return null;

		String keyvalues = null;

		try {

			keyvalues = new String(Base64.decodeBase64(URLDecoder.decode(str,
					"UTF-8").getBytes("UTF-8")));

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		}

		String[] keyvalueArray = keyvalues.split("\\&");

		Map<String, String> map = new HashMap<String, String>();

		for (String keyvalue : keyvalueArray) {

			String[] s = keyvalue.split("\\=");

			if (s == null || s.length != 2)

				return null;

			map.put(s[0], s[1]);

		}

		return map;

	}
}
