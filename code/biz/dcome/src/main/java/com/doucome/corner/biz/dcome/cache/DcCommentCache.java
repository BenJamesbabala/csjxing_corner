package com.doucome.corner.biz.dcome.cache;

import java.util.List;

import com.doucome.corner.biz.dcome.model.DcCommentDTO;

/**
 * ���ۻ���
 * @author shenjia.caosj 2012-7-28
 *
 */
public interface DcCommentCache {

	/**
	 * ��ȡ��һҳ����
	 * @param itemId
	 * @param pageSize
	 * @return
	 */
	List<DcCommentDTO> getFrontComments(long itemId , int pageSize) ;
	
	/**
	 * ���õ�һҳ����
	 * @param itemId
	 * @param pageSize
	 * @param comments
	 */
	void setFrontComments(long itemId , int pageSize , List<DcCommentDTO> comments) ;

	/**
	 * ɾ����һҳ���ۻ���
	 * @param itemId
	 * @param pageSize
	 */
	void removeFrontComments(long itemId , int pageSize) ;
}
