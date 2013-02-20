package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcTaobaoService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;
import com.doucome.corner.web.common.utils.TaobaoUrlUtils;

/**
 * 更新状态
 * @author langben 2012-7-24
 *
 */
@SuppressWarnings("serial")
public class BopsItemAjaxAction extends  BopsBasicAction {

	private static final Log log = LogFactory.getLog(BopsItemAjaxAction.class) ;
	
	private Long itemId ;
	
	/**
	 * 批量
	 */
	private String itemIds ;
	
	/**
	 * 商品状态.
	 */
	private String itemStatus;
	
	private String itemUrl;
	
	private JsonModel<String> json = new JsonModel<String>();
	
	@Autowired
	private DcItemService dcItemService;
	
	@Autowired
    private DcTaobaoService	dcTaobaokeService;
	
	/**
	 * 下架商品.
	 * @return
	 */
	public String updateItemStatus() {
		DcItemStatusEnum statusEnum = DcItemStatusEnum.getInstance(itemStatus);
		List<Long> idList = new ArrayList<Long>() ;
		if(itemId != null){
			idList.add(itemId) ;
		}
		
		if(StringUtils.isNotBlank(itemIds)){
			List<Long> ids = ArrayStringUtils.toLongList(itemIds) ;
			idList.addAll(ids) ;
		}
		
		
		if (CollectionUtils.isEmpty(idList) || statusEnum == null) {
			json.setCode(JsonModel.CODE_ILL_ARGS);
			json.setDetail("itemId or status required");
			return SUCCESS;
		}
		
		int count = dcItemService.updateItemStatusByIds(idList, statusEnum) ;
		if (count >= 1) {
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

	public void setItemId(Long itemId) {
		this.itemId = itemId;
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

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}
	
}
