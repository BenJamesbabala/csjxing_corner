package com.doucome.corner.biz.dcome.enums;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.doucome.corner.biz.core.utils.StreamUtils;

public enum DcCategoryEnum {
	CLOTH(1L, "CLO"),
	PANTS(2L, "PAN"),
	SHOES(3L, "SHO"),
	BAG(4L, "BAG"),
	COSMETIC(5L, "COS"),
	ACCESSORIES(6L, "ACC"),
	UNKNOW(0l, "");
	
	private Long id;
	
	private String value;
	
	List<String> keywords = new ArrayList<String>();
	
	private DcCategoryEnum(Long id, String value) {
		this.id = id;
		this.value = value;
		BufferedReader reader = null;
		try {
			InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("keywords/categoryKeywords.txt");
			reader = new BufferedReader(new InputStreamReader(input, "GBK"));
			String line = null, idStr = String.valueOf(this.id);
			while ((line = reader.readLine()) != null) {
				String[] temps = line.split("=");
				if (idStr.equals(temps[0])) {
					keywords = Arrays.asList(temps[1].split(","));
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			StreamUtils.close(reader);
		}
	}
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	/**
     * 
     * @param status
     * @return
     */
    public static DcCategoryEnum toEnum(Long id) {
    	for (DcCategoryEnum temp: DcCategoryEnum.values()) {
    		if (temp.getValue().equals(id)) {
    			return temp;
    		}
    	}
    	return UNKNOW;
    }

    public static Long getPossibleId(String itemName) {
    	for (DcCategoryEnum temp: DcCategoryEnum.values()) {
    		List<String> keyWords = temp.getKeywords();
    		for (String key: keyWords) {
    			if (itemName.indexOf(key) != -1) {
    				return temp.getId();
    			}
    		}
    	}
    	return ACCESSORIES.getId();
    }
}

