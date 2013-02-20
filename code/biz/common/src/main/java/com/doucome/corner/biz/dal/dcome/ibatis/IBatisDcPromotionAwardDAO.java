package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionAwardDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionAwardDAO;

public class IBatisDcPromotionAwardDAO extends SqlMapClientDaoSupport implements DcPromotionAwardDAO  {

	@Override
	public long insertAward(DcPromotionAwardDO award) {
		return (Long)getSqlMapClientTemplate().insert("DcPromotionAward.insertAward" , award) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcPromotionAwardDO> queryAwardByUserId(long userId) {
		return (List<DcPromotionAwardDO>)getSqlMapClientTemplate().queryForList("DcPromotionAward.queryAwardByUserId" , userId) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DcPromotionAwardDO> getAwardsByPromId(Long promotionId) {
		return (List<DcPromotionAwardDO>) getSqlMapClientTemplate().queryForList("DcPromotionAward.getAwardsByPromId", promotionId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DcPromotionAwardDO> queryAwardsNoPage(DcPromotionAwardCondition condition) {
		Map<String,Object> params = condition.toMap();
		return getSqlMapClientTemplate().queryForList("DcPromotionAward.queryAwardsNoPage" , params);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DcPromotionAwardDO> queryAwardsWithPagination(DcPromotionAwardCondition searchCondition, int start, int size) {
		Map<String,Object> condition = searchCondition.toMap() ;
		condition.put("start", start - 1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("DcPromotionAward.queryAwardsWithPagination" , condition);
	}
	
	@Override
	public int countAwardsWithPagination(DcPromotionAwardCondition searchCondition) {
		Map<String,Object> condition = searchCondition.toMap() ;
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("DcPromotionAward.countAwardsWithPagination" , condition));
	}

	@Override
	public int updateReviewStatusById(Long awardId, String status) {
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("id", awardId) ;
		condition.put("reviewStatus", status) ;
		return getSqlMapClientTemplate().update("DcPromotionAward.updateReviewStatusById" , condition);
	}
	
	@Override
	public int updateSendStatusById(Long awardId, String status) {
		Map<String,Object> condition = new HashMap<String, Object>();
		condition.put("id", awardId) ;
		condition.put("sendStatus", status) ;
		return getSqlMapClientTemplate().update("DcPromotionAward.updateSendStatusById" , condition);
	}

	@Override
	public DcPromotionAwardDO queryAwardById(long awardId) {
		return (DcPromotionAwardDO)getSqlMapClientTemplate().queryForObject("DcPromotionAward.queryAwardsById" , awardId) ;
	}
	
	@Override
	public int updateShareStatus(Long awardId, Long userId, String shareStatus) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("awardId", awardId) ;
		params.put("userId", userId);
		params.put("shareStatus", shareStatus) ;
		return getSqlMapClientTemplate().update("DcPromotionAward.updateShareStatus" , params);
	}
	
	@Override
	public int updateAwardAddrInfo(DcPromotionAwardDO awardDO) {
		return getSqlMapClientTemplate().update("DcPromotionAward.updateAwardAddrInfo", awardDO);
	}
}
