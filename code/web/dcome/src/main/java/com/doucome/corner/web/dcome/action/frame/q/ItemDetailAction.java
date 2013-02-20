package com.doucome.corner.web.dcome.action.frame.q;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcCommentSearchCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcItemSearchCondition;
import com.doucome.corner.biz.dcome.enums.DcCommentStatusEnums;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.util.PromotionIntegralUtils;
import com.doucome.corner.biz.dcome.service.DcCommentService;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * …Ã∆∑detail
 * @author langben 2012-7-29
 *
 */
@SuppressWarnings("serial")
public class ItemDetailAction extends QBasicAction {

	private Long id ;
	
	private Long promItemId ;
	
	private DcItemDTO itemDTO ;
	
	private Map<Long,DcItemDTO> itemMap ;
	
	private List<DcCommentDTO> commentList ;
	
	private List<Long> pgcIdList ;
	
	private int integralCount ;
	
	/**---------------------------------------------------------------------**/
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcCommentService dcCommentService ;
	
	@Override
	public String execute() throws Exception {
				
		DcItemSearchCondition itemCondition = new DcItemSearchCondition() ;
		itemCondition.setGenWay(DcItemGenWayEnums.PROFESSIONAL.getValue()) ;
		pgcIdList = dcItemService.getItemIdsNoPagination(itemCondition, new Pagination(1,3)) ;
		
		List<Long> idList = mergeIds(pgcIdList ,  id) ;
		
		List<DcItemDTO> itemList = dcItemService.getItemsByIds(idList) ;
		itemMap = new HashMap<Long,DcItemDTO>() ;
		for(DcItemDTO dto : itemList) {
			itemMap.put(dto.getId(), dto) ;
		}
		
		
		itemDTO = itemMap.get(id) ;
		
		if(itemDTO != null && itemDTO.getComments() > 0){
			DcCommentSearchCondition commentCondition = new DcCommentSearchCondition() ;
			commentCondition.setItemId(id) ;
			commentCondition.setStatus(DcCommentStatusEnums.NORMAL.getValue()) ;
			this.commentList = dcCommentService.getCommentsNoPagination(commentCondition, new Pagination(1,20)) ;
		}
		
		integralCount = PromotionIntegralUtils.getIntegral(itemDTO) ;
		
		return SUCCESS ;
	}
	
	private List<Long> mergeIds(List<Long> pgcList , Long id) {
		List<Long> mergeIdList = new ArrayList<Long>() ;
		
		mergeIdList.addAll(pgcList) ;
		
		mergeIdList.add(id) ;
		
		return mergeIdList ;
	}

	public DcItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Map<Long, DcItemDTO> getItemMap() {
		return itemMap;
	}

	public List<Long> getPgcIdList() {
		return pgcIdList;
	}

	public List<DcCommentDTO> getCommentList() {
		return commentList;
	}

	public int getIntegralCount() {
		return integralCount;
	}

}
