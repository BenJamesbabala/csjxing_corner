package com.doucome.corner.biz.dcome.business;

import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.constant.Constant;
import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.biz.core.service.taobao.TaobaoCommentService;
import com.doucome.corner.biz.core.taobao.dto.TaobaoCommentDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaoItemDTO;
import com.doucome.corner.biz.core.taobao.dto.TaobaokeItemDTO;
import com.doucome.corner.biz.core.taobao.enums.TaobaoCommentEnum;
import com.doucome.corner.biz.core.utils.DcHttpUtils;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.dal.condition.dcome.DcCommentSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCommentDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcRateDetailDO;
import com.doucome.corner.biz.dal.model.DcItemPicModel;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.enums.DcItemSourceEnum;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.exception.DuplicateOperateException;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcPictureModel;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.param.DcUgcItemModel;
import com.doucome.corner.biz.dcome.service.DcCommentService;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcLoveDetailService;
import com.doucome.corner.biz.dcome.service.DcRateDetailService;
import com.doucome.corner.biz.dcome.service.DcTaobaoService;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;
import com.doucome.corner.biz.dcome.utils.DcPromotionOutCodeUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.common.model.ResultModel;
import com.doucome.corner.web.common.utils.TaobaoUrlUtils;

/**
 * ��ޢ��Ʒҵ���߼���
 * @author ze2200
 *
 */
public class DcItemBO {
	
	private static final Log log  = LogFactory.getLog(DcItemBO.class) ;

	@Autowired
	private DcCommentService dcCommentService ;
	@Autowired
	private DcItemService dcItemService ;
	@Autowired
	private DcFakeUserBO dcFakeUserBO;
	@Autowired
	private TaobaoCommentService taobaoCommentService;
	@Autowired
	private DcLoveDetailService dcLoveDetailService ;
	@Autowired
	private DcRateDetailService dcRateDetailService;
	@Autowired
	private DcTaobaoService dcTaobaoService;
	
	@Autowired
	private DcImageUploadBO dcImageUploadBO;
	@Autowired
	private DcUserIntegralBO dcUserIntegralBO;
	
	private static final String TB_URL = "http://item.taobao.com/item.htm?id=" ;
	
	private static final Log logger = LogFactory.getLog(DcItemBO.class);
	
	/**
	 * ugc��Ʒ�������.
	 * @param itemInfo �û��ṩ�Ķ�ޢ��Ʒ��Ϣ.
	 * @return
	 */
	public ResultModel<DcItemDTO> createUgcItem(DcUgcItemModel ugcInfo) {
		ResultModel<DcItemDTO> newItemInfo = new ResultModel<DcItemDTO>();
		ResultModel<Boolean> tempResult = ugcInfo.validate();
		if (!tempResult.isSuccess()) {
			newItemInfo.setFail(JsonModel.CODE_FAIL, tempResult.getDetail());
			return newItemInfo;
		}
		DcItemDTO itemDTO = getUgcDcItem(ugcInfo);
		if (itemDTO == null) {
			newItemInfo.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_ITEM_NOT_EXIST);
			return newItemInfo;
		}
		//�û��Ѵ���������Ʒ
		if (ugcInfo.getCreatorId().equals(itemDTO.getCreatorUserId())) {
			newItemInfo.setSuccess(JsonModel.CODE_SUCCESS, itemDTO);
			newItemInfo.setDetail(ResultModel.DETAIL_USER_ITEM_EXIST);
			return newItemInfo;
		}
		
		//ugc��Ʒcategory id��ʱ����Ϊ0.
		itemDTO.setId(null);
		itemDTO.setCategoryId(0);
		itemDTO.setCreatorUserId(ugcInfo.getCreatorId());
		itemDTO.setCreatorNick(ugcInfo.getCreatorNick());
		itemDTO.setItemDesc(ugcInfo.getRecommandReason());
		itemDTO.setRecommReason(ugcInfo.getRecommandReason());
		resetDcItemPictureUrl(ugcInfo, itemDTO);
		itemDTO.setStatus(DcItemStatusEnum.NORMAL);
		itemDTO.setGenWay(ugcInfo.getGenWay());
		if (itemDTO.getItemPromPrice() == null) {
			itemDTO.setItemPromPrice(ugcInfo.getPromPrice());
		}
		try {
			Long itemId = dcItemService.createItem(itemDTO.toItem());
			itemDTO.setId(itemId);
			newItemInfo.setSuccess(ResultModel.CODE_SUCCESS, itemDTO);
		} catch (DcDuplicateKeyException e) {
		    newItemInfo.setDetail(ResultModel.DETAIL_USER_ITEM_EXIST);
		    DcItemDTO item = getUgcItemFromDc(ugcInfo.getCreatorId(), ugcInfo.getExtItemId());
		    newItemInfo.setSuccess(JsonModel.CODE_SUCCESS, item);
		} catch (Exception e) {
			log.error(e);
			newItemInfo.setFail(ResultModel.CODE_FAIL, ResultModel.DETAIL_INTERNAL_ERROR);
		}
		return newItemInfo;
	}
	
	/**
	 * ����PGCitem�������ϴ�ͼƬ��
	 * @param item
	 * @return
	 * @throws DcDuplicateKeyException 
	 */
	public Long createPgcItem(DcItemDTO item , List<String> picUrlList) throws DcDuplicateKeyException {
		
		//�ϴ�ͼƬ
		List<DcItemPicModel> newPicModelList = uploadPic(picUrlList) ;
		if(CollectionUtils.isEmpty(newPicModelList)){
			return 0L ;
		}
		item.setPicUrls(JacksonHelper.toJSON(newPicModelList));
		item.setStatus(DcItemStatusEnum.DISABLE.getValue());
		item.setCreatorUserId(DcItemUtils.PGC_ITEM_CREATOR);
		item.setGenWay(DcItemGenWayEnums.PROFESSIONAL);
		
		Long id = dcItemService.createItem(item.toItem());
		
		return id ;
	}
	
	/**
	 * 
	 * @param item
	 * @param picUrls
	 * @return
	 */
	public Integer updateItem(DcItemDTO item, List<String> picUrls) {
		List<DcItemPicModel> picModels = uploadPic(picUrls);
		if(!CollectionUtils.isEmpty(picModels)){
			item.setPicUrls(JacksonHelper.toJSON(picModels));
		}
		return dcItemService.updateItem(item.toItem());
	}
	
	/**
	 * �ϴ�ͼƬ
	 * @return
	 */
	private List<DcItemPicModel> uploadPic(List<String> picUrls) {
		
		List<DcItemPicModel> result = new ArrayList<DcItemPicModel>();
		if (CollectionUtils.isEmpty(picUrls)) {
			return result ;
		}
		for (String tempUrl: picUrls) {
			if (StringUtils.isNotBlank(tempUrl)) {
				if (StringUtils.startsWithIgnoreCase(tempUrl, Constant.HTTP)) {
					try {
						DcPictureModel model = dcImageUploadBO.uploadItemPictureFromUrl(tempUrl);
						result.add(model.toItemPicModel());
					} catch (Exception e) {
						logger.error(e);
					}
				} else {
					DcPictureModel model = new DcPictureModel() ;
				    String host = DefaultUriService.getFactoryURI(URIConstant.DCOME_ITEM_UPLOADED_SERVER);
					model.setPath(tempUrl) ;
					try {
						InputStream is = DcHttpUtils.getInputStream(host + tempUrl) ;
						Dimension dimension = ImageUtils.getImageDimension(is, org.apache.poi.ss.usermodel.Workbook.PICTURE_TYPE_JPEG) ;
						model.setDimension(dimension) ;
					} catch (IOException e) {
						log.error(e.getMessage() , e) ;
					}
					result.add(model.toItemPicModel());
				}
			}
		}
		return result ;
	}
	
	/**
	 * ���ݶ˴��ݵ�ͼƬ����������ƷͼƬ.
	 * ����ͻ��˲����޷��ͷ����ƥ�䣬��ͻ���δ���ݲ�����Ĭ��ȥ��һ��ͼƬ.
	 * @param ugcItem
	 * @param dcItem
	 */
	public void resetDcItemPictureUrl(DcUgcItemModel ugcItem, DcItemDTO dcItem) {
		if (StringUtils.isEmpty(ugcItem.getPictureUrl())) {
			return ;
		}
		String picUrl = ugcItem.getPictureUrl();
		if (!CollectionUtils.isEmpty(dcItem.getPicUrlList())) {
			for (String temp: dcItem.getPicUrlList()) {
				if (picUrl.indexOf(temp) != -1) {
					dcItem.setPicUrls(temp);
					return;
				}
			}
			dcItem.setPicUrls(dcItem.getPicUrlList().get(0));
		}
	}
	
	/**
	 * ��ȡugc��Ʒ.���ȴӶ�ޢ��Ʒ���ȡ,��ʹ��֮ǰ��Ҫ����creator_user_id�ж�
	 * ��Ʒ�Ƿ����ύ����ޢ.
	 * @param itemInfo
	 * @return DcItemDTO: creator != null����ޢ��Ʒ����Ʒ.
	 */
	public DcItemDTO getUgcDcItem(DcUgcItemModel ugcInfo) {
		//���ȴӶ�ޢ��Ʒ���ȡ
		DcItemDTO item = getUgcItemFromDc(ugcInfo.getCreatorId(), ugcInfo.getExtItemId());
		if (item != null) {
			try {
			    BigDecimal promPrice = dcTaobaoService.getItemPromoPrice(Long.valueOf(ugcInfo.getExtItemId()));
			    item.setItemPromPrice(promPrice);
			} catch (Exception e) {
				
			}
			return item;
		}
		return getDcItemFromTB(ugcInfo.getItemUrl());
	}
	
	/**
	 * �Ӷ�ޢ��Ʒ���ȡugc��Ʒ.
	 * @param userId
	 * @param externalItemId
	 * @return
	 */
	private DcItemDTO getUgcItemFromDc(Long userId, String externalItemId) {
		try {
			List<DcItemDTO> itemDTOs = dcItemService.getItemsByExtlId(externalItemId);
			for (DcItemDTO temp: itemDTOs) {
				if (userId.equals(temp.getCreatorUserId())) {
					return temp;
				}
			}
			return CollectionUtils.isEmpty(itemDTOs) ? null: itemDTOs.get(0);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	/**
	 * ���Ա���ȡ��ޢ��Ʒ.
	 * @param itemUrl ��Ʒ����
	 * @return
	 */
	public DcItemDTO getDcItemFromTB(String itemUrl) {
		String tbItemId = TaobaoUrlUtils.parseItemId(itemUrl);
		if (StringUtils.isBlank(tbItemId)) {
			return null;
		}
		TaobaokeItemDTO taokeItem = null;
		TaobaoItemDTO taobaoItem = null;
		try {
			taokeItem = dcTaobaoService.conventItem(tbItemId, OutCodeUtils.encodeOutCode("QQ", OutCodeEnums.DOUCOME));
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			taobaoItem = dcTaobaoService.getTaobaoItem(tbItemId);
		} catch (Exception e) {
			logger.error(e);
		}
		DcItemDTO dcItem = DcItemUtils.mergeInfoTo(taokeItem, taobaoItem);
		if (dcItem == null) {
			return null;
		}
		//��۸���ȡ����.
		BigDecimal promPrice = getItemPromPrice(tbItemId);
		if (promPrice == null) {
			promPrice = getItemPromPrice(tbItemId);
		}
		dcItem.setItemPromPrice(promPrice);
		//�ӿڷ��ص�srcUrlȫ��item.taobao.com...�ģ��˴�Ҫ���ݴ����itemUrl����item source
		dcItem.setSrcUrl(itemUrl);
		boolean isTmall = itemUrl.indexOf("tmall") != -1;
		dcItem.setSource(isTmall ? DcItemSourceEnum.TMALL.getValue():DcItemSourceEnum.TAOBAO.getValue());
		return dcItem;
	}
	
	/**
	 * 
	 * @param nativeIds
	 * @return
	 */
	public List<DcItemDTO> getDcItemsFromTB(String[] nativeIds) {
		if (nativeIds == null || nativeIds.length == 0) {
			return new ArrayList<DcItemDTO>();
		}
		List<TaobaokeItemDTO> taokeItems = null;
		try {
			taokeItems = dcTaobaoService.convertItems(nativeIds, OutCodeUtils.encodeOutCode("QQ", OutCodeEnums.DOUCOME));
		} catch (Exception e) {
			logger.error(e);
		}
		List<TaobaoItemDTO> taobaoItems = null;
		try {
			taobaoItems = dcTaobaoService.getTaobaoItems(nativeIds);
		} catch (Exception e) {
			logger.error(e);
		}
		List<DcItemDTO> dcItems = new ArrayList<DcItemDTO>();
		for (TaobaoItemDTO taobaoItem: taobaoItems) {
			for (TaobaokeItemDTO taokeItem: taokeItems) {
				if (taobaoItem.getNumIid().equals(taokeItem.getNumIid())) {
					dcItems.add(DcItemUtils.mergeInfoTo(taokeItem, taobaoItem));
					break;
				}
			}
			dcItems.add(DcItemUtils.mergeInfoTo(null, taobaoItem));
		}
		return dcItems;
	}
	
	private BigDecimal getItemPromPrice(String tbItemId) {
		try {
		    return dcTaobaoService.getItemPromoPrice(Long.valueOf(tbItemId));
		} catch (Exception e) {
			//ʧ��Ƶ�ʸߣ����ü���־
			return null;
		}
	}
	
	/**
	 * Ϊ��Ʒ�������.
	 * @param comment
	 * @return
	 */
	public Long addComment(DcCommentDO comment) {
		if(comment == null || IDUtils.isNotCorrect(comment.getItemId()) || IDUtils.isNotCorrect(comment.getUserId())){
			throw new IllegalArgumentException("comment itemId and userId cant be null.") ;
		}
		Long id = dcCommentService.createComment(comment) ;
		dcItemService.incrCommentCount(comment.getItemId()) ;
		return id ;
	}
	
	/**
	 * 
	 * @param rate
	 * @throws DuplicateRateException
	 */
	public void addRate(DcRateDetailDO rate) throws DuplicateOperateException {
		if(rate == null || IDUtils.isNotCorrect(rate.getItemId()) || IDUtils.isNotCorrect(rate.getRateUserId())){
			throw new IllegalArgumentException("rate itemId and rateUserId cant be null.") ;
		}
		dcRateDetailService.createRate(rate) ;
		dcItemService.incrRateCount(rate.getItemId()) ;
	}
	
	
	/**
	 * ���۹�ˮ��
	 * @param fakeUserId ��ˮ�û�id��
	 * @param itemId ��Ʒid
	 * @param comments ��������.����Ӣ��';'�ָ�����Ʒ��
	 * @return ����id��
	 */
	public DcCommentDTO addBopsComment(Long fakeUserId, Long itemId, String comment) {
		DcCommentDO commentDO = new DcCommentDO();
		commentDO.setContent(comment);
		commentDO.setItemId(itemId);
		DcUserDTO commentUserDO = null;
		if (fakeUserId != null) {
			commentUserDO = dcFakeUserBO.getFakeUser(fakeUserId);
		} else {
			commentUserDO = dcFakeUserBO.getFakeUser();
		}
		commentDO.setUserId(commentUserDO.getUserId());
		commentDO.setUserNick(commentUserDO.getNick());
		commentDO.setSource(commentUserDO.getSource());
		
		Long id = dcCommentService.createComment(commentDO);
		dcItemService.incrCommentCount(itemId);
		commentDO.setId(id);
		return new DcCommentDTO(commentDO);
	}
	
	/**
	 * ������ˮ��
	 * @param itemId ��Ʒid.
	 * @param commentContents ���ۡ�
	 * @return ��
	 */
	public List<DcCommentDTO> addBopsComments(Long itemId, List<String> commentContents) {
		if (itemId == null || commentContents == null || commentContents.size() == 0) {
			return new ArrayList<DcCommentDTO>();
		}
		List<DcCommentDO> comments = new ArrayList<DcCommentDO>(commentContents.size());
		for (String content: commentContents) {
			DcCommentDO commentDO = new DcCommentDO();
			commentDO.setContent(content);
			commentDO.setItemId(itemId);
			DcUserDTO commentUserDO = dcFakeUserBO.getFakeUser();
			commentDO.setUserId(commentUserDO.getUserId());
			commentDO.setUserNick(commentUserDO.getNick());
			commentDO.setSource(commentUserDO.getSource());
			
			comments.add(commentDO);
		}
		
		int count = dcCommentService.createComments(comments);
		dcItemService.incrCommentCount(itemId, count);
		if (count != comments.size()) {
			log.error("expect insert " + comments.size() + " comment, actual insert " + count + " comments");
		}
		List<DcCommentDTO> result = new ArrayList<DcCommentDTO>(comments.size());
		for (DcCommentDO comment: comments) {
			result.add(new DcCommentDTO(comment));
		}
		
		return result;
	}
	
	public List<DcCommentDTO> getCommentsNoPagination(long itemId , int page, int size) {
		List<DcCommentDTO> commentList = null  ;
		DcCommentSearchCondition condition = new DcCommentSearchCondition() ;
		condition.setItemId(itemId) ;
		commentList = dcCommentService.getCommentsNoPagination(condition, new Pagination(page,size)) ;
		return commentList ;
	}
	
	/**
	 * ��ȡ��Ʒ���Ա�����.
	 * @param taobaoItemId ��Ʒ���Ա�����id.
	 * @param sellerId ��Ʒ������id.
	 * @param commentEnum
	 * @return ��Ʒ���Ա�����.null����ȡʧ�ܡ�
	 */
	public List<DcCommentDTO> getTaobaoComments(Long taobaoItemId, Long sellerId, TaobaoCommentEnum commentEnum) {
		if (IDUtils.isNotCorrect(taobaoItemId) || IDUtils.isNotCorrect(sellerId)) {
			return new ArrayList<DcCommentDTO>();
		}
		List<TaobaoCommentDTO> tbComments =
			  taobaoCommentService.getItemComments(taobaoItemId, sellerId, commentEnum);
		if (tbComments == null) {
			return null;
		}
		List<DcCommentDTO> dcComments = new ArrayList<DcCommentDTO>();
		for (TaobaoCommentDTO tbComment: tbComments) {
			dcComments.add(new DcCommentDTO(tbComment));
		}
		return dcComments;
	}
		
	/**
	 * �û����ϲ��
	 * @param userId
	 * @param itemId
	 * @throws DuplicateOperateException �Ѿ���ӹ�ϲ��
	 */
	public void addLove(long userId , long itemId) throws DuplicateOperateException {
		dcLoveDetailService.createDetail(itemId, userId) ;
		dcItemService.incrLoveCount(itemId) ;
	}
	
	public Map<Long,DcItemDTO> getItemsMapByIds(List<Long> idList){
		Map<Long,DcItemDTO> itemMap = new HashMap<Long,DcItemDTO>() ;
		if(CollectionUtils.isEmpty(idList)){
			return itemMap ;
		}
		List<DcItemDTO> itemList = dcItemService.getItemsByIds(idList) ;
		if(CollectionUtils.isEmpty(itemList)){
			return itemMap ;
		}
		for(DcItemDTO item : itemList){
			if(item == null){
				continue ;
			}
			itemMap.put(item.getId(), item) ;
		}
		return itemMap ;
	}
	
	/**
	 * ��ȡ��ޢ�Կ����ӣ�ֲ���û�id.
	 * @param dcItem
	 * @param userId
	 * @return
	 */
	public String getDcTaokeUrl(DcItemDTO dcItem, Long userId) {
		String nativeItemId = dcItem.getNativeId() ;
		// UserId �� ItemId ���б���
		String outCodeData = DcPromotionOutCodeUtils.encodeOutCode(dcItem.getId(), userId) ;
		String outCode = OutCodeUtils.encodeOutCode(outCodeData, OutCodeEnums.DOUCOME_PROMOTION) ;
//		
		TaobaokeItemDTO taokeItem = dcTaobaoService.conventItem(nativeItemId, outCode);
		if(taokeItem == null){
			return TB_URL + dcItem.getNativeId() ;
		} else {
			return taokeItem.getClickUrl() ;
		}
	}
	
	/**
	 * 
	 * @param itemId
	 * @return
	 */
	public int acceptUgc(Long itemId) {
		DcItemDTO item = dcItemService.getItemById(itemId);
		if (item == null || DcItemGenWayEnums.PROFESSIONAL.getValue().equals(item.getGenWay())) {
			return 0;
		}
		List<String> picUrls = item.getPicUrlList();
		while (picUrls.size() > 1) {
			picUrls.remove(picUrls.size() - 1);
		}
		//�ϴ���ƷͼƬ.
		List<DcItemPicModel> upModels = uploadPic(picUrls);
		if(CollectionUtils.isEmpty(upModels)){
			return 0;
		}
		item.setPicUrls(JacksonHelper.toJSON(upModels));
		
		int count = dcItemService.updateGenWay(itemId, DcItemGenWayEnums.PROFESSIONAL, JacksonHelper.toJSON(upModels));
		int integral = 0;
		if (count > 0) {
			dcItemService.updateDisplayOrderById(itemId);
			//integral = dcUserIntegralBO.doAwardUgcAccept(item);
		}
		return integral;
	}
}