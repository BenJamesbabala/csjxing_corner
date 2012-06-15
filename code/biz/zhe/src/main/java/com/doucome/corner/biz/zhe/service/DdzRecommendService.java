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
 * 推荐
 * @author shenjia.caosj 2012-5-22
 *
 */
public interface DdzRecommendService {

	/**
	 * 新增推荐
	 * @param recommend
	 * @return
	 */
	int createRecommend(List<DdzRecommendDO> recommendList) ;
	
	/**
	 * 更新推荐
	 * @param id
	 * @param isDisplay
	 * @return
	 */
	int updateRecommendDisplayById(int id , String isDisplay) ;
	
	/**
	 * 准备别人正在买推荐数据
	 */
	List<TaobaokeItemDTO> prepareBuyingRecommends(RecommendBatchDate date) ;
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	List<DdzRecommendDO> getRecommends(RecommendBatchDate date , RecommendTypeEnums recommType) ;
	
	/**
	 * 分页查询
	 * @param condition
	 * @param pagination
	 * @return
	 */
	QueryResult<DdzRecommendDO> getRecommendsWithPagination(DdzRecommendSearchCondition condition , Pagination pagination) ;
}
