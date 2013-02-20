package com.doucome.corner.biz.core.utils;

import com.doucome.corner.biz.core.enums.TaobaoPicEnums;

public class TaobaoPicUtils {

	/**
	 * 根据原始图片找到对应图片
	 * @param picUrl
	 * @param type
	 * @return
	 */
	public static String findPic(String picUrl , TaobaoPicEnums type){
		if(type == null){
			return picUrl ;
		}
		return picUrl + "_" + type.getName() + ".jpg" ;
	}
}
