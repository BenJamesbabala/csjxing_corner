package com.doucome.corner.web.common.toolbox;

import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.biz.core.utils.AvatarUtils;
import com.doucome.corner.biz.core.utils.PictureUtils;

/**
 * 用户头像工具
 * @author shenjia.caosj 2012-7-29
 *
 */
public class AvatarUtilsToolbox {

	public String getAvatarPath(long userId){
		return AvatarUtils.buildAvatarPath(userId) ;
	}
	
	public String getAvatarUrl(Long userId  , String picSize){
		String baseURI = DefaultUriService.getFactoryURI(URIConstant.DCOME_PIC_UPLOADED_SERVER) ;
		String avatarPath = getAvatarPath(userId) ;
		String avatarUrl = baseURI + avatarPath ;
		return PictureUtils.findPic(avatarUrl , PictureSizeEnums.toEnum(picSize)) ;
	}
}
