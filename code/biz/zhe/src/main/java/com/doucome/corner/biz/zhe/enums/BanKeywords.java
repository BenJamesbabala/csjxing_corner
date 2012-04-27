package com.doucome.corner.biz.zhe.enums;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taobao.api.internal.mapping.Reader;
import com.taobao.api.internal.util.StringUtils;

import com.doucome.corner.biz.core.constant.Constant;
import com.doucome.corner.biz.zhe.utils.StreamUtil;

public enum BanKeywords {
	INSTANCE;
	
//	private Log logger = LogFactory.getLog(BanKeywords.class);
	
	private List<String> keywords = new ArrayList<String>();
	
	private BanKeywords() {
		BufferedReader reader = null;
		try {
			InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("keyword/ban/bankeywords.txt");
			reader = new BufferedReader(new InputStreamReader(input, Constant.ENCODING));
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				keywords.add(temp.trim());
			}
		} catch (Throwable e) {
//			logger.error("load ban keywords failed", e);
		} finally {;
			StreamUtil.close(reader);
		}
	}
	
	public boolean isForbbiden(String content) {
		if (StringUtils.isEmpty(content)) {
			return false;
		}
		for (String temp : keywords) {
			if (content.indexOf(temp) != -1) {
				return true;
			}
		}
		return false;
	}
}
