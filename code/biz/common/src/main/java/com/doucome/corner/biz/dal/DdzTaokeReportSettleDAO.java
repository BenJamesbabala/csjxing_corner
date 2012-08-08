package com.doucome.corner.biz.dal;

import java.math.BigDecimal;
import java.util.List;

import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;


/**
 * �Ա��ͱ������dao.
 * @author ze2200
 *
 */
public interface DdzTaokeReportSettleDAO {
	/**
	 * 
	 * @param settleDO
	 * @return
	 */
	Long insertSettleReport(DdzTaokeReportSettleDO settleDO);
	
	/**
	 * ����id��ѯ
	 * @param id
	 * @return
	 */
	DdzTaokeReportSettleDO getById(Long id);
	/**
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteSettleReport(Long id);
	
	/**
	 * ��ȡͨ��֧����֧�����Կͱ�������.
	 * @param pagination .
	 * @return
	 */
	List<AlipayItemDO> getAliSettleItems(int start , int size);
	
	
	/**
	 * count �����¼
	 * @param condition
	 * @return
	 */
	int countSettlesWithPagination(DdzTaokeReportSettleSearchCondition condition) ;
	
	/**
	 * ��ѯ����
	 * @param condition
	 * @param pagination
	 * @return
	 */
	List<DdzTaokeReportSettleDO> selectSettlesWithPagination(DdzTaokeReportSettleSearchCondition searchCondition , int start, int size) ;
	
	/**
	 * 
	 * @return
	 */
	Integer countAlipayItem();
	/**
	 * ���½���״̬.
	 * @param ids
	 * @param status
	 * @param batchSettleNO
	 * @return
	 */
	int updateSettleStatus(final List<DdzTaokeReportSettleDO> settleDOs);
	/**
	 * 
	 * @param reportIds
	 * @param status
	 * @param alipayBatchNO
	 * @return
	 */
	int updateAlipayResult(final List<DdzTaokeReportSettleDO> settleDOs);
	
	/**
	 * ���½���״̬
	 * @param id
	 * @param settleStatus
	 * @return
	 */
	int updateSettleStatus(List<Integer> settleIds ,String settleStatus) ;
	
	/**
     * �����ʼ�״̬
     * @param id
     * @param settleStatus
     * @return
     */
    int updateEmailStatus(List<Integer> settleIds ,String emailStatus) ;
    
    /**
     * ���¶���״̬
     * @param settleIds
     * @param mobileStatus
     * @return
     */
    int updateMobileStatus(List<Integer> settleIds , String mobileStatus) ;

    /**
     * ͳ��ĳ��Ա�Ľ������
     * @param settleStatus
     * @return
     */
	BigDecimal sumTotalSettleFee(String settleAlipay , String[] settleStatusList);

	/**
	 * ͳ��һ��֧��������Ĵ���
	 * @param settleAlipay
	 * @param settleStatus
	 * @return
	 */
	int countTotalSettle(String settleAlipay, String[] settleStatus);
}
