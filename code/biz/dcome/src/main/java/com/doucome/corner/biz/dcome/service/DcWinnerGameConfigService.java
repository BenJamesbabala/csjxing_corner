package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.dcome.model.DcWinnerGameConfigDTO;

public interface DcWinnerGameConfigService {

	/**
	 * ��ȡ��������
	 * @return
	 */
	List<DcWinnerGameConfigDTO> getConfigs() ;
	
	/**
	 * ����cardName��ȡ����
	 * @param cardName
	 * @return
	 */
	DcWinnerGameConfigDTO getConfigByCardName(String cardName) ;
	
}
