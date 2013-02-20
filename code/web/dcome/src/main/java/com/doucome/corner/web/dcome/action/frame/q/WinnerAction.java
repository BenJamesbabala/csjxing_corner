package com.doucome.corner.web.dcome.action.frame.q;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.DcWinnerGameConfigDTO;
import com.doucome.corner.biz.dcome.service.DcWinnerGameConfigService;
import com.doucome.corner.biz.dcome.utils.DcWinnerGameUtils;

/**
 * ÀÏ»¢»ú
 * @author langben 2012-12-14
 *
 */
@SuppressWarnings("serial")
public class WinnerAction extends QBasicAction{
	
	@Autowired
	private DcWinnerGameConfigService dcWinnerGameConfigService ;
	
	private DcUserDTO user ;
	
	private Map<String,DcWinnerGameConfigDTO> winnerConfigMap ;
	
	@Override
	public String execute() throws Exception {
		
		this.user = dcAuthz.getUser() ;
		
		if(user == null){
			return SUCCESS ;
		}
		
		List<DcWinnerGameConfigDTO> configList = dcWinnerGameConfigService.getConfigs() ;
		
		this.winnerConfigMap = DcWinnerGameUtils.conventToMap(configList) ;
		
		return SUCCESS ;
	}

	public DcUserDTO getUser() {
		return user;
	}

	public Map<String, DcWinnerGameConfigDTO> getWinnerConfigMap() {
		return winnerConfigMap;
	}
	
}
