package com.doucome.corner.biz.dcome.service;

import java.util.List;

import com.doucome.corner.biz.dcome.model.DcSystemDTO;

/**
 * ��ޢϵͳ
 * @author shenjia.caosj 2012-7-26
 *
 */
public interface DcSystemService {

	/**
	 * ��ѯ���е�ϵͳ
	 * @return
	 */
	List<DcSystemDTO> getSystems() ;
	
	/**
	 * ����ID��ѯϵͳ
	 * @param id
	 * @return
	 */
	DcSystemDTO getSystemById(Long id) ;
}
