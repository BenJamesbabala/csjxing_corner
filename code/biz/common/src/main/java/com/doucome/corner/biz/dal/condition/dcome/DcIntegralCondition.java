package com.doucome.corner.biz.dal.condition.dcome;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * @author ze2200
 *
 */
public class DcIntegralCondition {
	/**
	 * 用户id.必填项.
	 */
	private Long userId;
	/**
	 * 积分来源.
	 */
	private String source;
	/**
	 * 积分来源.
	 */
	private Set<String> sources;
	
	private String readStatus;
	/**
	 * 包括此时间点的
	 */
	private Date gmtCreateStart;
	/**
	 * 不包括此时间点的
	 */
	private Date gmtCreateEnd;
	
	/**
	 * 
	 * @return
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 设置用户id.必填项.
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public void setSources(Set<String> sources) {
		this.sources = sources;
	}
	
	public List<String> getSources() {
		if (this.sources == null) {
			return null;
		}
		return new ArrayList<String>(this.sources);
	}
	
	public void addSource(String source) {
		if (this.sources == null) {
			this.sources = new HashSet<String>();
		}
		this.sources.add(source);
	}
	
	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public Date getGmtCreateStart() {
		return gmtCreateStart;
	}

	public void setGmtCreateStart(Date gmtCreateStart) {
		this.gmtCreateStart = gmtCreateStart;
	}

	public Date getGmtCreateEnd() {
		return gmtCreateEnd;
	}

	public void setGmtCreateEnd(Date gmtCreateEnd) {
		this.gmtCreateEnd = gmtCreateEnd;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("userId", getUserId());
		temp.put("source", getSource());
		temp.put("sources", getSources());
		temp.put("readStatus", readStatus);
		temp.put("gmtCreateStart", getGmtCreateStart());
		temp.put("gmtCreateEnd", getGmtCreateEnd());
		return temp;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this ,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
