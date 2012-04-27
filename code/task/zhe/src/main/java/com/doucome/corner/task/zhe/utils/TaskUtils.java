package com.doucome.corner.task.zhe.utils;

import java.util.Calendar;
import java.util.Date;

import com.doucome.corner.biz.core.utils.DateUtils;

/**
 * Task Utils
 * @author shenjia.caosj 2012-4-11
 *
 */
public class TaskUtils {

	/**
	 * ͬ��������ʱ��
	 * @return
	 */
	public static Date getLastLateDate(){
		
		Calendar daydayBefore = Calendar.getInstance() ;
		daydayBefore.add(Calendar.DATE, -2) ;// ȡ���������
		Date date = daydayBefore.getTime() ;
		date = DateUtils.setTime(date, 0, 0, 0) ;
		return date ;
		
	}
}
