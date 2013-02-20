package com.doucome.corner.task.zhe.syncReport.handler;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.constant.LogConstant;
import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.taobao.config.SettleConfigMgr;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.core.utils.OutCodeUtils.OutCode;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dcome.business.DcUserIntegralOperateBO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.biz.dcome.utils.DcTbCommissionUtils;
import com.doucome.corner.biz.dcome.utils.DcTbCommissionUtils.InternalCommissions;

/**
 * 豆蔻购买返利
 * @author langben 2013-1-9
 *
 */
public class DcBuyRebateIntegralHandler implements Handler {

private static final Log syncReportLog = LogFactory.getLog(LogConstant.task_syncReport_log) ;
	
	private static final Log log = LogFactory.getLog(DcBuyRebateIntegralHandler.class) ;
	
	@Autowired
	private DcUserIntegralOperateBO dcUserIntegralOperateBO ;
	
	@Autowired
	private DcUserService dcUserService ;
	
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
				
				//只处理 DOUCOME_USER_ID
				if(oc.getType() != OutCodeEnums.DOUCOME_USER_ID){
					continue ;
				}
				
				String outCode = oc.getOutCode() ;
				
				//非数字型ID
				if(StringUtils.isEmpty(outCode) || !StringUtils.isNumeric(outCode)) {
					log.error("not correct outCode : " + report.getOutCode()) ;
					syncReportLog.error("not correct outCode : " + report.getOutCode()) ;
				}
				
				Long userId = Long.parseLong(outCode) ;
				
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
				
			} catch(Exception e){
				log.error(e.getMessage() , e) ;
				syncReportLog.error(e.getMessage()) ;
			}
		}
		return null;
	}

}
