package com.doucome.corner.biz.dcome.cache;

import java.util.List;

import com.doucome.corner.biz.dcome.model.DcWinnerGameConfigDTO;

public interface DcWinnerGameConfigCache {

	/**
	 * ��ȡ����
	 * @return
	 */
	List<DcWinnerGameConfigDTO> get() ;
	
	/**
	 * ���û���
	 * @param itemList
	 */
	void set(List<DcWinnerGameConfigDTO> configList) ;
}
