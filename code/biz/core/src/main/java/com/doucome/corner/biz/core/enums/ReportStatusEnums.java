package com.doucome.corner.biz.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * email����״̬ENUM
 * 
 * @author 2012-4-17
 */
public enum ReportStatusEnums {
    /**
     * δ����
     */
    UNSEND("U"),
    /**
     * ����ʧ��
     */
    SEND_FAIL("F"),
    /**
     * �����ӳ���Ϣʧ��
     */
    SEND_DELAY_FAIL("DF"),
    /**
     * �����ӳ���Ϣ�ɹ�
     */
    SEND_DELAY("D"),

    /**
     * �����ʼ��ɹ�
     */
    SEND_EMAIL_SUCCESS("S"),

    /**
     * ���ųɹ�
     */
    SEND_SMS_SUCCESS("SS"),

    /**
     * ��״̬
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
