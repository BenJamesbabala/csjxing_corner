package com.doucome.corner.biz.dcome.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.dal.model.KeyValuePair;
import com.doucome.corner.biz.dcome.model.DcWinnerGameConfigDTO;

/**
 * 
 * @author langben 2012-12-14
 *
 */
public class DcWinnerGameUtils {

	/**
	 * 把List 转换成 Map
	 * @param configList
	 * @return
	 */
	public static Map<String,DcWinnerGameConfigDTO> conventToMap(List<DcWinnerGameConfigDTO> configList){
		Map<String,DcWinnerGameConfigDTO> map = new HashMap<String,DcWinnerGameConfigDTO>() ;
		if(CollectionUtils.isNotEmpty(configList)){
			for(DcWinnerGameConfigDTO dto : configList){
				map.put(dto.getCardName(), dto) ;
			}
		}
		return map ;
	}
	
	/**
	 * 解析下注的参数
	 * @param betParam
	 * @return
	 */
	public static List<KeyValuePair> parseBetParam(String betParam) {
		
		String[] paramSplit = StringUtils.split(betParam , ",") ;
		
		List<KeyValuePair> list = new ArrayList<KeyValuePair>() ;
		
		if(ArrayUtils.isEmpty(paramSplit)) {
			return list ;
		}
				
		for(String str : paramSplit){
			if(StringUtils.isNotBlank(str)){
				String[] keyValue = StringUtils.split(str , ":") ;
				if(ArrayUtils.isNotEmpty(keyValue) && keyValue.length == 2 && StringUtils.isNumeric(keyValue[1])){
					KeyValuePair kv = new KeyValuePair() ;
					kv.setKey(keyValue[0]) ;
					kv.setValue(keyValue[1]) ;
					list.add(kv) ;
				}
			}
		}
		
		return list ;
	}
	
	/**
	 * 根据下注计算需要消耗多少积分
	 * @param betParamList
	 * @return
	 */
	public static int calcIntegral(List<KeyValuePair> betParamList){
		
		if(CollectionUtils.isEmpty(betParamList)){
			return 0 ;
		}
		
		int totalBetCount = 0 ;
		
		for(KeyValuePair kv : betParamList){
			if(kv != null){
				String betCount = kv.getValue() ;
				if(StringUtils.isNumeric(betCount)){
					totalBetCount += Integer.valueOf(betCount) ;
				}
			}
		}
		
		return totalBetCount * 5 ; //一次5积分
	}
	
	public static int calcTotalWinScore(List<KeyValuePair> betParamList , DcWinnerGameConfigDTO winConfig){
		String winCardName = winConfig.getCardName() ;
		for(KeyValuePair kv : betParamList){
			if(StringUtils.equals(kv.getKey() , winCardName)){
				
				int betCount = Integer.valueOf(kv.getValue()) ;
				return betCount * winConfig.getScore() ;
				
			}
		}
		
		return 0 ;
		
	}
}
