package com.doucome.corner.biz.core.model.page;

import java.util.List;

import com.doucome.corner.biz.core.model.AbstractModel;

/**
 * 查询结果
 * 
 * @author shenjia.caosj 2012-1-12
 * 
 */
public class QueryResult<T> extends AbstractModel {

	@SuppressWarnings("unused")
	private QueryResult() {
	}

	/**
	 * 总记录数
	 */
	private long totalRecords;

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public QueryResult(List<T> items, Pagination pagination, long totalRecords) {
		super();
		this.items = items;
		this.pagination = pagination;
		this.totalRecords = totalRecords ;
		pagination.setTotalPages(Pagination.calcTotalPages(pagination,totalRecords));
	}

	/**
	 * 查询结果
	 */
	private List<T> items;

	private Pagination pagination;

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}
