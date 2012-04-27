package com.doucome.corner.biz.core.utils;

/**
 * 类RandomStringUtil.java的实现描述：随机字符串生成工具
 * 
 * @author ib 2012-4-1 上午01:53:26
 */
public class RandomStringUtil {

    private static final char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z' };

    /**
     * 生成由指定为字母和数字组合的随机字符串
     * 
     * @param len
     * @return
     */
    public static String randomString(int charLen, int numLen) {
        // 修改生成算法，简化密码：3位小写字母，5位数字
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < charLen; i++) {
            int offset = (int) Math.round(Math.random() * (chars.length - 1));
            builder.append(chars[offset]);
        }

        for (int i = 0; i < numLen; i++) {
            int offset = (int) Math.round(Math.random() * (10));
            builder.append(offset);
        }
        StringBuilder targetBuilder = new StringBuilder();
        while (builder.length() > 0) {
            int offset = (int) Math.round(Math.random() * (builder.length() - 1));
            targetBuilder.append(builder.charAt(offset));
            builder.deleteCharAt(offset);
        }
        return targetBuilder.toString();
    }
    
    /**
     * 获取 1 至 maxLen 位的随机数字，包括0在最前，如：012，123，01，23,1等。
     * 
     * @param maxLen 随机数的最大长度
     * @return
     */
    public static String getRandomNum(int maxLen) {
        StringBuffer randomNum = new StringBuffer();
        int random = 0;
        int len = (int) (1 + Math.random() * maxLen);
        for (int i = 0; i < len; i++) {
            random = (int) (Math.random() * 9);
            randomNum.append(random);
        }

        return randomNum.toString();
    }

    /**
     * 固定长度的随机数字
     * 
     * @param len 随机数的指定长度
     * @return
     */
    public static String getFixLenRandomNum(int len) {
        StringBuffer randomNum = new StringBuffer();
        int random = 0;
        for (int i = 0; i < len; i++) {
            random = (int) (Math.random() * 9);
            randomNum.append(random);
        }

        return randomNum.toString();
    }

}
