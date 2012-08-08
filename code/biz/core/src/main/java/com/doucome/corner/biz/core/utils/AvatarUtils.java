package com.doucome.corner.biz.core.utils;

import org.apache.commons.lang.StringUtils;

/**
 * �û�ͷ�񹤾�
 * @author shenjia.caosj 2012-7-29
 *
 */
public class AvatarUtils {

	/**
	 * ����UserId��ȡͷ�����·��
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
	
	public static void main(String[] args) {
		System.out.println(buildAvatarPath(12L));
	}
}
