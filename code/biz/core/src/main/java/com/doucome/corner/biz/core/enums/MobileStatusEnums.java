package com.doucome.corner.biz.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * ���ŷ���״̬ENUM
 * 
 * @author 2012-4-17
 */
public enum MobileStatusEnums {
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

    private MobileStatusEnums(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    private static final Map<String, MobileStatusEnums> map = new HashMap<String, MobileStatusEnums>();
    static {
        MobileStatusEnums[] enums = MobileStatusEnums.values();
        for (MobileStatusEnums e : enums) {
            map.put("" + e, e);
        }
    }

    public static MobileStatusEnums get(String key) {
        return map.get(key);
    }

}
