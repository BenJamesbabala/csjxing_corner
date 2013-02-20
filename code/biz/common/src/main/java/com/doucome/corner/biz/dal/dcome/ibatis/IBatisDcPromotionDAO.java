package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.dal.condition.dcome.DcPromotionCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionDAO;

public class IBatisDcPromotionDAO extends SqlMapClientDaoSupport implements DcPromotionDAO {

	@Override
	public Long insertPromotion(DcPromotionDO promotion) {
		return (Long)getSqlMapClientTemplate().insert("DcPromotion.insertPromotion" , promotion) ;
	}

	@Override
	public DcPromotionDO queryPromotionById(Long promotionId) {
		return (DcPromotionDO)getSqlMapClientTemplate().queryForObject("DcPromotion.queryPromotionById" , promotionId) ;
	}
	
	@Override
	public DcPromotionDO queryPromotionByDate(Date date) {
		return (DcPromotionDO)getSqlMapClientTemplate().queryForObject("DcPromotion.queryPromotionByDate" , date) ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DcPromotionDO> getPromotionsWithPage(DcPromotionCondition condition, int pageStart, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", condition.getName());
		params.put("status", condition.getStatus());
		params.put("startDate", condition.getStartDate());
		params.put("endDate", condition.getEndDate());
		params.put("pageStart", pageStart);
		params.put("pageSize", pageSize);
		return (List<DcPromotionDO>) getSqlMapClientTemplate().queryForList("DcPromotion.getPromotionsWithPage", params);
	}
	
	@Override
	public Integer countPromotions(DcPromotionCondition condition) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", condition.getName());
		params.put("status", condition.getStatus());
		params.put("startDate", condition.getStartDate());
		params.put("endDate", condition.getEndDate());
		return (Integer) getSqlMapClientTemplate().queryForObject("DcPromotion.countPromotions", params);
	}
	@SuppressWarnings("unchecked")
	public List<DcPromotionDO> queryPromotionsWithPagination(DcPromotionSearchCondition searchCondition , int start , int size) {
		Map<String,Object> condition = searchCondition.toMap() ;
		condition.put("start", start - 1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("DcPromotion.queryPromotionsWithPagination" , condition) ;
	}

    @Override
    public int updatePromotion(DcPromotionDO promotion) {
        return getSqlMapClientTemplate().update("DcPromotion.updatePromotion", promotion);
    }
}
