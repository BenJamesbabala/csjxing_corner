package com.doucome.corner.biz.dcome.utils;

import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.biz.dcome.model.DcIntegralDesc;
import com.doucome.corner.biz.dcome.model.DcMessageDTO;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;

public final class DcConvertUtils {
	
	public static void convertItemPicUrls(List<String> picUrls) {
		if (CollectionUtils.isEmpty(picUrls)) {
			return ;
		}
		String baseUrl = DefaultUriService.getFactoryURI(URIConstant.DCOME_ITEM_UPLOADED_SERVER);
		for (int i = 0 ; i < picUrls.size(); i++) {
			if (picUrls.get(i).indexOf("http://") == -1) {
				String temp = picUrls.remove(i);
				picUrls.add(i, baseUrl + temp);
			}
		}
	}
	
	public static DcMessageDTO convertToDcMessage(DcUserIntegralDetailDTO integralDetail) {
		if (integralDetail == null) {
			return null;
		}
		DcMessageDTO message = new DcMessageDTO();
		message.setUserId(integralDetail.getUserId());
		message.setActivity(integralDetail.getSource());
		message.setIntegral(integralDetail.getIntegralCount());
		message.setGmtCreate(integralDetail.getGmtCreate() == null? new Date(): integralDetail.getGmtCreate());
		DcIntegralDesc integralDesc = integralDetail.getIntegralDesc();
		message.setUserNick(integralDesc.getToObjName());
		message.setRelateObjName(integralDesc.getFromObjName());
		message.setRelateObjId(integralDesc.getFromObjId());
		message.setOtherInfo(integralDesc.getOtherInfo());
		return message;
	}
}
