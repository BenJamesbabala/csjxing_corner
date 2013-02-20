package com.doucome.corner.biz.dcome.business;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.ReflectUtils;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.utils.DcHttpUtils;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.model.DcItemPicModel;
import com.doucome.corner.biz.dcome.enums.DcItemSourceEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcTaobaoService;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;
import com.doucome.corner.web.common.utils.TaobaoUrlUtils;
import com.taobao.api.domain.ItemImg;

/**
 * 
 * @author ze2200
 *
 */
public class DcTaobaoItemBO {
	@Autowired
	private DcTaobaoService dcTaobaoService;
	
	private static final Log logger = LogFactory.getLog(DcTaobaoItemBO.class);
	
	/**
	 * 获取淘宝(集市和天猫)商品信息.
	 * @param taobaoItemId
	 * @return
	 */
	public DcItemDTO getTaobaoItem(String itemUrl) {
		String tbItemId = TaobaoUrlUtils.parseItemId(itemUrl);
		if (StringUtils.isBlank(tbItemId)) {
			return null;
		}
		TaobaokeItemDTO taokeItem = null;
		TaobaoItemDTO taobaoItem = null;
		try {
			taokeItem = dcTaobaoService.conventItem(tbItemId, OutCodeUtils.encodeOutCode("QQ", OutCodeEnums.DOUCOME));
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			taobaoItem = dcTaobaoService.getTaobaoItem(tbItemId);
		} catch (Exception e) {
			logger.error(e);
		}
		DcItemDTO dcItem = mergeInfoTo(taokeItem, taobaoItem);
		//taokeItem是没有srcUrl的，在taobaoItem=null的情况下，设置srcUrl和source字段
		if (dcItem != null && StringUtils.isEmpty(dcItem.getSrcUrl())) {
			dcItem.setSrcUrl(itemUrl);
			boolean isTmall = itemUrl.indexOf("tmall") != -1;
			dcItem.setSource(isTmall ? DcItemSourceEnum.TMALL.getValue():DcItemSourceEnum.TAOBAO.getValue());
		}
		return dcItem;
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
			dcTaobaoItem.setSrcUrl(taobaoItem.getDetailUrl());
			dcTaobaoItem.setSrcUrl(taobaoItem.getDetailUrl());
			dcTaobaoItem.setClickUrl(dcTaobaoItem.getSrcUrl());
			boolean isTmall = dcTaobaoItem.getSrcUrl().indexOf("tmall") != -1;
			dcTaobaoItem.setSource(isTmall ? DcItemSourceEnum.TMALL.getValue():DcItemSourceEnum.TAOBAO.getValue());
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
			dcTaokeItem.setClickUrl(taokeItem.getClickUrl());
			dcTaokeItem.setPicUrls(taokeItem.getPicUrl());
			dcTaokeItem.setNativeId(taokeItem.getNumIid() == null ? "" : taokeItem.getNumIid().toString());
			dcTaokeItem.setSells(taokeItem.getVolume() == null? 0 : taokeItem.getVolume().intValue());
		}
		ReflectUtils.mergeObject(dcTaokeItem, dcTaobaoItem);
		DcItemDTO result = new DcItemDTO(dcTaobaoItem) ;
		List<DcItemPicModel> picModelList = new ArrayList<DcItemPicModel>() ;
		if(CollectionUtils.isNotEmpty(picUrlList)){
			for(String picUrl : picUrlList){
				DcItemPicModel model = new DcItemPicModel() ;
				model.setUrl(picUrl) ;
				InputStream is = null ;
				try {
					is = DcHttpUtils.getInputStream(picUrl);
					Dimension di = ImageUtils.getImageDimension(is, org.apache.poi.ss.usermodel.Workbook.PICTURE_TYPE_JPEG) ;
					model.setHeightProp(DcItemUtils.getItemPicHeightProp(di)) ;
				} catch (IOException e) {
					logger.error(e.getMessage() , e) ;
				}
				
				picModelList.add(model) ;
			}
			result.setPicModelList(picModelList) ;
		}
		
		return result;
	}
}
