package com.doucome.corner.biz.core.utils;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;

/**
 * 用户头像工具
 * @author langben 2012-7-29
 *
 */
public class AvatarUtils {

	/**
	 * 根据UserId获取头像相对路径
	 * @param userId
	 */
	public static String buildAvatarPath(Long userId) {
		String userIdStr = String.valueOf(userId) ;
		StringBuilder sb = new StringBuilder("/avatar/") ;
		for(int i=0 ;i<4 ;i++){
			String s = StringUtils.substring(userIdStr, i, i+1) ;
			if(StringUtils.isBlank(s)){
				s = "0" ;
			}
			sb.append(s).append("/") ;
		}
		sb.append(userIdStr).append(".jpg") ;
		return sb.toString() ;
	}
	
	public static String buildAvatarPath(String appName , Long userId) {
		String userIdStr = String.valueOf(userId) ;
		StringBuilder sb = new StringBuilder("/avatar/") ;
		sb.append(appName).append("/") ;
		for(int i=0 ;i<4 ;i++){
			String s = StringUtils.substring(userIdStr, i, i+1) ;
			if(StringUtils.isBlank(s)){
				s = "0" ;
			}
			sb.append(s).append("/") ;
		}
		sb.append(userIdStr).append(".jpg") ;
		return sb.toString() ;
	}
	
	public static String getAvatarUrl(Long userId, String picSize) {
		String baseURI = DefaultUriService.getFactoryURI(URIConstant.DCOME_PIC_UPLOADED_SERVER) ;
		String avatarPath = buildAvatarPath(userId) ;
		String avatarUrl = baseURI + avatarPath ;
		return PictureUtils.findPic(avatarUrl , PictureSizeEnums.toEnum(picSize)) ;
	}
	
	public static void main(String[] args) {
		System.out.println(buildAvatarPath(12L));
	}
}
