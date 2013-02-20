package com.doucome.corner.task.zhe.unactive;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.dal.DdzTaokeReportDAO;
import com.doucome.corner.biz.dal.condition.DdzAccountSearchCondition;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzAccountDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.zhe.service.DdzAccountService;
import com.doucome.corner.task.zhe.starter.TaskStarterEnv;

/**
 * 
 * @author langben 2012-11-4
 *
 */
public class StatDdzUnactiveUser {

	@Autowired
	private DdzAccountService ddzAccountService ;
	
	@Autowired
	private DdzTaokeReportDAO ddzTaokeReportDAO ;
	
	public void stat() throws IOException{
		//BufferedReader br = new BufferedReader(new FileReader(new File("d:/output.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("d:/output_RELEASE.txt"))) ;
		DdzAccountSearchCondition condition = new DdzAccountSearchCondition() ;
		QueryResult<DdzAccountDO> account = ddzAccountService.getAccountsWithPagination(condition, new Pagination(1,100000)) ;
		TaokeReportSearchCondition rsc = new TaokeReportSearchCondition() ;
		for(DdzAccountDO ac : account.getItems()){
			rsc.setSettleAlipay(ac.getAlipayId()) ;
			List<DdzTaokeReportDO> list = ddzTaokeReportDAO.selectReportsWithPagination(rsc, 1, 1) ;
			if(CollectionUtils.isEmpty(list)){
				String alipayId = ac.getAlipayId() ;
				if(ValidateUtil.checkIsAlipay(alipayId)){
					StringBuilder sql = new StringBuilder("INSERT INTO DDZ_TAOKE_REPORT_SETTLE_TEMP(SETTLE_ALIPAY, SETTLE_FEE, SETTLE_STATUS, OUTCODE_TYPE,GMT_MODIFIED, GMT_CREATE) VALUES('")
					.append(ac.getAlipayId())
					.append("','0.01','U','S',NOW(),NOW());") ;
					bw.newLine();
					bw.append(sql) ;
				
				}
				
			}
		}
		bw.flush() ;
		bw.close() ;
		System.exit(0) ;
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		StatDdzUnactiveUser ins = (StatDdzUnactiveUser)ac.getBean("statDdzUnactiveUser") ;
		try {
			ins.stat() ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
