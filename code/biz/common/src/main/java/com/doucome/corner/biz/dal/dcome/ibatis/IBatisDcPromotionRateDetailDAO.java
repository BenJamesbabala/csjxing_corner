package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionRateDetailCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionRateDetailDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcRobOtherActivityDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionRateDetailDAO;


public class IBatisDcPromotionRateDetailDAO extends SqlMapClientDaoSupport implements DcPromotionRateDetailDAO{

	@Override
	public Long insertRateDetail(DcPromotionRateDetailDO rateDetail) {
		return (Long)getSqlMapClientTemplate().insert("DcPromotionRateDetail.insertRateDetail" , rateDetail) ;
	}

	@Override
	public DcPromotionRateDetailDO queryRateDetailByPromItemAndRateUser(Long promotionItemId, Long rateUserId) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("promotionItemId", promotionItemId) ;
		condition.put("rateUserId", rateUserId) ;
		return (DcPromotionRateDetailDO)getSqlMapClientTemplate().queryForObject("DcPromotionRateDetail.queryRateDetailByItemAndRateUser" , condition) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcPromotionRateDetailDO> queryRateDetailsWithPagination(DcPromotionRateDetailCondition searchCondition, int start, int size) {
		Map<String,Object> condition = searchCondition.toMap() ;
		condition.put("start", start - 1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("DcPromotionRateDetail.queryRateDetailsWithPagination" , condition) ;
	}

	@Override
	public int countRateDetailsWithPagination(DcPromotionRateDetailCondition searchCondition) {
		Map<String,Object> condition = searchCondition.toMap() ;
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("DcPromotionRateDetail.countRateDetailsWithPagination" , condition) );
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DcRobOtherActivityDO> getUserRobActivities(Long promotionItemId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("promotionItemId", promotionItemId);
//		params.put("pageStart", pageStart);
//		params.put("pageSize", pageSize);
		return getSqlMapClientTemplate().queryForList("DcPromotionRateDetail.getUserRobActivities", params);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DcRobOtherActivityDO> getPromRobActivities(Long promotionId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("promotionId", promotionId);
		
		return getSqlMapClientTemplate().queryForList("DcPromotionRateDetail.getPromRobActivities", params);
	}
}
