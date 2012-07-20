package com.doucome.corner.web.bops.action.zhe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.core.sms.config.SmsBusiness;
import com.doucome.corner.biz.core.sms.config.SmsBusinessConfig;
import com.doucome.corner.biz.core.sms.model.SmsBusinessEnums;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.core.utils.MobileUtil;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 结算短信xls导出
 * @author shenjia.caosj 2012-6-17
 *
 */
@SuppressWarnings("serial")
public class SettleSMSExportAction extends BopsBasicAction implements ModelDriven<DdzTaokeReportSettleSearchCondition> {
	
	private static String[] titleRow = new String[]{
			"num" , "mobile" , "sms" , "date" , "status"
	} ;
	
	private DdzTaokeReportSettleSearchCondition  condition = new DdzTaokeReportSettleSearchCondition() ;

	private String format = "xls" ;
	
	private String fileName ;
	
	private InputStream inputStream ;
	
	@Autowired
	private SmsBusinessConfig smsBusinessConfig ;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ;
	
	@Override
	public String execute() throws Exception {
		
		condition.setGmtSettledStart(DateUtils.setTime(condition.getGmtSettledStart(), 0 , 0 , 0)) ;
		condition.setGmtSettledEnd(DateUtils.setTime(condition.getGmtSettledEnd(), 23 , 59 , 59)) ;
		condition.setGmtCreateStart(DateUtils.setTime(condition.getGmtCreateStart(), 0, 0, 0)) ;
		condition.setGmtCreateEnd(DateUtils.setTime(condition.getGmtCreateEnd(), 23 , 59 , 59)) ;
		
		QueryResult<DdzTaokeReportSettleDO> settleResult = ddzTaokeReportService.getSettlesWithPagination(condition, new Pagination(1,60000)) ;
		if(CollectionUtils.isEmpty(settleResult.getItems())){
			return NONE ;
		}
//		
//		HttpServletResponse response = ServletActionContext.getResponse() ;
//		OutputStream output = response.getOutputStream();
		try {
			DateFormat df = new SimpleDateFormat("yyyyMMdd") ;
			fileName = "settle_sms_" + df.format(new Date()) + "." + format ;
			if(StringUtils.equalsIgnoreCase("xls" , format)){
	
//				response.setHeader("Content-disposition", "attachment;filename=" + fileName);
//		        response.setContentType("application/msexcel");
//				
		        HSSFWorkbook workbook = createExcel(settleResult) ;
//		        workbook.write(output);
//		        output.flush();
		        
		        inputStream = workbook2inputStream(workbook) ;
		        
		        return "excel" ;
			}else if(StringUtils.equalsIgnoreCase("txt", format)){
//				response.setHeader("Content-disposition", "attachment;filename=" + fileName);
//		        response.setContentType("application/plain");
//		        
		        String content = createTxtContent(settleResult) ;
//		        BufferedWriter b = new BufferedWriter(new OutputStreamWriter(output)) ;
		        inputStream = new ByteArrayInputStream(content.getBytes()) ;
		        return "text" ;
			}
		}finally{
			
		}
        
		return SUCCESS ;
	}
	
	private InputStream workbook2inputStream(HSSFWorkbook workbook) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream() ;
		workbook.write(out) ;
		out.flush() ;
		byte[] bytes = out.toByteArray() ;
		InputStream input = new ByteArrayInputStream(bytes , 0 , bytes.length) ;
		return input ;
	}
	
	private String createTxtContent(QueryResult<DdzTaokeReportSettleDO> settleResult){
		StringBuilder content = new StringBuilder()  ;
		SmsBusiness business = smsBusinessConfig.getSmsBusiness(SmsBusinessEnums.DDZ_SETTLE_SMS) ;
		List<DdzTaokeReportSettleDO> items = settleResult.getItems() ;
		DecimalFormat dformat = new DecimalFormat("0.00") ;
		
		for(int i=0 ;i<items.size() ; i++){
			
			DdzTaokeReportSettleDO item = items.get(i) ;
			String alipay = item.getSettleAlipay() ;
			if(ValidateUtil.checkIsMobile(alipay)){
				String mobile = alipay ;
				String sms = business.getDefaultFormattedMessage(new String[]{dformat.format(item.getSettleFee()) , MobileUtil.getPrivateMobileForShort(mobile)}) ;
				
				content.append(mobile).append(" ").append(sms).append("\r\n") ;
			}

		}
		
		return content.toString() ;
	}
	
	private HSSFWorkbook createExcel(QueryResult<DdzTaokeReportSettleDO> settleResult){
		HSSFWorkbook workbook = new HSSFWorkbook();// 创建一个Excel文件   
		HSSFSheet sheet = workbook.createSheet();// 创建一个Excel的Sheet 
		
		createExcelHead(sheet) ;
		List<DdzTaokeReportSettleDO> items = settleResult.getItems() ;
		int num = 1 ;
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd") ;
		DecimalFormat dformat = new DecimalFormat("0.00") ;
		SmsBusiness business = smsBusinessConfig.getSmsBusiness(SmsBusinessEnums.DDZ_SETTLE_SMS) ;
		
		for(int i=0 ;i<items.size() ; i++){
			
			DdzTaokeReportSettleDO item = items.get(i) ;
			String alipay = item.getSettleAlipay() ;
			if(ValidateUtil.checkIsMobile(alipay)){
				String mobile = alipay ;
				String sms = business.getDefaultFormattedMessage(new String[]{dformat.format(item.getSettleFee()) , MobileUtil.getPrivateMobile(mobile)}) ;
				String date = null ;
				if(item.getGmtSettled() != null){
					date = format.format(item.getGmtSettled()) ;
				}
				HSSFRow row = sheet.createRow(num) ;
				row.createCell(0).setCellValue((new HSSFRichTextString(String.valueOf(num)))) ;
				row.createCell(1).setCellValue(mobile) ;
				row.createCell(2).setCellValue(sms) ;
				row.createCell(3).setCellValue(date) ;
				row.createCell(4).setCellValue(item.getSettleStatus()) ;
				num ++ ;
			}
			
			
		}
		return workbook ;
	}

	private void createExcelHead(HSSFSheet sheet) {
        // 创建第一行   
        HSSFRow fristRow = sheet.createRow(0);
        // 创建第一行的列
        for (int i = 0; i < titleRow.length; i++) {
            sheet.setColumnWidth( i, (short) 5000);
            String cellName = titleRow[i];
            fristRow.createCell(i).setCellValue(new HSSFRichTextString(cellName)) ;

        }
    }
	
	
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getFileName() {
		return fileName;
	}	
	
	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public DdzTaokeReportSettleSearchCondition getModel() {
		return condition ;
	}

	
}
