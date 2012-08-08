package com.doucome.corner.biz.core.utils;

import org.apache.commons.io.FilenameUtils;

import com.doucome.corner.biz.core.enums.PictureSizeEnums;

/**
 * ͼƬ����
 * @author shenjia.caosj 2012-7-24
 *
 */
public class PictureUtils {

	/**
	 * ����ԭʼͼƬ�ҵ���ӦͼƬ
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
