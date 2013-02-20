package com.doucome.corner.task.zhe.syncReport.handler;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.taobao.config.SettleConfigMgr;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.core.utils.OutCodeUtils.OutCode;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.business.DcUserIntegralOperateBO;
import com.doucome.corner.biz.dcome.model.DcIntegralDesc;
import com.doucome.corner.biz.dcome.model.DcItemDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.param.DcRebateModel;
import com.doucome.corner.biz.dcome.model.util.PromotionIntegralUtils;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.biz.dcome.utils.DcPromotionOutCodeUtils;
import com.doucome.corner.biz.dcome.utils.DcTbCommissionUtils;
import com.doucome.corner.biz.dcome.utils.DcPromotionOutCodeUtils.OutCodeData;
import com.doucome.corner.biz.dcome.utils.DcTbCommissionUtils.InternalCommissions;

/**
 * 豆蔻活动返积分
 * @author langben 2012-9-6
 *
 */
public class DcPromotionReportHandler implements Handler {
	
	private static final Log syncReportLog = LogFactory.getLog(LogConstant.task_syncReport_log) ;
	
	private static final Log log = LogFactory.getLog(DcPromotionReportHandler.class) ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Autowired
	private DcUserIntegralOperateBO dcUserIntegralOperateBO ;
	
	@Autowired
	private DcUserService dcUserService;

	@Override
	public HandleResult handleReport(List<DdzTaokeReportDO> reportList) {
		if(CollectionUtils.isEmpty(reportList)){
			return new HandleResult();
		}
		
		for(DdzTaokeReportDO report : reportList){
			try {
				if(report == null){
					continue ;
				}
				
				OutCode oc = OutCodeUtils.decodeOutCode(report.getOutCode()) ;
				
				if(oc.getType() != OutCodeEnums.DOUCOME_PROMOTION){
					continue ;
				}
				
				String outCodeData = oc.getOutCode() ;
				if(StringUtils.isBlank(outCodeData)){
					continue ;
				}
				
				OutCodeData data = DcPromotionOutCodeUtils.decodeOutCode(outCodeData) ;
				if(data == null){
					continue ;
				}
				//解析userId 和 itemId
				Long userId = data.getUserId() ;
				Long itemId = data.getItemId() ;
				if(IDUtils.isNotCorrect(userId) || IDUtils.isNotCorrect(itemId)){
					syncReportLog.error("error parse outcode [" + data + "] to userId and itemId ") ;
					continue ;
				}
				
				InternalCommissions internalCommission = DcTbCommissionUtils.calcFacadeCommissions(report.getRealPayFee(), report.getCommission(), SettleConfigMgr.get(SettleConfigMgr.DCOME)) ;
				
				int jfbCount = internalCommission.getUserIntegral() ;
				
				if(jfbCount > 0){ //增加积分
					DcUserDTO user = dcUserService.getUser(userId) ;
					if(user == null){
						DcUserDO userMock = new DcUserDO() ;
						userMock.setUserId(userId) ;
						user = new DcUserDTO(userMock) ;
					}
					dcUserIntegralOperateBO.doBuyRebate(user, jfbCount , report) ;
				} else {
					log.error("error settle integral count : " + jfbCount + " , report : " + report) ;
					syncReportLog.error("error settle integral count : " + jfbCount + " , report : " + report) ;
				}
				
				
//				//无法找到item
//				DcItemDTO item = dcItemService.getItemById(itemId) ;
//				if(item == null){ 
//					syncReportLog.error("cant find item for itemId : " + itemId) ;
//					continue ;
//				}
//				
//				//不是同一个商品，不进行结算
//				if(!StringUtils.equals(item.getNativeId() , String.valueOf(report.getNumIid()))){
//					continue ;
//				}
//				
//				try {
//					//销量加1
//					dcItemService.incrTaokeSellCount(itemId, 1) ;
//				}catch (Exception e){
//					log.error(e.getMessage() , e) ;
//				}				
//				
//				DcUserDTO user = dcUserService.getUser(userId);
//				if(user == null) {
//					syncReportLog.error("user: " + userId + " not exist");
//					continue;
//				}
//				//计算积分
//				int integralCount = PromotionIntegralUtils.getIntegral(item) ;
//				
//				//没有积分
//				if(integralCount <= 0){
//					syncReportLog.error("----no integral, item id:" + itemId);
//					continue ;
//				}
				
//				DcRebateModel rebateModel = new DcRebateModel();
//				rebateModel.setUserId(userId);
//				rebateModel.setUserNick(user.getNick());
//				rebateModel.setItemId(itemId);
//				rebateModel.setItemTitle(item.getItemTitle());
//				rebateModel.setIntegralCount(integralCount);
//				rebateModel.setItemOwnerUserId(item.getCreatorUserId());
//				//购买返积分
//				dcUserIntegralBO.doBuyRebate(rebateModel);
			}catch(Exception e){
				log.error(e.getMessage() , e) ;
				syncReportLog.error(e.getMessage()) ;
			}
		}
		return new HandleResult() ;
	}
	
	public static void main(String[] args) {
		DcIntegralDesc desc = new DcIntegralDesc() ;
		desc.setFromObjId(10002L) ;
		desc.setFromObjName("3232");
		System.out.println(JacksonHelper.toJSON(desc)) ;
	}
}
