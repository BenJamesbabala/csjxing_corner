package com.doucome.corner.biz.zhe.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.model.AlipayItemFacade;

public class SettleUtils {
	
	/**
	 * 
	 * @param alipayItems
	 */
	public static void resetPayAmount(List<AlipayItemDO> alipayItems) {
		for (AlipayItemDO alipayItem: alipayItems) {
			alipayItem.setAmount(new BigDecimal("0.01"));
		}
	}
	
	/**
	 * 
	 * @param alipayItems
	 * @return
	 */
	public static List<AlipayItemFacade> merge(List<AlipayItemDO> alipayItems) {
		if (alipayItems == null || alipayItems.size() == 0) {
			return new ArrayList<AlipayItemFacade>();
		}
		Comparator<AlipayItemDO> comparator = new Comparator<AlipayItemDO>() {

			@Override
			public int compare(AlipayItemDO arg0, AlipayItemDO arg1) {
				return arg0.getAccount().compareToIgnoreCase(arg1.getAccount());
			}
		};
		Collections.sort(alipayItems, comparator);
		List<AlipayItemFacade> payItems = new ArrayList<AlipayItemFacade>();
		AlipayItemFacade payItem = convert(alipayItems.get(0));
		payItems.add(payItem);
		AlipayItemDO temp = null;
		for (int i = 1; i < alipayItems.size(); i++) {
			temp = alipayItems.get(i);
			payItem = payItems.get(payItems.size() - 1);
			if (temp.getAccount().equalsIgnoreCase(payItem.getAccount())) {
				payItem.setAmount(payItem.getAmount().add(temp.getAmount()));
				payItem.addIds(temp.getIds().toString());
			} else {
				payItem = convert(alipayItems.get(i));
				payItems.add(payItem);
			}
		}
		return payItems;
	}
	
	private static AlipayItemFacade convert(AlipayItemDO alipayItemDO) {
		AlipayItemFacade payItem = new AlipayItemFacade();
		payItem.addIds(alipayItemDO.getIds().toString());
		payItem.setAccount(alipayItemDO.getAccount());
		payItem.setAmount(alipayItemDO.getAmount());
		return payItem;
	}
	
	public static <V extends Object> List<V> difference(List<V> list1, List<V> list2) {
		List<V> result = new ArrayList<V>();
		if (list1 == null || list1.size() == 0) {
			return result;
		}
		if (list2 == null || list2.size() == 0) {
			result.addAll(list1);
			return result;
		}
		Set<V> tempSet = new HashSet<V>();
		tempSet.addAll(list2);
		int size = tempSet.size();
		for (V temp : list1) {
			tempSet.add(temp);
			if (tempSet.size() != size) {
				size = tempSet.size();
				result.add(temp);
			}
		}
		return result;
	}
	
	public static List<Long> split(String strs, String splitChar) {
		List<Long> result = new ArrayList<Long>();
		if (StringUtils.isEmpty(strs)) {
			return result; 
		}
		String[] temps = strs.split(splitChar);
		for(String temp : temps) {
			if (StringUtils.isNotEmpty(temp)) {
				Long id = valueOf(temp);
				if (id != null) {
					result.add(id);
				}
			}
		}
		return result;
	}
	
	private static Long valueOf(String str) {
		try {
			return Long.valueOf(str);
		} catch(NumberFormatException e) {
			return null;
		}
	}
	
	public static List<DdzTaokeReportSettleDO> build(List<Long> settleIds) {
		List<DdzTaokeReportSettleDO> settleDOs = new ArrayList<DdzTaokeReportSettleDO>();
		if (settleIds == null || settleIds.size() == 0) {
			return settleDOs;
		}
		for(Long id: settleIds) {
			DdzTaokeReportSettleDO temp = new DdzTaokeReportSettleDO();
			temp.setId(id);
			settleDOs.add(temp);
		}
		return settleDOs;
	}
}