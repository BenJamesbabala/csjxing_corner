package com.doucome.corner.web.bops.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.constant.SettleConstant;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.enums.TrueFalseEnums;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.UUIDUtils;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleUpdateCondition;
import com.doucome.corner.biz.dal.dataobject.DdzJfbSettleRecordDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.dal.model.KeyValuePair;
import com.doucome.corner.biz.dcome.enums.DcExchangeApproveStatusEnums;
import com.doucome.corner.biz.dcome.service.DcUserExchangeApproveService;
import com.doucome.corner.biz.dcome.service.DcUserIntegralRecordService;
import com.doucome.corner.biz.zhe.exception.DdzDuplicateKeyException;
import com.doucome.corner.biz.zhe.model.JfbExportModel;
import com.doucome.corner.biz.zhe.service.DdzJfbSettleRecordService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportSettleService;
import com.doucome.corner.biz.zhe.utils.DdzTaokeReportUtils;

public class JfbSettleBO {
	
	private static final Log syncReportLog = LogFactory.getLog(LogConstant.task_syncReport_log) ;

	@Autowired
	private DdzTaokeReportSettleService ddzTaokeReportSettleService ;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ; 
	
	@Autowired
	private DdzJfbSettleRecordService ddzJfbSettleRecordService ;
	
	@Autowired
	private DcUserIntegralRecordService dcUserIntegralRecordService ;

	@Autowired
	private DcUserExchangeApproveService dcUserExchangeApproveService ;
	
	/**
	 * 集分宝计算的数据
	 * @return
	 */
	@Transactional(rollbackFor=RuntimeException.class)
	public JfbExportModel exportJfbSettle(){
		
		DdzTaokeReportSettleSearchCondition condition = new DdzTaokeReportSettleSearchCondition() ;
		condition.setSettleStatus(SettleStatusEnums.UNSETTLE.getValue()) ;
		condition.setOutcodeTypeList(SettleConstant.JFB_SETTLE_OUTCODE_TYPES) ;
		JfbExportModel exportModel = new JfbExportModel() ;
		//
		List<DdzTaokeReportSettleDO> jfbSettleList = ddzTaokeReportService.getSettleReports(condition) ;
		
		String settleBatchno = UUIDUtils.random20Num() ;

		if(CollectionUtils.isNotEmpty(jfbSettleList)){
			
			int planJfbCount = 0 ;
			
			for(int i = 0 ; i < jfbSettleList.size() ; i += 20){
				List<Long> idList = new ArrayList<Long>() ;
				//分批更新
				int startIndex = i ;
				int endIndex = i + 20 - 1 ;
				if(endIndex >= jfbSettleList.size()){
					endIndex = jfbSettleList.size() - 1 ;
				}
				for(int m = startIndex ; m <= endIndex ; m ++){
					
					DdzTaokeReportSettleDO settle = jfbSettleList.get(m) ;
					Long id = settle.getId() ;
					planJfbCount += settle.getSettleJfb() ;
					idList.add(id) ;
					
				}
				
				//状态更新成集分宝结算中
				DdzTaokeReportSettleUpdateCondition updateConfition = new DdzTaokeReportSettleUpdateCondition() ;
				updateConfition.setSettleIds(idList) ;
				int effectCount = ddzTaokeReportSettleService.updateTaokeSettleStatus(updateConfition, SettleStatusEnums.SETTLE_JFB_PENDING , settleBatchno) ;
				if(effectCount != idList.size()){
					throw new IllegalStateException("update status count is not equal input count .") ;
				}
			}
			
			//插入记录
			DdzJfbSettleRecordDO settleRecord = new DdzJfbSettleRecordDO() ;
			settleRecord.setSettleBatchno(settleBatchno) ;
			settleRecord.setPlanAlipayCount(jfbSettleList.size()) ;
			settleRecord.setPlanJfbCount(planJfbCount) ;
			long id = ddzJfbSettleRecordService.createRecord(settleRecord) ;
			
		}
		
		//
		exportModel.setSettleList(jfbSettleList) ;
		exportModel.setSettleBatchno(settleBatchno) ;
		return exportModel ;
	}
	
	/**
	 * 部分成功，上传成功清单
	 * @param id 
	 * @param tradeNo
	 * @param successList
	 */
	@Transactional(rollbackFor=RuntimeException.class)
	public void uploadJfbSettleSuccessList(Long id , String tradeNo , List<KeyValuePair> successList , boolean allSuccessful){
		
		if(IDUtils.isNotCorrect(id)){
			throw new IllegalArgumentException("ddz.jfb.settle.id.required") ;
		}
		
		if(StringUtils.isBlank(tradeNo)){
			throw new IllegalArgumentException("ddz.jfb.settle.tradeNo.required") ;
		}
		
		DdzJfbSettleRecordDO jfbSettleRecord = ddzJfbSettleRecordService.getRecordById(id) ;
		
		if(jfbSettleRecord == null || StringUtils.equals(jfbSettleRecord.getIsSettled() , TrueFalseEnums.TRUE.getValue())){
			throw new IllegalArgumentException("ddz.jfb.settle.cantSettle") ;
		}
		
		String settleBatchno = jfbSettleRecord.getSettleBatchno() ;
		
		if(StringUtils.isBlank(settleBatchno)){
			throw new IllegalArgumentException("ddz.jfb.settle.batchno.required") ;
		}
		
		Map<String,String> successMap = new HashMap<String,String>() ;
		if(CollectionUtils.isNotEmpty(successList)){
			for(KeyValuePair kvp : successList){
				successMap.put(StringUtils.lowerCase(kvp.getKey()), kvp.getValue()) ;
			}
		}
		
		//开始结算
		DdzTaokeReportSettleSearchCondition condition = new DdzTaokeReportSettleSearchCondition() ;
		condition.setSettleBatchno(settleBatchno) ;
		condition.setOutcodeTypeList(SettleConstant.JFB_SETTLE_OUTCODE_TYPES) ;
		condition.setSettleStatus(SettleStatusEnums.SETTLE_JFB_PENDING.getValue()) ;
		
		//查询该结算批次的数据，同时结算状态为集分宝结算中
		List<DdzTaokeReportSettleDO> settleList = ddzTaokeReportService.getSettleReports(condition) ;
		
		if(CollectionUtils.isEmpty(settleList)) {
			throw new IllegalArgumentException("ddz.jfb.settle.data.empty") ;
		}
		
		int successAlipayCount = 0 ;
		int successJfbCount = 0 ;
		
		//每次更新20个
		for(int i = 0 ; i < settleList.size() ; i += 20){
			
			List<Long> settleIdList = new ArrayList<Long>() ;
			//豆蔻的兑换类型（U标志）
			List<Long> dcExchangeSettleIdList = new ArrayList<Long>() ; 
			//分批更新
			int startIndex = i ;
			int endIndex = i + 20 - 1 ;
			if(endIndex >= settleList.size()){
				endIndex = settleList.size() - 1 ;
			}
			
			for(int m = startIndex ; m <= endIndex ; m ++){
				
				DdzTaokeReportSettleDO settle = settleList.get(m) ;
				Long settleId = settle.getId() ;
				String settleAlipay = StringUtils.lowerCase(settle.getSettleAlipay()) ;
				
				if(allSuccessful || successMap.containsKey(settleAlipay)) {
				
					settleIdList.add(settleId) ;
					successAlipayCount ++ ;
					successJfbCount += NumberUtils.parseInt(settle.getSettleJfb()) ;
					
					//
					if(OutCodeEnums.toOutCodeEnums(settle.getOutcodeType()) == OutCodeEnums.DOUCOME_USER_ID){
						dcExchangeSettleIdList.add(settleId) ;
					}
					
				}
			}
			
			//更新结算记录状态
			ddzTaokeReportSettleService.updateTaokeSettleStatus(settleIdList, SettleStatusEnums.SETTLE_SUCCESS) ;
			
			//更新豆蔻用户兑换的-状态标志-为已经发放
			if(CollectionUtils.isNotEmpty(dcExchangeSettleIdList)) {

				Long[] dcExchangeSettleIdArray = ArrayStringUtils.toLongArray(dcExchangeSettleIdList) ;
				dcUserIntegralRecordService.updateStatusBySettleIdList(DcExchangeApproveStatusEnums.SENDED, dcExchangeSettleIdArray) ;
				dcUserExchangeApproveService.updateStatusBySettleIdList(DcExchangeApproveStatusEnums.SENDED, dcExchangeSettleIdArray) ;
				
			}
		}
		
		//
		if(!allSuccessful){
			//将失败的订正为取消
			DdzTaokeReportSettleSearchCondition trsc = new DdzTaokeReportSettleSearchCondition() ;
			trsc.setSettleBatchno(settleBatchno) ;
			trsc.setSettleStatus(SettleStatusEnums.SETTLE_JFB_PENDING.getValue()) ;
			List<DdzTaokeReportSettleDO> failList = ddzTaokeReportService.getSettleReports(trsc) ;
			
			List<Long> failSettleIdList = DdzTaokeReportUtils.getSettleIdList(failList) ;
			
			DdzTaokeReportSettleUpdateCondition updateCondition = new DdzTaokeReportSettleUpdateCondition() ;
			updateCondition.setEqualSettleStatus(SettleStatusEnums.SETTLE_JFB_PENDING.getValue()) ;
			updateCondition.setSettleIds(failSettleIdList) ;
			ddzTaokeReportSettleService.updateTaokeSettleStatus(updateCondition, SettleStatusEnums.SETTLE_CANCEL, null) ;
			
			//更新豆蔻用户兑换的-状态标志-为取消
			List<Long> dcExchangeFailSettleIdList = DdzTaokeReportUtils.getSettleIdList(failList, OutCodeEnums.DOUCOME_USER_ID) ;
			if(CollectionUtils.isNotEmpty(dcExchangeFailSettleIdList)) {
				Long[] dcExchangeFailSettleIdArray = ArrayStringUtils.toLongArray(dcExchangeFailSettleIdList) ;
				dcUserIntegralRecordService.updateStatusBySettleIdList(DcExchangeApproveStatusEnums.CANCEL, dcExchangeFailSettleIdArray) ;
				dcUserExchangeApproveService.updateStatusBySettleIdList(DcExchangeApproveStatusEnums.CANCEL, dcExchangeFailSettleIdArray) ;
			}
		}
		
		jfbSettleRecord.setSuccessAlipayCount(successAlipayCount) ;
		jfbSettleRecord.setSuccessJfbCount(successJfbCount) ;
		jfbSettleRecord.setTradeNo(tradeNo) ;
		jfbSettleRecord.setIsSettled(TrueFalseEnums.TRUE.getValue()) ;
		jfbSettleRecord.setGmtSettled(new Date()) ;
		try {
			ddzJfbSettleRecordService.updateSuccessInfoById(id, jfbSettleRecord) ;
		} catch (DdzDuplicateKeyException e){
			throw new IllegalArgumentException("ddz.jfb.settle.tradeNo.duplicate") ;
		}
	}
	
	/**
	 * 全部成功
	 * @param id
	 * @param tradeNo
	 */
	@Transactional(rollbackFor=RuntimeException.class)
	public void uploadJfbSettleAllSuccess(Long id , String tradeNo){
		uploadJfbSettleSuccessList(id, tradeNo , null , true) ;		
	}
}
