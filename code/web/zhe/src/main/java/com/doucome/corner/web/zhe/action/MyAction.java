package com.doucome.corner.web.zhe.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.enums.OutCodeEnums;
import com.doucome.corner.biz.core.enums.SettleStatusEnums;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.TaokeReportSearchCondition;
import com.doucome.corner.biz.dal.dataobject.DdzTaokeReportDO;
import com.doucome.corner.biz.dal.dataobject.DdzUserDO;
import com.doucome.corner.biz.zhe.bo.DdzTaokeReportSettleBO;
import com.doucome.corner.biz.zhe.enums.DdzRefundStatusEnums;
import com.doucome.corner.biz.zhe.model.DdzTaokeReportDTO;
import com.doucome.corner.biz.zhe.service.DdzTaokeReportService;
import com.doucome.corner.biz.zhe.utils.DdzTaokeReportUtils;
import com.doucome.corner.biz.zhe.utils.DdzTaokeReportUtils.SettleModel;
import com.doucome.corner.web.zhe.model.ManualSettleResultModel;

/**
 * 我的点点折
 * 
 * @author langben 2012-3-20
 */
@SuppressWarnings("serial")
public class MyAction extends DdzBasicAction {

    @Autowired
    private DdzTaokeReportService         ddzTaokeReportService;
    
    @Autowired
    private DdzTaokeReportSettleBO ddzTaokeReportSettleBO ;

    private QueryResult<DdzTaokeReportDTO> itemList;
    
    //退款维权的记录
    private List<DdzTaokeReportDTO> refundList ;
    
    private ManualSettleResultModel settleResultModel = new ManualSettleResultModel() ;

    private int                           page = 1;

    private DdzUserDO                     user;

    private String                        alipayId ;
    
    private String	type ;

    private String 	maskAlipayId ;
    
    @Override
    public String execute() throws Exception {
        // 非内部人员，不能通过url set alipayId，只能从cookie取
        if(!ddzAuthz.isPrivateUser()){
            alipayId = null;
        }
        
        
        user = ddzAuthz.getUser();
        
        if (user != null) {
            alipayId = user.getAlipayId();
        }
        
        maskAlipayId = StringUtils.isNotBlank(alipayId) ? alipayId : ddzAuthz.getAlipayId() ;
        

        // 如果是内部用户，可以直接访问，无需登陆
        if (user == null && !ddzAuthz.isPrivateUser()) {
            return DDZ_INDEX;
        }

        if (StringUtils.isBlank(alipayId)) {
            return SUCCESS;
        }

        Pagination pagination = new Pagination(page, 10);
        TaokeReportSearchCondition searchCondition = new TaokeReportSearchCondition();
        searchCondition.setSettleAlipay(alipayId);
        
        if(StringUtils.equals(type , "ktx")){ //查询可提现
        	searchCondition.setOutcodeTypeList(new String[]{
        			OutCodeEnums.DDZ_ACCOUNT_ID_JFB.getName() , OutCodeEnums.DDZ_ACCOUNT_ID_MANUAL.getName()
        	}) ;
        	searchCondition.setSettleStatus(SettleStatusEnums.UNSETTLE.getValue()) ;
        	searchCondition.setRequireSettleId(false) ;
        } else if(StringUtils.equals(type, "txz")){ //提现中
        	searchCondition.setSettleStatus(SettleStatusEnums.UNSETTLE.getValue()) ;
        	searchCondition.setRequireSettleId(true) ;
        } else if(StringUtils.equals(type, "ytx")){ //已提现
        	searchCondition.setSettleStatus(SettleStatusEnums.SETTLE_SUCCESS.getValue()) ;
        }
        
        QueryResult<DdzTaokeReportDO> reportList = ddzTaokeReportService.getReportsWithPagination(searchCondition, pagination);
        
        
        TaokeReportSearchCondition refundCondition = new TaokeReportSearchCondition();
        refundCondition.setSettleAlipay(alipayId);
        refundCondition.setRefundStatus(DdzRefundStatusEnums.REFUND_TRUE.getValue()) ;
        
        //维权退款
        List<DdzTaokeReportDO> refundDOList = ddzTaokeReportService.getReports(refundCondition) ;
        
        //报表记录
        this.itemList = wrapperResult(reportList) ;
        //维权记录
        this.refundList = wrapperResult(refundDOList) ;
        
        //退款维权金额
        BigDecimal refundCashAmount = DdzTaokeReportUtils.calcTotalSettleFee(refundDOList) ;
        int refundJfbAmount = DdzTaokeReportUtils.calcTotalSettleJfb(refundDOList) ;
        
        List<DdzTaokeReportDO> canSettleList = ddzTaokeReportSettleBO.getManualCombineSettlesByUser(alipayId) ;
        BigDecimal settleCashAmount = DdzTaokeReportUtils.calcTotalSettleFee(canSettleList) ;
        int settleJfbAmount = DdzTaokeReportUtils.calcTotalSettleJfb(canSettleList) ;
        
        SettleModel settleModel = DdzTaokeReportUtils.calcSettleModel(settleCashAmount, refundCashAmount, settleJfbAmount, refundJfbAmount) ;
        
        settleResultModel.setRefundCashAmount(refundCashAmount) ;
        settleResultModel.setRefundJfbAmount(refundJfbAmount) ;
        settleResultModel.setSettleCashAmount(settleCashAmount) ;
        settleResultModel.setSettleJfbAmount(settleJfbAmount) ;
        settleResultModel.setSettleModel(settleModel) ;
        settleResultModel.setSettleSize(CollectionUtils.size(canSettleList)) ;

        return SUCCESS;
    }
    
    private List<DdzTaokeReportDTO> wrapperResult(List<DdzTaokeReportDO> list){
    	List<DdzTaokeReportDTO> dtoList = new ArrayList<DdzTaokeReportDTO>() ;
    	List<DdzTaokeReportDO> items = list ;
    	if(CollectionUtils.isNotEmpty(items)){
    		for(DdzTaokeReportDO report : items){
    			dtoList.add(new DdzTaokeReportDTO(report)) ;
    		}
    	}
    	return dtoList ;
    }
    
    private QueryResult<DdzTaokeReportDTO> wrapperResult(QueryResult<DdzTaokeReportDO> list){
    	
    	List<DdzTaokeReportDTO> dtoList = new ArrayList<DdzTaokeReportDTO>() ;
    	
    	if(list.getTotalRecords() <= 0){
    		return new QueryResult<DdzTaokeReportDTO>( dtoList , list.getPagination() , 0) ;
    	}
    	
    	List<DdzTaokeReportDO> items = list.getItems() ;
    	if(CollectionUtils.isNotEmpty(items)){
    		for(DdzTaokeReportDO report : items){
    			dtoList.add(new DdzTaokeReportDTO(report)) ;
    		}
    	}
    	
    	return new QueryResult<DdzTaokeReportDTO>( dtoList , list.getPagination() , list.getTotalRecords()) ;
    }

    public DdzUserDO getUser() {
        return user;
    }

    public String getAlipayId() {
        return alipayId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public QueryResult<DdzTaokeReportDTO> getItemList() {
        return itemList;
    }   

    public void setType(String type) {
		this.type = type;
	}

	public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

	public String getMaskAlipayId() {
		return maskAlipayId;
	}

	public String getType() {
		return type;
	}

	public List<DdzTaokeReportDTO> getRefundList() {
		return refundList;
	}

	public ManualSettleResultModel getSettleResultModel() {
		return settleResultModel;
	}

}
