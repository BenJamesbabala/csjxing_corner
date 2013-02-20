package com.doucome.corner.web.dcome.action.frame.q;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcAppVoteDO;
import com.doucome.corner.biz.dcome.exception.DcDuplicateKeyException;
import com.doucome.corner.biz.dcome.service.DcAppVoteService;

public class JoinAppVoteAction extends QBasicAction {
	
	private static final Log log = LogFactory.getLog(JoinAppVoteAction.class) ;

	@Autowired
	private DcAppVoteService dcAppVoteService ;
	
	@Override
	public String execute() throws Exception {
		
		Long userId = getUserId() ;
		
		DcAppVoteDO vote = new DcAppVoteDO() ;
		vote.setUserId(userId) ;
		try {
			dcAppVoteService.createAppVote(vote) ;
		} catch (DcDuplicateKeyException e){
			//²Î¼Ó¹ý
		} catch (Exception e){
			log.error(e.getMessage() , e) ;
		}
		
		redirect("http://contest.open.qq.com/vote/pc") ;
		
		return SUCCESS ;
	}
}
