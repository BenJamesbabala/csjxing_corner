package com.doucome.corner.biz.core.taobao.model;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.doucome.corner.biz.core.taobao.enums.TaobaoSellerStartCreditEnums;
import com.doucome.corner.biz.dal.model.AbstractModel;

/**
 * �Ա��Ͳ�ѯ��Ʒ����
 * @author langben 2012-2-27
 *
 */
public class TaokeItemSearchCondition extends AbstractModel {

	
	/**
	 * ��Ʒ�����а����Ĺؼ���. ע��:��ѯʱkeyword,cid����ѡ������һ������ 
	 */
	private String keyword ;
	
	/**
	 * ��Ʒ��������id
	 */
	private Long cid ;
	
	/**
	 * ��ʼ�۸�.����۸����ʱ,��ע����ʼ�۸����߼۸����һ����,���� start_price <= end_price
	 */
	private BigDecimal startPrice ;
	
	/**
	 * ��߼۸�
	 */
	private BigDecimal endPrice ;
	
	/**
	 * �Ƿ��Զ�����
	 */
	private Boolean autoSend ;
	
	/**
	 * ��Ʒ���ڵ�,���� 		
	 */
	private String area ;
	
	/**
	 * ��������: 1heart(һ��) 2heart (����) 3heart(����) 4heart(����) 5heart(����) 1diamond(һ��) 2diamond(����) 3diamond(����) 4diamond(����) 5diamond(����) 1crown(һ��) 2crown(����) 3crown(����) 4crown(�Ĺ�) 5crown(���) 1goldencrown(һ�ƹ�) 2goldencrown(���ƹ�) 3goldencrown(���ƹ�) 4goldencrown(�Ļƹ�) 5goldencrown(��ƹ�)
	 * @see {@link TaobaoSellerStartCreditEnums}
	 */
	private String startCredit ;
	
	/**
	 * ��ѡֵ��start_creditһ��.start_credit��ֵһ��ҪС�ڻ����end_credit��ֵ��ע��end_credit��start_creditһ��ʹ�ò���Ч
	 * @see {@link TaobaoSellerStartCreditEnums}
	 */
	private String endCredit ;
	
	/**
	 * String 	��ѡ 	price_desc 		Ĭ������:default price_desc(�۸�Ӹߵ���) price_asc(�۸�ӵ͵���) credit_desc(���õȼ��Ӹߵ���) commissionRate_desc(Ӷ����ʴӸߵ���) commissionRate_asc(Ӷ����ʴӵ͵���) commissionNum_desc(�ɽ����ɸߵ���) commissionNum_asc(�ɽ����ӵ͵���) commissionVolume_desc(��֧��Ӷ��Ӹߵ���) commissionVolume_asc(��֧��Ӷ��ӵ͵���) delistTime_desc(��Ʒ�¼�ʱ��Ӹߵ���) delistTime_asc(��Ʒ�¼�ʱ��ӵ͵���)
	 */
	private String sort ;
	
	/**
	 * �Ƿ��ѯ��������
	 */
	private Boolean guarantee ;
	
	/**
	 * ��ʼ�ۼ��ƹ���Ӷ��.ע�����ص�������30�����ۼ��ƹ������߸��ֶ�Ҫ������ۼ��ƹ���һ��ʹ�ò���Ч.,2345
	 */
	private Long startCommissionRate ;
	
	/**
	 * ���Ӷ�����ѡ��磺2345��ʾ23.45%��ע��Ҫ��ʼӶ����ʺ����Ӷ�����һ�����ò���Ч�� ,2345 		
	 */
	private Long endCommissionRate ;
	
	/**
	 *  1000  ,��ʼ�ۼ��ƹ���Ӷ��.ע�����ص�������30�����ۼ��ƹ������߸��ֶ�Ҫ������ۼ��ƹ���һ��ʹ�ò���Ч
	 */
	private Long startCommissionNum ;
	
	/**
	 * ��ѡ 	10000 		����ۼ��ƹ�Ӷ��ѡ��
	 */
	private Long endCommissionNum ;
	
	/**
	 * �ۼ��ƹ�����Χ��ʼ
	 */
	private Long startTotalnum ;
	
	/**
	 * �ۼ��ƹ�����Χ����
	 */
	private Long endTotalnum ;
	
	/**
	 * �Ƿ�֧�ֵּ�ȯ������Ϊtrue��ʾ����Ʒ֧�ֵּ�ȯ������Ϊfalse�����ñ�ʾ���ж��������
	 */
	private  Boolean cashCoupon ;
	
	/**
	 * �Ƿ�֧��VIP��������Ϊtrue��ʾ����Ʒ֧��VIP��������Ϊfalse�����ñ�ʾ���ж��������
	 */
	private Boolean vipCard ;
	
	/**
	 * �Ƿ�����Ʒ������Ϊtrue��ʾ����Ʒ�����ں�����Ʒ��Ĭ��Ϊfalse
	 */
	private Boolean overseasItem ;
	
	/**
	 * �Ƿ�֧��7���˻�������Ϊtrue��ʾ����Ʒ֧��7���˻�������Ϊfalse�����ñ�ʾ���ж��������
	 */
	private Boolean sevendaysReturn ;
	
	/**
	 * �Ƿ���ʵ����(��:�����⸶)��Ʒ������Ϊtrue��ʾ����Ʒ����ʵ��������Ʒ������Ϊfalse�����ñ�ʾ���ж��������
	 */
	private Boolean realDescribe ;
	
	/**
	 * �Ƿ�30��ά�ޣ�����Ϊtrue��ʾ����Ʒ��֧��30��ά�ޣ�����Ϊfalse�����ñ�ʾ���ж��������
	 */
	private Boolean onemonthRepair ;
	
	/**
	 * �Ƿ�֧�ֻ����������Ϊtrue��ʾ����Ʒ��֧�ֻ����������Ϊfalse�����ñ�ʾ���ж��������
	 */
	private Boolean cashOndelivery ;
	
	/**
	 * �Ƿ��̳ǵ���Ʒ������Ϊtrue��ʾ����Ʒ�������Ա��̳ǵ���Ʒ������Ϊfalse�����ñ�ʾ���ж��������
	 */
	private Boolean mallItem ;
	
	/**
	 * ��ʶһ��Ӧ���Ƿ��������߻����ֻ�Ӧ��,�����true���ʹ������������ܵ����.�������ֵ,��Ĭ����false.
	 */
	private Boolean isMobile ;
	
	/**
	 * 
	 */
	private String outerCode ;

	public void complete() throws IllegalArgumentException {
		if(StringUtils.isBlank(keyword) && cid == null){
			throw new IllegalArgumentException("input keyword and cid both null .")  ;
		}
	}
	

	public String getOuterCode() {
		return outerCode;
	}

	public void setOuterCode(String outerCode) {
		this.outerCode = outerCode;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}

	public BigDecimal getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}

	public Boolean getAutoSend() {
		return autoSend;
	}

	public void setAutoSend(Boolean autoSend) {
		this.autoSend = autoSend;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStartCredit() {
		return startCredit;
	}

	public void setStartCredit(String startCredit) {
		this.startCredit = startCredit;
	}

	public String getEndCredit() {
		return endCredit;
	}

	public void setEndCredit(String endCredit) {
		this.endCredit = endCredit;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Boolean getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(Boolean guarantee) {
		this.guarantee = guarantee;
	}

	
	public Long getStartCommissionRate() {
		return startCommissionRate;
	}

	public void setStartCommissionRate(Long startCommissionRate) {
		this.startCommissionRate = startCommissionRate;
	}

	public Long getEndCommissionRate() {
		return endCommissionRate;
	}

	public void setEndCommissionRate(Long endCommissionRate) {
		this.endCommissionRate = endCommissionRate;
	}

	public Long getStartCommissionNum() {
		return startCommissionNum;
	}

	public void setStartCommissionNum(Long startCommissionNum) {
		this.startCommissionNum = startCommissionNum;
	}

	public Long getEndCommissionNum() {
		return endCommissionNum;
	}

	public void setEndCommissionNum(Long endCommissionNum) {
		this.endCommissionNum = endCommissionNum;
	}

	public Long getStartTotalnum() {
		return startTotalnum;
	}

	public void setStartTotalnum(Long startTotalnum) {
		this.startTotalnum = startTotalnum;
	}

	public Long getEndTotalnum() {
		return endTotalnum;
	}

	public void setEndTotalnum(Long endTotalnum) {
		this.endTotalnum = endTotalnum;
	}

	public Boolean getCashCoupon() {
		return cashCoupon;
	}

	public void setCashCoupon(Boolean cashCoupon) {
		this.cashCoupon = cashCoupon;
	}

	public Boolean getVipCard() {
		return vipCard;
	}

	public void setVipCard(Boolean vipCard) {
		this.vipCard = vipCard;
	}

	public Boolean getOverseasItem() {
		return overseasItem;
	}

	public void setOverseasItem(Boolean overseasItem) {
		this.overseasItem = overseasItem;
	}

	public Boolean getSevendaysReturn() {
		return sevendaysReturn;
	}

	public void setSevendaysReturn(Boolean sevendaysReturn) {
		this.sevendaysReturn = sevendaysReturn;
	}

	public Boolean getRealDescribe() {
		return realDescribe;
	}

	public void setRealDescribe(Boolean realDescribe) {
		this.realDescribe = realDescribe;
	}

	public Boolean getOnemonthRepair() {
		return onemonthRepair;
	}

	public void setOnemonthRepair(Boolean onemonthRepair) {
		this.onemonthRepair = onemonthRepair;
	}

	public Boolean getCashOndelivery() {
		return cashOndelivery;
	}

	public void setCashOndelivery(Boolean cashOndelivery) {
		this.cashOndelivery = cashOndelivery;
	}

	public Boolean getMallItem() {
		return mallItem;
	}

	public void setMallItem(Boolean mallItem) {
		this.mallItem = mallItem;
	}

	public Boolean getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(Boolean isMobile) {
		this.isMobile = isMobile;
	}
		
}
