package com.doucome.corner.biz.core.service.upyun;

import java.io.File;

public interface UpYunService {

	/**
	 * �ϴ��ļ�
	 * @param path �ļ�·���������ļ�����
	 * @param file
	 * @return
	 */
	boolean uploadFile(String path ,  File in , boolean auto) ;
	
	/**
	* ɾ���ļ�
	* @param path �ļ�·���������ļ�����
	* return true or false
	*/
	boolean deleteFile(String path) ;
	
}
