package com.doucome.corner.biz.apps.namefate.utils;

import com.doucome.corner.biz.apps.namefate.model.NamefateUserDTO;

public class NamefateUserUtils {

	public static Long getUserId (NamefateUserDTO user){
		if(user == null){
			return null ;
		}
		return user.getUserId() ;
	}
	
	public static String getUserNick(NamefateUserDTO user) {
		if(user == null){
			return null ;
		}
		return user.getUserNick() ;
	}
}
