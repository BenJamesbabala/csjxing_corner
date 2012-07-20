package com.doucome.corner.biz.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信发送状态ENUM
 * 
 * @author 2012-4-17
 */
public enum MobileStatusEnums {
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
