package com.doucome.corner.web.bops.action.dcome.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.model.JsonModel;

/**
 * 看某个nativeId是否在数据库中存在
 * @author langben 2012-11-11
 *
 */
@SuppressWarnings("serial")
public class CheckDcItemExistsByNativeIdAjaxAction extends BopsBasicAction{

	private static final Log log = LogFactory.getLog(CheckDcItemExistsByNativeIdAjaxAction.class) ;

	private JsonModel<Map<String,Boolean>> json = new JsonModel<Map<String,Boolean>>() ;
	
	private String nativeIds ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Override
	public String execute() throws Exception {
		List<String> nativeIdList = ArrayStringUtils.toList(nativeIds) ;
		List<DcItemDTO> itemList = dcItemService.getItemsByNativeIds(nativeIdList) ;
		
		Map<String,Boolean> map = new HashMap<String,Boolean>() ;
		
		for(DcItemDTO item : itemList){
			if(item.getGenWay() == DcItemGenWayEnums.PROFESSIONAL){
				map.put(item.getNativeId(), true) ;
			}
		}
		
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(map) ;
		
		return SUCCESS ;
	}

	public JsonModel<Map<String, Boolean>> getJson() {
		return json;
	}

	public void setNativeIds(String nativeIds) {
		this.nativeIds = nativeIds;
	}
	
	
}
