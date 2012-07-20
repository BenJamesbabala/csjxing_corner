package cn.emay.sdk.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for mo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;mo&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;addSerial&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;addSerialRev&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;channelnumber&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;mobileNumber&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;sentTime&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;smsContent&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mo", propOrder = { "addSerial", "addSerialRev",
		"channelnumber", "mobileNumber", "sentTime", "smsContent" })
public class Mo {

	protected String addSerial;
	protected String addSerialRev;
	protected String channelnumber;
	protected String mobileNumber;
	protected String sentTime;
	protected String smsContent;

	/**
	 * Gets the value of the addSerial property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAddSerial() {
		return addSerial;
	}

	/**
	 * Sets the value of the addSerial property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAddSerial(String value) {
		this.addSerial = value;
	}

	/**
	 * Gets the value of the addSerialRev property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAddSerialRev() {
		return addSerialRev;
	}

	/**
	 * Sets the value of the addSerialRev property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAddSerialRev(String value) {
		this.addSerialRev = value;
	}

	/**
	 * Gets the value of the channelnumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getChannelnumber() {
		return channelnumber;
	}

	/**
	 * Sets the value of the channelnumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setChannelnumber(String value) {
		this.channelnumber = value;
	}

	/**
	 * Gets the value of the mobileNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * Sets the value of the mobileNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMobileNumber(String value) {
		this.mobileNumber = value;
	}

	/**
	 * Gets the value of the sentTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSentTime() {
		return sentTime;
	}

	/**
	 * Sets the value of the sentTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSentTime(String value) {
		this.sentTime = value;
	}

	/**
	 * Gets the value of the smsContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSmsContent() {
		return smsContent;
	}

	/**
	 * Sets the value of the smsContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSmsContent(String value) {
		this.smsContent = value;
	}

}
