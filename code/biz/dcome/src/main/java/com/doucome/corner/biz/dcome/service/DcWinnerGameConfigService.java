package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.dcome.model.DcWinnerGameConfigDTO;

public interface DcWinnerGameConfigService {

	/**
	 * 获取所有配置
	 * @return
	 */
	List<DcWinnerGameConfigDTO> getConfigs() ;
	
	/**
	 * 根据cardName读取配置
	 * @param cardName
	 * @return
	 */
	DcWinnerGameConfigDTO getConfigByCardName(String cardName) ;
	
}
