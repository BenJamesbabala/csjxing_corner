package com.doucome.corner.biz.core.service.upyun;

import java.io.File;

public interface UpYunService {

	/**
	 * 上传文件
	 * @param path 文件路径（包含文件名）
	 * @param file
	 * @return
	 */
	boolean uploadFile(String path ,  File in , boolean auto) ;
	
	/**
	* 删除文件
	* @param path 文件路径（包含文件名）
	* return true or false
	*/
	boolean deleteFile(String path) ;
	
}
