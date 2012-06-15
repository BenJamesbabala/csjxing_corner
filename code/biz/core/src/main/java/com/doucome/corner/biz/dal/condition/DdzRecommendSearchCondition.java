package com.doucome.corner.biz.dal.condition;

import com.doucome.corner.biz.core.enums.RecommendTypeEnums;


/**
 * ≤È—Øaccount
 * @author shenjia.caosj 2012-5-20
 *
 */
public class DdzRecommendSearchCondition {

	private String batchNo ;
	
	private String recommendType = RecommendTypeEnums.BUYING.getValue() ;

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(String recommendType) {
		this.recommendType = recommendType;
	}
	
	
}
