package com.doucome.corner.biz.core.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.enums.PictureSizeEnums;

/**
 * ͼƬ����
 * @author langben 2012-7-24
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
		if(type == PictureSizeEnums.NONE){
			return picUrl ;
		}
		return picUrl + "_" + type.getName() + ".jpg" ;
	}
	
	public static String findOriginalPicUrl(String picUrl) {
		if (StringUtils.isEmpty(picUrl)) {
			return picUrl;
		}
		for (PictureSizeEnums sizeEnums: PictureSizeEnums.values()) {
			String suffix = "_" + sizeEnums.getName() + ".jpg";
			if (picUrl.endsWith(suffix)) {
				int index = picUrl.indexOf(suffix);
				String temp = picUrl.substring(0, index);
				if (!temp.endsWith(".jpg")) {
					return picUrl;
				}
				return temp;
			}
		}
		return picUrl;
	}
	
}
