package com.doucome.corner.biz.core.utils;

import org.apache.commons.lang.StringUtils;

/**
 * ��EmailUtil.java��ʵ�������������ַ��ع���
 * 
 * @author ib 2012-5-8 ����01:25:47
 */
public class EmailUtil {

    private static final String AT_STRING      = "@";
    private static final String PRIVATE_STRING = "***";

    public static String getPrivateEmail(String email) {
        if (email == null) {
            return null;
        }
        if (!StringUtils.contains(email, '@')) {
            return email;
        }
        String prefix = StringUtils.substringBefore(email, AT_STRING);
        String privatePrefix = null;
        if (prefix.length() > 3) {
            privatePrefix = StringUtils.substring(prefix, 0, 3) + PRIVATE_STRING;
        } else {
            privatePrefix = StringUtils.substring(prefix, 0, 1) + PRIVATE_STRING;
        }

        return privatePrefix + AT_STRING + StringUtils.substringAfter(email, AT_STRING);
    }

    public static void main(String[] args) {
        System.out.println(getPrivateEmail("asdf"));
        System.out.println(getPrivateEmail("@af.com"));
        System.out.println(getPrivateEmail(""));
        System.out.println(getPrivateEmail(null));
        System.out.println(getPrivateEmail("a@afd.com"));
        System.out.println(getPrivateEmail("aad@afd.com"));
        System.out.println(getPrivateEmail("acvb@afd.com"));
        System.out.println(getPrivateEmail("afadjfkl@afd.com"));
    }
}
