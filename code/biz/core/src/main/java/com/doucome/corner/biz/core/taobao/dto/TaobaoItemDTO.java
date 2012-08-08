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
 * �Ա�����
 * @author shenjia.caosj 2012-4-9
 *
 */
public class TaobaoItemDTO extends AbstractModel {
	
	public TaobaoItemDTO (Item item) {
		ReflectUtils.reflectTo(item, this) ;
	}
	
	/**
	 * ��Ʒurl ,,http://item.taobao.com/item.htm?id=4947813209 	
	 */
	private String detailUrl ;
	
	/**
	 * ��Ʒ����id
	 */
	private Long numIid ;
	
	/**
	 * ��Ʒ����,���ܳ���60�ֽ�
	 */
	private String title ;
	
	/**
	 * �����ǳ�
	 */
	private String nick ;
	
	/**
	 * ��Ʒ����(fixed:һ�ڼ�;auction:����)ע��ȡ���Ź�
	 */
	private String type ;
	
	/**
	 * ��Ʒ����, ����Ҫ����5���ַ���С��25000���ַ�,����һ������Ʒ 	
	 */
	private String desc ;
	
	/**
	 * Item�ķ���ʱ�䣬Ŀǰ����taobao.item.add��taobao.item.get����
	 */
	private Date created ;
	
	/**
	 * �Ƿ�24Сʱ���緢�� 
	 */
	private Boolean isLightningConsignment ;
	
	/**
	 * �Ƿ��У���Ƿ�����Ʒ��0��������1��������2
	 */
	private Long isFenxiao ;
	
	/**
	 * �̳Ƿ��������Ϊ5�ı��������0.5%
	 */
	private Long auctionPoint ;
	
	/**
	 * ����ֵ����,������ɫ���Զ�������
	 */
	private String propertyAlias ;
	
	/**
	 * ҳ��ģ��id
	 */
	private String templateId ;
	
	/**
	 * �ۺ����ID,���ֶν���taobao.item.get�ӿ��з���
	 */
	private Long afterSaleId ;
	
	/**
	 * ��ʾ��Ʒ�Ƿ�Ϊ��Ʒ��ֵ���壺true-�ǣ�false-��
	 */
	private Boolean isXinpin ;
	
	/**
	 * ��ʶ��Ʒ�����ķ�ʽֵ���壺1-���¼���棬2-�������档
	 */
	private Long subStock ;

	/**
	 * �û��ڵ걦��װ��ģ��id
	 */
	private Long innerShopAuctionTemplateId ;
	
	/**
	 * �û����װ��ģ��id
	 */
	private Long outerShopAuctionTemplateId ;
	
	/**
	 * 	��Ʒ������Ҷ����Ŀ id
	 */
	private Long cid ;
	
	/**
	 * ��Ʒ�����ĵ����������Զ�����Ŀ�б�
	 */
	private String sellerCids ;
	
	/**
	 * ��Ʒ���� ��ʽ��pid:vid;pid:vid
	 */
	private String props ;
	
	/**
	 * �û������������Ŀ����ID�����ṹ��"pid1,pid2,pid3"���磺"20000"����ʾƷ�ƣ� ע��ͨ��һ����Ŀ���û�������Ĺؼ����Բ�����1����
	 */
	private String inputPids ;
	
	/**
	 * �Ϳ�;�Ϳ�ϵ��;�Ʊ�ϵ��;�Ʊ�ϵ��;2K5 	�û������������������������ֵ���ṹ:"������ֵ;һ����������;һ��������ֵ;������������;�Զ�������ֵ,....",�磺���Ϳ�;�Ϳ�ϵ��;�Ʊ�ϵ��;�Ʊ�ϵ��;2K5����input_str��Ҫ��input_pidsһһ��Ӧ��ע��ͨ��һ����Ŀ���û�������Ĺؼ����Բ�����1�����������Ա������������ܳ��� 3999�ֽڡ�
	 */
	private String inputStr ;
	
	/**
	 * 	��Ʒ��ͼƬ��ַ
	 */
	private String picUrl ;
	
	/**
	 * ��Ʒ����
	 */
	private Long num ;
	
	/**
	 * ��Ч��,7����14��Ĭ����14�죩
	 */
	private Long validThru ;
	
	/**
	 * 2009-10-22 14:22:06 	�ϼ�ʱ�䣨��ʽ��yyyy-MM-dd HH:mm:ss��
	 */
	private Date listTime ;
	
	/**
	 * �¼�ʱ�䣨��ʽ��yyyy-MM-dd HH:mm:ss��
	 */
	private Date delistTime ;
	
	/**
	 * ��Ʒ�¾ɳ̶�(ȫ��:new������:unused�����֣�second)
	 */
	private String stuffStatus ;
	
	/**
	 * ��Ʒ���ڵ�
	 */
	private Location location ;
	
	/**
	 * ��Ʒ�۸񣬸�ʽ��5.00����λ��Ԫ����ȷ������
	 */
	private BigDecimal price ;
	
	/**
	 * ƽ�ʷ���,��ʽ��5.00����λ��Ԫ����ȷ������
	 */
	private BigDecimal postFee ;
	
	/**
	 * ��ݷ���,��ʽ��5.00����λ��Ԫ����ȷ������
	 */
	private BigDecimal expressFee ;
	
	/**
	 * ems����,��ʽ��5.00����λ��Ԫ����ȷ������
	 */
	private BigDecimal emsFee ;
	
	/**
	 * ֧�ֻ�Ա����,true/false
	 */
	private Boolean hasDiscount ;
	
	/**
	 * �˷ѳе���ʽ,seller�����ҳе�����buyer(��ҳе���
	 */
	private String freightPayer ;
	
	/**
	 * �Ƿ��з�Ʊ,true/false
	 */
	private Boolean hasInvoice ;
	
	/**
	 * �Ƿ��б���,true/false
	 */
	private Boolean hasWarranty ;
	
	/**
	 * �����Ƽ�,true/false
	 */
	private Boolean hasShowcase ;
	
	/**
	 * ��Ʒ�޸�ʱ�䣨��ʽ��yyyy-MM-dd HH:mm:ss��
	 */
	private Date modified ;
	
	/**
	 * �Ӽ۷��ȡ����Ϊ0������ϵͳ������ȡ��ھ����У�Ϊ�˳�Խ��һ�����ۣ���Ա��Ҫ�ڵ�ǰ���������ӽ���������ǼӼ۷��ȡ������ڷ���������ʱ������Զ���Ӽ۷��ȣ�Ҳ������ϵͳ�Զ�����Ӽۡ�ϵͳ�Զ�����Ӽ۵ļӼ۷������ŵ�ǰ���۽������Ӷ����ӣ����ǽ����Աʹ��ϵͳ�Զ�����Ӽۣ���������ڳ���ǰ������Ӽ۷��ȵľ����������Ҫע���ǣ��˹���ֻ��������������Ʒ��������ϵͳ�Զ�����Ӽ۷��ȱ���ǰ�ۣ��Ӽ۷��� �� 1-40�� 1 ����41-100�� 2 ����101-200��5 ����201-500 ��10����501-1001��15����001-2000��25����2001-5000��50����5001-10000��100�� 10001���� 200
	 */
	private String increment ;
	
	/**
	 * ��Ʒ�ϴ����״̬��onsale�����У�instock����
	 */
	private String approveStatus ;
	
	/**
	 * 	�����������˷�ģ��ID�����û�з�����˵��û��ʹ���˷�ģ��
	 */
	private Long postageId ;
	
	/**
	 * ����������Ʒ��id(����Ϊ��). ���ֶο���ͨ��taobao.products.search �õ�
	 */
	private Long productId ;
	
	/**
	 * �̼��ⲿ����(�����̼��ⲿϵͳ�Խ�)
	 */
	private String outerId ;
	
	/**
	 * ������Ʒ��״̬�ֶ�
	 */
	private Boolean isVirtual ;
	
	/**
	 * �Ƿ����Ա���ʾ
	 */
	private Boolean isTaobao ;
	
	/**
	 * �Ƿ����ⲿ������ʾ
	 */
	private Boolean isEx ;
	
	/**
	 * �Ƿ�ʱ�ϼ���Ʒ
	 */
	private Boolean isTiming ;
	
	/**
	 * �Ƿ���3D�Ա�����Ʒ
	 */
	private Boolean is3D ;
	
	/**
	 * �Ƿ���1վ��Ʒ
	 */
	private Boolean oneStation ;
	
	/**
	 * ��ɱ��Ʒ���͡�������ɱ��ǵ���Ʒ���û�ֻ���¼ܲ��������ϼܣ������κα༭��ɾ�����������ܽ��С�����û���ȡ����ɱ��ǣ���Ҫ��ϵС�����в����������ɱ������Ҫ���ɱ༭����ϵ������ˣ�С����ȥ����ɱ��ǡ���ѡ���� web_only(ֻ��ͨ��web������ɱ) wap_only(ֻ��ͨ��wap������ɱ) web_and_wap(����ͨ��web��ɱҲ��ͨ��wap��ɱ)
	 */
	private String secondKill ;
	
	/**
	 * ������Ʒ���͡�ֻ��������Ŀ�µ���Ʒ���Ա���ϴ��ֶΣ�������Щ��Ŀ�����ϴ�����ͨ��taobao.itemcat.features.get��á��ڴ�����Ʒ����Ŀ�£�������ʾ�������Ʒ���ͣ����������оͲ���ͨ������ѵ���صĽ����ˣ�����ѡ���ͣ� time_card(�㿨�������) fee_card(�����������)
	 */
	private String autoFill ;
	
	/**
	 * ��Ʒ�Ƿ�Υ�棬Υ�棺true , ��Υ�棺false
	 */
	private Boolean violation ;
	
	/**
	 * Wap�������� 	����html��ǩ��desc�ı���Ϣ�����ֶ�ֻ��taobao.item.get�ӿ��з���
	 */
	private String wapDesc ;
	
	/**
	 * http://auction1.wap.taobao.com/auction/item_detail-0db0-1234567.jhtml 	�ʺ�wapӦ�õ���Ʒ����url �����ֶ�ֻ��taobao.item.get�ӿ��з���
	 */
	private String wapDetailUrl ;
	
	/**
	 * ���������˷�ģ��ID
	 */
	private Long codPostageId ;
	
	/**
	 * �Ƿ��ŵ�˻�������!
	 */
	private Boolean sellPromise ;
	
	/**
	 * ��ƷͼƬ�б�(������ͼ)��fields��ֻ����item_img���Է���ItemImg�ṹ���������ֶΣ��������Ϊitem_img.id��item_img.url��item_img.position����ʽ��ֻ�᷵����Ӧ���ֶ�
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
