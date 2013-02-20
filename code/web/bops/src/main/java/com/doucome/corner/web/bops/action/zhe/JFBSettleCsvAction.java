package com.doucome.corner.web.bops.action.zhe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.constant.EnvConstant;
import com.doucome.corner.biz.core.constant.SettleConstant;
import com.doucome.corner.biz.core.utils.EnvPropertiesUtil;
import com.doucome.corner.biz.core.utils.ValidateUtil;
import com.doucome.corner.biz.dal.condition.DdzTaokeReportSettleSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzJfbSettleRecordDO;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportSettleDO;
import com.doucome.corner.biz.dal.model.KeyValuePair;
import com.doucome.corner.biz.zhe.bo.DdzTaokeReportSettleBO;
import com.doucome.corner.biz.zhe.model.JfbExportModel;
import com.doucome.corner.biz.zhe.service.DdzJfbSettleRecordService;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.bops.bo.JfbSettleBO;

@SuppressWarnings("serial")
public class JFBSettleCsvAction extends BopsBasicAction {
	
	private InputStream inputStream ;

	private String fileName ;
	
	/**
	 * settleRecord ID
	 */
	private Long id ;
	
	/**
	 * 上传的CSV
	 */
	private File successFile ;
	
	private String successFileFileName ;
	
	private String successFileContentType ;
	
	/**
	 * 全部成功
	 */
	private String allSuccessful ;
	
	/**
	 * 交易号
	 */
	private String tradeNo ;
	
	
	@Autowired
	private JfbSettleBO jfbSettleBO ;
	
	@Autowired
	private DdzJfbSettleRecordService ddzJfbSettleRecordService ;
	
	@Autowired
	private DdzTaokeReportService ddzTaokeReportService ;
	
	public String exportCsv() throws Exception {
		
		JfbExportModel exportModel = jfbSettleBO.exportJfbSettle() ;
		
		inputStream = _export(exportModel) ;
		
		fileName = "JFB-PLAN-" + exportModel.getSettleBatchno() + ".csv" ;
		
		return SUCCESS ;
	}
	
	public String reExportCsv() throws Exception {
		
		if(IDUtils.isNotCorrect(id)){
			return NONE ;
		}
		
		DdzJfbSettleRecordDO jsrd = ddzJfbSettleRecordService.getRecordById(id) ;
		
		if(jsrd == null){
			return NONE ;
		}
		
		String settleBatchno = jsrd.getSettleBatchno() ;
		
		DdzTaokeReportSettleSearchCondition condition = new DdzTaokeReportSettleSearchCondition() ;
		condition.setSettleBatchno(settleBatchno) ;
		condition.setOutcodeTypeList(SettleConstant.JFB_SETTLE_OUTCODE_TYPES) ;
		List<DdzTaokeReportSettleDO> settleList = ddzTaokeReportService.getSettleReports(condition) ;
		
		if(CollectionUtils.isEmpty(settleList)){
			return NONE ;
		}
		
		JfbExportModel exportModel = new JfbExportModel() ;
		
		exportModel.setSettleBatchno(settleBatchno) ;
		exportModel.setSettleList(settleList) ;
		
		inputStream = _export(exportModel) ;
		
		fileName = "JFB-PLAN-" + exportModel.getSettleBatchno() + ".csv" ;
		
		return SUCCESS ;
	}
	
	private InputStream _export(JfbExportModel exportModel) throws Exception {
		
		if(CollectionUtils.isEmpty(exportModel.getSettleList())){
			throw new IllegalArgumentException("data is empty .") ;
		}		
		
		//File csvTemplateFile = ResourceUtils.getFile("classpath:/corner/bean/zhe/jfb-template.csv") ;
		
		String settleBatchno = exportModel.getSettleBatchno() ;
		
		List<DdzTaokeReportSettleDO> settleList = exportModel.getSettleList() ;
		String outputDir = EnvPropertiesUtil.getProperty(EnvConstant.CORNER_OUTPUT) ;
		File csvFile = new File(outputDir + "/csvtmp/" + settleBatchno + "_tmp.csv") ;
		
		//FileUtils.copyFile(templateFile, csvFile) ;
		//CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(csvTemplateFile) , "GBK")) ; 
		CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csvFile) , "GBK")) ;
		
//		while(true){
//			String[] str = csvReader.readNext() ;
//			if(str == null){
//				break ;
//			}
//			csvWriter.writeNext(str) ;
//		}
		//乱码风险
		String account = "收款帐号" ;
		String amount = "发放集分宝数（个）" ;
		
		csvWriter.writeNext(new String[]{ account , amount}) ;
		 
		int rowNum = 2 ;
		
		for(int i=0 ;i<settleList.size() ; i++){
			
			DdzTaokeReportSettleDO settleDO = settleList.get(i) ;
			String alipay = settleDO.getSettleAlipay() ;
			
			if(!ValidateUtil.checkIsAlipay(alipay)){
				continue ;
			}
			
			int settleJfb = settleDO.getSettleJfb() ;
			if(settleJfb <= 0){
				continue ;
			}
			
			csvWriter.writeNext(new String[]{alipay , String.valueOf(settleJfb) }) ;
						
			rowNum ++ ;
			
		}
		csvWriter.flush() ;
		
		InputStream is = csv2inputStream(csvFile) ;
		
		return is ;
	}
	
	/**
	 * 上传成功发放清单
	 * @return
	 * @throws Exception
	 */
	public String uploadSuccessList() throws Exception {
		
		if(StringUtils.isBlank(tradeNo) || IDUtils.isNotCorrect(id)) {
			addActionError("ddz.jfb.settle.parameter.required") ;
			return ERROR ;
		}

		if("on".equalsIgnoreCase(allSuccessful)) {
			try {
				jfbSettleBO.uploadJfbSettleAllSuccess(id, tradeNo) ;
			} catch (IllegalArgumentException e){
				String errMsg = e.getMessage() ;
				addActionError(errMsg) ;
				return ERROR ;
			}
		} else {
			if(successFile == null){
				addActionError("ddz.jfb.settle.csv.upload.file.required") ;
				return ERROR ;
			}
				
			String extName = FilenameUtils.getExtension(successFileFileName);
			if(!StringUtils.equalsIgnoreCase(extName, "csv")){
				addActionError("ddz.jfb.settle.csv.upload.ext.incorrect") ;
				//后缀只能是CSV
				return ERROR ;
			}
			CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(successFile) , "GBK")) ; 
			
			List<KeyValuePair> successList = new ArrayList<KeyValuePair>() ;
			while(true){
				String[] data = csvReader.readNext() ;
				if(data == null){
					break ;
				}
				if(data != null && data.length == 2){
					successList.add(new KeyValuePair(data[0] , data[1])) ;
				}
			}
			
			if(CollectionUtils.isEmpty(successList)){
				addActionError("ddz.jfb.settle.data.empty") ;
				return ERROR ;
			}
			
			try {
				jfbSettleBO.uploadJfbSettleSuccessList(id, tradeNo, successList, false) ;
			} catch (IllegalArgumentException e){
				String errMsg = e.getMessage() ;
				addActionError(errMsg) ;
				return ERROR ;
			}
		
		}

		
		return SUCCESS ;
	}
	
	private InputStream csv2inputStream(File f) throws Exception {
		InputStream input = new FileInputStream(f) ;
		return input ;
	}
	
		
	public InputStream getInputStream() {
		return inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setSuccessFile(File successFile) {
		this.successFile = successFile;
	}

	public void setSuccessFileFileName(String successFileFileName) {
		this.successFileFileName = successFileFileName;
	}

	public void setSuccessFileContentType(String successFileContentType) {
		this.successFileContentType = successFileContentType;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAllSuccessful(String allSuccessful) {
		this.allSuccessful = allSuccessful;
	}

}
