package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.dal.dataobject.dcome.DcSystemDO;
import com.doucome.corner.biz.dal.dcome.DcSystemDAO;
import com.doucome.corner.biz.dcome.model.DcSystemDTO;
import com.doucome.corner.biz.dcome.service.DcSystemService;

public class DcSystemServiceImpl implements DcSystemService {

	@Autowired
	private DcSystemDAO dcSystemDAO ;
	
	@Override
	public List<DcSystemDTO> getSystems() {
		
		List<DcSystemDO> systemList = dcSystemDAO.querySystems() ;
		List<DcSystemDTO> dtoList = new ArrayList<DcSystemDTO>() ;
        if(!CollectionUtils.isEmpty(systemList)){
	        for(DcSystemDO item : systemList){
	        	dtoList.add(new DcSystemDTO(item)) ;
	        }
        }
        
		return dtoList ;
	}

	@Override
	public DcSystemDTO getSystemById(Long id) {
		DcSystemDO system = dcSystemDAO.querySystemById(id);
		if(system == null){
			return null ;
		}
		return new DcSystemDTO(system) ;
	}

}
