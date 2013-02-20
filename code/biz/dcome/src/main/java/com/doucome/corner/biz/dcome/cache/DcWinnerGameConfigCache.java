package com.doucome.corner.biz.dcome.cache;

import java.util.List;

import com.doucome.corner.biz.dcome.model.DcWinnerGameConfigDTO;

public interface DcWinnerGameConfigCache {

	/**
	 * ªÒ»°ª∫¥Ê
	 * @return
	 */
	List<DcWinnerGameConfigDTO> get() ;
	
	/**
	 * …Ë÷√ª∫¥Ê
	 * @param itemList
	 */
	void set(List<DcWinnerGameConfigDTO> configList) ;
}
