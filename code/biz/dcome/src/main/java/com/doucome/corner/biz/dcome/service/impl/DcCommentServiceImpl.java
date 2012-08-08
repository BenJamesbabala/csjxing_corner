package com.doucome.corner.biz.dcome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.doucome.corner.biz.core.model.page.Pagination;
import com.doucome.corner.biz.core.model.page.QueryResult;
import com.doucome.corner.biz.dal.condition.dcome.DcCommentSearchCondition;
import com.doucome.corner.biz.dal.dataobject.dcome.DcCommentDO;
import com.doucome.corner.biz.dal.dcome.DcCommentDAO;
import com.doucome.corner.biz.dcome.enums.DcCommentStatusEnums;
import com.doucome.corner.biz.dcome.model.DcCommentDTO;
import com.doucome.corner.biz.dcome.service.DcCommentService;

public class DcCommentServiceImpl implements DcCommentService {

	@Autowired
	private DcCommentDAO dcCommentDAO ;
	
	@Override
	public Long createComment(DcCommentDO comment) {
		return dcCommentDAO.insertComment(comment) ;
	}
	
	@Override
	public int createComments(List<DcCommentDO> comments) {
		if (comments == null || comments.size() == 0) {
			return 0;
		}
		return dcCommentDAO.insertComments(comments);
	}
	
	@Override
	public DcCommentDTO getCommentById(long id) {
		DcCommentDO comment = dcCommentDAO.queryCommentById(id) ;
		if(comment == null){
			return null ;
		}
		return new DcCommentDTO(comment);
	}
	
	@Override
	public int updateStatus(Long commentId, DcCommentStatusEnums status) {
		return dcCommentDAO.updateStatus(commentId , status.getValue()) ;
	}

	@Override
	public QueryResult<DcCommentDTO> getCommentsWithPagination(DcCommentSearchCondition searchCondition, Pagination pagination) {
		int totalRecords = dcCommentDAO.countCommentsWithPagination(searchCondition) ;
        if (totalRecords <= 0) {
            return new QueryResult<DcCommentDTO>(new ArrayList<DcCommentDTO>(), pagination, totalRecords);
        }
        List<DcCommentDO> commentList = dcCommentDAO.queryCommentsWithPagination(searchCondition, pagination.getStart(), pagination.getSize());
        
        List<DcCommentDTO> dtoList = new ArrayList<DcCommentDTO>() ;
        if(!CollectionUtils.isEmpty(commentList)){
	        for(DcCommentDO item : commentList){
	        	dtoList.add(new DcCommentDTO(item)) ;
	        }
        }
        
        return new QueryResult<DcCommentDTO>(dtoList, pagination, totalRecords);
	}

	@Override
	public List<DcCommentDTO> getCommentsNoPagination(
			DcCommentSearchCondition searchCondition, Pagination pagination) {
		
		List<DcCommentDO> commentList = dcCommentDAO.queryCommentsWithPagination(searchCondition, pagination.getStart(), pagination.getSize());
        
        List<DcCommentDTO> dtoList = new ArrayList<DcCommentDTO>() ;
        if(!CollectionUtils.isEmpty(commentList)){
	        for(DcCommentDO item : commentList){
	        	dtoList.add(new DcCommentDTO(item)) ;
	        }
        }
        return dtoList ;
	}

	

}
