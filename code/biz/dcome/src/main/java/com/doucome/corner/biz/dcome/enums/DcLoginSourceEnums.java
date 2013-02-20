package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 类DcLoginSourceEnums.java的实现描述：TODO 类实现描述
 * 
 * @author ib 2012-7-29 下午09:23:58
 */
public enum DcLoginSourceEnums {
    Qzone("qzone"), Pengyou("pengyou"), Unkown("unknow");

    private static Map<String, DcLoginSourceEnums> sourceHolder = new HashMap<String, DcLoginSourceEnums>();

    static {
        DcLoginSourceEnums[] enums = values();
        for (DcLoginSourceEnums e : enums) {
            sourceHolder.put(e.getValue(), e);
        }
    }

    public static DcLoginSourceEnums get(String key) {
        if (key == null) {
            return Unkown;
        }

        DcLoginSourceEnums result = sourceHolder.get(key);
        if (result != null) {
            return result;
        }
        return Unkown;
    }

    private DcLoginSourceEnums(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
