package com.doucome.corner.biz.apps.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * ��DcLoginSourceEnums.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-7-29 ����09:23:58
 */
public enum AppLoginSourceEnums {
    Qzone("qzone"), Pengyou("pengyou"), Unkown("unknow");

    private static Map<String, AppLoginSourceEnums> sourceHolder = new HashMap<String, AppLoginSourceEnums>();

    static {
        AppLoginSourceEnums[] enums = values();
        for (AppLoginSourceEnums e : enums) {
            sourceHolder.put(e.getValue(), e);
        }
    }

    public static AppLoginSourceEnums get(String key) {
        if (key == null) {
            return Unkown;
        }

        AppLoginSourceEnums result = sourceHolder.get(key);
        if (result != null) {
            return result;
        }
        return Unkown;
    }

    private AppLoginSourceEnums(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
