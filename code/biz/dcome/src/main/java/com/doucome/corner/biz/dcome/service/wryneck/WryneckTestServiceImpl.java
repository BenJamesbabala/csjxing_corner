package com.doucome.corner.biz.dcome.service.wryneck;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.wryneck.WryneckTestDAO;
import com.doucome.corner.biz.dal.wryneck.dataobject.WryneckTestDO;
import com.doucome.corner.biz.dcome.cache.wryneck.WryneckTestCache;
import com.doucome.corner.biz.dcome.model.wryneck.WryneckTestDTO;

public class WryneckTestServiceImpl implements WryneckTestService  {

	@Autowired
	private WryneckTestDAO wryneckTestDAO ;
	
	@Autowired
	private WryneckTestCache wryneckTestCache ;
	
	@Override
	public List<WryneckTestDTO> getTests() {
		List<WryneckTestDTO> testList = wryneckTestCache.getCache() ;
		if(testList == null){
			List<WryneckTestDO> testDOList = wryneckTestDAO.queryTests() ;
			testList = new ArrayList<WryneckTestDTO>() ;
			for(WryneckTestDO test : testDOList){
				testList.add(new WryneckTestDTO(test)) ;
			}
			
			wryneckTestCache.setCache(testList) ;
		}
		return testList ;
	}

}
