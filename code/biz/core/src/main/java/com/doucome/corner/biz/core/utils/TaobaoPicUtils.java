package com.doucome.corner.biz.core.utils;

import com.doucome.corner.biz.core.enums.TaobaoPicEnums;

public class TaobaoPicUtils {

	/**
	 * ����ԭʼͼƬ�ҵ���ӦͼƬ
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
