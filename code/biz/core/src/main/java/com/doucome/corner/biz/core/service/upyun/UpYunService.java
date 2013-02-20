package com.doucome.corner.biz.core.service.upyun;

import com.doucome.corner.biz.core.exception.UpyunException;
import com.doucome.corner.biz.core.service.upyun.model.UpyunDataEntity;

/**
 * 又拍接口封装
 * @author langben 2012-7-22
 *
 */
public interface UpYunService {

	/**
	 * 从URL拷贝图片到指定又拍路径
	 * @param imgUrl
	 * @param picPath
	 */
	void upload(UpyunDataEntity upyunEntity) throws UpyunException ;
	
	/**
	* 删除文件
	* @param path 文件路径（包含文件名）
	* return true or false
	*/
	boolean deleteFile(String path) ;
	
}
