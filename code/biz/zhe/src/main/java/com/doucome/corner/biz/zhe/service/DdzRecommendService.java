package com.doucome.corner.biz.zhe.service;

import java.util.List;

import com.doucome.corner.biz.core.enums.RecommendTypeEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.dal.condition.DdzRecommendSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendDO;
import com.doucome.corner.biz.zhe.model.RecommendBatchDate;

/**
 * �Ƽ�
 * @author shenjia.caosj 2012-5-22
 *
 */
public interface DdzRecommendService {

	/**
	 * �����Ƽ�
	 * @param recommend
	 * @return
	 */
	int createRecommend(List<DdzRecommendDO> recommendList) ;
	
	/**
	 * �����Ƽ�
	 * @param id
	 * @param isDisplay
	 * @return
	 */
	int updateRecommendDisplayById(int id , String isDisplay) ;
	
	/**
	 * ׼�������������Ƽ�����
	 */
	List<TaobaokeItemDTO> prepareBuyingRecommends(RecommendBatchDate date) ;
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	List<DdzRecommendDO> getRecommends(RecommendBatchDate date , RecommendTypeEnums recommType) ;
	
	/**
	 * ��ҳ��ѯ
	 * @param condition
	 * @param pagination
	 * @return
	 */
	QueryResult<DdzRecommendDO> getRecommendsWithPagination(DdzRecommendSearchCondition condition , Pagination pagination) ;
}
