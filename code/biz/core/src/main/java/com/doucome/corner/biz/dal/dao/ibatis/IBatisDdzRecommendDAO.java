package com.doucome.corner.biz.dal.dao.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.core.utils.NumberUtils;
import com.doucome.corner.biz.dal.DdzRecommendDAO;
import com.doucome.corner.biz.dal.condition.DdzRecommendSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzRecommendDO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * 
 * @author shenjia.caosj 2012-5-22
 *
 */
public class IBatisDdzRecommendDAO extends SqlMapClientDaoSupport implements DdzRecommendDAO {

	@Override
	public int insertRecommend(final List<DdzRecommendDO> recommendList) {
		return getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				
				executor.startBatch();
				for (DdzRecommendDO recommend : recommendList) {
					executor.update("ddzRecommend.insertRecommend", recommend);
				}
				return executor.executeBatch();
			}
		}) ;
	}

	@Override
	public int updateRecommendDisplayById(int id, String isDisplay) {
		Map<String ,Object> condition = new HashMap<String,Object>() ;
		condition.put("id", id);
		condition.put("isDisplay", isDisplay) ;
		return getSqlMapClientTemplate().update("ddzRecommend.updateRecommendDisplayById" , condition ) ;
	}

	@Override
	public List<DdzRecommendDO> queryRecommends(String batchNo, String recommendType) {

		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("batchNo", batchNo) ;
		condition.put("recommendType", recommendType) ;
		return getSqlMapClientTemplate().queryForList("ddzRecommend.queryRecommends" ,condition) ;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DdzRecommendDO> queryRecommendsWithPagination(DdzRecommendSearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("batchNo", searchCondition.getBatchNo()) ;
		condition.put("recommendType", searchCondition.getRecommendType()) ;
		condition.put("start", start-1) ;
		condition.put("size", size) ;
		
		return getSqlMapClientTemplate().queryForList("ddzRecommend.queryRecommendsWithPagination" , condition) ;
		
	}

	@Override
	public int countRecommendsWithPagination(DdzRecommendSearchCondition searchCondition) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("batchNo", searchCondition.getBatchNo()) ;
		condition.put("recommendType", searchCondition.getRecommendType()) ;
		return NumberUtils.integerToInt((Integer)getSqlMapClientTemplate().queryForObject("ddzRecommend.countRecommendsWithPagination" , condition)) ;
	}

}
