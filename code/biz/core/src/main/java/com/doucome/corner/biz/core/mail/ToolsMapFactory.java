package com.doucome.corner.biz.core.mail;

import java.util.HashMap;
import java.util.Map;

import com.doucome.corner.biz.core.utils.DateTool;
import com.doucome.corner.biz.core.utils.DecimalTool;

/**
 * 类ToolsMapFactory.java的实现描述：tools map工厂
 * 
 * @author ib 2012-5-12 下午06:40:30
 */
public class ToolsMapFactory {

    private static final DateTool            dataTool    = new DateTool();
    private static final DecimalTool         decimalTool = new DecimalTool();
    private static final Map<String, Object> toolsMap;
    static {
        toolsMap = new HashMap<String, Object>();
        toolsMap.put("dateUtils", dataTool);
        toolsMap.put("decimalUtils", decimalTool);
    }

    public static Map generateDefaultToolsMap() {
        Map map = new HashMap();
        map.putAll(toolsMap);
        return map;
    }
}
