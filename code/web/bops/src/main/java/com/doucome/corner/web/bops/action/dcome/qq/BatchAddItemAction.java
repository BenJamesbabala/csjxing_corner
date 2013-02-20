package com.doucome.corner.web.bops.action.dcome.qq;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.service.taobao.TaobaoServiceDecorator;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.fields.TaobaoFields;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.taobao.utils.TaobaoItemUtils;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.dcome.business.DcItemBO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcTaobaoService;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;
import com.doucome.corner.web.bops.action.BopsBasicAction;

/**
 * 批量添加商品
 * @author langben 2012-11-18
 *
 */
@SuppressWarnings("serial")
public class BatchAddItemAction extends BopsBasicAction {
	
	private static final Log log = LogFactory.getLog(BatchAddItemAction.class) ;

	@Autowired
	private TaobaoServiceDecorator taobaoServiceDecorator ;
	
	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcTaobaoService dcTaobaoService ;
	
	@Autowired
	private DcItemBO dcItemBO ;
	
	
	
	private String numIids ;
	
	private List<String> numIidList ;
	
	private Map<Long,TaobaoItemDTO> taobaoItemMap = new HashMap<Long,TaobaoItemDTO>();
	
	private Map<Long,TaobaokeItemDTO> taobaokeItemMap = new HashMap<Long,TaobaokeItemDTO>();
	
	private List<TaobaoItemDTO> taobaoItemList = null ;
	
	@Override
	public String execute() throws Exception {
		
		if(StringUtils.isBlank(numIids)){
			return INPUT ;
		}
		
		String[] numIidArray = ArrayStringUtils.toArray(numIids) ;
		
		taobaoItemList = taobaoServiceDecorator.getListItems(numIidArray, TaobaoFields.taobao_item_fields_full) ;
		String outCode = OutCodeUtils.encodeOutCode("dcitem", OutCodeEnums.SYSTEM) ;
		List<TaobaokeItemDTO> taobaokeItemList = taobaokeServiceDecorator.widgetConventItems(numIidArray, outCode, TaobaokeFields.taoke_item_fields) ;
		
		for(TaobaoItemDTO dto : taobaoItemList) {
			taobaoItemMap.put(dto.getNumIid(), dto) ;
		}
		
		for(TaobaokeItemDTO dto : taobaokeItemList) {
			taobaokeItemMap.put(dto.getNumIid(), dto) ;
		}
		
		return SUCCESS ;
	}
	
	/**
	 * piliang
	 * @return
	 */
	public String doAdd(){
		
		if(CollectionUtils.isEmpty(numIidList)){
			return INPUT ;
		}
		
		String[] tbItemIds = ArrayStringUtils.toStringArray(this.numIidList) ;
		
		String outCode = OutCodeUtils.encodeOutCode("dcitem", OutCodeEnums.SYSTEM) ;
		List<TaobaokeItemDTO> taobaokeItemList = dcTaobaoService.convertItems(tbItemIds, outCode) ;
		List<TaobaoItemDTO> taobaoItemList = dcTaobaoService.getTaobaoItems(tbItemIds) ;
		
		
		Map<Long,TaobaokeItemDTO> taobaokeItemMap = TaobaoItemUtils.convertTaobaokeItemToMap(taobaokeItemList) ;
		
		for(TaobaoItemDTO tbItem : taobaoItemList){
			try {
				Long numIid = tbItem.getNumIid() ;
				
				List<DcItemDTO> existsItemList = dcItemService.getItemsByExtlId(String.valueOf(numIid)) ;
				
				if(CollectionUtils.isEmpty(existsItemList)) {
				
					TaobaokeItemDTO taokeDTO = taobaokeItemMap.get(numIid) ;
					
					DcItemDTO dcItemDTO = DcItemUtils.mergeInfoTo(taokeDTO, tbItem) ;
					
					List<String> picUrlList = TaobaoItemUtils.getItemImgUrls(tbItem) ;
					
					try {
						BigDecimal itemPromPrice = dcTaobaoService.getItemPromoPrice(numIid) ;
						dcItemDTO.setItemPromPrice(itemPromPrice) ;
					} catch (Exception e){
						log.error(e.getMessage() , e) ;
					}
					
					dcItemBO.createPgcItem(dcItemDTO , picUrlList ) ;
				
				} else {
					for(DcItemDTO dto : existsItemList){
						dcItemService.updateDisplayOrderById(dto.getId()) ;
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage() , e) ;
			}
		}
		
		return SUCCESS ;
	}

	public void setNumIids(String numIids) {
		this.numIids = numIids;
	}

	public Map<Long, TaobaoItemDTO> getTaobaoItemMap() {
		return taobaoItemMap;
	}

	public Map<Long, TaobaokeItemDTO> getTaobaokeItemMap() {
		return taobaokeItemMap;
	}

	public List<TaobaoItemDTO> getTaobaoItemList() {
		return taobaoItemList;
	}

	public void setNumIidList(List<String> numIidList) {
		this.numIidList = numIidList;
	}
	
	

}
