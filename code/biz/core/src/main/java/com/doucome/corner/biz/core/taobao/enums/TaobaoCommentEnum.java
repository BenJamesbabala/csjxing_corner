package com.doucome.corner.biz.core.taobao.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.doucome.corner.biz.core.taobao.dto.TaobaoCommentDTO;
import com.doucome.corner.biz.core.utils.JacksonHelper;

/**
 * 
 * @author ze2200
 *
 */
public enum TaobaoCommentEnum {
	TAOBAO("TB", 2) {
		@Override
		public String getItemCommentAjaxUri() {
			return "http://rate.taobao.com/feedRateList.htm";
		}

		@Override
		protected void fillOtherParams(Map<String, Object> params) {
			params.put("currentPageNum", 1);
			//����.
			params.put("rateType", 1);
			params.put("orderType", "sort_weight");
			params.put("callback", "jsonp_reviews_list");
			
		}

		@Override
		public List<TaobaoCommentDTO> getItemComments(String commentsJson) {
			try {
				CommentListInfo rateList = JacksonHelper.fromJSON(commentsJson, CommentListInfo.class);
				if (rateList == null) {
					//�����Ա�������Ʒ����json����ʧ��.
					return null;
				}
				//
				List<TaobaoCommentDTO> comments = rateList.getComments();
				if (comments == null) {
					//service�㷵��ֵΪnull��ʾ������������ʧ�ܣ��˴�����Ϊnull��ʾ����û����Ʒû����������
					return new ArrayList<TaobaoCommentDTO>();
				}
				for (TaobaoCommentDTO tbcomComment: comments) {
					tbcomComment.setSouce(getSource());
				}
				return comments;
			} catch (Exception e) {
				
				return null;
			}
		}
	},
	TMALL("TM", 7) {
		
		@Override
		public String getItemCommentAjaxUri() {
			return "http://rate.taobao.com/detail_rate_4mall.htm";
		}

		@Override
		protected void fillOtherParams(Map<String, Object> params) {
			params.put("currentPage", 1);
			params.put("ismore", 1);
		}

		@Override
		public List<TaobaoCommentDTO> getItemComments(String commentsJson) {
			try {
				CommentDetail commentDetail = JacksonHelper.fromJSON(commentsJson, CommentDetail.class);
				if (commentDetail == null) {
					//������è��Ʒ����json����ʧ��.
					return null;
				}
				if (commentDetail.getRateListInfo() == null) {
					return new ArrayList<TaobaoCommentDTO>();
				}
				List<TaobaoCommentDTO> comments = commentDetail.getRateListInfo().getComments();
				if (comments == null) {
					//service�㷵��ֵΪnull��ʾ������������ʧ�ܣ��˴�����Ϊnull��ʾ����û����Ʒû����������
					return new ArrayList<TaobaoCommentDTO>();
				}
				for (TaobaoCommentDTO tbcomComment: comments) {
					tbcomComment.setSouce(getSource());
				}
				return comments;
			} catch (Exception e) {
				
				return null;
			}
		}
	};
	
	private String source;
	
	private int siteId;
	
	private TaobaoCommentEnum(String source, int siteId) {
		this.source = source;
		this.siteId = siteId;
	}
	
	public String getSource() {
		return this.source;
	}
	
	
	/**
	 * ��Ʒ����ajax����url��
	 * @return
	 */
	public String getItemCommentAjaxUrl(Map<String, Object> params) {
		if (params == null) {
			return "";
		}
		params.put("siteId", this.siteId);
		StringBuilder ajaxUrl = new StringBuilder(getItemCommentAjaxUri()).append("?");
		Object value = null;
		for (Map.Entry<String, Object> entry: params.entrySet()) {
			value = entry.getValue();
			ajaxUrl.append(entry.getKey()).append("=").append(value == null ? "" : value).append("&");
		}
		return ajaxUrl.toString();
	}
	
	/**
	 * ��ȡ�����������.
	 * @param itemId
	 * @param sellerId
	 * @return
	 */
	public Map<String, Object> getCommentRequestParams(Long itemId, Long sellerId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("auctionNumId", itemId);
		params.put("userNumId", sellerId);
		params.put("siteId", siteId);
		//Ĭ�ϻ�ȡ���������ݵ�����
		params.put("showContent", 1);
		fillOtherParams(params);
		return params;
	}
	
	protected abstract void fillOtherParams(Map<String, Object> params);
	
	/**
	 * ��Ʒ����ajax����uri
	 * @return
	 */
	public abstract String getItemCommentAjaxUri();
	/**
	 * ����json�ַ�������ȡ�Ա���Ʒ����.
	 * @param commentsJson ��Ʒ����json����.
	 * @return �Ա���Ʒ����. null:����ʧ��.
	 */
	public abstract List<TaobaoCommentDTO> getItemComments(String commentsJson);
	
	/**
	 * ��ȡö��ʵ��.
	 * @param source
	 * @return
	 */
	public static TaobaoCommentEnum getInstance(String source) {
		try {
			return valueOf(TaobaoCommentEnum.class, source);
		} catch (Exception e) {
			for (TaobaoCommentEnum sourceEnum: values()) {
				if (sourceEnum.getSource().equalsIgnoreCase(source)) {
					return sourceEnum;
				}
			}
		}
		return null;
	}
}

class CommentDetail {
	
//	private ScoreInfo scoreInfo;
	private CommentListInfo rateListInfo;
	
//	public ScoreInfo getScoreInfo() {
//		return scoreInfo;
//	}
//	
//	public void setScoreInfo(ScoreInfo scoreInfo) {
//		this.scoreInfo = scoreInfo;
//	}
//	
	public CommentListInfo getRateListInfo() {
		return rateListInfo;
	}
	
	public void setRateListInfo(CommentListInfo rateListInfo) {
		this.rateListInfo = rateListInfo;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

class CommentListInfo {
	
	private int watershed;
	
	private List<TaobaoCommentDTO> comments;
	
	private int maxPage;
	
	private int currentPageNum;
	
	public int getWatershed() {
		return watershed;
	}
	public void setWatershed(int watershed) {
		this.watershed = watershed;
	}
	public List<TaobaoCommentDTO> getComments() {
		return comments;
	}
	
	public void setComments(List<TaobaoCommentDTO> comments) {
		this.comments = comments;
	}
	
	/**
	 * tmall�������б�json�ֶ���ΪrateList;
	 * @param comments
	 */
	public void setRateList(List<TaobaoCommentDTO> comments) {
		this.comments = comments;
	}
	
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
