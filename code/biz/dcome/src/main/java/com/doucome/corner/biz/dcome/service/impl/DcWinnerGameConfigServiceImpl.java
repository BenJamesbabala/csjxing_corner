package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcWinnerGameConfigDO;
import com.doucome.corner.biz.dal.dcome.DcWinnerGameConfigDAO;
import com.doucome.corner.biz.dcome.cache.DcWinnerGameConfigCache;
import com.doucome.corner.biz.dcome.model.DcWinnerGameConfigDTO;
import com.doucome.corner.biz.dcome.service.DcWinnerGameConfigService;

public class DcWinnerGameConfigServiceImpl implements DcWinnerGameConfigService {

	@Autowired
	private DcWinnerGameConfigDAO dcWinnerGameConfigDAO ;
	
	@Autowired
	private DcWinnerGameConfigCache dcWinnerGameConfigCache ;
	
	@Override
	public List<DcWinnerGameConfigDTO> getConfigs() {
		List<DcWinnerGameConfigDTO> dtoList = dcWinnerGameConfigCache.get() ;
		if(dtoList == null){
			List<DcWinnerGameConfigDO> DOList = dcWinnerGameConfigDAO.queryConfigs() ;
			
			dtoList = new ArrayList<DcWinnerGameConfigDTO>() ;
			if(CollectionUtils.isNotEmpty(DOList)){
				for(DcWinnerGameConfigDO DO : DOList){
					dtoList.add(new DcWinnerGameConfigDTO(DO)) ;
				}
			}
		}
		return dtoList ;
	}

	@Override
	public DcWinnerGameConfigDTO getConfigByCardName(String cardName) {
		DcWinnerGameConfigDO DO = dcWinnerGameConfigDAO.queryConfigByCardName(cardName) ;
		if(DO == null){
			return null ;
		}
		return new DcWinnerGameConfigDTO(DO) ;
	}

}
