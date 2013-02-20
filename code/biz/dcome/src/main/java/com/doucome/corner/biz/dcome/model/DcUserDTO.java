package com.doucome.corner.biz.dcome.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.common.utils.NumberUtils;
import com.doucome.corner.biz.core.enums.GenderEnums;
import com.doucome.corner.biz.core.utils.ArrayStringUtils;
import com.doucome.corner.biz.core.utils.BitOperateUtils;
import com.doucome.corner.biz.core.utils.BitOperateUtils.BitEnums;
import com.doucome.corner.biz.core.utils.DateUtils;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dal.model.AbstractModel;
import com.doucome.corner.biz.dcome.enums.DcUserExtendDescEnums;
import com.doucome.corner.biz.dcome.enums.DcUserGuideEnum;
import com.doucome.corner.biz.dcome.enums.DcUserLevelEnum;
import com.doucome.corner.biz.dcome.enums.DcUserNewGuideEnums;

/**
 * UserDTO
 * @author langben 2012-8-28
 *
 */
public class DcUserDTO extends AbstractModel {
	
	/**
	 * 每月可以兑换5次
	 */
	public static final int EXCHANGE_COUNT_PER_MONTH = 5 ;
	
	public static final String EXCHANGE_MON_FORMAT = "yyyyMM" ;
	
	private static final Log log = LogFactory.getLog(DcUserDTO.class) ;

   
	private static final long serialVersionUID = -7158100372274642302L;
    private DcUserDO user;
    private DcUserLevelEnum levelEnum;
    
	private Map<String,String> extendDescMap = new HashMap<String,String>() ;
	
	public DcUserDTO(DcUserDO user){
		if(user == null){
			user = new DcUserDO() ;
		}
		this.user = user;
		levelEnum = DcUserLevelEnum.toEnum(user.getLevel());
		String extendDesc = user.getExtendDesc() ;
		if(StringUtils.isBlank(extendDesc)) {
			extendDescMap = new HashMap<String,String> () ;
		} else {
			try {
				extendDescMap = JacksonHelper.fromJSON(extendDesc, Map.class) ;
			} catch (Exception e){
				log.error(e.getMessage() , e) ;
			}
		}
	}
	
	/**
	 * 今天是否签到过
	 * @return
	 */
	public boolean isTodayCheckin(){
		Date lastCheckin = getGmtLastCheckin() ; 
		if(lastCheckin == null){
			return false ;
		}
		Date date = new Date() ;
		if(DateUtils.isSameDay(lastCheckin, date)){
			return true ;
		}
		return false ;
	}
	
	/**
	 * 是否已经引导过
	 * @param guideName
	 * @return
	 */
	public boolean isGuided(String guideName){
		
		DcUserNewGuideEnums guideEnums = DcUserNewGuideEnums.getInstanceByName(guideName) ;
		if(guideEnums == DcUserNewGuideEnums.UNKNOWN){
			return false ;
		}
		
		long guideValue = NumberUtils.parseLong(getNewGuide()) ;
		
		int index = guideEnums.getIndex() ;
		
		BitEnums bit = BitOperateUtils.getBit(guideValue, index) ;
		
		return bit.toBoolean() ;
		
	}
	
	public String getGuideJson(String guideNames){
		List<String> guideNameList = ArrayStringUtils.toList(guideNames) ;
		Map<String,Boolean> guideMap = new HashMap<String,Boolean>() ;
		if(CollectionUtils.isNotEmpty(guideNameList)){
			for(String guideName : guideNameList){
				if(StringUtils.isNotBlank(guideName)) {
					boolean isGuided = isGuided(guideName) ;
					if(isGuided){
						guideMap.put(guideName, Boolean.TRUE) ;
					}
				}
			}
		}
		return StringUtils.trim(JacksonHelper.toJSON(guideMap)) ;
	}
	
	public String getGmtCreateFormat(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
    	return df.format(this.getGmtCreate()) ;
	}
	
	public Map<String, String> getExtendDescMap() {
		return extendDescMap;
	}
	
	/**
	 * 还能兑换的次数
	 * @return
	 */
	public int getCanExchangeCount() {
		//每个月可兑换5次
		Map<String,String> exMap = getExtendDescMap() ;
		String exMonth = exMap.get(DcUserExtendDescEnums.EXCHANGE_MONTH.getValue()) ;
		int exCount = NumberUtils.parseInt(exMap.get(DcUserExtendDescEnums.EXCHANGE_COUNT.getValue())) ;
		if(StringUtils.isBlank(exMonth)){
			return EXCHANGE_COUNT_PER_MONTH ;
		}
		
		DateFormat df = new SimpleDateFormat(EXCHANGE_MON_FORMAT) ;
		try {
			Date exDate = df.parse(exMonth) ;
			Date curDate = new Date() ;
			
			if(DateUtils.isSameMonth(exDate, curDate)){
				return EXCHANGE_COUNT_PER_MONTH - exCount ;
			} else {
				return EXCHANGE_COUNT_PER_MONTH ;
			}

		} catch (ParseException e) {
			return EXCHANGE_COUNT_PER_MONTH ;
		}
	}
	
	public DcUserDO getUser(){
		return user ;
	}

	/**
	 * -------------------------------------------------------
	 */
	
	public String getAlipayAccount(){
		return user.getAlipayAccount() ;
	}
	
	public Long getUserId() {
        return user.getUserId();
    }

    public String getExternalId() {
        return user.getExternalId();
    }

    public String getExternalPf() {
        return user.getExternalPf();
    }

    public String getNick() {
        return user.getNick();
    }

    public GenderEnums getGender() {
        return GenderEnums.get(user.getGender());
    }

    public String getMobile() {
        return user.getMobile();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getMd5Password() {
        return user.getMd5Password();
    }

    public String getSource() {
        return user.getSource();
    }
    
    public Integer getUnreadMsg(){
    	return user.getUnreadMsg() ;
    }

    public Date getGmtModified() {
        return user.getGmtModified();
    }

    public Date getGmtCreate() {
        return user.getGmtCreate();
    }

    public Date getGmtLastLogin() {
        return user.getGmtLastLogin();
    }

	public Integer getIntegralCount() {
		return user.getIntegralCount();
	}
	
	public Integer getCheckInCount() {
		return user.getCheckInCount() == null? 0: user.getCheckInCount();
	}

	public void setCheckInCount(Integer checkInCount) {
		user.setCheckInCount(checkInCount);
	}

	public Date getGmtLastCheckin() {
		return user.getGmtLastCheckin();
	}

	public Long getNewGuide(){
		return user.getNewGuide() ;
	}
	
	public Integer getWinnerScore() {
		return user.getWinnerScore();
	}

	public void setWinnerScore(Integer winnerScore) {
		user.setWinnerScore(winnerScore) ;
	}
	
	public Date getGmtLastShare() {
		return user.getGmtLastShare();
	}
	
	public void setGmtLastShare(Date gmtLastShare) {
		user.setGmtLastShare(gmtLastShare);
	}
	
	public Integer getGoldEggCount() {
		return user.getGoldEggCount() ;
	}

	public void setGoldEggCount(Integer goldEggCount) {
		user.setGoldEggCount(goldEggCount) ;
	}
	
	public Integer getFrozenIntegralCount() {
		return user.getFrozenIntegralCount() ;
	}
	
	public Date getGmtFollowQzone() {
		return user.getGmtFollowQzone();
	}

	public void setFrozenIntegralCount(Integer frozenIntegralCount) {
		user.setFrozenIntegralCount(frozenIntegralCount) ;
	}

	public void setGmtFollowQzone(Date gmtFollowQzone) {
		user.setGmtFollowQzone(gmtFollowQzone);
	}
	
	public void setLevel(Integer level) {
		user.setLevel(level);
	}
	
	public Integer getLevel() {
		return user.getLevel();
	}
	
	public DcUserLevelEnum getLevelEnum() {
		return this.levelEnum;
	}
	
	/**
	 * 用户想到是否完成.
	 * @param guideStr
	 * @return
	 */
	public Boolean isGuideDone(String guideStr) {
		DcUserGuideEnum guideEnum = DcUserGuideEnum.toEnum(guideStr);
		return guideEnum.isDone(getNewGuide());
	}
}
