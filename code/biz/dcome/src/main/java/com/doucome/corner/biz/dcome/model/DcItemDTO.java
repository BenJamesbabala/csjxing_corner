package com.doucome.corner.biz.dcome.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.constant.URIConstant;
import com.doucome.corner.biz.core.enums.PictureSizeEnums;
import com.doucome.corner.biz.core.service.impl.DefaultUriService;
import com.doucome.corner.biz.core.utils.AvatarUtils;
import com.doucome.corner.biz.core.utils.DecimalUtils;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.core.utils.PictureUtils;
import com.doucome.corner.biz.dal.dataobject.dcome.DcItemDO;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dal.model.DcItemPicModel;
import com.doucome.corner.biz.dcome.enums.DcCategoryEnum;
import com.doucome.corner.biz.dcome.enums.DcItemGenWayEnums;
import com.doucome.corner.biz.dcome.enums.DcItemStatusEnum;
import com.doucome.corner.biz.dcome.model.util.PromotionIntegralUtils;
import com.doucome.corner.biz.dcome.utils.DcItemUtils;

/**
 * @author langben 2012-7-21
 *
 */
public class DcItemDTO extends AbstractModel {	

	private static final long serialVersionUID = 2714724008075358867L;

	private DcItemDO item ;

	private List<DcItemPicModel> picModelList ;
	
	/**
	 * 0:pgc商品,1:自己ugc的商品,2:他人ugc的商品,3，豆蔻商品库外的商品
	 */
	private Integer ownerSign;
	
	private DcExchangeModel exchangeModel;
	
	public DcItemDTO() {
		this(null);
	}
	
	public DcItemDTO(DcItemDO item){
		if(item == null){
			item = new DcItemDO() ;
		}
		this.item = item ;
		this.picModelList = DcItemUtils.parsePicUrls(item.getPicUrls()) ;
		exchangeModel = new DcExchangeModel();
	}
	
	public String getPicUrl(int index , String type){
		
		if(index >= CollectionUtils.size(this.picModelList)){
			return null;
		}
		
		DcItemPicModel picModel = this.picModelList.get(index) ;
		
		if(picModel == null){
			return null ;
		}
		
		String picUrl = picModel.getUrl() ;
		
		return DcItemUtils.getPictureAbsoluteUrl(picUrl, PictureSizeEnums.toEnum(type)) ;
	}
	
	public DcItemPicModel getPicModel(int index){
		
		if(index >= CollectionUtils.size(this.picModelList)){
			return null ;
		}
		return this.picModelList.get(index) ;
	}
	
	public String getPicUrl80x80() {
		return getPicUrl(0, PictureSizeEnums._80x80.getName());
	}
	
	public Pic getPicModel220x000(){
		DcItemPicModel picModel = getPicModel(0) ;
		if(picModel == null){
			return new Pic() ;
		}
		Pic pic = new Pic() ;
		pic.setUrl(DcItemUtils.getPictureAbsoluteUrl(picModel.getUrl(), PictureSizeEnums._220x000)) ;
		pic.setWidth(PictureSizeEnums._220x000.getWidth()) ;
		pic.setHeight(picModel.getHeight(PictureSizeEnums._220x000.getWidth())) ;
		return pic ;
	}
	
	public String[] getPicUrlList80x80() {
		String[] temp = new String[picModelList.size()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = getPicUrl(i, "80x80");
		}
		return temp;
	}
	
	public String getPicUrl160x160(){
		return getPicUrl(0, PictureSizeEnums._160x160.getName()) ;
	}
	
	public String getPicUrl160x000(){
		return getPicUrl(0, PictureSizeEnums._160x000.getName()) ;
	}
	
	public String getPicUrl220x220(){
		return getPicUrl(0, PictureSizeEnums._220x220.getName()) ;
	}
	
	public String getPicUrl220x000(){
		return getPicUrl(0, PictureSizeEnums._220x000.getName()) ;
	}
	
	public String getPicUrl300x300() {
		return getPicUrl(0, PictureSizeEnums._300x300.getName());
	}
	
	public String getItemPriceFormat(){
		return DecimalUtils.format(getItemPrice(), "0.00");
	}
	
	public String getDisplayPriceFormat() {
		BigDecimal itemPromPrice = getItemPromPrice();
		if(itemPromPrice != null) {
			return getItemPromPriceFormat() ;
		}
		return getItemPriceFormat() ;
	}
	
	public DcItemDO toItem() {
		return this.item;
	}
	
	
	public int getIntegralCount(){
		return PromotionIntegralUtils.getIntegral(this) ;
	}
	
	/**
	 * 根据返利积分计算的返利.
	 * @return
	 */
	public String getUserCommmission() {
		int integral = getIntegralCount();
		BigDecimal userCommission = new BigDecimal(integral).divide(new BigDecimal("100"));
		return DecimalUtils.format(userCommission, "0.00");
	}
	
	public String getItemPromPriceFormat(){
		BigDecimal itemPromPrice = getItemPromPrice();
		if (itemPromPrice != null) {
			return DecimalUtils.format(itemPromPrice, "0.00");
		} else {
			return getItemPriceFormat();
		}
	}
	
	
	public List<DcItemPicModel> getPicModelList() {
		return picModelList;
	}

	public void setPicModelList(List<DcItemPicModel> picModelList) {
		this.picModelList = picModelList;
		this.item.setPicUrls(JacksonHelper.toJSON(this.picModelList)) ;
	}
	
	public List<String> getPicUrlList(){
		List<String> picUrlList = new ArrayList<String>() ;
		if(CollectionUtils.isEmpty(this.picModelList)){
			return picUrlList ;
		}
		
		for(DcItemPicModel model : picModelList) {
			if(model != null) {
				picUrlList.add(model.getUrl()) ;
			}
		}
		
		return picUrlList ;
	}


	/**
	 * -----------------------------------------------------------------------
	 */
	
	
	
	public Long getId() {
		return item.getId();
	}
	
	public void setId(Long id) {
		this.item.setId(id);
	}

	public String getNativeId() {
		return item.getNativeId();
	}

	public void setNativeId(String nativeId) {
		this.item.setNativeId(nativeId);
	}
	
	public Long getNativeCategoryId() {
		return item.getNativeCategoryId();
	}

	public void setNativeCategoryId(Long nativeCategoryId) {
		item.setNativeCategoryId(nativeCategoryId);
	}
	
	public String getItemTitle() {
		return item.getItemTitle();
	}
	
	public void setItemTitle(String itemTitle) {
		this.item.setItemTitle(itemTitle);
	}

	public String getItemDesc() {
		return StringUtils.trim(item.getItemDesc());
	}
	
	public void setItemDesc(String itemDesc) {
		item.setItemDesc(StringUtils.trim(itemDesc));
	}

	public BigDecimal getItemPrice() {
		return item.getItemPrice();
	}
	
	public String getItemPriceFmt() {
		BigDecimal temp = item.getItemPrice();
		return DecimalUtils.format(temp, "###,##0.00");
	}
	
	public void setItemPrice(BigDecimal itemPrice) {
		item.setItemPrice(itemPrice);
	}
	
	public void setItemPrice(String itemPrice) {
		if (StringUtils.isEmpty(itemPrice)) {
			return ;
		}
	    item.setItemPrice(new BigDecimal(itemPrice));
	}

	public String getSource() {
		return item.getSource();
	}
	
	public void setSource(String source) {
		item.setSource(source);
	}

	public String getSrcUrl() {
		return item.getSrcUrl();
	}
	
	public void setSrcUrl(String srcUrl) {
		item.setSrcUrl(srcUrl);
	}

	public String getClickUrl() {
		return item.getClickUrl();
	}
	
	public void setClickUrl(String clickUrl) {
		item.setClickUrl(clickUrl);
	}

	public String getPicUrls() {
		return item.getPicUrls();
	}
	
	public void setPicUrls(String picUrls) {
		item.setPicUrls(picUrls);
	}
	
	public Integer getLoves() {
		return item.getLoves();
	}
	
	public void setLoves(int loves) {
		item.setLoves(loves);
	}

	public Integer getSells() {
		return item.getSells();
	}
	
	public Integer getFakeSells(int a) {
		if (item.getSells() < 300) {
			return 300 + new Random(a).nextInt(300);
		}
		return item.getSells();
	}
	
	public void setSells(int sells) {
		item.setSells(sells);
	}

	public Integer getComments() {
		return item.getComments();
	}
	
	public void setComments(int comments) {
		item.setComments(comments);
	}

	public Long getCategoryId() {
		return item.getCategoryId();
	}
	
	public Long getPossibleCategoryId() {
//		Long id = item.getCategoryId();
//		if (id == null) {
			return DcCategoryEnum.getPossibleId(getItemTitle());
//		}
//		return id;
	}
	
	public void setCategoryId(long categoryId) {
		item.setCategoryId(categoryId);
	}

	public Date getGmtModified() {
		return item.getGmtModified();
	}
	
	public void setGmtModified(Date gmtModified) {
		item.setGmtModified(gmtModified);
	}


	public Date getGmtCreate() {
		return item.getGmtCreate();
	}

	public void setGmtCreate(Date gtmCreate) {
		item.setGmtCreate(gtmCreate);
	}
	
	public String getStatus() {
		return item.getStatus();
	}

	public void setStatus(String status) {
		item.setStatus(status);
	}
	
	public void setStatus(DcItemStatusEnum status) {
		item.setStatus(status.getValue());
	}
	
	public Integer getRates() {
		return item.getRates();
	}

	public void setRates(Integer rates) {
		this.item.setRates(rates);
	}

	public DcItemGenWayEnums getGenWay() {
		return DcItemGenWayEnums.toEnum(item.getGenWay());
	}

	public String getGenWayStr() {
		return item.getGenWay();
	}
	
	public void setGenWay(DcItemGenWayEnums genWay) {
		this.item.setGenWay(genWay.getValue());
	}

	public Long getCreatorUserId() {
		return item.getCreatorUserId();
	}

	public void setCreatorUserId(Long creatorUserId) {
		this.item.setCreatorUserId(creatorUserId) ;
	}
	
	public String getRecommReason() {
		return item.getRecommReason();
	}

	public void setRecommReason(String recommReason) {
		item.setRecommReason(recommReason);
	}
	
	public BigDecimal getCommissionRate() {
		return item.getCommissionRate() ;
	}
	
	public void setCommissionRate(String commissionRate) {
		if(StringUtils.isEmpty(commissionRate)) {
			return ;
		}
		item.setCommissionRate(new BigDecimal(commissionRate)) ;
	}
	
	public BigDecimal getItemPromPrice(){
		return item.getItemPromPrice() ;
	}
	
	public void setItemPromPrice(String itemPromPrice){
		if(StringUtils.isEmpty(itemPromPrice)) {
			return ;
		}
		this.item.setItemPromPrice(new BigDecimal(itemPromPrice)) ;
	}
	
	public void setItemPromPrice(BigDecimal itemPromPrice){
		this.item.setItemPromPrice(itemPromPrice) ;
	}
	
	public String getAvatarUrl30x30() {
		return DefaultUriService.getFactoryURI(URIConstant.DCOME_PIC_UPLOADED_SERVER)
		  + PictureUtils.findPic(AvatarUtils.buildAvatarPath(getCreatorUserId()), PictureSizeEnums._30x30);
	}
	
	public void setCreatorNick(String creatorNick) {
		this.item.setCreatorNick(creatorNick);
	}
	
	public String getCreatorNick() {
		return this.item.getCreatorNick() == null ? "": this.item.getCreatorNick();
	}
	
	public String getRecommType() {
		return item.getRecommType();
	}

	public void setRecommType(String recommType) {
		this.item.setRecommType(recommType) ;
	}

	public Date getGmtItemUpdate() {
		return item.getGmtItemUpdate() ;
	}
	
	public Integer getTaokeSellCount(){
		return item.getTaokeSellCount() ;
	}
	
	/**
	 * 
	 * @return 0:pgc商品,1:自己ugc的商品,2:他人ugc的商品,3，豆蔻商品库外的商品
	 */
	public Integer getOwnerSign() {
		return ownerSign;
	}
	
	/**
	 * 
	 * @param ownerSign 
	 */
	public void setOwnerSign(Integer ownerSign) {
		this.ownerSign = ownerSign;
	}
	
	public String getPostalEnable() {
		return this.item.getPostalEnable();
	}
	
	public void setPostalEnable(String postalEnable) {
		this.item.setPostalEnable(postalEnable);
	}
	
	public DcExchangeModel getExchangeModel() {
		return this.exchangeModel;
	}

	public static class Pic {
		
		private int width ;
		
		private int height ;
		
		private String url ;

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
	
	public class DcExchangeModel {
		/**
		 * 
		 * @return
		 */
		public Integer getExIntegral() {
			BigDecimal price = getItemPromPrice();
			if (price == null) {
				price = getItemPrice();
			}
			return price == null? -1: price.multiply(new BigDecimal("100")).intValue();
		}
	}
}
