package com.doucome.corner.biz.core.taobao.constant;

import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;

/**
 * 淘客店铺推广字段常量
 * @see {@link TaobaokeItemDTO }
 * @author shenjia.caosj 2012-2-24
 *
 */
public class TaokeItemConst {

	/**
	 * 淘宝客商品id(注意：iid近期即将废弃，请用num_iid参数) ,比如： 234
	 * 
	 */
	public static final String iid = "iid" ;
	
	/**
	 * 淘宝客商品数字id , 比如： 123
	 */
	public static final String num_iid = "num_iid" ;
	
	/**
	 * 商品title 宝贝名称
	 */
	public static final String title = "title" ;
	

	/**
	 * 卖家昵称 , 比如：jayzhou
	 */
	public static final String nick = "nick" ;
	
	/**
	 * 图片url ,
	 * 比如：http://img01.taobaocdn.com/bao/uploaded/i1/T1GM8KXkheXXXz9q_b_093149
	 * .jpg
	 */
	public static final String pic_url = "pic_url" ;
	
	/**
	 * 商品价格 , 比如：12.15
	 */
	public static final String price = "price" ;
	
	/**
	 * 推广点击url
	 * ,比如：http://s.click.taobao.com/.....&p=mm_15999136_0_0&n=19&u=12001469
	 */
	public static final String click_url = "click_url" ;
	
	/**
	 * 淘宝客佣金 ,比如：12.15
	 */
	public static final String commission = "commission" ;
	
	/**
	 * 淘宝客佣金比率，比如：1234.00代表12.34% ,比如：500.00
	 */
	public static final String commission_rate = "commission_rate" ;
	
	/**
	 * 累计成交量.注：返回的数据是30天内累计推广量 ,比如：123
	 */
	public static final String commission_num = "commission_num" ;
	
	/**
	 * 累计总支出佣金量,比如：12.15
	 */
	public static final String commission_volume = "commission_volume" ;
	
	/**
	 * 商品所在店铺的推广点击url
	 * ,比如：http://s.click.taobao.com/.....&p=mm_15999136_0_0&n=19&u=12001469
	 */
	public static final String shop_click_url = "shop_click_url" ; 
	
	/**
	 * 卖家信用等级 , 比如：12
	 */
	public static final String seller_credit_score = "seller_credit_score" ;
	
	/**
	 * 商品所在地 ,比如：北京
	 */
	public static final String item_location = "item_location" ;
	
	/**
	 * 30天内交易量,比如：20
	 */
	public static final String volume = "volume" ;
	
	
}
