package com.doucome.corner.biz.dal;

import java.math.BigDecimal;
import java.util.List;

import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.dataobject.AlipayItemDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;


/**
 * 淘宝客报表结算dao.
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
	 * 根据id查询
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
	 * 获取通过支付宝支付的淘客报表数据.
	 * @param pagination .
	 * @return
	 */
	List<AlipayItemDO> getAliSettleItems(int start , int size);
	
	
	/**
	 * count 结算记录
	 * @param condition
	 * @return
	 */
	int countSettlesWithPagination(DdzTaokeReportSettleSearchCondition condition) ;
	
	/**
	 * 查询结算
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
	 * 更新结算状态.
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
	 * 更新结算状态
	 * @param id
	 * @param settleStatus
	 * @return
	 */
	int updateSettleStatus(List<Integer> settleIds ,String settleStatus) ;
	
	/**
     * 更新邮件状态
     * @param id
     * @param settleStatus
     * @return
     */
    int updateEmailStatus(List<Integer> settleIds ,String emailStatus) ;
    
    /**
     * 更新短信状态
     * @param settleIds
     * @param mobileStatus
     * @return
     */
    int updateMobileStatus(List<Integer> settleIds , String mobileStatus) ;

    /**
     * 统计某会员的结算费用
     * @param settleStatus
     * @return
     */
	BigDecimal sumTotalSettleFee(String settleAlipay , String[] settleStatusList);

	/**
	 * 统计一个支付宝结算的次数
	 * @param settleAlipay
	 * @param settleStatus
	 * @return
	 */
	int countTotalSettle(String settleAlipay, String[] settleStatus);
}
