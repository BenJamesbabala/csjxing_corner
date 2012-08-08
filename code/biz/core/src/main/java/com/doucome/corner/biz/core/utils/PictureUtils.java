package com.doucome.corner.biz.core.utils;

import org.apache.commons.io.FilenameUtils;

import com.doucome.corner.biz.core.enums.PictureSizeEnums;

/**
 * 图片工具
 * @author shenjia.caosj 2012-7-24
 *
 */
public class PictureUtils {

	/**
	 * 根据原始图片找到对应图片
	 * @param picUrl
	 * @param type
	 * @return
	 */
	public static String findPic(String picUrl , PictureSizeEnums type){
		if(type == null){
			return picUrl ;
		}
		
		String ext = FilenameUtils.getExtension(picUrl) ;
		
		return picUrl + "_" + type.getName() + ".jpg" ;
	}
	
}
