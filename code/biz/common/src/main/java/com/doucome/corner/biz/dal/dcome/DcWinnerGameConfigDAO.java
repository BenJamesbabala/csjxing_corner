package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcWinnerGameConfigDO;

/**
 * 老虎机配置
 * @author langben 2012-12-14
 *
 */
public interface DcWinnerGameConfigDAO {

	/**
	 * 获取所有配置
	 * @return
	 */
	List<DcWinnerGameConfigDO> queryConfigs() ;
	
	/**
	 * 根据cardName读取配置
	 * @param cardName
	 * @return
	 */
	DcWinnerGameConfigDO queryConfigByCardName(String cardName) ;
}
