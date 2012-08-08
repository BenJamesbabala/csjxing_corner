package com.doucome.corner.web.bops.action.dcome.qq;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.dcome.business.DcCommentBO;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcTaobaokeService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;
import com.doucome.corner.web.common.utils.TaobaoUrlUtils;

/**
 * 灌水
 * @author shenjia.caosj 2012-7-24
 *
 */
@SuppressWarnings("serial")
public class BopsItemAjaxAction extends  BopsBasicAction {

	private static final Log log = LogFactory.getLog(BopsItemAjaxAction.class) ;
	
	private Long itemId ;
	/**
	 * 评论内容
	 */
	private String content;
	/**
	 * 
	 */
	private Long bopsUserId;
	/**
	 * 商品状态.
	 */
	private String itemStatus;
	
	private String itemUrl;
	
	private JsonModel<List<DcCommentDTO>> comments = new JsonModel<List<DcCommentDTO>>();
	
	private JsonModel<String> json = new JsonModel<String>();

	@Autowired
	private DcCommentBO dcCommentBO ;
	
	@Autowired
	private DcItemService dcItemService;
	
	@Autowired
    private DcTaobaokeService	dcTaobaokeService;
	
	/**
	 * 灌水。
	 * @return
	 */
	public String addBopsComment() {
		if(itemId == null || StringUtils.isEmpty(content)) {
			comments.setCode(JsonModel.CODE_ILL_ARGS) ;
			comments.setDetail("itemId and comment content required") ;
			return SUCCESS ;
		}
		try {
			String temp = URLDecoder.decode(content,"UTF-8");
			List<String> tempComments = new ArrayList<String> (Arrays.asList(temp.split(";")));
			for (int i = tempComments.size() - 1; i >= 0; i--) {
				if (StringUtils.isEmpty(tempComments.get(i)) || StringUtils.length(tempComments.get(i)) > 160) {
					tempComments.remove(i);
				}
			}
			List<DcCommentDTO> result = null;
			if (tempComments.size() == 1) {
				DcCommentDTO comment = dcCommentBO.addBopsComment(bopsUserId, itemId, tempComments.get(0));
				result = new ArrayList<DcCommentDTO>();
				result.add(comment);
			} else {
				result = dcCommentBO.addBopsComments(itemId, tempComments);
			}
			comments.setCode(JsonModel.CODE_SUCCESS);
			comments.setData(result) ;
		} catch (Exception e) {
			log.error(e.getMessage() , e) ;
			comments.setCode(JsonModel.CODE_FAIL) ;
			comments.setDetail("internal error .") ;
			return SUCCESS ;
		}
		return SUCCESS;
	}
	
	/**
	 * 下架商品.
	 * @return
	 */
	public String resetItemStatus() {
		DcItemStatusEnum statusEnum = DcItemStatusEnum.getInstance(itemStatus);
		if (itemId == null || statusEnum == null) {
			json.setCode(JsonModel.CODE_ILL_ARGS);
			json.setDetail("itemId or status required");
			return SUCCESS;
		}
		
		int count = dcItemService.resetItemStatus(itemId, statusEnum);
		if (count == 1) {
			json.setCode(JsonModel.CODE_SUCCESS);
		} else {
			json.setCode(JsonModel.CODE_FAIL);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 重新生成淘客链接.
	 * @return
	 */
	public String resetTaokeUrl() {
		if (StringUtils.isEmpty(itemUrl)) {
			json.setCode(JsonModel.CODE_ILL_ARGS);
			json.setDetail("itemUrl required");
			return SUCCESS;
		}
		String tempItemId = TaobaoUrlUtils.parseItemId(itemUrl);
		if (StringUtils.isBlank(tempItemId)) {
			json.setCode(JsonModel.CODE_ILL_ARGS);
			json.setDetail("itemUrl required");
			return SUCCESS;
		}
		try {
			TaobaokeItemDTO taokeItem = dcTaobaokeService.conventItem(tempItemId, OutCodeUtils.encodeOutCode("QQ", OutCodeEnums.DOUCOME));
			json.setCode(JsonModel.CODE_SUCCESS);
			if (taokeItem != null) {
				json.setData(taokeItem.getClickUrl());
			} else {
				json.setData(itemUrl);
			}
		} catch (Exception e) {
			log.error(e);
			json.setCode(JsonModel.CODE_FAIL);
			json.setDetail("taoke request error");
		}
		return SUCCESS;
	}
	
	public JsonModel<List<DcCommentDTO>> getComments() {
		return comments;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setBopsUserId(Long bopsUserId) {
		this.bopsUserId = bopsUserId;
	}

	public JsonModel<String> getJson() {
		return json;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
}
