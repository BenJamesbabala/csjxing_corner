package com.doucome.corner.web.bops.action.dcome.qq;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.core.utils.ReflectUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dcome.enums.DcItemSourceEnums;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcTaobaokeService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.common.utils.TaobaoUrlUtils;
import com.taobao.api.domain.ItemImg;

/**
 * 
 * @author ze2200
 *
 */
@SuppressWarnings("serial")
public class ItemSearchAction extends BopsBasicAction{

	private String wd;
		
	private DcItemDTO item;
	
	@Autowired
    private DcTaobaokeService	dcTaobaokeService;
	
	@Autowired
	private DcItemService dcItemService;
	
	private static final Log logger = LogFactory.getLog(ItemSearchAction.class);
	
	public String execute() {
		if (StringUtils.isBlank(wd)) {
			return SUCCESS;
		}
		String itemId = null;
		if (StringUtils.isNumeric(wd)) {
			long id = Long.valueOf(wd);
			item = dcItemService.getItemById(id);
			if (item != null) {
				return SUCCESS;
			}
			return "item_not_found";
		}
		itemId = TaobaoUrlUtils.parseItemId(wd);
		if (StringUtils.isBlank(itemId)) {
			return "item_not_found";
		}

		TaobaokeItemDTO taokeItem = null;
		TaobaoItemDTO taobaoItem = null;
		try {
			taokeItem = dcTaobaokeService.conventItem(itemId, OutCodeUtils.encodeOutCode("QQ", OutCodeEnums.DOUCOME));
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			taobaoItem = dcTaobaokeService.getTaobaoItem(itemId);
		} catch (Exception e) {
			logger.error(e);
		}
		item = mergeInfoTo(taokeItem, taobaoItem);
		if (item == null) {
			return "item_not_found";
		}
		
		return SUCCESS;
	}
	
	private DcItemDTO mergeInfoTo(TaobaokeItemDTO taokeItem, TaobaoItemDTO taobaoItem) {
		if (taokeItem == null && taobaoItem == null) {
			return null;
		}
		DcItemDO dcTaobaoItem = null;
		List<String> picUrlList = new ArrayList<String>();
		if (taobaoItem != null) {
			dcTaobaoItem = new DcItemDO();
			dcTaobaoItem.setItemTitle(taobaoItem.getTitle());
			dcTaobaoItem.setItemDesc(taobaoItem.getWapDesc());
			dcTaobaoItem.setItemPrice(taobaoItem.getPrice());
			dcTaobaoItem.setNativeCategoryId(taobaoItem.getCid());
			dcTaobaoItem.setSrcUrl(wd);
			if (StringUtils.isNotEmpty(taobaoItem.getDetailUrl())) {
				dcTaobaoItem.setSrcUrl(taobaoItem.getDetailUrl());
			}
			dcTaobaoItem.setClickUrl(dcTaobaoItem.getSrcUrl());
			boolean isTmall = dcTaobaoItem.getSrcUrl().indexOf("tmall") != -1;
			dcTaobaoItem.setSource(isTmall ? DcItemSourceEnums.TMALL.getValue():DcItemSourceEnums.TAOBAO.getValue());
			dcTaobaoItem.setPicUrls(taobaoItem.getPicUrl());
			dcTaobaoItem.setNativeId(taobaoItem.getNumIid() == null ? "" : taobaoItem.getNumIid().toString());
			dcTaobaoItem.setLoves(0);
			dcTaobaoItem.setSells(0);
			dcTaobaoItem.setComments(0);
			if (taobaoItem.getItemImgs() != null) {
				for (ItemImg itemImg: taobaoItem.getItemImgs()) {
					picUrlList.add(itemImg.getUrl());
				}
			}
		}
		DcItemDO dcTaokeItem = null;
		if (taokeItem != null) {
			dcTaokeItem = new DcItemDO();
			dcTaokeItem.setItemTitle(taokeItem.getTitle());
			dcTaokeItem.setItemPrice(taokeItem.getPrice());
			dcTaokeItem.setSrcUrl(wd);
			dcTaokeItem.setClickUrl(taokeItem.getClickUrl());
			boolean isTmall = dcTaokeItem.getSrcUrl().indexOf("tmall") != -1;
			dcTaokeItem.setSource(isTmall ? DcItemSourceEnums.TMALL.getValue():DcItemSourceEnums.TAOBAO.getValue());
			dcTaokeItem.setPicUrls(taokeItem.getPicUrl());
			dcTaokeItem.setNativeId(taokeItem.getNumIid() == null ? "" : taokeItem.getNumIid().toString());
			dcTaokeItem.setSells(taokeItem.getVolume() == null? 0 : taokeItem.getVolume().intValue());
		}
		ReflectUtils.mergeObject(dcTaokeItem, dcTaobaoItem);
		DcItemDTO result = new DcItemDTO(dcTaobaoItem);
		result.setPicUrlList(picUrlList);
		return result;
	}
	
	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public DcItemDTO getItem() {
		return item;
	}

}
