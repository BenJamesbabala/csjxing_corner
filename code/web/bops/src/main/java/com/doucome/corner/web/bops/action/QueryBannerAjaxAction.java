package com.doucome.corner.web.bops.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.service.BannerConfigService;
import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;
import com.doucome.corner.web.bops.model.JsonModel;

@SuppressWarnings("serial")
public class QueryBannerAjaxAction extends BopsBasicAction {

	private static final Log log = LogFactory.getLog(BannerOperateAjaxAction.class) ;

	private JsonModel<BannerConfigDO> json = new JsonModel<BannerConfigDO>() ;
	
	private String bannerId ;
	
	@Autowired
	private BannerConfigService bannerConfigService ;
	
	@Override
	public String execute() throws Exception {
		
		if(StringUtils.isBlank(bannerId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("bops.banner.bannerId.required") ;
			return SUCCESS ;
		}
		
		try {
			BannerConfigDO bannerConfig = bannerConfigService.getConfigByBannerId(bannerId) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(bannerConfig) ;
			
		} catch (Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
			log.error(e.getMessage() , e) ;
		}
		
		return SUCCESS ;
	}

	public JsonModel<BannerConfigDO> getJson() {
		return json;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
	
}
