package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcWinnerGameConfigDO;

/**
 * �ϻ�������
 * @author langben 2012-12-14
 *
 */
public interface DcWinnerGameConfigDAO {

	/**
	 * ��ȡ��������
	 * @return
	 */
	List<DcWinnerGameConfigDO> queryConfigs() ;
	
	/**
	 * ����cardName��ȡ����
	 * @param cardName
	 * @return
	 */
	DcWinnerGameConfigDO queryConfigByCardName(String cardName) ;
}
