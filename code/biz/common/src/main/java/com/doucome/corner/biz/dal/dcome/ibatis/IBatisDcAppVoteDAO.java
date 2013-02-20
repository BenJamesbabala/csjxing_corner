package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcAppVoteDO;
import com.doucome.corner.biz.dal.dcome.DcAppVoteDAO;

public class IBatisDcAppVoteDAO extends SqlMapClientDaoSupport implements DcAppVoteDAO {

	@Override
	public Long insertAppVote(DcAppVoteDO appVote) {
		return NumberUtils.parseLong((Long)getSqlMapClientTemplate().insert("DcAppVote.insertAppVote" , appVote)) ;
	}

	@Override
	public int updateIsVoteByUserId(Long userId, String isVote) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("userId", userId) ;
		map.put("isVote", isVote) ;
		return getSqlMapClientTemplate().update("DcAppVote.updateIsVoteByUserId" , map) ;
	}

	@Override
	public DcAppVoteDO selectAppVoteByUserId(Long userId) {
		return (DcAppVoteDO)getSqlMapClientTemplate().queryForObject("DcAppVote.selectAppVoteByUserId" , userId);
	}
	
	@Override
	public Integer updateAwardStatus(Long userId, String status) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("userId", userId) ;
		map.put("status", status) ;
		return (Integer) getSqlMapClientTemplate().update("DcAppVote.updateAwardStatus" , map) ;
	}
}
