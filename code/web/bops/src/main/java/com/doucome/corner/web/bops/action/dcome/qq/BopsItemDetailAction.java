package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoUserDTO;
import com.doucome.corner.biz.dal.condition.dcome.DcCommentSearchCondition;
import com.doucome.corner.biz.dcome.enums.DcCommentStatusEnums;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcCommentService;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcTaobaoService;
import com.doucome.corner.web.bops.action.BopsBasicAction;

/**
 * bops商品detail action。
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class BopsItemDetailAction extends BopsBasicAction {

	/**
	 * 商品id
	 */
	private Long id ;
	
	private DcItemDTO item ;
	
	private List<DcCommentDTO> commentList ;
	/**
	 * 卖家userid
	 */
	private Long sellerUserId;
	
	@Autowired
	private DcItemService dcItemService;
	@Autowired
	private DcCommentService dcCommentService ;
	@Autowired
	private DcTaobaoService dcTaobaoService;
	
	public String execute() {
		item = dcItemService.getItemById(id);
		if (item == null) {
			return "item_not_found";
		}
		DcCommentSearchCondition condition = new DcCommentSearchCondition() ;
		condition.setStatus(DcCommentStatusEnums.NORMAL.getValue()) ;
		condition.setItemId(item.getId()) ;
		commentList = dcCommentService.getCommentsNoPagination(condition, new Pagination(1,5)) ;
//		this.sellerUserId = getSellerUserId(item);
		return SUCCESS;
	}

//	private Long getSellerUserId(DcItemDTO tempItem) {
//		TaobaoItemDTO taobaoItem = dcTaobaoService.getTaobaoItem(tempItem.getNativeId());
//		if (taobaoItem == null) {
//			return null;
//		}
//		String sellerNick = taobaoItem.getNick();
//		TaobaoUserDTO taobaoUser = dcTaobaoService.getUser(sellerNick);
//		return taobaoUser.getUserId();
//	}
	
	public DcItemDTO getItem() {
		return item;
	}

	public Long getSellerUserId() {
		return sellerUserId;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<DcCommentDTO> getCommentList() {
		return commentList;
	}
	
	public class ItemCommentReqParam {
		private String name;
		private String value;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
	}
}
