package com.doucome.corner.biz.core.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * 类ValidateUtil.java的实现描述：格式校验工具
 * 
 * @author ib 2012-4-1 上午02:15:12
 */
public class ValidateUtil {

    private static final Log    log                   = LogFactory.getLog(ValidateUtil.class);

    /** 默认的邮件正则表达式 **/
    private static final String DEFAULT_EMAIL_PATTERN = "^[_a-zA-Z0-9\\-]+(\\.[_a-zA-Z0-9\\-]*)*@[a-zA-Z0-9\\-]+([\\.][a-zA-Z0-9\\-]+)+";

    /**
     * 预编译Pattern
     */
    private static class EmailPattern {

        private static Pattern emailPattern = null;
        static {
            try {
                emailPattern = new Perl5Compiler().compile(DEFAULT_EMAIL_PATTERN, Perl5Compiler.READ_ONLY_MASK
                                                                                  | Perl5Compiler.SINGLELINE_MASK);
            } catch (MalformedPatternException e) {
                log.error("init email pattern error.", e);
            }
        }
    }

    /**
     * 检查是否是手机
     * 
     * @param mobileNo
     * @return true 如果是手机;false 如果不是手机
     */
    public static boolean checkIsMobile(String mobileNo) {
        if (StringUtils.isBlank(mobileNo)) {
            return false;
        }

        mobileNo = mobileNo.trim();

        if (mobileNo.length() == 11
            && StringUtils.isNumeric(mobileNo)
            && (mobileNo.startsWith("1"))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查是否是电子邮箱
     * 
     * @param email
     * @return
     */
    public static boolean checkIsEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        PatternMatcher matcher = new Perl5Matcher();
        return matcher.matches(email, EmailPattern.emailPattern);
    }

    /**
     * 检查email格式
     * 
     * @param email
     * @return 如果符合要求，返回true
     */
    public static boolean checkEmailFormat(String email) {
        return !(StringUtils.isBlank(email) || email.length() > 50 || !checkIsEmail(email));
    }
    
    /**
     * 是否是支付宝
     * @param alipay
     * @return
     */
    public static boolean checkIsAlipay(String alipay){
    	if(StringUtils.isBlank(alipay)){
    		return false ;
    	}
    	
    	if(!checkIsEmail(alipay) && !checkIsMobile(alipay)){
    		return false ;
    	}
    	
    	return true ;
    }
    
    public static boolean checkIsQQ(String qq){
    	if(StringUtils.isBlank(qq)) {
    		return false ;
    	}
    	
    	return StringUtils.isNumeric(qq) ;
    }
    

}
