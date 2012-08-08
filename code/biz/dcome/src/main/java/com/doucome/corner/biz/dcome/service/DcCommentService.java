package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcCommentSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCommentDO;
import com.doucome.corner.biz.dcome.enums.DcCommentStatusEnums;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;

/**
 * ����
 * @author shenjia.caosj 2012-7-22
 *
 */
public interface DcCommentService {

	/**
	 * ���һ������
	 * @param comment
	 */
	Long createComment(DcCommentDO comment) ;
	
	/**
	 * ��Ӷ���Ʒ��
	 * @param comments
	 * @return
	 */
	int createComments(List<DcCommentDO> comments) ;
	
	/**
	 * ��ѯ����
	 * @param id
	 * @return
	 */
	DcCommentDTO getCommentById(long id);

	/**
	 * ͨ��������ҳ��ѯ����
	 * 
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	QueryResult<DcCommentDTO> getCommentsWithPagination(DcCommentSearchCondition searchCondition , Pagination pagination);
	
	/**
	 * ͨ�������Ƿ�ҳ��ѯ
	 * @param searchCondition
	 * @param pagination
	 * @return
	 */
	List<DcCommentDTO> getCommentsNoPagination(DcCommentSearchCondition searchCondition , Pagination pagination);

	/**
	 * ��������״̬
	 * @param commentId
	 * @param statusEnum
	 * @return
	 */
	int updateStatus(Long commentId, DcCommentStatusEnums status);
	
}
