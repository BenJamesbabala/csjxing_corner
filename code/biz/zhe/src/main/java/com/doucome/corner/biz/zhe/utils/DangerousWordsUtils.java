package com.doucome.corner.biz.zhe.utils;

import com.doucome.corner.biz.zhe.enums.BanKeywords;

public class DangerousWordsUtils {

	public static boolean isForbbiden(String content){
		return BanKeywords.INSTANCE.isForbbiden(content) ;
	}
	
	
	public static void main(String[] args) {
		DangerousWordsUtils.isForbbiden("test") ;
		DangerousWordsUtils.isForbbiden("test") ;
		DangerousWordsUtils.isForbbiden("test") ;
		DangerousWordsUtils.isForbbiden("test") ;
	}
}
