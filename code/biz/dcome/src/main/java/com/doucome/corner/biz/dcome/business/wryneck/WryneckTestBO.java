package com.doucome.corner.biz.dcome.business.wryneck;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dcome.model.wryneck.WryneckTestDTO;
import com.doucome.corner.biz.dcome.service.wryneck.WryneckTestService;

/**
 * ≤‚≤‚ƒƒ÷÷Õ·≤±◊”
 * @author langben 2013-1-7
 *
 */
public class WryneckTestBO {
	
	@Autowired
	private WryneckTestService wryneckTestService ;
	
	public WryneckTestDTO test(){
		
		List<WryneckTestDTO> testList = wryneckTestService.getTests() ;
		
		int size = testList.size() ;
		int randomInt = RandomUtils.nextInt(new Random(System.currentTimeMillis()), size) ;
		
		return testList.get(randomInt) ;
	}
	
}
