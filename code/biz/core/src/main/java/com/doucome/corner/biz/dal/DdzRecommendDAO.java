package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.DdzRecommendSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendDO;

/**
 * 推荐
 * @author shenjia.caosj 2012-5-22
 *
 */
public interface DdzRecommendDAO {

	/**
	 * 新增推荐
	 * @param recommend
	 * @return
	 */
	int insertRecommend(List<DdzRecommendDO> recommendList) ;
	
	/**
	 * 更新推荐
	 * @param id
	 * @param isDisplay
	 * @return
	 */
	int updateRecommendDisplayById(int id , String isDisplay) ;
	
	/**
	 * 查询推荐
	 * @param batchNo
	 * @return
	 */
	List<DdzRecommendDO> queryRecommends(String batchNo , String recommendType) ;
	
	/**
     * 查询account
     * @param searchCondition
     * @param start
     * @param size
     * @return
     */
    List<DdzRecommendDO> queryRecommendsWithPagination(DdzRecommendSearchCondition searchCondition , int start , int size ) ;
    
    /**
     * count account
     * @param searchCondition
     * @return
     */
    int countRecommendsWithPagination(DdzRecommendSearchCondition searchCondition) ;
}
