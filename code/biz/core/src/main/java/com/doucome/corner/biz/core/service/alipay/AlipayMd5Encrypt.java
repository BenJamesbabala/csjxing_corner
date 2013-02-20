package com.doucome.corner.biz.core.service.alipay;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import org.apache.commons.codec.digest.DigestUtils;

import com.doucome.corner.biz.core.constant.EnvConstant;
import com.doucome.corner.biz.core.utils.EnvPropertiesUtil;

/** 
* ���ܣ�֧����MD5ǩ����������ļ�������Ҫ�޸�
* �汾��3.1
* �޸����ڣ�2010-11-01
* ˵����
* ���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
* �ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ��
* */

public class AlipayMd5Encrypt {
	
    /**
     * ���ַ�������MD5ǩ��
     * 
     * @param text
     *            ����
     * 
     * @return ����
     */
    public static String md5(String text) {

        return DigestUtils.md5Hex(getContentBytes(text, EnvPropertiesUtil.getProperty(EnvConstant.CORNER_ALIPAY_CHARSET)));

    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }

        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5ǩ�������г��ִ���,ָ���ı��뼯����,��Ŀǰָ���ı��뼯��:" + charset);
        }
    }

}