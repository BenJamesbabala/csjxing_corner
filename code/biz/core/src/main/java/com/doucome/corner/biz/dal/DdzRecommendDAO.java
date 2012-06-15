package com.doucome.corner.biz.dal;

import java.util.List;

import com.doucome.corner.biz.dal.condition.DdzRecommendSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendDO;

/**
 * �Ƽ�
 * @author shenjia.caosj 2012-5-22
 *
 */
public interface DdzRecommendDAO {

	/**
	 * �����Ƽ�
	 * @param recommend
	 * @return
	 */
	int insertRecommend(List<DdzRecommendDO> recommendList) ;
	
	/**
	 * �����Ƽ�
	 * @param id
	 * @param isDisplay
	 * @return
	 */
	int updateRecommendDisplayById(int id , String isDisplay) ;
	
	/**
	 * ��ѯ�Ƽ�
	 * @param batchNo
	 * @return
	 */
	List<DdzRecommendDO> queryRecommends(String batchNo , String recommendType) ;
	
	/**
     * ��ѯaccount
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
