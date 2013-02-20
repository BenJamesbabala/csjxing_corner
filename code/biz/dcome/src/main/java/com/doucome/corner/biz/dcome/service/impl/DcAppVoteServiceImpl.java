package com.doucome.corner.biz.dcome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.doucome.corner.biz.core.enums.TrueFalseEnums;
import com.doucome.corner.biz.dal.dataobject.dcome.DcAppVoteDO;
import com.doucome.corner.biz.dal.dcome.DcAppVoteDAO;
import com.doucome.corner.biz.dcome.enums.DcYesOrNoEnum;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.service.DcAppVoteService;

public class DcAppVoteServiceImpl implements DcAppVoteService {

	@Autowired
	private DcAppVoteDAO dcAppVoteDAO ;
	
	@Override
	public Long createAppVote(DcAppVoteDO appVote) throws DcDuplicateKeyException {
		try {
		    return dcAppVoteDAO.insertAppVote(appVote) ;
		}catch (DuplicateKeyException e){
			throw new DcDuplicateKeyException(e) ;
		}
	}

	@Override
	public int updateIsVoteByUserId(Long userId, TrueFalseEnums isVote) {
		return dcAppVoteDAO.updateIsVoteByUserId(userId, isVote.getValue()) ;
	}

	@Override
	public DcAppVoteDO getAppVoteByUserId(Long userId) {
		return dcAppVoteDAO.selectAppVoteByUserId(userId) ;
	}
	
	@Override
	public int updateAwardStatus(Long userId, DcYesOrNoEnum status) {
		return dcAppVoteDAO.updateAwardStatus(userId, status.getValue());
	}
}
