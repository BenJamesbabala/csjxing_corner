package com.doucome.corner.biz.dal.dcome;

import com.doucome.corner.biz.dal.dataobject.dcome.DcAppVoteDO;

/**
 * 
 * @author langben 2012-11-21
 *
 */
public interface DcAppVoteDAO {

	Long insertAppVote(DcAppVoteDO appVote) ;
	
	int updateIsVoteByUserId(Long userId , String isVote) ;
	
	DcAppVoteDO selectAppVoteByUserId(Long userId) ;
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	Integer updateAwardStatus(Long userId, String status);
}
