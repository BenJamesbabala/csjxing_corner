package com.doucome.corner.biz.dcome.cache;

import com.doucome.corner.biz.dcome.model.DcQIndexConfigDTO;

/**
 * ��������ѡ��ҳ�Ļ���
 * @author langben 2012-8-9
 *
 */
public interface DcQIndexConfigCache {

	/**
	 * ���û���
	 * @param config
	 */
	void set(DcQIndexConfigDTO config) ;
	
	/**
	 * ��ȡ
	 * @param sysId
	 * @return
	 */
	DcQIndexConfigDTO get(Long sysId , String puStatus) ;
	
	/**
	 * �Ƴ�
	 * @param sysId
	 */
	void remove(Long sysId,String puStatus) ;
}
