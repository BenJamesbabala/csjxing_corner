package com.doucome.corner.biz.core.service.upyun.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.doucome.corner.biz.core.service.upyun.UpYunUtils;

/**
 * 
 * @author langben 2012-7-22
 *
 */
public class UpyunDataEntity {
	
	private static final Log log = LogFactory.getLog(UpyunDataEntity.class) ;

	/**
	 * �Ƿ��Զ�����Ŀ¼
	 */
	private boolean autoCreateDirectory = true ;
	
	/**
	 * ����
	 */
	private byte[] data ;

	/**
	 * �����ϵ�·��
	 */
	private String toPath ;
	
	/**
	 * MD5
	 */
	private String contentMD5 ;
	
	/**
	 * �ļ�����
	 */
	private String fileSecret ;
	
	/**
	 * �ϴ��Ŀռ�
	 */
	private String bucketname ;
	
	public String getToPath() {
		return toPath;
	}

	public String getContentMD5() {
		return contentMD5;
	}

	public void setContentMD5(String contentMD5) {
		this.contentMD5 = contentMD5;
	}

	public String getFileSecret() {
		return fileSecret;
	}

	public void setFileSecret(String fileSecret) {
		this.fileSecret = fileSecret;
	}

	public void setToPath(String toPath) {
		this.toPath = toPath;
	}

	public boolean isAutoCreateDirectory() {
		return autoCreateDirectory;
	}

	public void setAutoCreateDirectory(boolean autoCreateDirectory) {
		this.autoCreateDirectory = autoCreateDirectory;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
		try {
			this.contentMD5 = UpYunUtils.md5(this.data) ;
		} catch (Exception e) {
			log.error(e.getMessage() , e) ;
		}
	}

	public String getBucketname() {
		return bucketname;
	}

	public void setBucketname(String bucketname) {
		this.bucketname = bucketname;
	}
	
	
	
}
