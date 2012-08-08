package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.dcome.model.DcSystemDTO;

/**
 * 豆蔻系统
 * @author shenjia.caosj 2012-7-26
 *
 */
public interface DcSystemService {

	/**
	 * 查询所有的系统
	 * @return
	 */
	List<DcSystemDTO> getSystems() ;
	
	/**
	 * 根据ID查询系统
	 * @param id
	 * @return
	 */
	DcSystemDTO getSystemById(Long id) ;
}
