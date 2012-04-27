package com.doucome.corner.biz.core.utils;

import org.apache.commons.lang.StringUtils;

/**
 * Array =&gt; String ���� String =&gt; Array
 * @author shenjia.caosj 2012-2-24
 *
 */
public class ArrayStringUtils {

	/**
	 * String��","Ϊ�ָ���Array
	 * @param str
	 * @return
	 */
	public static String[] toArray(String str){
		String[] arr = StringUtils.split(str, ",") ;
		return arr ;
	}
	
	/**
	 * ���ַ�������ת��","���ӵ��ַ���
	 * @param arr
	 * @return
	 */
	public static String toString(String[] arr){
		if(arr == null || arr.length == 0){
			return "" ;
		}
		
		StringBuilder strConcat = new StringBuilder() ; 
		for(int i=0 ;i<arr.length;i++){
			String str = arr[i] ;
			strConcat.append(str) ;
			if(i != arr.length-1){
				strConcat.append(",") ;
			}
		}
		return strConcat.toString() ;
	}
}
