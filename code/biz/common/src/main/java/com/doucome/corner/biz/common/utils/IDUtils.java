package com.doucome.corner.biz.common.utils;

/**
 * ���ݿ�ID����
 * @author langben 2012-8-10
 *
 */
public class IDUtils {

	public static boolean isCorrect(Long id){
		if(id == null){
			return false ;
		}
		if(id < 0L){
			return false ;
		}
		return true ;
	}
	
	public static boolean isNotCorrect(Long id) {
		return !isCorrect(id) ;
	}
	
	public static boolean isCorrect(Integer id){
		if(id == null){
			return false ;
		}
		if(id < 0L){
			return false ;
		}
		return true ;
	}
	
	public static boolean isNotCorrect(Integer id) {
		return !isCorrect(id) ;
	}
}
