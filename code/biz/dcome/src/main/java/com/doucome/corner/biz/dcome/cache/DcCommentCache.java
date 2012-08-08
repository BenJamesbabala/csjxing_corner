package com.doucome.corner.biz.dcome.cache;

import java.util.List;

import com.doucome.corner.biz.dcome.model.DcCommentDTO;

/**
 * 评论缓存
 * @author shenjia.caosj 2012-7-28
 *
 */
public interface DcCommentCache {

	/**
	 * 获取第一页评论
	 * @param itemId
	 * @param pageSize
	 * @return
	 */
	List<DcCommentDTO> getFrontComments(long itemId , int pageSize) ;
	
	/**
	 * 设置第一页评论
	 * @param itemId
	 * @param pageSize
	 * @param comments
	 */
	void setFrontComments(long itemId , int pageSize , List<DcCommentDTO> comments) ;

	/**
	 * 删除第一页评论缓存
	 * @param itemId
	 * @param pageSize
	 */
	void removeFrontComments(long itemId , int pageSize) ;
}
