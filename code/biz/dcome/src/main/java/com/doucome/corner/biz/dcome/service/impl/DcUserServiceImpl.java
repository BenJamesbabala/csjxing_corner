package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.CollectionUtils;

import com.doucome.corner.biz.common.utils.IDUtils;
import com.doucome.corner.biz.core.utils.JacksonHelper;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserDO;
import com.doucome.corner.biz.dal.dcome.DcUserDAO;
import com.doucome.corner.biz.dcome.cache.DcUserCache;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.model.DcUserDTO;
import com.doucome.corner.biz.dcome.service.DcUserService;

/**
 * @author ib 2012-7-28 ÏÂÎç11:50:22
 */
public class DcUserServiceImpl implements DcUserService {
	
    @Autowired
    private DcUserDAO dcUserDAO;

    @Autowired
    private DcUserCache dcUserCache ;
    
    @Override
    public long insertUser(DcUserDO dcUserDO) {
    	if(dcUserDO == null){
    		throw new IllegalArgumentException("user cant be null.") ;
    	}
        return dcUserDAO.insertUser(dcUserDO);
    }

    @Override
    public DcUserDTO getUser(Long userId) {
    	if (IDUtils.isNotCorrect(userId)) {
    		return null;
    	}
        DcUserDTO user = dcUserCache.get(userId) ;
        if(user == null){
        	DcUserDO userDO = dcUserDAO.queryUser(userId);
        	if(userDO != null){
        		user = new DcUserDTO(userDO) ;
        		dcUserCache.set(user) ;
        	}
        }
        return user;
    }
    
    @Override
    public List<DcUserDTO> queryUsers(List<Long> userIds) {
    	if (CollectionUtils.isEmpty(userIds)) {
    		return new ArrayList<DcUserDTO>();
    	}
    	Set<Long> tempSet = new HashSet<Long>();
    	tempSet.addAll(userIds);
    	userIds.clear();
    	userIds.addAll(tempSet);
    	List<DcUserDTO> result = new ArrayList<DcUserDTO>();
    	List<Long> tempIds = new ArrayList<Long>();
    	DcUserDTO tempUser = null;
    	Map<Long, DcUserDTO> users = dcUserCache.getCacheMap(userIds);
    	if (users == null) {
    		tempIds.addAll(userIds);
    	} else {
	    	for (Long userId: userIds) {
	    		tempUser = users.get(userId);
	    		if (tempUser == null) {
	    			tempIds.add(userId);
	    		} else {
	    			result.add(tempUser);
	    		}
	    	}
    	}
    	if (tempIds.size() != 0) {
    		List<DcUserDO> tempUsers = dcUserDAO.queryUsers(tempIds);
    		for (DcUserDO temp: tempUsers) {
    			DcUserDTO tempDTO = new DcUserDTO(temp);
    			result.add(tempDTO);
    			dcUserCache.set(tempDTO);
    		}
    	}
    	return result;
    }

    @Override
    public DcUserDTO queryUserByExternalId(String externalId, String externalPf) {
        if (StringUtils.isNotBlank(externalId) && StringUtils.isNotBlank(externalPf)) {
            DcUserDO userDO = dcUserDAO.queryUserByExternalId(externalId, externalPf);
            if(userDO == null) {
            	return null ;
            }
            return new DcUserDTO(userDO) ;
        }
        return null;
    }

    @Override
    public int updateLastLoginTime(long userId) {
    	int effectCount = dcUserDAO.updateLastLoginTime(userId);
    	triggerUserModified(userId) ;
    	return effectCount ;
    }
    
    @Override
	public int updateLastLoginTimeAndTrace(long userId, String trace) {
    	trace = StringUtils.substring(trace, 0, 128) ;
    	int effectCount = dcUserDAO.updateLastLoginTimeAndTrace(userId , trace);
    	triggerUserModified(userId) ;
    	return effectCount ;
	}
    
    @Override
    public int updateLastCheckinTime(long userId, int checkInCount) {
    	int effectCount = dcUserDAO.updateLastCheckinTime(userId, checkInCount) ;
    	triggerUserModified(userId) ;
    	return effectCount ;
    }

    @Override
    public int updateUser(DcUserDO dcUserDO) {
        if (dcUserDO != null && dcUserDO.getUserId() > 0) {
        	int effectCount = dcUserDAO.updateUser(dcUserDO);
            triggerUserModified(dcUserDO.getUserId()) ;
            return effectCount ;
        }
        return 0;
    }
    
	@Override
	public int incrIntegralByUser(long userId, int count) {
		int effectCount = dcUserDAO.incrIntegralByUser(userId, count) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}

	@Override
	public int decrIntegralByUser(long userId, int count) {
		int effectCount = dcUserDAO.decrIntegralByUser(userId, count) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}
	
	@Override
	public int incrGoldEggByUser(long userId, int count) {
		int effectCount = dcUserDAO.incrGoldEggByUser(userId, count) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}

	@Override
	public int decrGoldEggByUser(long userId) {
		int effectCount = dcUserDAO.decrGoldEggByUser(userId, 1) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}
	
	@Override
	public int incrUnreadMsgCountByUser(long userId, int count) {
		int effectCount = dcUserDAO.incrUnreadMsgCountByUser(userId, count) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}
	
	@Override
	public int clearUnreadMsgCountByUser(long userId) {
		int effectCount = dcUserDAO.clearUnreadMsgCountByUser(userId) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}
	
	@Override
	public int updateNewGuide(long userId, long newValue) {
		int effectCount = dcUserDAO.updateNewGuide(userId, newValue) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}
	
	@Override
	public int markDailyShare(Long userId) {
		if (!IDUtils.isCorrect(userId)) {
			return 0;
		}
		int count = dcUserDAO.updateDailyShare(userId);
		triggerUserModified(userId);
		return count;
	}
	
	@Override
	public int updateFollowQzone(Long userId) {
		if (!IDUtils.isCorrect(userId)) {
			return 0;
		}
		int count = dcUserDAO.updateFollowQzone(userId);
		triggerUserModified(userId);
		return count;
	}
	
	private void triggerUserModified(Long userId){
    	dcUserCache.remove(userId) ;
    }
	
	private boolean resetCache(DcUserDTO user) {
		boolean result = dcUserCache.set(user);
		if (!result) {
			triggerUserModified(user.getUserId());
		}
		return result;
	}

	@Override
	public int unfrozenIntegralByUser(long userId, Integer integralCount) {
		int effectCount = dcUserDAO.unfrozenIntegralByUser(userId, integralCount) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}

	@Override
	public int frozenIntegralByUser(long userId, Integer integralCount) {
		int effectCount = dcUserDAO.frozenIntegralByUser(userId, integralCount) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}

	@Override
	public int descFrozenIntegralByUser(long userId, Integer integralCount) {
		int effectCount = dcUserDAO.descFrozenIntegralByUser(userId, integralCount) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}

	@Override
	public int updateNick(Long userId, String nick)
			throws DcDuplicateKeyException {
		try {
			int effectCount = dcUserDAO.updateNick(userId, nick) ;
			triggerUserModified(userId) ;
			return effectCount ;
		} catch(DuplicateKeyException e){
			throw new DcDuplicateKeyException(e) ;
		}
	}

	@Override
	public int incrWinnerScoreByUser(long userId, int score) {
		int effectCount = dcUserDAO.incrWinnerScoreByUser(userId, score) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}

	@Override
	public int updateWinnerScoreToIntegralByUser(long userId) {
		int effectCount = dcUserDAO.updateWinnerScoreToIntegralByUser(userId) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}

	@Override
	public int updateAlipayAccountByUser(long userId , String alipayAccount) {
		int effectCount = dcUserDAO.updateAlipayAccountByUser(userId , alipayAccount) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}

	@Override
	public int updateExtendDescByUser(long userId, Map<String,String> extendDescMap) {
		String extendDesc = JacksonHelper.toJSON(extendDescMap) ;
		int effectCount = dcUserDAO.updateExtendDescByUser(userId , extendDesc) ;
		triggerUserModified(userId) ;
		return effectCount ;
	}
}
