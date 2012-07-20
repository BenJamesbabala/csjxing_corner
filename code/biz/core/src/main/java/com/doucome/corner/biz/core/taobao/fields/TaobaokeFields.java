package com.doucome.corner.biz.core.taobao.fields;

import com.doucome.corner.biz.core.taobao.constant.TaokeItemConst;
import com.doucome.corner.biz.core.taobao.constant.TaokeReportMembConst;
import com.doucome.corner.biz.core.taobao.constant.TaokeShopConst;

public class TaobaokeFields {

	public static final String[] taoke_item_fields = new String[] { 
		TaokeItemConst.click_url, 
		TaokeItemConst.shop_click_url,
		TaokeItemConst.commission, 
		TaokeItemConst.commission_rate,
        TaokeItemConst.commission_volume, 
        TaokeItemConst.price, 
        TaokeItemConst.nick,
        TaokeItemConst.num_iid, 
        TaokeItemConst.pic_url,
        TaokeItemConst.title ,
        TaokeItemConst.volume,
        TaokeItemConst.seller_credit_score,
    };
	
	public static final String[] taoke_shop_fields = new String[] { 
		TaokeShopConst.user_id , 
		TaokeShopConst.click_url ,
		TaokeShopConst.shop_title ,
		TaokeShopConst.commission_rate, 
		TaokeShopConst.auction_count
    };
	
	public static final String[] taoke_report_memb_fields = new String[]{
		TaokeReportMembConst.app_key , 
		TaokeReportMembConst.category_id ,
		TaokeReportMembConst.category_name , 
		TaokeReportMembConst.commission , 
		TaokeReportMembConst.commission_rate , 
		TaokeReportMembConst.iid , 
		TaokeReportMembConst.item_num , 
		TaokeReportMembConst.item_title , 
		TaokeReportMembConst.num_iid , 
		TaokeReportMembConst.outer_code , 
		TaokeReportMembConst.pay_price , 
		TaokeReportMembConst.pay_time , 
		TaokeReportMembConst.real_pay_fee , 
		TaokeReportMembConst.seller_nick , 
		TaokeReportMembConst.shop_title , 
		TaokeReportMembConst.trade_id
	} ;
	
}
