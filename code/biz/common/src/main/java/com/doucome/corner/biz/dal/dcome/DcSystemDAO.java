package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcSystemDO;

/**
 * ��ޢϵͳ
 * @author langben 2012-7-21
 *
 */
public interface DcSystemDAO {
	
	/**
	 * ��ѯ���е�ϵͳ
	 * @return
	 */
	List<DcSystemDO> querySystems() ;
	
	/**
	 * ����ID��ѯϵͳ
	 * @param id
	 * @return
	 */
	DcSystemDO querySystemById(Long id) ;
	
	
}
