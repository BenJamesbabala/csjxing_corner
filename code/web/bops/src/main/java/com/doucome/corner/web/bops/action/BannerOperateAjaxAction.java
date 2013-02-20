package com.doucome.corner.web.bops.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.enums.BannerStatusEnums;
import com.doucome.corner.biz.core.service.BannerConfigService;
import com.doucome.corner.biz.dal.dataobject.BannerConfigDO;
import com.doucome.corner.web.bops.model.JsonModel;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class BannerOperateAjaxAction extends BopsBasicAction implements ModelDriven<BannerConfigDO>{
	
	private static final Log log = LogFactory.getLog(BannerOperateAjaxAction.class) ;

	private JsonModel<Integer> json = new JsonModel<Integer>() ;
	
	private BannerConfigDO bannerConfig = new BannerConfigDO() ;
	
	@Autowired
	private BannerConfigService bannerConfigService ;
	
	/**
	 * 更新status(启用、禁用)
	 * @return
	 * @throws Exception
	 */
	public String updateStatus() throws Exception {
		
		if(StringUtils.isBlank(bannerConfig.getBannerId())){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("bops.banner.bannerId.required") ;
			return SUCCESS ;
		}
		
		BannerStatusEnums statusEnums = BannerStatusEnums.getInstance(bannerConfig.getStatus()) ;
		if(statusEnums == BannerStatusEnums.UNKNOWN){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("bops.banner.status.required") ;
			return SUCCESS ;
		}
		
		try {
			int effectCount = bannerConfigService.updateConfigStatusByBannerId(bannerConfig.getBannerId(), bannerConfig.getStatus()) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(effectCount) ;
		} catch (Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
			log.error(e.getMessage() , e) ;
		}
		return SUCCESS ;
	}
	
	public String update() throws Exception {
		if(StringUtils.isBlank(bannerConfig.getBannerId())){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("bops.banner.bannerId.required") ;
			return SUCCESS ;
		}
		
		if(StringUtils.isBlank(bannerConfig.getBannerPicUrl())){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("bops.banner.picUrl.required") ;
			return SUCCESS ;
		}
		
		try {
			int effectCount = bannerConfigService.updateConfigByBannerId(bannerConfig) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(effectCount) ;
		} catch (Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
			log.error(e.getMessage() , e) ;
		}
		
		return SUCCESS ;
	}
	
	public String updateBindEvent(){
		if(StringUtils.isBlank(bannerConfig.getBannerId())){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("bops.banner.bannerId.required") ;
			return SUCCESS ;
		}
		try {
			int effectCount = bannerConfigService.updateBindEventById(bannerConfig.getBannerId(), bannerConfig.getBindEvent(), bannerConfig.getBindEventFunction()) ;
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(effectCount) ;
		} catch (Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			json.setDetail(e.getMessage()) ;
			log.error(e.getMessage() , e) ;
		}
		return SUCCESS ;
	}

	@Override
	public BannerConfigDO getModel() {
		return bannerConfig ;
	}

	public JsonModel<Integer> getJson() {
		return json;
	}
	
	
}
