package com.doucome.corner.biz.dal.dcome;

import java.util.List;

import com.doucome.corner.biz.dal.dataobject.dcome.DcSystemDO;

/**
 * 豆蔻系统
 * @author langben 2012-7-21
 *
 */
public interface DcSystemDAO {
	
	/**
	 * 查询所有的系统
	 * @return
	 */
	List<DcSystemDO> querySystems() ;
	
	/**
	 * 根据ID查询系统
	 * @param id
	 * @return
	 */
	DcSystemDO querySystemById(Long id) ;
	
	
}
