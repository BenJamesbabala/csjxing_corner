package com.doucome.corner.biz.dcome.service;

import com.doucome.corner.biz.core.enums.TrueFalseEnums;
import com.doucome.corner.biz.dal.dataobject.dcome.DcAppVoteDO;
import com.doucome.corner.biz.dcome.enums.DcYesOrNoEnum;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;

public interface DcAppVoteService {

	Long createAppVote(DcAppVoteDO appVote) throws DcDuplicateKeyException ;
	
	int updateIsVoteByUserId(Long userId , TrueFalseEnums isVote) ;
	
	DcAppVoteDO getAppVoteByUserId(Long userId) ;
	/**
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	int updateAwardStatus(Long userId, DcYesOrNoEnum status);
}
