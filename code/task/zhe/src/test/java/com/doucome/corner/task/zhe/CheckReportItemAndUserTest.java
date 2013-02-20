package com.doucome.corner.task.zhe;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.utils.OutCodeUtils;
import com.doucome.corner.biz.core.utils.OutCodeUtils.OutCode;
import com.doucome.corner.biz.dal.DdzTaokeReportDAO;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dcome.service.DcItemService;
import com.doucome.corner.biz.dcome.utils.DcPromotionOutCodeUtils;
import com.doucome.corner.biz.dcome.utils.DcPromotionOutCodeUtils.OutCodeData;
import com.doucome.corner.unittest.AbstractBaseJUnit4Test;

@ContextConfiguration(locations={
		"classpath:biz-task-test.xml" 
	})
public class CheckReportItemAndUserTest extends AbstractBaseJUnit4Test {

	@Autowired
	private DdzTaokeReportDAO ddzTaokeReportDAO ;
	
	@Autowired
	private DcItemService dcItemService ;
	
	@Test
	public void test(){
		TaokeReportSearchCondition condition = new TaokeReportSearchCondition() ;
		condition.setOutcodeType(OutCodeEnums.DOUCOME_PROMOTION.getName()) ;
		List<DdzTaokeReportDO> reportList = ddzTaokeReportDAO.selectReportsWithPagination(condition, 1, 10000) ;
		if(!CollectionUtils.isEmpty(reportList)) {
			for(DdzTaokeReportDO report : reportList){
				try {
					if(true) {
						if(StringUtils.isBlank(report.getOutCode())){
							continue ;
						}
						Long reportId = Long.valueOf(report.getId()) ;
						OutCode oc = OutCodeUtils.decodeOutCode(report.getOutCode()) ;
						OutCodeData ocd = DcPromotionOutCodeUtils.decodeOutCode(oc.getOutCode()) ;
						Long userId = ocd.getUserId() ;
						Long itemId = ocd.getItemId() ;
						int effectCount = ddzTaokeReportDAO.updateDcUserAndItemById(reportId, userId, itemId) ;
						if(effectCount > 0){
//							dcItemService.incrTaokeSellCount(ocd.getItemId(), 1) ;
						}
					}
					
				} catch (Exception e){
					e.printStackTrace() ;
				}
			}
		}
	}
}
