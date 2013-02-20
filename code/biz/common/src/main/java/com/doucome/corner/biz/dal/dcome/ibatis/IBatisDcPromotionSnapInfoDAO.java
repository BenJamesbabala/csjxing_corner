package com.doucome.corner.biz.dal.dcome.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcPromotionSnapInfoDO;
import com.doucome.corner.biz.dal.dcome.DcPromotionSnapInfoDAO;

public class IBatisDcPromotionSnapInfoDAO extends SqlMapClientDaoSupport implements DcPromotionSnapInfoDAO {

	@Override
	public long insertPromotionSnap(DcPromotionSnapInfoDO snapInfo) {
		return NumberUtils.parseLong((Long)getSqlMapClientTemplate().insert("DcPromotionSnapInfo.insertPromotionSnap" , snapInfo)) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DcPromotionSnapInfoDO> queryPromotionSnapWithPagination(Long promotionId, int start, int size) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("promotionId", promotionId) ;
		map.put("start", start - 1) ;
		map.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("DcPromotionSnapInfo.queryPromotionSnapWithPagination" , map) ;
	}

	@Override
	public int incrWishCountByPromotionAndItem(Long promotionId, Long itemId, int count) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("promotionId", promotionId) ;
		map.put("itemId", itemId) ;
		map.put("count", count) ;
		return getSqlMapClientTemplate().update("DcPromotionSnapInfo.incrWishCountByPromotionAndItem" , map);
	}

}
