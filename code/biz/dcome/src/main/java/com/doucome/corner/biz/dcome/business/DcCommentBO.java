package com.doucome.corner.biz.dcome.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcCommentSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCommentDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.cache.DcCommentCache;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.biz.dcome.service.DcCommentService;
import com.doucome.corner.biz.dcome.service.DcItemService;

public class DcCommentBO {
	
	private static final Log log  = LogFactory.getLog(DcCommentBO.class) ;

	@Autowired
	private DcCommentService dcCommentService ;
	
	@Autowired
	private DcCommentCache dcCommentCache ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcFakeUserBO dcFakeUserBO;
	
	public Long addComment(DcCommentDO comment) {
		Long id = dcCommentService.createComment(comment) ;
		dcItemService.incrCommentCount(comment.getItemId()) ;
		return id ;
	}
	
	
	/**
	 * 评论灌水。
	 * @param fakeUserId 灌水用户id。
	 * @param itemId 商品id
	 * @param comments 评论内容.请以英文';'分隔多条品论
	 * @return 评论id。
	 */
	public DcCommentDTO addBopsComment(Long fakeUserId, Long itemId, String comment) {
		DcCommentDO commentDO = new DcCommentDO();
		commentDO.setContent(comment);
		commentDO.setItemId(itemId);
		DcUserDO commentUserDO = null;
		if (fakeUserId != null) {
			commentUserDO = dcFakeUserBO.getFakeUser(fakeUserId);
		} else {
			commentUserDO = dcFakeUserBO.getFakeUser();
		}
		commentDO.setUserId(commentUserDO.getUserId());
		commentDO.setUserNick(commentUserDO.getNick());
		commentDO.setSource(commentUserDO.getSource());
		
		Long id = dcCommentService.createComment(commentDO);
		dcItemService.incrCommentCount(itemId);
		commentDO.setId(id);
		return new DcCommentDTO(commentDO);
	}
	
	/**
	 * 批量灌水。
	 * @param itemId 商品id.
	 * @param commentContents 评论。
	 * @return 。
	 */
	public List<DcCommentDTO> addBopsComments(Long itemId, List<String> commentContents) {
		if (itemId == null || commentContents == null || commentContents.size() == 0) {
			return new ArrayList<DcCommentDTO>();
		}
		List<DcCommentDO> comments = new ArrayList<DcCommentDO>(commentContents.size());
		for (String content: commentContents) {
			DcCommentDO commentDO = new DcCommentDO();
			commentDO.setContent(content);
			commentDO.setItemId(itemId);
			DcUserDO commentUserDO = dcFakeUserBO.getFakeUser();
			commentDO.setUserId(commentUserDO.getUserId());
			commentDO.setUserNick(commentUserDO.getNick());
			commentDO.setSource(commentUserDO.getSource());
			
			comments.add(commentDO);
		}
		
		int count = dcCommentService.createComments(comments);
		dcItemService.incrCommentCount(itemId, count);
		if (count != comments.size()) {
			log.error("expect insert " + comments.size() + " comment, actual insert " + count + " comments");
		}
		List<DcCommentDTO> result = new ArrayList<DcCommentDTO>(comments.size());
		for (DcCommentDO comment: comments) {
			result.add(new DcCommentDTO(comment));
		}
		
		return result;
	}
	
	public List<DcCommentDTO> getCommentsNoPagination(long itemId , int page, int size) {
		List<DcCommentDTO> commentList = null  ;
		DcCommentSearchCondition condition = new DcCommentSearchCondition() ;
		condition.setItemId(itemId) ;
		if(page == 1){
			commentList = dcCommentCache.getFrontComments(itemId, size) ;
		}
		if(commentList == null){
			commentList = dcCommentService.getCommentsNoPagination(condition, new Pagination(page,size)) ;
			if(page == 1 && CollectionUtils.isNotEmpty(commentList)){
				dcCommentCache.setFrontComments(itemId, size, commentList) ;
			}
		}
		return commentList ;
		
	}
}
