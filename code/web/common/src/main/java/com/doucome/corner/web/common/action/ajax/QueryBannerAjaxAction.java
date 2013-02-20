package com.doucome.corner.web.common.action.ajax;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.enums.BannerStatusEnums;
import com.doucome.corner.biz.core.service.BannerConfigService;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;
import com.doucome.corner.web.common.action.BasicAction;
import com.doucome.corner.web.common.model.JsonModel;

/**
 * ²éÑ¯banner
 * @author langben 2012-10-29
 *
 */
public class QueryBannerAjaxAction extends BasicAction {

	private JsonModel<Map<String,BannerConfigDO>> json = new JsonModel<Map<String,BannerConfigDO>>(); 
	
	/**
	 * ¸ù¾Ý
	 */
	private String bannerIds ;
	
	@Autowired
	private BannerConfigService bannerConfigService ;
	
	@Override
	public String execute() throws Exception {
		
		if(StringUtils.isBlank(bannerIds)) {
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("banner.config.bannerIds.required") ;
			return SUCCESS ;
		}
		
		List<String> bannerIdList = ArrayStringUtils.toList(bannerIds) ;
		
		if(CollectionUtils.isEmpty(bannerIdList)) {
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("banner.config.bannerIds.required") ;
			return SUCCESS ;
		}
		
		Map<String,BannerConfigDO> bannerMap = new HashMap<String,BannerConfigDO>() ;
		
		for(String bannerId : bannerIdList){
			if(StringUtils.isBlank(bannerId)){
				continue ;
			}
			BannerConfigDO bannerConfig = bannerConfigService.getConfigByBannerId(bannerId) ;
			
			if(bannerConfig == null){
				continue ;
			}
			
			String status = bannerConfig.getStatus() ;
			BannerStatusEnums statusEnums = BannerStatusEnums.getInstance(status) ;
			if(statusEnums == BannerStatusEnums.NORMAL){
				bannerMap.put(bannerConfig.getBannerId(), bannerConfig) ;
			}
		}
				
		json.setCode(JsonModel.CODE_SUCCESS) ;
		json.setData(bannerMap) ;
		return SUCCESS ;
	}

	public JsonModel<Map<String,BannerConfigDO>> getJson() {
		return json;
	}

	public void setBannerIds(String bannerIds) {
		this.bannerIds = bannerIds;
	}
	
}
