package com.doucome.corner.biz.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * email发送状态ENUM
 * 
 * @author 2012-4-17
 */
public enum ReportStatusEnums {
    /**
     * 未发生
     */
    UNSEND("U"),
    /**
     * 发送失败
     */
    SEND_FAIL("F"),
    /**
     * 发送延迟消息失败
     */
    SEND_DELAY_FAIL("DF"),
    /**
     * 发送延迟消息成功
     */
    SEND_DELAY("D"),

    /**
     * 发送邮件成功
     */
    SEND_EMAIL_SUCCESS("S"),

    /**
     * 短信成功
     */
    SEND_SMS_SUCCESS("SS"),

    /**
     * 无状态
     */
    NULL("N"),

    UNKNOWN(""), ;

    private ReportStatusEnums(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    private static final Map<String, ReportStatusEnums> map = new HashMap<String, ReportStatusEnums>();
    static {
        ReportStatusEnums[] enums = ReportStatusEnums.values();
        for (ReportStatusEnums e : enums) {
            map.put("" + e, e);
        }
    }

    public static ReportStatusEnums get(String key) {
        return map.get(key);
    }

}
