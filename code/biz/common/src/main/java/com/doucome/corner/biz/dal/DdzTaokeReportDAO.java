package com.doucome.corner.biz.dal;

import java.math.BigDecimal;
import java.util.List;

import com.doucome.corner.biz.common.enums.SumTaokeReportTypeEnums;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleUpdateCondition;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;

/**
 * ����
 * @author langben 2012-3-7
 *
 */
public interface DdzTaokeReportDAO {

	/**
	 * ���뱨��
	 * @param report
	 * @return
	 */
	Long insertReport(DdzTaokeReportDO report) ;
	
	/**
	 * ����
	 * @param settle
	 * @return
	 */
	int settleByTradeId(DdzTaokeReportSettleDO settle) ;
	/**
	 * ����id��ѯ�Կͱ���.
	 * @param reportId .
	 * @return
	 */
	DdzTaokeReportDO getReportById(Long reportId);
	
	/**
	 * ����SettleId��ѯ����
	 * @param settleId
	 * @return
	 */
	List<DdzTaokeReportDO> selectReportsBySettleId(Integer settleId) ;
	
	/**
	 * �����ѯ
	 * @param searchCondition
	 * @param start
	 * @param size
	 * @return
	 */
	List<DdzTaokeReportDO> selectReportsWithPagination(TaokeReportSearchCondition searchCondition , int start , int size ) ;
	/**
	 * 
	 * @param pagination
	 * @return
	 */
	List<AlipayItemDO> getUnMergedReportSettleInfo(int start , int size);
	/**
	 * 
	 * @param searchCondition
	 * @return
	 */
	int countReportsWithPagination(TaokeReportSearchCondition searchCondition) ;
	
	/**
	 * 
	 * @param settleDOs
	 * @return
	 */
	int updateSettleStatusBySettleReport(List<DdzTaokeReportDO> settleDOs);
	/**
	 * 
	 * @param ids
	 * @return
	 */
	int updateTaokeReportSettleId(List<Long> reportIds, Long settleId);
	
	/**
	 * ���½���״̬
	 * @param reportIds
	 * @param settleStatus
	 * @return
	 */
	int updateTaokeReportSettleStatus(List<Long> settleIds , String toSettleStatus) ;
	
	/**
	 * ���½���״̬
	 * @param reportIds
	 * @param settleStatus
	 * @return
	 */
	int updateTaokeReportSettleStatus(DdzTaokeReportSettleUpdateCondition condition , String toSettleStatus) ;
	
	/**
	 * άȨ�˿�
	 * @param reportId
	 * @param refundStats
	 * @return
	 */
	int updateRefundById(Long reportId , String refundStats) ;

	/**
	 * άȨ�˿�
	 * @param ids
	 * @param refundStats
	 * @return
	 */
	int updateRefundByIds(List<Long> ids, String refundStats) ;
	
	/**
	 * ����������
	 * @param searchCondition
	 * @return
	 */
	BigDecimal sumTaokeReportTotalSettleFee(TaokeReportSearchCondition searchCondition , SumTaokeReportTypeEnums sumType);
	
	/**
	 * ����itemId �� userId
	 * @param reportId
	 * @param dcUserId
	 * @param dcItemId
	 * @return
	 */
	int updateDcUserAndItemById(Long reportId , Long dcUserId , Long dcItemId) ;
	
	/**
	 * ���½���״̬
	 * @param reportId
	 * @param settleStatus
	 * @return
	 */
	int updateSettleStatusByIds(List<Long> ids , String settleStatus) ;
	
	/**
	 * ���½������
	 * @param id
	 * @param settleFee
	 * @return
	 */
	int updateSettleFeeById(Long id , BigDecimal settleFee);
	/**
	 * ͳ�ƶ�ޢ�û�Ӷ��
	 * @return
	 */
	List<DdzTaokeReportDO> countDcUserCommission();
}
