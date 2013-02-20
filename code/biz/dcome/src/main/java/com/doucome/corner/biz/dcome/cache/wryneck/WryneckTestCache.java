package com.doucome.corner.biz.dcome.cache.wryneck;

import java.util.List;

import com.doucome.corner.biz.dcome.model.wryneck.WryneckTestDTO;

public interface WryneckTestCache {

	void setCache(List<WryneckTestDTO> list) ;
	
	List<WryneckTestDTO> getCache() ;
}
