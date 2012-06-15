package com.doucome.corner.task.zhe.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.doucome.corner.biz.core.model.AbstractModel;
import com.doucome.corner.biz.core.utils.JacksonHelper;

/**
 * 同步任务运行结束后的结果
 * 
 * @author shenjia.caosj 2012-4-3
 * 
 */
public class SyncReportRunResult {

	private SyncReportCodeEnums code = SyncReportCodeEnums.SUCCESS;

	private String detailMsg;

	/**
	 * 总共的报表会员数
	 */
	private int totalCount;

	/**
	 * 成功的报表会员数
	 */
	private int successCount;

	/**
	 * 同步失败的页数
	 */
	private List<Page> failPages;
	/**
	 * 生成的结算报表信息.
	 */
	private SettleReportResult settleReportResult; 

	public SyncReportCodeEnums getCode() {
		return code;
	}

	public void setCode(SyncReportCodeEnums code) {
		this.code = code;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	public boolean isSuccess() {
		return code == SyncReportCodeEnums.SUCCESS;
	}

	public String getDetailMsg() {
		return detailMsg;
	}

	public void setDetailMsg(String detailMsg) {
		this.detailMsg = detailMsg;
	}

	public List<Page> getFailPages() {
		return failPages;
	}

	public void setFailPages(List<Page> failPages) {
		this.failPages = failPages;
	}

	public SettleReportResult getSettleReportResult() {
		return settleReportResult;
	}

	public void setSettleReportResult(SettleReportResult settleReportResult) {
		this.settleReportResult = settleReportResult;
	}

	public static void main(String[] args) {
		SyncReportRunResult r = new SyncReportRunResult();
		r.setCode(SyncReportCodeEnums.ASSIGN_ERROR);
		r.setDetailMsg("fewfewfewgregre") ;
		r.setSuccessCount(1);
		r.setTotalCount(100);
		List<Page> failPages = new ArrayList<Page>() ;
		failPages.add(new Page(1,2,3)) ;
		failPages.add(new Page(1,2,3)) ;
		failPages.add(new Page(1,2,3)) ;
 		r.setFailPages(failPages) ;
		System.out.println(r);

	}

	public static class Page extends AbstractModel {

		public Page(int page, int start, int size) {
			this.page = page;
			this.start = start;
			this.size = size;
		}

		private int page;
		private int start;
		private int size;

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

	}
	
	public static class SettleReportResult {
		private int totalCount;
		private int succCount;
		
		public SettleReportResult() {
			this(0, 0);
		}
		
		public SettleReportResult(int totalCount, int succCount) {
			this.totalCount = totalCount;
			this.succCount = succCount;
		}
		
		public int getTotalCount() {
			return totalCount;
		}
		
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		
		public int getSuccCount() {
			return succCount;
		}
		
		public void setSuccCount(int succCount) {
			this.succCount = succCount;
		}
		
		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}
	}

	@Override
	public String toString() {
		return JacksonHelper.toJSON(this) ;
	}
}
