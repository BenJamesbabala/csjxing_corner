package com.doucome.corner.biz.core.service.upyun;

import com.doucome.corner.biz.core.exception.UpyunException;
import com.doucome.corner.biz.core.service.upyun.model.UpyunDataEntity;

/**
 * ���Ľӿڷ�װ
 * @author langben 2012-7-22
 *
 */
public interface UpYunService {

	/**
	 * ��URL����ͼƬ��ָ������·��
	 * @param imgUrl
	 * @param picPath
	 */
	void upload(UpyunDataEntity upyunEntity) throws UpyunException ;
	
	/**
	* ɾ���ļ�
	* @param path �ļ�·���������ļ�����
	* return true or false
	*/
	boolean deleteFile(String path) ;
	
}
