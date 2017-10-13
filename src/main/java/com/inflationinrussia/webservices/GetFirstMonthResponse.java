
package com.inflationinrussia.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetFirstMonthResult" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getFirstMonthResult"
})
@XmlRootElement(name = "GetFirstMonthResponse")
public class GetFirstMonthResponse {

    @XmlElement(name = "GetFirstMonthResult", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar getFirstMonthResult;

    /**
     * Gets the value of the getFirstMonthResult property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGetFirstMonthResult() {
        return getFirstMonthResult;
    }

    /**
     * Sets the value of the getFirstMonthResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGetFirstMonthResult(XMLGregorianCalendar value) {
        this.getFirstMonthResult = value;
    }

}
