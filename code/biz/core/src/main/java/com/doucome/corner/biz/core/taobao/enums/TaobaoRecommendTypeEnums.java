package com.doucome.corner.biz.core.taobao.enums;


/**
 * 
 * @author shenjia.caosj 2012-3-22
 *
 */
public enum TaobaoRecommendTypeEnums {

	/**
	 * ͬ����Ʒ�Ƽ�
	 */
	SAME_STYLE (1L),
	/**
	 * ������Ʒ�Ƽ�
	 */
	DIFF_STYLE(2L) , 
	/**
	 * ͬ����Ʒ�Ƽ�
	 */
	SAME_SHOP(3L) ,
	
	/**
	 * 
	 */
	UNKNOWN(-1L)
	;
	
	private Long code ;
	
	private TaobaoRecommendTypeEnums(Long code){
		this.code = code ;
	}

	public Long getCode() {
		return code;
	}
	
	public static TaobaoRecommendTypeEnums fromCode(Long code){
		TaobaoRecommendTypeEnums[] enums = TaobaoRecommendTypeEnums.values() ;
		for(TaobaoRecommendTypeEnums e : enums){
			if(e.getCode() == code){
				return e ;
			}
		}
		return UNKNOWN ;
	}
	
}
