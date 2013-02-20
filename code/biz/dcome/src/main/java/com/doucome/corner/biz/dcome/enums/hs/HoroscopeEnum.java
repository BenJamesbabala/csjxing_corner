package com.doucome.corner.biz.dcome.enums.hs;

/**
 * ÐÇ×ùÃ¶¾Ù.
 * @author ze2200
 *
 */
public enum HoroscopeEnum {
	ARIES(1, "aries", "3.21-4.19"),
	TAURUS(2, "taurus", "4.20-5.20"),
	GEMINI(3, "genimi", "5.21-6.21"),
	CANCER(4, "cancer", "6.22-7.22"),
	LION(5, "lion", "7.23-8.22"),
	VIRGO(6, "virgo", "8.23-9.22"),
	LIBRA(7, "libra", "9.23-10.23"),
	SCORPIO(8, "scorpio", "10.24-11.22"),
	SAGITTARIUS(9, "sagittarius", "11.23-12.21"),
	CAPRICORN(10, "capricorn", "12.22-1.19"),
	AQUARIUS(11, "aquarius", "1.20-2.18"),
	PISCES(12, "pisces", "2.19-3.20"),
	UNKNOW(-1, "", "");
	
	private Integer id;
	private String name;
	private String validPeriodStr;
	
	private HoroscopeEnum(int id, String name, String validPeriodStr) {
		this.id = id;
		this.name = name;
		this.validPeriodStr = validPeriodStr;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValidPeriodStr() {
		return this.validPeriodStr;
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static HoroscopeEnum toEnum(String name) {
		for (HoroscopeEnum temp: HoroscopeEnum.values()) {
			if (temp.name().equals(name)) {
				return temp;
			}
		}
		return UNKNOW;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
    public static HoroscopeEnum toEnum(Integer id) {
    	for (HoroscopeEnum temp: HoroscopeEnum.values()) {
			if (temp.getId().equals(id)) {
				return temp;
			}
		}
    	return UNKNOW;
	}
}
