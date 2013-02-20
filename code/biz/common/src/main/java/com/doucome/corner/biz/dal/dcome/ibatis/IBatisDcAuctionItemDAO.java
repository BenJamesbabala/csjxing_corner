package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcAuctionItemSearchCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcBidInfo;
import com.doucome.corner.biz.dal.dataobject.dcome.DcAuctionItemDO;
import com.doucome.corner.biz.dal.dcome.DcAuctionItemDAO;

/**
 * ¶¹Þ¢ÆÀÂÛ
 * @author langben 2012-7-21
 *
 */
public class IBatisDcAuctionItemDAO extends SqlMapClientDaoSupport implements DcAuctionItemDAO {

	@Override
	public Long insertAuctionItem(DcAuctionItemDO autionItemDO) {
		return (Long) getSqlMapClientTemplate().insert("dcAuctionItem.insertAuctionItem", autionItemDO);
	}

	@Override
	public DcAuctionItemDO getAuctionItem(Long id) {
		return (DcAuctionItemDO) getSqlMapClientTemplate().queryForObject("dcAuctionItem.getAuctionItem", id);
	}

	@Override
	public int updateBidInfo(Long id, DcBidInfo bidInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("userId", bidInfo.getUserId());
		params.put("userNick", bidInfo.getUserNick());
		params.put("integral", bidInfo.getIntegral());
		return getSqlMapClientTemplate().update("dcAuctionItem.updateBidInfo", params);
	}

	@Override
	public List<DcAuctionItemDO> queryAuctionItemWithPagination(
			DcAuctionItemSearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition = searchCondition.toMap() ;
		condition.put("start", start - 1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("dcAuctionItem.queryAuctionWithPagination" , condition);
	}

	@Override
	public int countAuctionItemWithPagination(
			DcAuctionItemSearchCondition searchCondition) {
		return NumberUtils.parseInt((Integer)getSqlMapClientTemplate().queryForObject("dcAuctionItem.countAuctionWithPagination" , searchCondition));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcAuctionItemDO> getAuctionItemInIng(Date timeStamp, int start, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeStamp", timeStamp);
		params.put("start", start);
		params.put("size", size);
		return (List<DcAuctionItemDO>) getSqlMapClientTemplate().queryForList("dcAuctionItem.getAuctionItemInIng", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcAuctionItemDO> getAuctionItemInOver(Date timeStamp, int start, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeStamp", timeStamp);
		params.put("start", start);
		params.put("size", size);
		return (List<DcAuctionItemDO>) getSqlMapClientTemplate().queryForList("dcAuctionItem.getAuctionItemInOver", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcAuctionItemDO> getAuctionItemInFuture(Date timeStamp, int start, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("timeStamp", timeStamp);
		params.put("start", start);
		params.put("size", size);
		return (List<DcAuctionItemDO>) getSqlMapClientTemplate().queryForList("dcAuctionItem.getAuctionItemInFuture", params);
	}

	@Override
	public int updatePromoInfoById(DcAuctionItemDO autionItemDO) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", autionItemDO.getId());
		params.put("baseIntegral", autionItemDO.getBaseIntegral());
		params.put("integralPer", autionItemDO.getIntegralPer());
		params.put("gmtStart", autionItemDO.getGmtStart());
		params.put("gmtEnd", autionItemDO.getGmtEnd());
		return getSqlMapClientTemplate().update("dcAuctionItem.updatePromoInfoById", autionItemDO);
	}

	@Override
	public int updateReviewStatusById(Long id, String reviewStatus) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("reviewStatus", reviewStatus);
		return getSqlMapClientTemplate().update("dcAuctionItem.updateReviewStatusById" , params) ;
	}
	
}
