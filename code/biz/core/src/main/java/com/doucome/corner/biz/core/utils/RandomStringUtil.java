package com.doucome.corner.biz.core.utils;

/**
 * ��RandomStringUtil.java��ʵ������������ַ������ɹ���
 * 
 * @author ib 2012-4-1 ����01:53:26
 */
public class RandomStringUtil {

    private static final char[] chars = { 'a', 'b', 'c', 'd', 'e', 'f', 'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z' };

    /**
     * ������ָ��Ϊ��ĸ��������ϵ�����ַ���
     * 
     * @param len
     * @return
     */
    public static String randomString(int charLen, int numLen) {
        // �޸������㷨�������룺3λСд��ĸ��5λ����
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
     * ��ȡ 1 �� maxLen λ��������֣�����0����ǰ���磺012��123��01��23,1�ȡ�
     * 
     * @param maxLen ���������󳤶�
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
     * �̶����ȵ��������
     * 
     * @param len �������ָ������
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
