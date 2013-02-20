package com.doucome.corner.web.common.model;

public class ResultModel<T> extends JsonModel<T> {
	/**
	 * .
	 */
	public static final String DETAIL_USERID_INVALID = "userid.invalid";
	/**
	 * 商品链接无效.
	 */
	public static final String DETAIL_URL_INVALID = "url.invalid";
	/**
	 * 商品图片链接无效
	 */
	public static final String DETAIL_PIC_URL_INVALID = "picurl.invalid";
	/**
	 * 商品价格高于活动限制.
	 */
	public static final String DETAIL_ITEMPRICE_MORE = "itemprice.more";
	/**
	 * 商品重复提交.
	 */
	public static final String DETAIL_ITEM_RESUBMIT = "item.resubmit";
	/**
	 * 商品不存在.
	 */
	public static final String DETAIL_ITEM_NOT_EXIST = "item.not.exist";
	/**
	 * 用户已ugc过此商品.
	 */
	public static final String DETAIL_USER_ITEM_EXIST = "user.item.exist";
	/**
	 * 活动未开始.
	 */
	public static final String DETAIL_PROM_NOT_START = "promotion.not.start";
	/**
	 * 活动已结束.
	 */
	public static final String DETAIL_PROM_ENDED = "promotion.ended";
	/**
	 * 重复参与活动.
	 */
	public static final String DETAIL_TAKEIN_REPEAT = "takein.repeat";
	/**
	 * 内部错误.
	 */
	public static final String DETAIL_INTERNAL_ERROR = "internal.error";
}
