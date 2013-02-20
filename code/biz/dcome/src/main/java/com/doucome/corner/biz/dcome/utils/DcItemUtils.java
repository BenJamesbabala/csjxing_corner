package com.doucome.corner.biz.dcome.utils;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.util.JSONUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.util.ImageUtils;

import com.doucome.corner.biz.common.utils.ReflectUtils;
import com.doucome.corner.biz.core.constant.Constant;
import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.DcHttpUtils;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.core.utils.PictureUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.model.DcItemPicModel;
import com.doucome.corner.biz.dcome.enums.DcItemSourceEnum;
import com.doucome.corner.biz.dcome.enums.DcYesOrNoEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPictureModel;
import com.taobao.api.domain.ItemImg;

public class DcItemUtils {
	
	public static final String ITEM_PIC_BASE_URL = "dcomeItemUploadedServer";
	
	public static final Long PGC_ITEM_CREATOR = 1L;
	
	private static final Log log = LogFactory.getLog(DcItemUtils.class) ;

	public static void sortByLoves(List<DcItemDTO> itemList){
		Collections.sort(itemList, new Comparator<DcItemDTO>() {
			@Override
			public int compare(DcItemDTO o1, DcItemDTO o2) {
				int l1 = getItemLoves(o1) ;
				int l2 = getItemLoves(o2) ;
				if(l1 == l2){
					return 0 ;
				}
				return l1 > l2 ? -1 : 1 ;
				
			}
		}) ;
	}

	public static void sortByComments(List<DcItemDTO> itemList){
		Collections.sort(itemList, new Comparator<DcItemDTO>() {
			@Override
			public int compare(DcItemDTO o1, DcItemDTO o2) {
				int l1 = getItemComments(o1) ;
				int l2 = getItemComments(o2) ;
				if(l1 == l2){
					return 0 ;
				}
				return l1 > l2 ? -1 : 1 ;
				
			}
		}) ;
	}
	
	public static int getItemLoves(DcItemDTO item){
		if(item == null){
			return 0 ;
		}
		if(item.getLoves() == null){
			return 0 ;
		}
		return item.getLoves() ;
	}
	
	public static int getItemComments(DcItemDTO item){
		if(item == null){
			return 0 ;
		}
		if(item.getComments() == null){
			return 0 ;
		}
		return item.getComments() ;
	}
	
	public static Map<Long,DcItemDTO> conventToMap(List<DcItemDTO> itemList){
		Map<Long,DcItemDTO> retMap = new HashMap<Long,DcItemDTO>() ;
		if(CollectionUtils.isNotEmpty(itemList)) {
			for(DcItemDTO item : itemList){
				retMap.put(item.getId(), item) ;
			}
		}
		return retMap ;
	}
	
	public static DcItemDTO mergeInfoTo(TaobaokeItemDTO taokeItem, TaobaoItemDTO taobaoItem) {
		if (taokeItem == null && taobaoItem == null) {
			return null;
		}
		DcItemDO dcTaobaoItem = null;
		List<String> picUrlList = new ArrayList<String>();
		if (taobaoItem != null) {
			dcTaobaoItem = new DcItemDO();
			dcTaobaoItem.setItemTitle(taobaoItem.getTitle());
			String temp = StringUtils.substring(taobaoItem.getWapDesc() , 0 ,150) ;
			dcTaobaoItem.setItemDesc(temp);
			dcTaobaoItem.setItemPrice(taobaoItem.getPrice());
			dcTaobaoItem.setNativeCategoryId(taobaoItem.getCid());
			dcTaobaoItem.setSrcUrl(taobaoItem.getDetailUrl());
			dcTaobaoItem.setClickUrl(taobaoItem.getDetailUrl());
			//dcTaobaoItem.setPicUrls(taobaoItem.getPicUrl());
			dcTaobaoItem.setNativeId(taobaoItem.getNumIid() == null ? "" : taobaoItem.getNumIid().toString());
			dcTaobaoItem.setLoves(0);
			dcTaobaoItem.setSells(0);
			dcTaobaoItem.setComments(0);
			//是否包邮
			if (DcYesOrNoEnum.YES.getTBPostalPayer().equals(taobaoItem.getFreightPayer())) {
				dcTaobaoItem.setPostalEnable(DcYesOrNoEnum.YES.getValue());
			} else {
				dcTaobaoItem.setPostalEnable(DcYesOrNoEnum.NO.getValue());
			}
			if (taobaoItem.getItemImgs() != null) {
				for (ItemImg itemImg : taobaoItem.getItemImgs()) {
					picUrlList.add(itemImg.getUrl());
				}
			}
			
			boolean isTmall = StringUtils.indexOf(taobaoItem.getDetailUrl() , "tmall") != -1;
			dcTaobaoItem.setSource(isTmall ? DcItemSourceEnum.TMALL.getValue():DcItemSourceEnum.TAOBAO.getValue());
		}
		DcItemDO dcTaokeItem = null;
		if (taokeItem != null) {
			dcTaokeItem = new DcItemDO();
			dcTaokeItem.setItemTitle(taokeItem.getTitle());
			dcTaokeItem.setItemPrice(taokeItem.getPrice());
			dcTaokeItem.setCommissionRate(convertTaokeItemDiscount(taokeItem.getCommissionRate()));
			dcTaokeItem.setNativeId(taokeItem.getNumIid()== null? null: taokeItem.getNumIid().toString());
			dcTaokeItem.setClickUrl(taokeItem.getClickUrl());
			//dcTaokeItem.setPicUrls(taokeItem.getPicUrl());
			dcTaokeItem.setNativeId(taokeItem.getNumIid() == null ? "" : taokeItem.getNumIid().toString());
			dcTaokeItem.setSells(taokeItem.getVolume() == null? 0 : taokeItem.getVolume().intValue());
			dcTaokeItem.setLoves(0);
			dcTaokeItem.setComments(0);
		}
		ReflectUtils.mergeObject(dcTaokeItem, dcTaobaoItem);
		DcItemDTO result = new DcItemDTO(dcTaobaoItem);
		
		//处理图片
		DcItemUtils.parsePicUrls2ModelList(result , picUrlList) ;
		
		return result;
	}
	
	/**
	 * 转换淘客比例
	 * @param discount
	 * @return
	 */
	public static BigDecimal convertTaokeItemDiscount(BigDecimal discount) {
		return discount.divide(new BigDecimal("10000"), 4, RoundingMode.FLOOR);
	}
	
	public static Integer getItemOwerSign(Long itemCreator, Long userId) {
		if (itemCreator == null) {
			return 3;
		}
		if (itemCreator.equals(userId)) {
			return 1;
		}
		if (itemCreator.equals(PGC_ITEM_CREATOR)) {
			return 0;
		}
		return 2;
	}
	
	/**
	 * Item里的淘客、价格等信息是否过期，判断GMT_UPDATE字段
	 * @param item
	 * @return
	 */
	public static boolean isItemExpired(DcItemDO item){
		if(item == null){
			return false ;
		}
		
		Date gmtLastUpdate = item.getGmtItemUpdate() ;
		
		if(gmtLastUpdate == null){
			return true ;
		}
		
		Calendar cal = Calendar.getInstance() ;
		Calendar calUpdate = Calendar.getInstance() ;
		calUpdate.setTime(gmtLastUpdate) ;
		calUpdate.add(Calendar.DATE, 3) ;
		//商品还是新的
		if(calUpdate.after(cal)) {
			return false ;
		}
		
		return true ;
		
	}
	
	public static List<Long> getItemDOIds(List<DcItemDO> list){
		List<Long> idList = new ArrayList<Long>() ;
		for(DcItemDO item : list){
			idList.add(item.getId()) ;
		}
		return idList ;
	}
	
	/**
	 * 
	 * @param picModelList
	 * @return
	 */
	public static List<String> getPicUrlList(List<DcPictureModel> picModelList){
		List<String> picUrlList = new ArrayList<String>() ;
		if(CollectionUtils.isEmpty(picModelList)) {
			return picUrlList ;
		}
		
		for(DcPictureModel model : picModelList){
			picUrlList.add(model.getPath()) ;
		}
		
		return picUrlList ;
	}
	
	public static List<String> getPicHeightPropList(List<DcPictureModel> picModelList) {
		
		List<String> picHeightPropList = new ArrayList<String>() ;
		
		if(CollectionUtils.isEmpty(picModelList)) {
			return picHeightPropList ;
		}
		
		for(DcPictureModel model : picModelList){
			Dimension de = model.getDimension() ;
			if(de == null){
				picHeightPropList.add("0") ;
			} else {
				int heightProp = getItemPicHeightProp(de) ;
				picHeightPropList.add(String.valueOf(heightProp)) ;
			}
		}
		
		return picHeightPropList ;
	}
	
	public static int getItemPicHeightProp(Dimension de){
		if(de == null){
			return 0 ;
		}
		double height = de.getHeight() ;
		double width = de.getWidth() ;
		int heightProp = (int)((height * 100) / width) ;
		return heightProp ;
	}
	
	/**
	 * 解析picUrls
	 * @param picUrls
	 * @return
	 */
	public static List<DcItemPicModel> parsePicUrls(String picUrls){
		if(StringUtils.isBlank(picUrls)){
			return new ArrayList<DcItemPicModel>() ;
		}
		if(JSONUtils.mayBeJSON(picUrls)) {
			 DcItemPicModel[] arr = JacksonHelper.fromJSON(picUrls, DcItemPicModel[].class) ;
			 return Arrays.asList(arr) ;
		} else {
			List<String> picUrlList = ArrayStringUtils.toList(picUrls) ;
			List<DcItemPicModel> modelList = new ArrayList<DcItemPicModel>() ;
			for(String str : picUrlList){
				DcItemPicModel model = new DcItemPicModel() ;
				model.setUrl(str) ;
				modelList.add(model) ;
			}
			return modelList ;
		}
	}
	
	public static void parsePicUrls2ModelList(DcItemDTO itemDTO , List<String> picUrlList) {
		List<DcItemPicModel> picModelList = new ArrayList<DcItemPicModel>() ;
		//处理图片
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
					log.error(e.getMessage() , e) ;
				}
				
				picModelList.add(model) ;
			}
			itemDTO.setPicModelList(picModelList) ;
		}
	}
	
	public static String getPictureAbsoluteUrl(String picUrl , PictureSizeEnums size) {
		
		String findPicUrl = PictureUtils.findPic(picUrl, size) ;
		
		if(StringUtils.startsWithIgnoreCase(findPicUrl, Constant.HTTP)){
			return findPicUrl ;
		}
		
		return DefaultUriService.getFactoryURI(URIConstant.DCOME_ITEM_UPLOADED_SERVER) +  findPicUrl ;	
	}
	
}