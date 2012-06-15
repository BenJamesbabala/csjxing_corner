package com.doucome.corner.biz.zhe.buyingRecomm;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * ÍÆ¼ö¸ßÎ£´Ê¹ýÂË
 * @author shenjia.caosj 2012-6-8
 *
 */
public class BuyingRecommendDanderousKeywords {

	private List<String> keywordsList ;

	public boolean isDangerousKeywords(String str){
		for(String words : keywordsList){
			
			if(StringUtils.indexOf(str, words) != -1){
				return true ;
			}
		}
		return false ;
	}
	
	public void setKeywordsList(List<String> keywordsList) {
		this.keywordsList = keywordsList;
	}
}
