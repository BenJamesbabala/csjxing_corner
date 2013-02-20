package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.condition.dcome.DcCommentSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCommentDO;

/**
 * ��ޢ����
 * @author langben 2012-7-21
 *
 */
public interface DcCommentDAO {
	
	/**
	 * ���һ������
	 * @param comment
	 */
	Long insertComment(DcCommentDO comment) ;
	/**
	 * ��Ӷ���Ʒ��
	 * @param comments
	 * @return
	 */
	int insertComments(List<DcCommentDO> comments) ;
	
	/**
	 * ��ѯ����
	 * @param id
	 * @return
	 */
	DcCommentDO queryCommentById(long id);

	/**
	 * ͨ��������ҳ��ѯ����
	 * 
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DcCommentDO> queryCommentsWithPagination(DcCommentSearchCondition searchCondition , int start , int size);
	
	/**
	 * ͨ������ͳ����Ʒ
	 * @param searchCondition
	 * @return
	 */
	int countCommentsWithPagination(DcCommentSearchCondition searchCondition) ;

	/**
	 * ��������״̬
	 * @param commentId
	 * @param value
	 * @return
	 */
	int updateStatus(Long commentId, String status);

	
	
}
