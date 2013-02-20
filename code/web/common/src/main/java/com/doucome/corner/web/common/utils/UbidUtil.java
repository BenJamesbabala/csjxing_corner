package com.doucome.corner.web.common.utils;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.utils.UUIDUtils;

/**
 * @author ib 2012-9-15 ÏÂÎç5:05:17
 */
public class UbidUtil {

    private static final int[] INDEXS1 = { 3, 15, 8, 13, 2, 4 };
    private static final int[] INDEXS2 = { 3, 6, 12, 7, 14, 11 };

    public static String createUbid() {
        String random = UUIDUtils.random10() + UUIDUtils.random10();
        String checkCode = getCheckCode(random);
        return random + checkCode;
    }

    private static String getCheckCode(String baseID) {
        int sum = 0;
        char[] chars = new char[2];
        for (int index : INDEXS1) {
            sum += baseID.charAt(index);
        }
        chars[0] = (char) ('a' + sum % 25);
        for (int index : INDEXS2) {
            sum += baseID.charAt(index);
        }
        chars[1] = (char) ('a' + sum % 25);
        return new String(chars).toLowerCase();
    }

    public static boolean checkUbid(String ubid) {
        if (StringUtils.isBlank(ubid) || ubid.length() != 22) {
            return false;
        }
        String checkCode = getCheckCode(ubid.substring(0, 20));
        String userCode = ubid.substring(20);
        return StringUtils.equals(checkCode, userCode);
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            String ubid = createUbid();
            if(!checkUbid(ubid)){
                System.out.println(ubid);
            }
        }
        long e = System.currentTimeMillis();
        System.out.println(e-s);
    }
}
