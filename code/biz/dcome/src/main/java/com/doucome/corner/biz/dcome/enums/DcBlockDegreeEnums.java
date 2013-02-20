package com.doucome.corner.biz.dcome.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * ��DcBlockLevelEnums.java��ʵ��������TODO ��ʵ������
 * 
 * @author ib 2012-8-13 ����12:57:14
 */
public enum DcBlockDegreeEnums {
    Undo("undo"), CheckCode("checkcode"), Block("block");

    private static Map<String, DcBlockDegreeEnums> levelHolder = new HashMap<String, DcBlockDegreeEnums>();

    static {
        DcBlockDegreeEnums[] enums = values();
        for (DcBlockDegreeEnums e : enums) {
            levelHolder.put(e.getValue(), e);
        }
    }

    public static DcBlockDegreeEnums get(String key) {
        if (key == null) {
            return Undo;
        }

        DcBlockDegreeEnums result = levelHolder.get(key);
        if (result != null) {
            return result;
        }
        return Undo;
    }

    private DcBlockDegreeEnums(String value){
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
