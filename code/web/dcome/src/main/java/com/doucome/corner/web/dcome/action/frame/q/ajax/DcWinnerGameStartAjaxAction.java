package com.doucome.corner.web.dcome.action.frame.q.ajax;

import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.dataobject.dcome.DcWinnerGamePlayDetailDO;
import com.doucome.corner.biz.dal.model.KeyValuePair;
import com.doucome.corner.biz.dcome.business.DcUserIntegralBO;
import com.doucome.corner.biz.dcome.exception.IntegralNotEnoughException;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.model.DcWinnerGameConfigDTO;
import com.doucome.corner.biz.dcome.model.param.DcWinnerGameConfigParser;
import com.doucome.corner.biz.dcome.model.param.DcWinnerGameResultModel;
import com.doucome.corner.biz.dcome.service.DcUserFilterService;
import com.doucome.corner.biz.dcome.service.DcUserService;
import com.doucome.corner.biz.dcome.service.DcWinnerGameConfigService;
import com.doucome.corner.biz.dcome.service.DcWinnerGamePlayDetailService;
import com.doucome.corner.biz.dcome.utils.DcWinnerGameUtils;
import com.doucome.corner.web.common.model.JsonModel;
import com.doucome.corner.web.dcome.action.DComeBasicAction;

/**
 * �ϻ�����Ϸ[��ʼ]
 * @author langben 2012-12-14
 *
 */
public class DcWinnerGameStartAjaxAction extends DComeBasicAction {
	
	private static final Log log = LogFactory.getLog(DcWinnerGameStartAjaxAction.class) ;
	
	private JsonModel<DcWinnerGameResultModel> json = new JsonModel<DcWinnerGameResultModel>() ;

	@Autowired
	private DcWinnerGameConfigService dcWinnerGameConfigService ;
	
	@Autowired
	private DcUserService dcUserService ;
	
	@Autowired
	private DcUserIntegralBO dcUserIntegralBO ;
	
	@Autowired
	private DcWinnerGamePlayDetailService dcWinnerGamePlayDetailService ;
	
	@Autowired
	private DcUserFilterService dcUserFilterService ;
	
	/**
	 * �������
	 */
	private String betParam ;
	
	
	@Override
	public String execute() throws Exception {
		
		DcUserDTO user = dcAuthz.getUser() ;
		
		if(user == null){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.user.notLogin") ;
			return SUCCESS ;
		}
		
		Long userId = user.getUserId() ;
		
		List<DcWinnerGameConfigDTO> configList = dcWinnerGameConfigService.getConfigs() ;
		
		//û������
		if(CollectionUtils.isEmpty(configList)){
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.winner.config.error") ;
			return SUCCESS ;
		}
		
		//�����ǲ����Ѿ�����̫��� ��ÿ�������10��// TODO ��Ҫ�Ż���Ŀǰ��ֱ��COUNT
		if(!dcUserFilterService.isWhiteListUser(userId)){
			int todayPlayCount = dcWinnerGamePlayDetailService.countTodayPlayDetaiByUser(userId) ;
			
			if(todayPlayCount > 10){
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("dcome.winner.play.limit") ;
				return SUCCESS ;
			}
		}
		
		List<KeyValuePair> betParamList = DcWinnerGameUtils.parseBetParam(betParam) ;
		if(CollectionUtils.isEmpty(betParamList)){
			//û����ע
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.winner.config.betParam.error") ;
			return SUCCESS ;
		}
		
		//����Ҫ���Ķ��ٻ���
		int needIntegralCount = DcWinnerGameUtils.calcIntegral(betParamList) ;
		
		try {
			
			//���������
			int randomNum = RandomUtils.nextInt(new Random(System.currentTimeMillis()) , 10000) ;
			
			DcWinnerGameConfigParser configParser = new DcWinnerGameConfigParser(configList) ;
			
			DcWinnerGameConfigDTO winConfig = configParser.win(randomNum) ;
			
			if(winConfig == null){
				json.setCode(JsonModel.CODE_ILL_ARGS) ;
				json.setDetail("dcome.winner.config.error") ;
				return SUCCESS ;
			}
			
			//�ѷ����ӵ�����
			dcUserService.updateWinnerScoreToIntegralByUser(userId) ;
			
			//kou�۳�����
			int comsumeIntegral = dcUserIntegralBO.doWinnerGame(userId, -needIntegralCount) ;
			
			
			
			
			DcWinnerGameResultModel resultModel = new DcWinnerGameResultModel() ;
			resultModel.setBetParamList(betParamList) ;
			resultModel.setWinCardName(winConfig.getCardName()) ;
			resultModel.setWinCardScore(winConfig.getScore()) ;
			
			int totalScore = DcWinnerGameUtils.calcTotalWinScore(betParamList, winConfig) ;
			
			dcUserService.incrWinnerScoreByUser(userId, totalScore) ;
			
			resultModel.setWinScore(totalScore) ;
			
			DcUserDTO userdto = dcUserService.getUser(userId) ;
			int integral = userdto.getIntegralCount() ;
			resultModel.setUserIntegralCount(integral) ;
			
			//����detail 
			DcWinnerGamePlayDetailDO detail = new DcWinnerGamePlayDetailDO() ;
			detail.setBetParam(JacksonHelper.toJSON(betParamList)) ;
			detail.setUserId(userId) ;
			detail.setUserNick(user.getNick()) ;
			detail.setWinScore(totalScore) ;
			detail.setResult(JacksonHelper.toJSON(resultModel)) ;
			detail.setBetIntegral(Math.abs(comsumeIntegral)) ;
			dcWinnerGamePlayDetailService.insertPlayDetail(detail) ;
			
			json.setCode(JsonModel.CODE_SUCCESS) ;
			json.setData(resultModel) ;
			
		} catch (IntegralNotEnoughException e){
			//���ֲ���
			json.setCode(JsonModel.CODE_ILL_ARGS) ;
			json.setDetail("dcome.winner.user.integral.notEnough") ;
			return SUCCESS ;
		} catch (Exception e){
			json.setCode(JsonModel.CODE_FAIL) ;
			return SUCCESS ;
		}
		
		return SUCCESS ;
	}


	public JsonModel<DcWinnerGameResultModel> getJson() {
		return json;
	}


	public void setBetParam(String betParam) {
		this.betParam = betParam;
	}

}
