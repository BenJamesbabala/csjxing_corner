package com.doucome.corner.biz.dal.dcome.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.dal.condition.dcome.DcIntegralCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralDetailDO;
import com.doucome.corner.biz.dal.dcome.DcUserIntegralDetailDAO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

public class IBatisDcUserIntegralDetailDAO extends SqlMapClientDaoSupport implements DcUserIntegralDetailDAO {

	@Override
	public Long insertDetail(DcUserIntegralDetailDO detail) {
		return (Long)getSqlMapClientTemplate().insert("DcUserIntegralDetail.insertDetail" , detail) ;
	}
	
	@Override
	public Integer batchInsertDetail(final List<DcUserIntegralDetailDO> details) {
		if (CollectionUtils.isEmpty(details)) {
			return 0;
		}
		return getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (DcUserIntegralDetailDO detail: details) {
					executor.insert("DcUserIntegralDetail.insertDetail", detail);
				}
				executor.executeBatch();
				return details.size();
			}
		});
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DcUserIntegralDetailDO> getUserIntegralDetails(Long userId, int start, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("start", start);
		params.put("size", size);
		return (List<DcUserIntegralDetailDO>) getSqlMapClientTemplate().queryForList(
				   "DcUserIntegralDetail.getUserIntegralDetails", params);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DcUserIntegralDetailDO> getLatestIntegralDetails(String source, int start, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("source", source);
		params.put("start", start);
		params.put("size", size);
		return (List<DcUserIntegralDetailDO>) getSqlMapClientTemplate().queryForList(
				   "DcUserIntegralDetail.getLatestIntegralDetails", params);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DcUserIntegralDetailDO> getIntegralDetails(DcIntegralCondition condition, int start, int size) {
		Map<String, Object> params = condition.toMap();
		params.put("start", start);
		params.put("size", size);
		return (List<DcUserIntegralDetailDO>) getSqlMapClientTemplate().queryForList(
				   "DcUserIntegralDetail.getIntegralDetails", params);
	}
	
	@Override
	public Integer countIntegralDetails(DcIntegralCondition condition) {
		Map<String, Object> params = condition.toMap();
		return (Integer) getSqlMapClientTemplate().queryForObject("DcUserIntegralDetail.countIntegralDetails", params);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<DcUserIntegralDetailDO> getAuctionList(Long auctionId, int size) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("auctionId", "%" + auctionId + "%");
		params.put("size", size);
		return (List<DcUserIntegralDetailDO>) getSqlMapClientTemplate().queryForList(
				   "DcUserIntegralDetail.getAuctionList", params);
	}
	
	@Override
	public int updateReadStatus(Long id, String readStatus) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("readStatus", readStatus);
		return (Integer) getSqlMapClientTemplate().update("DcUserIntegralDetail.updateReadStatus", params);
	}
}
