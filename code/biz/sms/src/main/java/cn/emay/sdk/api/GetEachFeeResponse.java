package cn.emay.sdk.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for getEachFeeResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;getEachFeeResponse&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;return&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}double&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEachFeeResponse", propOrder = { "_return" })
public class GetEachFeeResponse {

	@XmlElement(name = "return")
	protected double _return;

	/**
	 * Gets the value of the return property.
	 * 
	 */
	public double getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 */
	public void setReturn(double value) {
		this._return = value;
	}

}
