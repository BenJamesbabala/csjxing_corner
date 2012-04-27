package com.doucome.corner.biz.core.utils;

import java.util.Random;

/**
 * 类UidCreater.java的实现描述：用户ID生成器
 * 
 * @author ib 2012-2-29 下午10:48:36
 */
public class UidCreateUtil {

    final static char[] digits = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

    /**
     * 根据时间和外部id生成uid,11位
     * 
     * @param externalId 外部id
     * @return uid
     */
    public static String createUid(String externalId) {
        return createUid(externalId, 11);
    }

    /**
     * 根据时间和外部id生成uid
     * 
     * @param externalId 外部id
     * @param bitNum 生成id长度
     * @return uid
     */
    public static String createUid(String externalId, int bitNum) {
        long time = System.currentTimeMillis();
        String timeStr = longToString(time, digits.length);
        String extStr = hexString(externalId, digits.length, bitNum - timeStr.length());
        return timeStr + extStr;
    }

    /**
     * 把字符串压缩一下
     * 
     * @param source
     * @param radix
     * @param bitNum
     * @return
     */
    public static String hexString(String source, int radix, int bitNum) {
        if (source == null || source.length() == 0) {
            int max = (int) Math.pow(radix, bitNum);
            return ((Integer) new Random().nextInt(max)).toString();
        }

        if (radix < 1 || radix > digits.length) {
            radix = digits.length;
        }

        if (bitNum < 1) {
            return "";
        }

        char[] chars = source.toCharArray();
        long count = 0;
        for (int i = 0; i < chars.length; i++) {
            count += chars[i];
        }

        long index = (long) (count % Math.pow(radix, bitNum));
        return longToString(index, radix);
    }

    private static String longToString(long i, int radix) {
        char[] buf = new char[65];
        int charPos = 0;
        boolean negative = (i < 0);

        if (!negative) {
            i = -i;
        }

        while (i <= -radix) {
            buf[charPos++] = digits[(int) (-(i % radix))];
            i = i / radix;
        }
        buf[charPos] = digits[(int) (-i)];

        if (negative) {
            buf[charPos++] = '-';
        }

        return new String(buf, 0, charPos + 1);
    }

    public static void main(String[] args) {
        for (int i = 1000; i < 1200; i++) {
            System.out.println(createUid(i + "", 11) + " " + i);
        }
    }
}
