package com.doucome.corner.biz.core.taobao.fields;

import com.doucome.corner.biz.core.taobao.constant.TaobaoItemConst;
import com.doucome.corner.biz.core.taobao.constant.TaobaoUserConstant;

public class TaobaoFields {

	public static final String[] taobao_user_fields = new String[]{
		TaobaoUserConstant.alipay_account , 
		TaobaoUserConstant.sex ,
		TaobaoUserConstant.alipay_bind ,
		TaobaoUserConstant.birthday ,
		TaobaoUserConstant.nick , 
		TaobaoUserConstant.user_id
	} ;
	
	public static final String[] taobao_item_fields_short = new String[]{
		TaobaoItemConst.cid , 
		TaobaoItemConst.num_iid , 
		TaobaoItemConst.pic_url ,
		TaobaoItemConst.title , 
		TaobaoItemConst.price , 
		TaobaoItemConst.detail_url
	} ;
	
	public static final String[] taobao_item_fields_full = new String[]{
		TaobaoItemConst.cid , 
		TaobaoItemConst.num_iid , 
		TaobaoItemConst.pic_url ,
		TaobaoItemConst.title , 
		TaobaoItemConst.price , 
		TaobaoItemConst.detail_url,
		TaobaoItemConst.nick,
		TaobaoItemConst.wap_desc,
		TaobaoItemConst.created ,
		TaobaoItemConst.cid,
		TaobaoItemConst.post_fee,
		TaobaoItemConst.express_fee ,
		TaobaoItemConst.ems_fee ,
		TaobaoItemConst.item_img,
		TaobaoItemConst.freight_payer,
		TaobaoItemConst.approve_status
	} ;
}
