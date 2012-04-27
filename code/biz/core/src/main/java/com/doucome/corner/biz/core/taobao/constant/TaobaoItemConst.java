package com.doucome.corner.biz.core.taobao.constant;


public class TaobaoItemConst {
	
	/**
	 * 商品url ,,http://item.taobao.com/item.htm?id=4947813209 	
	 */
	public static final String detail_url = "detail_url" ;
	
	/**
	 * 商品数字id
	 */
	public static final String num_iid = "num_iid" ;
	
	/**
	 * 商品标题,不能超过60字节
	 */
	public static final String title = "title" ;
	
	/**
	 * 卖家昵称
	 */
	public static final String nick = "nick" ;
	
	/**
	 * 商品类型(fixed:一口价;auction:拍卖)注：取消团购
	 */
	public static final String type = "type" ;
	
	/**
	 * 商品描述, 字数要大于5个字符，小于25000个字符,这是一个好商品 	
	 */
	public static final String desc = "desc" ;
	
	/**
	 * Item的发布时间，目前仅供taobao.item.add和taobao.item.get可用
	 */
	public static final String created = "created" ;
	
	/**
	 * 是否24小时闪电发货 
	 */
	public static final String is_lightning_consignment = "is_lightning_consignment" ;
	
	/**
	 * 是否分校，非分销商品：0，代销：1，经销：2
	 */
	public static final String is_fenxiao = "is_fenxiao" ;
	
	/**
	 * 商城返点比例，为5的倍数，最低0.5%
	 */
	public static final String auction_point = "auction_point" ;
	
	/**
	 * 属性值别名,比如颜色的自定义名称
	 */
	public static final String property_alias = "property_alias" ;
	
	/**
	 * 页面模板id
	 */
	public static final String template_id = "template_id" ;
	
	/**
	 * 售后服务ID,该字段仅在taobao.item.get接口中返回
	 */
	public static final String after_sale_id = "after_sale_id" ;
	
	/**
	 * 标示商品是否为新品。值含义：true-是，false-否。
	 */
	public static final String is_xinpin = "is_xinpin" ;
	
	/**
	 * 标识商品减库存的方式值含义：1-拍下减库存，2-付款减库存。
	 */
	public static final String sub_stock = "sub_stock" ;

	/**
	 * 用户内店宝贝装修模板id
	 */
	public static final String inner_shop_auction_template_id = "inner_shop_auction_template_id" ;
	
	/**
	 * 用户外店装修模板id
	 */
	public static final String outer_shop_auction_template_id = "outer_shop_auction_template_id" ;
	
	/**
	 * 	商品所属的叶子类目 id
	 */
	public static final String cid = "cid" ;
	
	/**
	 * 商品所属的店铺内卖家自定义类目列表
	 */
	public static final String seller_cids = "seller_cids" ;
	
	/**
	 * 商品属性 格式：pid:vid;pid:vid
	 */
	public static final String props = "props" ;
	
	/**
	 * 用户自行输入的类目属性ID串。结构："pid1,pid2,pid3"，如："20000"（表示品牌） 注：通常一个类目下用户可输入的关键属性不超过1个。
	 */
	public static final String input_pids = "input_pids" ;
	
	/**
	 * 耐克;耐克系列;科比系列;科比系列;2K5 	用户自行输入的子属性名和属性值，结构:"父属性值;一级子属性名;一级子属性值;二级子属性名;自定义输入值,....",如：“耐克;耐克系列;科比系列;科比系列;2K5”，input_str需要与input_pids一一对应，注：通常一个类目下用户可输入的关键属性不超过1个。所有属性别名加起来不能超过 3999字节。
	 */
	public static final String input_str = "input_str" ;
	
	/**
	 * 	商品主图片地址
	 */
	public static final String pic_url = "pic_url" ;
	
	/**
	 * 商品数量
	 */
	public static final String num = "num" ;
	
	/**
	 * 有效期,7或者14（默认是14天）
	 */
	public static final String valid_thru = "valid_thru" ;
	
	/**
	 * 2009-10-22 14:22:06 	上架时间（格式：yyyy-MM-dd HH:mm:ss）
	 */
	public static final String list_time = "list_time";
	
	/**
	 * 下架时间（格式：yyyy-MM-dd HH:mm:ss）
	 */
	public static final String delist_time = "delist_time" ;
	
	/**
	 * 商品新旧程度(全新:new，闲置:unused，二手：second)
	 */
	public static final String stuff_status = "stuff_status" ;
	
	/**
	 * 商品所在地
	 */
	public static final String location = "location" ;
	
	/**
	 * 商品价格，格式：5.00；单位：元；精确到：分
	 */
	public static final String price = "price";
	
	/**
	 * 平邮费用,格式：5.00；单位：元；精确到：分
	 */
	public static final String post_fee = "post_fee" ;
	
	/**
	 * 快递费用,格式：5.00；单位：元；精确到：分
	 */
	public static final String express_fee = "express_fee" ;
	
	/**
	 * ems费用,格式：5.00；单位：元；精确到：分
	 */
	public static final String ems_fee = "ems_fee" ;
	
	/**
	 * 支持会员打折,true/false
	 */
	public static final String has_discount = "has_discount" ;
	
	/**
	 * 运费承担方式,seller（卖家承担），buyer(买家承担）
	 */
	public static final String freight_payer = "freight_payer" ;
	
	/**
	 * 是否有发票,true/false
	 */
	public static final String has_invoice = "has_invoice" ;
	
	/**
	 * 是否有保修,true/false
	 */
	public static final String has_warranty = "has_warranty" ;
	
	/**
	 * 橱窗推荐,true/false
	 */
	public static final String has_showcase = "has_showcase" ;
	
	/**
	 * 商品修改时间（格式：yyyy-MM-dd HH:mm:ss）
	 */
	public static final String modified = "modified" ;
	
	/**
	 * 加价幅度。如果为0，代表系统代理幅度。在竞拍中，为了超越上一个出价，会员需要在当前出价上增加金额，这个金额就是加价幅度。卖家在发布宝贝的时候可以自定义加价幅度，也可以让系统自动代理加价。系统自动代理加价的加价幅度随着当前出价金额的增加而增加，我们建议会员使用系统自动代理加价，并请买家在出价前看清楚加价幅度的具体金额。另外需要注意是，此功能只适用于拍卖的商品。以下是系统自动代理加价幅度表：当前价（加价幅度 ） 1-40（ 1 ）、41-100（ 2 ）、101-200（5 ）、201-500 （10）、501-1001（15）、001-2000（25）、2001-5000（50）、5001-10000（100） 10001以上 200
	 */
	public static final String increment = "increment" ;
	
	/**
	 * 商品上传后的状态。onsale出售中，instock库中
	 */
	public static final String approve_status = "approve_status" ;
	
	/**
	 * 	宝贝所属的运费模板ID，如果没有返回则说明没有使用运费模板
	 */
	public static final String postage_id = "postage_id" ;
	
	/**
	 * 宝贝所属产品的id(可能为空). 该字段可以通过taobao.products.search 得到
	 */
	public static final String product_id = "product_id" ;
	
	/**
	 * 商家外部编码(可与商家外部系统对接)
	 */
	public static final String outer_id = "outer_id" ;
	
	/**
	 * 虚拟商品的状态字段
	 */
	public static final String is_virtual = "is_virtual" ;
	
	/**
	 * 是否在淘宝显示
	 */
	public static final String is_taobao = "is_taobao" ;
	
	/**
	 * 是否在外部网店显示
	 */
	public static final String is_ex = "is_ex" ;
	
	/**
	 * 是否定时上架商品
	 */
	public static final String is_timing = "is_timing" ;
	
	/**
	 * 是否是3D淘宝的商品
	 */
	public static final String is_3D = "is_3D" ;
	
	/**
	 * 是否淘1站商品
	 */
	public static final String one_station = "one_station" ;
	
	/**
	 * 秒杀商品类型。打上秒杀标记的商品，用户只能下架并不能再上架，其他任何编辑或删除操作都不能进行。如果用户想取消秒杀标记，需要联系小二进行操作。如果秒杀结束需要自由编辑请联系活动负责人（小二）去掉秒杀标记。可选类型 web_only(只能通过web网络秒杀) wap_only(只能通过wap网络秒杀) web_and_wap(既能通过web秒杀也能通过wap秒杀)
	 */
	public static final String second_kill = "second_kill" ;
	
	/**
	 * 代充商品类型。只有少数类目下的商品可以标记上此字段，具体哪些类目可以上传可以通过taobao.itemcat.features.get获得。在代充商品的类目下，不传表示不标记商品类型（交易搜索中就不能通过标记搜到相关的交易了）。可选类型： time_card(点卡软件代充) fee_card(话费软件代充)
	 */
	public static final String auto_fill = "auto_fill" ;
	
	/**
	 * 商品是否违规，违规：true , 不违规：false
	 */
	public static final String violation = "violation" ;
	
	/**
	 * Wap宝贝详情 	不带html标签的desc文本信息，该字段只在taobao.item.get接口中返回
	 */
	public static final String wap_desc = "wap_desc" ;
	
	/**
	 * http://auction1.wap.taobao.com/auction/item_detail-0db0-1234567.jhtml 	适合wap应用的商品详情url ，该字段只在taobao.item.get接口中返回
	 */
	public static final String wap_detail_url = "wap_detail_url" ;
	
	/**
	 * 货到付款运费模板ID
	 */
	public static final String cod_postage_id = "cod_postage_id" ;
	
	/**
	 * 是否承诺退换货服务!
	 */
	public static final String sell_promise = "sell_promise" ;
}
