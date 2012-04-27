package com.doucome.corner.biz.core.utils;

/**
 * 类ShortUrl.java的实现描述：短url生成工具
 * 
 * @author ib 2012-3-11 下午09:32:05
 */
public class ShortUrlUtil {

    // 要使用生成URL的字符
    private static final char[] chars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static String[] ShortText(String str) {
        String hex = MD5Util.getMD5(str);
        String[] ShortStr = new String[4];
        for (int i = 0; i < hex.length() / 8; i++) {
            char[] outChars = new char[6];
            int j = i + 1;
            String subHex = hex.substring(i * 8, j * 8);
            long idx = 0x3FFFFFFF & Long.valueOf(subHex, 16);

            for (int k = 0; k < 6; k++) {
                int index = (int) (0x0000003D & idx);
                outChars[k] = chars[index];
                idx = idx >> 5;
            }
            ShortStr[i] = new String(outChars);
        }

        return ShortStr;
    }
    
    public static String md5(String url){
    	return MD5Util.getMD5(url) ;
    }

    public static void main(String[] args) {
        String url = "http://www.doucome.com";
        for (String string : ShortText(url)) {
            System.out.println(string);
        }
    }

}
