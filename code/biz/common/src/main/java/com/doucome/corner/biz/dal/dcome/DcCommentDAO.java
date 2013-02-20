package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcCommentSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCommentDO;

/**
 * 豆蔻评论
 * @author langben 2012-7-21
 *
 */
public interface DcCommentDAO {
	
	/**
	 * 添加一条评论
	 * @param comment
	 */
	Long insertComment(DcCommentDO comment) ;
	/**
	 * 添加多条品论
	 * @param comments
	 * @return
	 */
	int insertComments(List<DcCommentDO> comments) ;
	
	/**
	 * 查询评论
	 * @param id
	 * @return
	 */
	DcCommentDO queryCommentById(long id);

	/**
	 * 通过条件分页查询评论
	 * 
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcCommentDO> queryCommentsWithPagination(DcCommentSearchCondition searchCondition , int start , int size);
	
	/**
	 * 通过条件统计商品
	 * @param searchCondition
	 * @return
	 */
	int countCommentsWithPagination(DcCommentSearchCondition searchCondition) ;

	/**
	 * 更新评论状态
	 * @param commentId
	 * @param value
	 * @return
	 */
	int updateStatus(Long commentId, String status);

	
	
}
