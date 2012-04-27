package com.doucome.corner.web.common.toolbox;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class StringUtilsToolbox extends StringUtils{

    public String escapeHtml(String str) {
        return StringEscapeUtils.escapeHtml(str);
    }

    public String escapeJS(String str) {
        return StringEscapeUtils.escapeJavaScript(str);
    }

    public String escapeHiddenInputs(String str) {
        if (StringUtils.isNotBlank(str)) {
            String[] prams = StringUtils.split(str, '&');
            if (prams.length > 0) {
                StringBuilder builder = new StringBuilder();
                for (String pram : prams) {
                    String[] pairs = StringUtils.split(pram, '=');
                    if (pairs.length > 1) {
                        builder.append(escapeHiddenInput(pairs[0], pairs[1]));
                    }
                }
                return builder.toString();
            }
        }
        return StringUtils.EMPTY;
    }

    public String escapeHiddenInput(String name, String value) {
        if (StringUtils.isNotBlank(name)) {
            return "<input type='hidden' name='" + name + "' value='" + value + "' />";
        }
        return StringUtils.EMPTY;
    }

    public String replaceIgnorecase(String text, String searchString, String replacement) {
        String lowcase = StringUtils.lowerCase(searchString);
        String uppercase = StringUtils.upperCase(searchString);
        text = StringUtils.replace(text, lowcase, replacement);
        text = StringUtils.replace(text, uppercase, replacement);
        return text;
    }

    public String concat(String... strArray) {
        String rt = "";
        for (String s : strArray) {
            rt += s;
        }
        return rt;
    }
	
	/**
	 * 处理字符串到指定长度，多余的字符以“strAfter”处理
	 * @param str
	 * @param end
	 * @param strAfter
	 * @return
	 */
	public String substring(String str , int length , String strAfter){
		int strLen = StringUtils.length(str) ;
		if(length >= strLen){
			return str ;
		}
		
		String s = StringUtils.substring(str, 0 , length ) ;
		return s + strAfter ;
	}
}
