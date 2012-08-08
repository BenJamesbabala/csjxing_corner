package com.doucome.corner.biz.dal.dcome.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcCommentSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCommentDO;
import com.doucome.corner.biz.dal.dcome.DcCommentDAO;
import com.ibatis.sqlmap.client.SqlMapExecutor;

/**
 * ¶¹Þ¢ÆÀÂÛ
 * @author shenjia.caosj 2012-7-21
 *
 */
public class IBatisDcCommentDAO extends SqlMapClientDaoSupport implements DcCommentDAO {

	@SuppressWarnings("unchecked")
	@Override 
	public List<DcCommentDO> queryCommentsWithPagination(DcCommentSearchCondition searchCondition, int start, int size) {
		Map<String,Object> condition = searchCondition.toMap() ;
		condition.put("start", start-1) ;
		condition.put("size", size) ;
		return getSqlMapClientTemplate().queryForList("DcComment.queryCommentsWithPagination" , condition) ;
		
	}
	
	@Override
	public DcCommentDO queryCommentById(long id) {
		return (DcCommentDO)getSqlMapClientTemplate().queryForObject("DcComment.queryCommentById" , id) ;
	}
	
	

	@Override
	public int countCommentsWithPagination(DcCommentSearchCondition searchCondition) {
		Map<String,Object> condition = searchCondition.toMap() ;
		return NumberUtils.integerToInt((Integer)getSqlMapClientTemplate().queryForObject("DcComment.countCommentsWithPagination" , condition)) ;
	}

	@Override
	public Long insertComment(DcCommentDO comment) {
		return (Long)getSqlMapClientTemplate().insert("DcComment.insertComment",comment) ;
	}
	
	@Override
	public int insertComments(final List<DcCommentDO> comments) {
		return this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Integer>() {
			
			@Override
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (DcCommentDO comment: comments) {
					executor.insert("DcComment.insertComment", comment);
				}
				return executor.executeBatch();
			}
			
		});
	}

	@Override
	public int updateStatus(Long commentId, String status) {
		Map<String,Object> condition = new HashMap<String,Object>() ;
		condition.put("id", commentId) ;
		condition.put("status", status) ;
		return getSqlMapClientTemplate().update("DcComment.updateStatus" , condition ) ;
	}

	
}
