package com.doucome.corner.biz.dcome.model.util;

import java.util.Calendar;
import java.util.Date;

import com.doucome.corner.biz.dcome.constant.DcPromotionItemConstant;
import com.doucome.corner.biz.dcome.model.DcPromotionItemDTO;

/**
 * 
 * @author langben 2012-9-2
 *
 */
public class DcPromotionItemUtils {

	/**
	 * ��ѯ��ǰСʱ�ж��ٿ�����ȡ��Ը��ֵ
	 * @param item
	 * @param hour
	 * @return
	 */
	public static int getCanDrawByHour(DcPromotionItemDTO item , int hour){
		if(!isDrawHour(hour)){
			return 0 ;
		}
		if(hour == 10){
			return item.getHour10() ;
		} else if(hour == 11){
			return item.getHour11() ;
		} else if(hour == 12){
			return item.getHour12() ;
		} else if(hour == 13){
			return item.getHour13() ;
		} else if(hour == 14) {
			return item.getHour14() ;
		} else if(hour == 15){
			return item.getHour15() ;
		} else if(hour == 16){
			return item.getHour16() ;
		} else if(hour == 17){
			return item.getHour17() ;
		} else if(hour == 18){
			return item.getHour18() ;
		} else if(hour == 19){
			return item.getHour19() ;
		} else if(hour == 20){
			return item.getHour20() ;
		} else if(hour == 21){
			return item.getHour21() ;
		} 
		return 0 ;
	}
	
	/**
	 * ��ǰ�Ƿ��ǿ�����ȡԸ���ǵ�ʱ��
	 * @param hour
	 * @return
	 */
	public static boolean isDrawHour(int hour){
		if(hour > DcPromotionItemConstant.MAX_HOUR || hour < DcPromotionItemConstant.MIN_HOUR) {
			return false ;
		}
		return true ;
	}
	
	/**
	 * ��ȡ��ǰ�����ʱ��.
	 * @return
	 */
	public static Date getCurPromotionEndTime() {
		Calendar endCal = Calendar.getInstance();
		int hour = endCal.get(Calendar.HOUR_OF_DAY);
		//���22���Ժ󣬽�ֹʱ����ǵڶ���22:00:00
		if (hour >= 22) {
			endCal.add(Calendar.DAY_OF_YEAR, 1);
		}
		endCal.set(Calendar.MILLISECOND, 0);
		endCal.set(Calendar.SECOND, 0);
		endCal.set(Calendar.MINUTE, 0);
		endCal.set(Calendar.HOUR_OF_DAY, 22);
		return endCal.getTime();
	}
	
	/**
	 * ��һ��������ȡԸ��ֵʣ��ʱ�䣨��λ�룩
	 * @return -1 ��һ��ʱ���δ֪
	 */
	public static int getNextDrawRemainSeconds(){
		Calendar cal = Calendar.getInstance() ;
		int hour = cal.get(Calendar.HOUR_OF_DAY) ;
		if(!isDrawHour(hour)){
			return -1 ;
		}
		int minutes = cal.get(Calendar.MINUTE) ;
		int seconds = cal.get(Calendar.SECOND) ;
		int remain = (60 - minutes - 1) * 60 + (60 - seconds) ;
		return remain ;
	}
	
	public static void main(String[] args) {
		System.out.println(getNextDrawRemainSeconds()) ;
	}
}
