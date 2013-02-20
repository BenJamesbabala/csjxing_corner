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
 * ��ValidateUtil.java��ʵ����������ʽУ�鹤��
 * 
 * @author ib 2012-4-1 ����02:15:12
 */
public class ValidateUtil {

    private static final Log    log                   = LogFactory.getLog(ValidateUtil.class);

    /** Ĭ�ϵ��ʼ�������ʽ **/
    private static final String DEFAULT_EMAIL_PATTERN = "^[_a-zA-Z0-9\\-]+(\\.[_a-zA-Z0-9\\-]*)*@[a-zA-Z0-9\\-]+([\\.][a-zA-Z0-9\\-]+)+";

    /**
     * Ԥ����Pattern
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
     * ����Ƿ����ֻ�
     * 
     * @param mobileNo
     * @return true ������ֻ�;false ��������ֻ�
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
     * ����Ƿ��ǵ�������
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
     * ���email��ʽ
     * 
     * @param email
     * @return �������Ҫ�󣬷���true
     */
    public static boolean checkEmailFormat(String email) {
        return !(StringUtils.isBlank(email) || email.length() > 50 || !checkIsEmail(email));
    }
    
    /**
     * �Ƿ���֧����
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
