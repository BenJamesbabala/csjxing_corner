package cn.emay.sdk.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for statusReport complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;statusReport&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;errorCode&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;memo&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;mobile&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;receiveDate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;reportStatus&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;seqID&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}long&quot;/&gt;
 *         &lt;element name=&quot;serviceCodeAdd&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;submitDate&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statusReport", propOrder = { "errorCode", "memo", "mobile",
		"receiveDate", "reportStatus", "seqID", "serviceCodeAdd", "submitDate" })
public class StatusReport {

	protected String errorCode;
	protected String memo;
	protected String mobile;
	protected String receiveDate;
	protected int reportStatus;
	protected long seqID;
	protected String serviceCodeAdd;
	protected String submitDate;

	/**
	 * Gets the value of the errorCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the value of the errorCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setErrorCode(String value) {
		this.errorCode = value;
	}

	/**
	 * Gets the value of the memo property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * Sets the value of the memo property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMemo(String value) {
		this.memo = value;
	}

	/**
	 * Gets the value of the mobile property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the value of the mobile property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMobile(String value) {
		this.mobile = value;
	}

	/**
	 * Gets the value of the receiveDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReceiveDate() {
		return receiveDate;
	}

	/**
	 * Sets the value of the receiveDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReceiveDate(String value) {
		this.receiveDate = value;
	}

	/**
	 * Gets the value of the reportStatus property.
	 * 
	 */
	public int getReportStatus() {
		return reportStatus;
	}

	/**
	 * Sets the value of the reportStatus property.
	 * 
	 */
	public void setReportStatus(int value) {
		this.reportStatus = value;
	}

	/**
	 * Gets the value of the seqID property.
	 * 
	 */
	public long getSeqID() {
		return seqID;
	}

	/**
	 * Sets the value of the seqID property.
	 * 
	 */
	public void setSeqID(long value) {
		this.seqID = value;
	}

	/**
	 * Gets the value of the serviceCodeAdd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getServiceCodeAdd() {
		return serviceCodeAdd;
	}

	/**
	 * Sets the value of the serviceCodeAdd property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setServiceCodeAdd(String value) {
		this.serviceCodeAdd = value;
	}

	/**
	 * Gets the value of the submitDate property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSubmitDate() {
		return submitDate;
	}

	/**
	 * Sets the value of the submitDate property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSubmitDate(String value) {
		this.submitDate = value;
	}

}
