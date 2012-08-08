package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcCommentSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCommentDO;
import com.doucome.corner.biz.dcome.enums.DcCommentStatusEnums;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;

/**
 * 评论
 * @author shenjia.caosj 2012-7-22
 *
 */
public interface DcCommentService {

	/**
	 * 添加一条评论
	 * @param comment
	 */
	Long createComment(DcCommentDO comment) ;
	
	/**
	 * 添加多条品论
	 * @param comments
	 * @return
	 */
	int createComments(List<DcCommentDO> comments) ;
	
	/**
	 * 查询评论
	 * @param id
	 * @return
	 */
	DcCommentDTO getCommentById(long id);

	/**
	 * 通过条件分页查询评论
	 * 
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	QueryResult<DcCommentDTO> getCommentsWithPagination(DcCommentSearchCondition searchCondition , Pagination pagination);
	
	/**
	 * 通过条件非分页查询
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	List<DcCommentDTO> getCommentsNoPagination(DcCommentSearchCondition searchCondition , Pagination pagination);

	/**
	 * 更新评论状态
	 * @param commentId
	 * @param statusEnum
	 * @return
	 */
	int updateStatus(Long commentId, DcCommentStatusEnums status);
	
}
