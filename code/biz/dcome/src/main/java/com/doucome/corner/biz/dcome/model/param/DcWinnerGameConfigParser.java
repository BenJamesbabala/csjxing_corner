package com.doucome.corner.biz.dcome.model.param;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dcome.constant.DcWinnerGameConstant;
import com.doucome.corner.biz.dcome.model.DcWinnerGameConfigDTO;

public class DcWinnerGameConfigParser {
	
	private static final Log log = LogFactory.getLog(DcWinnerGameConfigParser.class) ;
	
	public static final int MAX_RANGE = 10000 ;
		
	private DcWinnerGameConfigDTO cattleConfig  ;

	private List<Range> rangeList = new ArrayList<Range>();
	
	public DcWinnerGameConfigParser(List<DcWinnerGameConfigDTO> configList){
				
		if(CollectionUtils.isEmpty(configList)){
			return ;
		}
		
		int start = 0 ;
		for(DcWinnerGameConfigDTO config : configList){
			
			if(StringUtils.equals(config.getCardName() , DcWinnerGameConstant.CATTLE)){
				this.cattleConfig = config ;
				continue ;
			}
			
			int probability = NumberUtils.parseInt(config.getProbability()) ;
			if(probability <= 0){
				continue ;
			}
			int end = start + probability - 1 ;
			
			rangeList.add(new Range(start , end , config)) ;
			
			start = end + 1;
		}
		
		if(start > MAX_RANGE){
			throw new IllegalArgumentException("CONFIG OUT OF RANGE [" + start + "]") ;
		}
		
		rangeList.add(new Range(start , MAX_RANGE-1 , cattleConfig)) ;
	}
	
	public DcWinnerGameConfigDTO win(int num){
		for(Range ran : rangeList){
			if(num >= ran.getStart() && num <= ran.getEnd()){
				return ran.getConfig() ;
			}
		}
		log.error("cant win for num : " + num) ;
		return cattleConfig ;
	}
	
	public static class Range {
		
		public Range(int start , int end , DcWinnerGameConfigDTO config){
			this.start = start ;
			this.end = end ;
			this.config = config ;
		}
		
		private int start ;
		
		private int end ;
		
		private DcWinnerGameConfigDTO config ;

		public DcWinnerGameConfigDTO getConfig() {
			return config;
		}

		public void setConfig(DcWinnerGameConfigDTO config) {
			this.config = config;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}
		
		
	}
}
