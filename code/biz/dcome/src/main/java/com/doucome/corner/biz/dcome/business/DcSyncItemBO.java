package com.doucome.corner.biz.dcome.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.exception.TaobaoRemoteException;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.enums.TaobaoItemApproveStatusEnums;
import com.doucome.corner.biz.core.taobao.utils.TaobaoItemUtils;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.enums.DcItemSourceEnum;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.enums.DcYesOrNoEnum;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcTaobaoService;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;

/**
 * 同步DcItem信息，包括
 * <ul>
 * 	<li>商品价格</li>
 * 	<li>佣金比例</li>
 * 	<li>促销价格</li>
 * 	<li>是否下架</li>
 * </ul>
 * @author langben 2012-10-31
 *
 */
public class DcSyncItemBO {
	
	private Log log = LogFactory.getLog(DcSyncItemBO.class) ;
	
	@Autowired
	private DcTaobaoService dcTaobaoService ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	/**
	 * 
	 * @param itemIdList 需控制一个批次大小
	 */
	public void updateItems(List<DcItemDTO> itemDTOList) {
		
		List<String> taobaoIdList = new ArrayList<String>() ;
		
		if(CollectionUtils.isEmpty(itemDTOList)){
			return ;
		}
		//转成DO
		List<DcItemDO> itemList = new ArrayList<DcItemDO>() ;
		for(DcItemDTO dto : itemDTOList){
			itemList.add(dto.toItem()) ;
		}
		for(DcItemDO dcItem : itemList){
			if(dcItem == null){
				continue ;
			}
			
			//下架的商品不处理
			if(DcItemStatusEnum.getInstance(dcItem.getStatus()) != DcItemStatusEnum.NORMAL){
				continue ;
			}
			
			DcItemSourceEnum itemSource = DcItemSourceEnum.get(dcItem.getSource()) ;
			//不是淘宝的商品不处理
			if(itemSource != DcItemSourceEnum.TAOBAO && itemSource != DcItemSourceEnum.TMALL) {
				continue ;
			}
			
			String taobaoItemId = dcItem.getNativeId() ;
			
			//淘宝itemId不合规范的不处理
			if(StringUtils.isBlank(taobaoItemId) || !StringUtils.isNumeric(taobaoItemId)){
				continue ;
			}
			
			//只处理PGC的
			DcItemGenWayEnums genWay = DcItemGenWayEnums.toEnum(dcItem.getGenWay()) ;
			if(genWay != DcItemGenWayEnums.PROFESSIONAL) {
				continue ;
			}
			
			//未过期的商品不处理
//			if(!DcItemUtils.isItemExpired(dcItem)){
//				continue ;
//			}
			
			try {
				taobaoIdList.add(taobaoItemId) ;
			} catch (NumberFormatException e){
				log.error(e.getMessage() , e) ;
				continue ;
			}
			
		} //end for
		
		if(CollectionUtils.isEmpty(taobaoIdList)){
			return ;
		}
		
		String outCode = OutCodeUtils.encodeOutCode("dcautoupdt", OutCodeEnums.SYSTEM) ;
		try {
			String[] taobaoIdArray = ArrayStringUtils.toStringArray(taobaoIdList) ;
			List<TaobaoItemDTO> taobaoItemList = dcTaobaoService.getTaobaoItems(taobaoIdArray) ;
			List<TaobaokeItemDTO> taokeItemList = dcTaobaoService.convertItems(taobaoIdArray, outCode) ;
			
			
			//转换成Map
			Map<Long , TaobaoItemDTO> taobaoItemMap = convertTaobaoList2Map(taobaoItemList) ;
			Map<Long , TaobaokeItemDTO> taokeItemMap = convertTaokeList2Map(taokeItemList) ;
			
			
			for(DcItemDO item : itemList){
				
				try {
					Long taobaoItemId = Long.valueOf(item.getNativeId()) ;
					TaobaoItemDTO taobaoItem = taobaoItemMap.get(taobaoItemId) ;
					TaobaokeItemDTO taokeItem = taokeItemMap.get(taobaoItemId) ;
					
					if(taobaoItem != null){ //商品
						//价格
						item.setItemPrice(taobaoItem.getPrice()) ;
						//是否包邮
						boolean isFreePostage = TaobaoItemUtils.isFreePostage(taobaoItem) ;
						item.setPostalEnable(isFreePostage ? DcYesOrNoEnum.YES.getValue() : DcYesOrNoEnum.NO.getValue()) ;
						//是否下架
						boolean onSale = TaobaoItemApproveStatusEnums.getInstance(taobaoItem.getApproveStatus()) == TaobaoItemApproveStatusEnums.onsale ;
						if(!onSale){ //下架商品
							item.setStatus(DcItemStatusEnum.DISABLE.getValue()) ;
						}
					} else { //商品不存在或下架
						item.setStatus(DcItemStatusEnum.DISABLE.getValue()) ;
					}
					
					if(taokeItem != null){
						
						//转换折扣
						item.setCommissionRate(DcItemUtils.convertTaokeItemDiscount(taokeItem.getCommissionRate())) ;
						item.setClickUrl(taokeItem.getClickUrl()) ;
					} else {
						item.setCommissionRate(null) ;
					}
					
					if(taobaoItem != null){ //查询促销价格
						try {
							BigDecimal itemPromPrice = dcTaobaoService.getItemPromoPrice(taobaoItemId) ;
							item.setItemPromPrice(itemPromPrice) ;
						} catch (TaobaoRemoteException e) {
							log.error(e.getMessage() , e) ;
						}
					}
					
				} catch(Exception e){
					log.error(e.getMessage() , e) ;
				}
			}
			//end for(DcItemDTO itemDTO : itemList){
			
		} catch (TaobaoRemoteException e){
			log.error(e.getMessage() , e) ;
		}
		
		//批量更新
		int effectCount = dcItemService.batchUpdateSyncItemInfo(itemList) ;
	}
	
	private static Map<Long , TaobaokeItemDTO> convertTaokeList2Map(List<TaobaokeItemDTO> taokeItemList){
		Map<Long , TaobaokeItemDTO> taokeItemMap = new HashMap<Long , TaobaokeItemDTO>() ;
		if(CollectionUtils.isEmpty(taokeItemList)){
			return taokeItemMap ;
		}
		
		for(TaobaokeItemDTO dto : taokeItemList) {
			if(dto == null){
				continue ;
			}
			taokeItemMap.put(dto.getNumIid(), dto) ;
		}
		
		return taokeItemMap ;
	}
	
	private static Map<Long , TaobaoItemDTO> convertTaobaoList2Map(List<TaobaoItemDTO> taobaoItemList){
		Map<Long , TaobaoItemDTO> taokeItemMap = new HashMap<Long , TaobaoItemDTO>() ;
		if(CollectionUtils.isEmpty(taobaoItemList)){
			return taokeItemMap ;
		}
		
		for(TaobaoItemDTO dto : taobaoItemList) {
			if(dto == null){
				continue ;
			}
			taokeItemMap.put(dto.getNumIid(), dto) ;
		}
		
		return taokeItemMap ;
	}

}
