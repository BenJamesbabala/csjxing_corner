package com.doucome.corner.biz.zhe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.core.service.taobao.TaobaoRecommandDecorator;
import com.doucome.corner.biz.core.service.taobao.TaobaoServiceDecorator;
import com.doucome.corner.biz.core.service.taobao.TaobaokeServiceDecorator;
import com.doucome.corner.biz.core.taobao.dto.TaobaoFavoriteItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeShopDTO;
import com.doucome.corner.biz.core.taobao.enums.TaobaoRecommendTypeEnums;
import com.doucome.corner.biz.core.taobao.fields.TaobaoFields;
import com.doucome.corner.biz.core.taobao.fields.TaobaokeFields;
import com.doucome.corner.biz.core.taobao.model.TaobaoRecommendItemCondition;
import com.doucome.corner.biz.zhe.model.TaobaokeItemFacade;
import com.doucome.corner.biz.zhe.model.TaobaokeShopFacade;
import com.doucome.corner.biz.zhe.rule.DdzEatDiscountRule;
import com.doucome.corner.biz.zhe.service.DdzTaobaokeService;

public class DdzTaobaokeServiceImpl implements DdzTaobaokeService {

	private static final int DEFAULT_RECOMMAND_SIZE = 20;

	private static final int MAX_RECOMMAND_SIZE = 40;

	@Autowired
	private TaobaokeServiceDecorator taobaokeServiceDecorator;

	@Autowired
	private DdzEatDiscountRule ddzEatDiscountRule;

	@Autowired
	private TaobaoServiceDecorator taobaoServiceDecorator;

	@Autowired
	private TaobaoRecommandDecorator taobaoRecommandDecorator;

	@Override
	public List<TaobaokeShopFacade> conventShops(List<String> shopIdList,
			String outCode) {

		List<TaobaokeShopFacade> facadeList = new ArrayList<TaobaokeShopFacade>();

		if (CollectionUtils.isEmpty(shopIdList)) {
			return facadeList;
		}

		// ≈˙¡ø≤È—Ø
		for (String shopId : shopIdList) {
			TaobaokeShopDTO dto = taobaokeServiceDecorator.conventShop(shopId,
					outCode, TaobaokeFields.taoke_shop_fields);
			TaobaokeShopFacade fd = new TaobaokeShopFacade(dto,
					ddzEatDiscountRule);
			fd.setSid(shopId);
			facadeList.add(fd);
		}

		return facadeList;
	}

	@Override
	public TaobaokeShopFacade conventShop(String ShopId, String outCode) {
		List<String> idList = new ArrayList<String>();
		idList.add(ShopId);
		List<TaobaokeShopFacade> list = conventShops(idList, outCode);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		TaobaokeShopFacade shop = list.get(0);

		return shop;
	}

	@Override
	public TaobaokeItemFacade conventItem(String itemId, String outCode) {
		TaobaokeItemDTO itemDTO = taobaokeServiceDecorator.widgetConventItem(itemId,
				outCode, TaobaokeFields.taoke_item_fields);
		if (itemDTO == null) {
			return null;
		}

		TaobaokeItemFacade item = new TaobaokeItemFacade(itemDTO,ddzEatDiscountRule);
		return item;
	}
	
	@Override
	public TaobaokeItemFacade conventItem(String itemId, String outCode,
			boolean isTmall) {
		TaobaokeItemDTO itemDTO = taobaokeServiceDecorator.widgetConventItem(itemId,
				outCode, TaobaokeFields.taoke_item_fields);
		if (itemDTO == null) {
			return null;
		}
		TaobaokeItemFacade item = new TaobaokeItemFacade(itemDTO,ddzEatDiscountRule,isTmall);
		return item;
	}

	@Override
	public TaobaokeItemFacade getTaobaoItem(String itemId) {
		TaobaoItemDTO taobaoItem = taobaoServiceDecorator.getItem(
				Long.valueOf(itemId), TaobaoFields.taobao_item_fields_short);
		if (taobaoItem == null) {
			return null;
		}
		return new TaobaokeItemFacade(taobaoItem);
	}

	@Override
	public List<TaobaokeItemFacade> getFavoriteItems(String itemId, int count) {
		if (count < 0) {
			count = DEFAULT_RECOMMAND_SIZE;
		} else {
			count = count > MAX_RECOMMAND_SIZE ? MAX_RECOMMAND_SIZE : count;
		}

		TaobaoRecommendItemCondition recommendCondition = new TaobaoRecommendItemCondition();
		recommendCondition.setCount(Long.valueOf(count));
		recommendCondition
				.setRecommendType(TaobaoRecommendTypeEnums.SAME_STYLE);
		List<TaobaoFavoriteItemDTO> recoList = taobaoRecommandDecorator
				.getRecommandItemsByItem(Long.valueOf(itemId),
						recommendCondition);

		return null;
	}

	

}
