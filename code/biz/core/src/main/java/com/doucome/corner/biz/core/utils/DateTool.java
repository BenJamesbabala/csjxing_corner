package com.doucome.corner.biz.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_2MM     = "yyyy-MM-dd HH:mm";

    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        DateFormat f = new SimpleDateFormat(format);
        return f.format(date);
    }

    /**
     * yyyy-MM-dd HH:mm:ss格式
     * 
     * @param date
     * @return
     */
    public static String defaultFormat(Date date) {
        return format(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * yyyy-MM-dd HH:mm格式
     * 
     * @param date
     * @return
     */
    public static String format_2mm(Date date) {
        return format(date, DATE_FORMAT_2MM);
    }
}
