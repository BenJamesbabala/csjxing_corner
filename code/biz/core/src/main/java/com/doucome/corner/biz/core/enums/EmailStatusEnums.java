package com.doucome.corner.biz.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * email发送状态ENUM
 * 
 * @author 2012-4-17
 */
public enum EmailStatusEnums {
    /**
     * 未发生
     */
    UNSEND("U"),
    /**
     * 结算失败
     */
    SEND_FAIL("F"),

    /**
     * 结算成功
     */
    SEND_SUCCESS("S"),
    
    /**
     * 无状态
     */
    NULL("N"),

    UNKNOWN(""), ;

    private EmailStatusEnums(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    private static final Map<String, EmailStatusEnums> map = new HashMap<String, EmailStatusEnums>();
    static {
        EmailStatusEnums[] enums = EmailStatusEnums.values();
        for (EmailStatusEnums e : enums) {
            map.put("" + e, e);
        }
    }

    public static EmailStatusEnums get(String key) {
        return map.get(key);
    }

}
