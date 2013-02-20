package com.doucome.corner.biz.dal.namefate;

import java.util.List;

import com.doucome.corner.biz.dal.namefate.condition.NamefateTrickSearchCondition;
import com.doucome.corner.biz.dal.namefate.dataobject.NamefateTrickDO;

/**
 * 
 * @author langben 2013-1-31
 *
 */
public interface NamefateTrickDAO {

	/**
	 * 
	 * @param trick
	 * @return
	 */
	long insertTrick(NamefateTrickDO trick) ;
	
	/**
	 * 
	 * @param condition
	 * @param start
	 * @param size
	 * @return
	 */
	List<NamefateTrickDO> queryTricksWithPagination(NamefateTrickSearchCondition condition , int start , int size) ;
	
	/**
	 * 
	 * @param condition
	 * @return
	 */
	int countTricksWithPagination(NamefateTrickSearchCondition condition) ;

	/**
	 * 
	 * @param trickId
	 * @return
	 */
	NamefateTrickDO queryTrickById(Long trickId);
}
