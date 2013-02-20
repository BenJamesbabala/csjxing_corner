package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.dal.condition.dcome.DcIntegralCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcUserIntegralDetailDO;
import com.doucome.corner.biz.dal.dcome.DcUserIntegralDetailDAO;
import com.doucome.corner.biz.dcome.enums.DcIntegralSourceEnums;
import com.doucome.corner.biz.dcome.enums.DcYesOrNoEnum;
import com.doucome.corner.biz.dcome.model.DcUserIntegralDetailDTO;
import com.doucome.corner.biz.dcome.service.DcUserIntegralDetailService;


public class DcUserIntegralDetailServiceImpl implements DcUserIntegralDetailService {

	@Autowired
	private DcUserIntegralDetailDAO dcUserIntegralDetailDAO ;
	
	@Override
	public Long createDetail(DcUserIntegralDetailDO detail) {
		return dcUserIntegralDetailDAO.insertDetail(detail);
	}
	
	@Override
	public List<DcUserIntegralDetailDTO> getUserIntegralDetails(Long userId, Pagination page) {
		List<DcUserIntegralDetailDO> integralDetails =
			dcUserIntegralDetailDAO.getUserIntegralDetails(userId, page.getStart() - 1, page.getSize());
		return convert(integralDetails);
	}
	/**
	@Override
	public List<DcUserIntegralDetailDTO> getIntegralDetails(Pagination page) {
		List<DcUserIntegralDetailDO> integralDetails =
			dcUserIntegralDetailDAO.getIntegralDetails(page.getStart() - 1, page.getSize());
		return convert(integralDetails);
	}
	**/
	@Override
	public List<DcUserIntegralDetailDTO> getLatestIntegralDetails(DcIntegralSourceEnums source, Pagination page) {
		String sourceStr = source == null? null: source.getValue();
		List<DcUserIntegralDetailDO> details =
			dcUserIntegralDetailDAO.getLatestIntegralDetails(sourceStr, page.getStart() - 1, page.getSize());
		return convert(details);
	}
	
	@Override
	public List<DcUserIntegralDetailDTO> getIntegralDetails(DcIntegralCondition condition, Pagination page) {
		List<DcUserIntegralDetailDO> integralDetails =
			dcUserIntegralDetailDAO.getIntegralDetails(condition, page.getStart() - 1, page.getSize());
		return convert(integralDetails);
	}
	
	/**
	 * 
	 */
	public Integer countIntegralDetails(DcIntegralCondition condition) {
		if (condition == null) {
			return 0;
		}
		return dcUserIntegralDetailDAO.countIntegralDetails(condition);
	}
	
	@Override
	public List<DcUserIntegralDetailDTO> getAuctionBidList(Long auctionId) {
		List<DcUserIntegralDetailDO> integralDetails = dcUserIntegralDetailDAO.getAuctionList(auctionId, 20);
		return convert(integralDetails);
	}
	
	@Override
	public int updateReadStatus(Long id, DcYesOrNoEnum readStatus) {
		try{
		return dcUserIntegralDetailDAO.updateReadStatus(id, readStatus.getValue());
		} catch (Exception e) {
			return 0;
		}
	}
	
	private List<DcUserIntegralDetailDTO> convert(List<DcUserIntegralDetailDO> integralDetails) {
		List<DcUserIntegralDetailDTO> result = new ArrayList<DcUserIntegralDetailDTO>();
		if (!CollectionUtils.isEmpty(integralDetails)) {
			for (DcUserIntegralDetailDO temp: integralDetails) {
				result.add(new DcUserIntegralDetailDTO(temp));
			}
		}
		return result;
	}
}