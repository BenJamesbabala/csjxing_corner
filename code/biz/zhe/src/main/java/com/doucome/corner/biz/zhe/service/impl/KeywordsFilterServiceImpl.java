package com.doucome.corner.biz.zhe.service.impl;

import com.doucome.corner.biz.zhe.enums.BanKeywords;
import com.doucome.corner.biz.zhe.service.KeywordsFilterService;

public class KeywordsFilterServiceImpl implements KeywordsFilterService {
	
	private BanKeywords banKeywords = BanKeywords.INSTANCE;
	
	@Override
	public boolean isForbbiden(String content) {
		return this.banKeywords.isForbbiden(content);
	}
}
