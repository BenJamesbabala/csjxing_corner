package com.doucome.corner.biz.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * email����״̬ENUM
 * 
 * @author 2012-4-17
 */
public enum EmailStatusEnums {
    /**
     * δ����
     */
    UNSEND("U"),
    /**
     * ����ʧ��
     */
    SEND_FAIL("F"),

    /**
     * ����ɹ�
     */
    SEND_SUCCESS("S"),
    
    /**
     * ��״̬
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
