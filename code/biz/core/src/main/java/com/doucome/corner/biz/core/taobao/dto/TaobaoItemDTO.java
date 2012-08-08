package com.doucome.corner.biz.core.taobao.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.doucome.corner.biz.core.model.AbstractModel;
import com.doucome.corner.biz.core.utils.ReflectUtils;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.ItemImg;
import com.taobao.api.domain.Location;

/**
 * 淘宝宝贝
 * @author shenjia.caosj 2012-4-9
 *
 */
public class TaobaoItemDTO extends AbstractModel {
	
	public TaobaoItemDTO (Item item) {
		ReflectUtils.reflectTo(item, this) ;
	}
	
	/**
	 * 商品url ,,http://item.taobao.com/item.htm?id=4947813209 	
	 */
	private String detailUrl ;
	
	/**
	 * 商品数字id
	 */
	private Long numIid ;
	
	/**
	 * 商品标题,不能超过60字节
	 */
	private String title ;
	
	/**
	 * 卖家昵称
	 */
	private String nick ;
	
	/**
	 * 商品类型(fixed:一口价;auction:拍卖)注：取消团购
	 */
	private String type ;
	
	/**
	 * 商品描述, 字数要大于5个字符，小于25000个字符,这是一个好商品 	
	 */
	private String desc ;
	
	/**
	 * Item的发布时间，目前仅供taobao.item.add和taobao.item.get可用
	 */
	private Date created ;
	
	/**
	 * 是否24小时闪电发货 
	 */
	private Boolean isLightningConsignment ;
	
	/**
	 * 是否分校，非分销商品：0，代销：1，经销：2
	 */
	private Long isFenxiao ;
	
	/**
	 * 商城返点比例，为5的倍数，最低0.5%
	 */
	private Long auctionPoint ;
	
	/**
	 * 属性值别名,比如颜色的自定义名称
	 */
	private String propertyAlias ;
	
	/**
	 * 页面模板id
	 */
	private String templateId ;
	
	/**
	 * 售后服务ID,该字段仅在taobao.item.get接口中返回
	 */
	private Long afterSaleId ;
	
	/**
	 * 标示商品是否为新品。值含义：true-是，false-否。
	 */
	private Boolean isXinpin ;
	
	/**
	 * 标识商品减库存的方式值含义：1-拍下减库存，2-付款减库存。
	 */
	private Long subStock ;

	/**
	 * 用户内店宝贝装修模板id
	 */
	private Long innerShopAuctionTemplateId ;
	
	/**
	 * 用户外店装修模板id
	 */
	private Long outerShopAuctionTemplateId ;
	
	/**
	 * 	商品所属的叶子类目 id
	 */
	private Long cid ;
	
	/**
	 * 商品所属的店铺内卖家自定义类目列表
	 */
	private String sellerCids ;
	
	/**
	 * 商品属性 格式：pid:vid;pid:vid
	 */
	private String props ;
	
	/**
	 * 用户自行输入的类目属性ID串。结构："pid1,pid2,pid3"，如："20000"（表示品牌） 注：通常一个类目下用户可输入的关键属性不超过1个。
	 */
	private String inputPids ;
	
	/**
	 * 耐克;耐克系列;科比系列;科比系列;2K5 	用户自行输入的子属性名和属性值，结构:"父属性值;一级子属性名;一级子属性值;二级子属性名;自定义输入值,....",如：“耐克;耐克系列;科比系列;科比系列;2K5”，input_str需要与input_pids一一对应，注：通常一个类目下用户可输入的关键属性不超过1个。所有属性别名加起来不能超过 3999字节。
	 */
	private String inputStr ;
	
	/**
	 * 	商品主图片地址
	 */
	private String picUrl ;
	
	/**
	 * 商品数量
	 */
	private Long num ;
	
	/**
	 * 有效期,7或者14（默认是14天）
	 */
	private Long validThru ;
	
	/**
	 * 2009-10-22 14:22:06 	上架时间（格式：yyyy-MM-dd HH:mm:ss）
	 */
	private Date listTime ;
	
	/**
	 * 下架时间（格式：yyyy-MM-dd HH:mm:ss）
	 */
	private Date delistTime ;
	
	/**
	 * 商品新旧程度(全新:new，闲置:unused，二手：second)
	 */
	private String stuffStatus ;
	
	/**
	 * 商品所在地
	 */
	private Location location ;
	
	/**
	 * 商品价格，格式：5.00；单位：元；精确到：分
	 */
	private BigDecimal price ;
	
	/**
	 * 平邮费用,格式：5.00；单位：元；精确到：分
	 */
	private BigDecimal postFee ;
	
	/**
	 * 快递费用,格式：5.00；单位：元；精确到：分
	 */
	private BigDecimal expressFee ;
	
	/**
	 * ems费用,格式：5.00；单位：元；精确到：分
	 */
	private BigDecimal emsFee ;
	
	/**
	 * 支持会员打折,true/false
	 */
	private Boolean hasDiscount ;
	
	/**
	 * 运费承担方式,seller（卖家承担），buyer(买家承担）
	 */
	private String freightPayer ;
	
	/**
	 * 是否有发票,true/false
	 */
	private Boolean hasInvoice ;
	
	/**
	 * 是否有保修,true/false
	 */
	private Boolean hasWarranty ;
	
	/**
	 * 橱窗推荐,true/false
	 */
	private Boolean hasShowcase ;
	
	/**
	 * 商品修改时间（格式：yyyy-MM-dd HH:mm:ss）
	 */
	private Date modified ;
	
	/**
	 * 加价幅度。如果为0，代表系统代理幅度。在竞拍中，为了超越上一个出价，会员需要在当前出价上增加金额，这个金额就是加价幅度。卖家在发布宝贝的时候可以自定义加价幅度，也可以让系统自动代理加价。系统自动代理加价的加价幅度随着当前出价金额的增加而增加，我们建议会员使用系统自动代理加价，并请买家在出价前看清楚加价幅度的具体金额。另外需要注意是，此功能只适用于拍卖的商品。以下是系统自动代理加价幅度表：当前价（加价幅度 ） 1-40（ 1 ）、41-100（ 2 ）、101-200（5 ）、201-500 （10）、501-1001（15）、001-2000（25）、2001-5000（50）、5001-10000（100） 10001以上 200
	 */
	private String increment ;
	
	/**
	 * 商品上传后的状态。onsale出售中，instock库中
	 */
	private String approveStatus ;
	
	/**
	 * 	宝贝所属的运费模板ID，如果没有返回则说明没有使用运费模板
	 */
	private Long postageId ;
	
	/**
	 * 宝贝所属产品的id(可能为空). 该字段可以通过taobao.products.search 得到
	 */
	private Long productId ;
	
	/**
	 * 商家外部编码(可与商家外部系统对接)
	 */
	private String outerId ;
	
	/**
	 * 虚拟商品的状态字段
	 */
	private Boolean isVirtual ;
	
	/**
	 * 是否在淘宝显示
	 */
	private Boolean isTaobao ;
	
	/**
	 * 是否在外部网店显示
	 */
	private Boolean isEx ;
	
	/**
	 * 是否定时上架商品
	 */
	private Boolean isTiming ;
	
	/**
	 * 是否是3D淘宝的商品
	 */
	private Boolean is3D ;
	
	/**
	 * 是否淘1站商品
	 */
	private Boolean oneStation ;
	
	/**
	 * 秒杀商品类型。打上秒杀标记的商品，用户只能下架并不能再上架，其他任何编辑或删除操作都不能进行。如果用户想取消秒杀标记，需要联系小二进行操作。如果秒杀结束需要自由编辑请联系活动负责人（小二）去掉秒杀标记。可选类型 web_only(只能通过web网络秒杀) wap_only(只能通过wap网络秒杀) web_and_wap(既能通过web秒杀也能通过wap秒杀)
	 */
	private String secondKill ;
	
	/**
	 * 代充商品类型。只有少数类目下的商品可以标记上此字段，具体哪些类目可以上传可以通过taobao.itemcat.features.get获得。在代充商品的类目下，不传表示不标记商品类型（交易搜索中就不能通过标记搜到相关的交易了）。可选类型： time_card(点卡软件代充) fee_card(话费软件代充)
	 */
	private String autoFill ;
	
	/**
	 * 商品是否违规，违规：true , 不违规：false
	 */
	private Boolean violation ;
	
	/**
	 * Wap宝贝详情 	不带html标签的desc文本信息，该字段只在taobao.item.get接口中返回
	 */
	private String wapDesc ;
	
	/**
	 * http://auction1.wap.taobao.com/auction/item_detail-0db0-1234567.jhtml 	适合wap应用的商品详情url ，该字段只在taobao.item.get接口中返回
	 */
	private String wapDetailUrl ;
	
	/**
	 * 货到付款运费模板ID
	 */
	private Long codPostageId ;
	
	/**
	 * 是否承诺退换货服务!
	 */
	private Boolean sellPromise ;
	
	/**
	 * 商品图片列表(包括主图)。fields中只设置item_img可以返回ItemImg结构体中所有字段，如果设置为item_img.id、item_img.url、item_img.position等形式就只会返回相应的字段
	 */
	private List<ItemImg> itemImgs ; 

	public List<ItemImg> getItemImgs() {
		return itemImgs;
	}

	public void setItemImgs(List<ItemImg> itemImgs) {
		this.itemImgs = itemImgs;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public Long getNumIid() {
		return numIid;
	}

	public void setNumIid(Long numIid) {
		this.numIid = numIid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Boolean getIsLightningConsignment() {
		return isLightningConsignment;
	}

	public void setIsLightningConsignment(Boolean isLightningConsignment) {
		this.isLightningConsignment = isLightningConsignment;
	}

	public Long getIsFenxiao() {
		return isFenxiao;
	}

	public void setIsFenxiao(Long isFenxiao) {
		this.isFenxiao = isFenxiao;
	}

	public Long getAuctionPoint() {
		return auctionPoint;
	}

	public void setAuctionPoint(Long auctionPoint) {
		this.auctionPoint = auctionPoint;
	}

	public String getPropertyAlias() {
		return propertyAlias;
	}

	public void setPropertyAlias(String propertyAlias) {
		this.propertyAlias = propertyAlias;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public Long getAfterSaleId() {
		return afterSaleId;
	}

	public void setAfterSaleId(Long afterSaleId) {
		this.afterSaleId = afterSaleId;
	}

	public Boolean getIsXinpin() {
		return isXinpin;
	}

	public void setIsXinpin(Boolean isXinpin) {
		this.isXinpin = isXinpin;
	}

	public Long getSubStock() {
		return subStock;
	}

	public void setSubStock(Long subStock) {
		this.subStock = subStock;
	}

	public Long getInnerShopAuctionTemplateId() {
		return innerShopAuctionTemplateId;
	}

	public void setInnerShopAuctionTemplateId(Long innerShopAuctionTemplateId) {
		this.innerShopAuctionTemplateId = innerShopAuctionTemplateId;
	}

	public Long getOuterShopAuctionTemplateId() {
		return outerShopAuctionTemplateId;
	}

	public void setOuterShopAuctionTemplateId(Long outerShopAuctionTemplateId) {
		this.outerShopAuctionTemplateId = outerShopAuctionTemplateId;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getSellerCids() {
		return sellerCids;
	}

	public void setSellerCids(String sellerCids) {
		this.sellerCids = sellerCids;
	}

	public String getProps() {
		return props;
	}

	public void setProps(String props) {
		this.props = props;
	}

	public String getInputPids() {
		return inputPids;
	}

	public void setInputPids(String inputPids) {
		this.inputPids = inputPids;
	}

	public String getInputStr() {
		return inputStr;
	}

	public void setInputStr(String inputStr) {
		this.inputStr = inputStr;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Long getValidThru() {
		return validThru;
	}

	public void setValidThru(Long validThru) {
		this.validThru = validThru;
	}

	public Date getListTime() {
		return listTime;
	}

	public void setListTime(Date listTime) {
		this.listTime = listTime;
	}

	public Date getDelistTime() {
		return delistTime;
	}

	public void setDelistTime(Date delistTime) {
		this.delistTime = delistTime;
	}

	public String getStuffStatus() {
		return stuffStatus;
	}

	public void setStuffStatus(String stuffStatus) {
		this.stuffStatus = stuffStatus;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPostFee() {
		return postFee;
	}

	public void setPostFee(BigDecimal postFee) {
		this.postFee = postFee;
	}

	public BigDecimal getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(BigDecimal expressFee) {
		this.expressFee = expressFee;
	}

	public BigDecimal getEmsFee() {
		return emsFee;
	}

	public void setEmsFee(BigDecimal emsFee) {
		this.emsFee = emsFee;
	}

	public Boolean getHasDiscount() {
		return hasDiscount;
	}

	public void setHasDiscount(Boolean hasDiscount) {
		this.hasDiscount = hasDiscount;
	}

	public String getFreightPayer() {
		return freightPayer;
	}

	public void setFreightPayer(String freightPayer) {
		this.freightPayer = freightPayer;
	}

	public Boolean getHasInvoice() {
		return hasInvoice;
	}

	public void setHasInvoice(Boolean hasInvoice) {
		this.hasInvoice = hasInvoice;
	}

	public Boolean getHasWarranty() {
		return hasWarranty;
	}

	public void setHasWarranty(Boolean hasWarranty) {
		this.hasWarranty = hasWarranty;
	}

	public Boolean getHasShowcase() {
		return hasShowcase;
	}

	public void setHasShowcase(Boolean hasShowcase) {
		this.hasShowcase = hasShowcase;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Long getPostageId() {
		return postageId;
	}

	public void setPostageId(Long postageId) {
		this.postageId = postageId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public Boolean getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(Boolean isVirtual) {
		this.isVirtual = isVirtual;
	}

	public Boolean getIsTaobao() {
		return isTaobao;
	}

	public void setIsTaobao(Boolean isTaobao) {
		this.isTaobao = isTaobao;
	}

	public Boolean getIsEx() {
		return isEx;
	}

	public void setIsEx(Boolean isEx) {
		this.isEx = isEx;
	}

	public Boolean getIsTiming() {
		return isTiming;
	}

	public void setIsTiming(Boolean isTiming) {
		this.isTiming = isTiming;
	}

	public Boolean getIs3D() {
		return is3D;
	}

	public void setIs3D(Boolean is3d) {
		is3D = is3d;
	}

	public Boolean getOneStation() {
		return oneStation;
	}

	public void setOneStation(Boolean oneStation) {
		this.oneStation = oneStation;
	}

	public String getSecondKill() {
		return secondKill;
	}

	public void setSecondKill(String secondKill) {
		this.secondKill = secondKill;
	}

	public String getAutoFill() {
		return autoFill;
	}

	public void setAutoFill(String autoFill) {
		this.autoFill = autoFill;
	}

	public Boolean getViolation() {
		return violation;
	}

	public void setViolation(Boolean violation) {
		this.violation = violation;
	}

	public String getWapDesc() {
		return wapDesc;
	}

	public void setWapDesc(String wapDesc) {
		this.wapDesc = wapDesc;
	}

	public String getWapDetailUrl() {
		return wapDetailUrl;
	}

	public void setWapDetailUrl(String wapDetailUrl) {
		this.wapDetailUrl = wapDetailUrl;
	}

	public Long getCodPostageId() {
		return codPostageId;
	}

	public void setCodPostageId(Long codPostageId) {
		this.codPostageId = codPostageId;
	}

	public Boolean getSellPromise() {
		return sellPromise;
	}

	public void setSellPromise(Boolean sellPromise) {
		this.sellPromise = sellPromise;
	}

	
	
}
