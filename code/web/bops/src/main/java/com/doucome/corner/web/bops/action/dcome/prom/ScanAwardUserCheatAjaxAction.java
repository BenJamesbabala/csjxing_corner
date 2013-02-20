package com.doucome.corner.web.bops.action.dcome.prom;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcPromotionAwardCondition;
import com.doucome.corner.biz.dal.condition.dcome.DcUserLoginTraceCondition;
import com.doucome.corner.biz.dcome.enums.DcPromotionAwardReviewStatusEnums;
import com.doucome.corner.biz.dcome.model.DcPromotionAwardDTO;
import com.doucome.corner.biz.dcome.model.DcPromotionDTO;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.DcUserLoginTraceDTO;
import com.doucome.corner.biz.dcome.service.DcPromotionAwardService;
import com.doucome.corner.biz.dcome.service.DcPromotionService;
import com.doucome.corner.biz.dcome.service.DcUserLoginTraceService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.web.bops.action.BopsBasicAction;
import com.doucome.corner.web.common.model.JsonModel;

/**
 * 检测中将的会员相关信息
 * @author langben 2012-10-17
 *
 */
public class ScanAwardUserCheatAjaxAction extends BopsBasicAction {

	private JsonModel<ResultModel> json = new JsonModel<ResultModel>();
	
	private Long promotionId ;
	
	private Long userId ;
	
	@Autowired
	private DcUserLoginTraceService dcUserLoginTraceService ;
	
	@Autowired
	private DcPromotionAwardService dcPromotionAwardService ;
	
	@Autowired
	private DcPromotionService dcPromotionService ;
	
	@Autowired
	private DcUserService dcUserService ;
	
	@Override
	public String execute() throws Exception {
		if(IDUtils.isNotCorrect(userId) || IDUtils.isNotCorrect(promotionId)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.userId.notCorrect") ;
			return SUCCESS ;
		}
		
		DcPromotionDTO promotion = dcPromotionService.getPromotionById(promotionId) ;
		
		if(promotion == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.promotionId.notCorrect") ;
			return SUCCESS ;
		}
		
		DcUserDTO user = dcUserService.getUser(userId) ;
		
		DcUserLoginTraceCondition condition = new DcUserLoginTraceCondition() ;
		condition.setUserId(userId) ;
		condition.setGmtCreateEnd(promotion.getEndTime()) ;
		condition.setGmtCreateStart(promotion.getStartTime()) ;
		//检测小号，检测中将记录
		QueryResult<DcUserLoginTraceDTO> loginTraceQuery = dcUserLoginTraceService.getTraceWithPagination(condition, new Pagination(1 , 100)) ;
		List<DcUserLoginTraceDTO> loginTraceList = loginTraceQuery.getItems() ;
		
		List<DcUserLoginTraceDTO> ipHistoryList = new ArrayList<DcUserLoginTraceDTO>() ;
		List<DcUserLoginTraceDTO> ubidHistoryList = new ArrayList<DcUserLoginTraceDTO>() ;
		List<DcPromotionAwardDTO> awardHistoryList = null ;
		
		
		DcPromotionAwardCondition awardCondition = new DcPromotionAwardCondition() ;
		awardCondition.setUserId(userId) ;
		awardCondition.setReviewStatus(DcPromotionAwardReviewStatusEnums.SUCCESS.getStatus());
		awardHistoryList = dcPromotionAwardService.getAwardsNoPagination(awardCondition, new Pagination(1,100)) ;
		
		if(CollectionUtils.isNotEmpty(loginTraceList)){

			//检测IP
			List<Long> ipList = parseIpList(loginTraceList) ;
			for(Long ip : ipList){
				DcUserLoginTraceCondition ipCondition = new DcUserLoginTraceCondition() ;
				ipCondition.setLoginIp(ip) ;
				ipCondition.setGmtCreateEnd(promotion.getEndTime()) ;
				ipCondition.setGmtCreateStart(promotion.getStartTime()) ;
				QueryResult<DcUserLoginTraceDTO> ipQuery = dcUserLoginTraceService.getTraceWithPagination(ipCondition, new Pagination(1,100)) ;
				ipHistoryList.addAll(ipQuery.getItems()) ;
			}
			
			//检测UBID
			List<String> ubidList = parseUbidList(loginTraceList) ;
			for(String ubid : ubidList){
				DcUserLoginTraceCondition ubidCondition = new DcUserLoginTraceCondition() ;
				ubidCondition.setUbid(ubid) ;
				ubidCondition.setGmtCreateEnd(promotion.getEndTime()) ;
				ubidCondition.setGmtCreateStart(promotion.getStartTime()) ;
				QueryResult<DcUserLoginTraceDTO> ubidQuery = dcUserLoginTraceService.getTraceWithPagination(ubidCondition, new Pagination(1,100)) ;
				ubidHistoryList.addAll(ubidQuery.getItems()) ;
			}
			
		}
		
		ipHistoryList = uniqueByUser(ipHistoryList) ;
		
		ubidHistoryList = uniqueByUser(ubidHistoryList) ;
		
		ResultModel model = new ResultModel() ;
		model.setAwardHistoryList(awardHistoryList);
		model.setTreatLoginIpList(ipHistoryList) ;
		model.setTreatUbidList(ubidHistoryList) ;
		model.setUser(user) ;
		
		json.setData(model) ;
		json.setCode(JsonModel.CODE_SUCCESS) ;
		
		return SUCCESS ;
	}
	
	
	private List<Long> parseIpList(List<DcUserLoginTraceDTO> loginTraceList){
		List<Long> ipList = new ArrayList<Long>() ;
		for(DcUserLoginTraceDTO loginTrace : loginTraceList) {
			Long ip = loginTrace.getLoginIp() ;
			if(!ipList.contains(ip)){
				ipList.add(ip) ;
			}
		}
		return ipList ;
	}
	
	private List<String> parseUbidList(List<DcUserLoginTraceDTO> loginTraceList){
		List<String> ubidList = new ArrayList<String>() ;
		for(DcUserLoginTraceDTO loginTrace : loginTraceList) {
			String ubid = loginTrace.getUbid() ;
			if(!ubidList.contains(ubid)){
				ubidList.add(ubid) ;
			}
		}
		return ubidList ;
	}
	
	private List<DcUserLoginTraceDTO> uniqueByUser(List<DcUserLoginTraceDTO> originList) {
		
		List<DcUserLoginTraceDTO> uniqueList = new ArrayList<DcUserLoginTraceDTO>() ;
		
		if(!CollectionUtils.isEmpty(originList)){
			boolean exists = false ;
			for(DcUserLoginTraceDTO dto : originList) {
				for(DcUserLoginTraceDTO destDTO : uniqueList){
					if(destDTO.getUserId().equals(dto.getUserId())){
						exists = true ;
						break ;
					}
				}
				if(!exists) {
					uniqueList.add(dto) ;
				}
			}
		}

		return uniqueList ;
	}


	public JsonModel<ResultModel> getJson() {
		return json;
	}


	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public static class ResultModel {
		
		DcUserDTO user ;
		
		/**
		 * 相同UBID
		 */
		List<DcUserLoginTraceDTO> treatUbidList ;
		
		/**
		 * 相同IP
		 */
		List<DcUserLoginTraceDTO> treatLoginIpList ;
		
		/**
		 * 历史记录
		 */
		List<DcPromotionAwardDTO> awardHistoryList ;

		public List<DcUserLoginTraceDTO> getTreatUbidList() {
			return treatUbidList;
		}

		public void setTreatUbidList(List<DcUserLoginTraceDTO> treatUbidList) {
			this.treatUbidList = treatUbidList;
		}

		public List<DcUserLoginTraceDTO> getTreatLoginIpList() {
			return treatLoginIpList;
		}

		public void setTreatLoginIpList(List<DcUserLoginTraceDTO> treatLoginIpList) {
			this.treatLoginIpList = treatLoginIpList;
		}

		public List<DcPromotionAwardDTO> getAwardHistoryList() {
			return awardHistoryList;
		}

		public void setAwardHistoryList(List<DcPromotionAwardDTO> awardHistoryList) {
			this.awardHistoryList = awardHistoryList;
		}

		public DcUserDTO getUser() {
			return user;
		}

		public void setUser(DcUserDTO user) {
			this.user = user;
		}
		
	}
}
