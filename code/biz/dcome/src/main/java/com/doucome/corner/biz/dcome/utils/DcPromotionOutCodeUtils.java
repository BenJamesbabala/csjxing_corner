package com.doucome.corner.biz.dcome.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * 
 * @author langben 2012-8-28
 *
 */
public class DcPromotionOutCodeUtils {
	
	private static final String SEPARATOR = "Z" ;
	
	public static OutCodeData decodeOutCode(String outCodeStr) {
		if (StringUtils.isBlank(outCodeStr)) {
			return null;
		}
		String[] array = StringUtils.split(outCodeStr, SEPARATOR ); ///中间用'0'隔开
		if (CollectionUtils.size(array) <= 0) {
			return null;
		}

		String itemIdEncode = array[0];
		String userIdEncode = array[1];

		Long itemId = Long.parseLong(itemIdEncode, Character.MAX_RADIX);
		Long userId = Long.parseLong(userIdEncode, Character.MAX_RADIX);
		OutCodeData outCode = new OutCodeData();
		outCode.setItemId(itemId);
		outCode.setUserId(userId);
		return outCode;
	}

	public static String encodeOutCode(Long itemId , Long userId) {
		String userIdEncode = Long.toString(NumberUtils.parseLong(userId),
				Character.MAX_RADIX);
		String itemIdEncode = Long.toString(NumberUtils.parseLong(itemId),
				Character.MAX_RADIX);

		String outCode = itemIdEncode + SEPARATOR + userIdEncode;///中间用'0'隔开
		return outCode;
	}

	public static class OutCodeData extends AbstractModel {
		
		private static final long serialVersionUID = -6789604703741442917L;
		
		private Long userId;
		private Long itemId;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getItemId() {
			return itemId;
		}

		public void setItemId(Long itemId) {
			this.itemId = itemId;
		}

	}
	
	public static void main(String[] args) {
		OutCodeData  date = DcPromotionOutCodeUtils.decodeOutCode("25qzZ7q6") ;
		System.out.println(date);
	}
}
