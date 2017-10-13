
package com.inflationinrussia.webservices;

import java.math.BigDecimal;
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
 *         &lt;element name="startAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="startMonth" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endMonth" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="applyDenominationOf1998" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "startAmount",
    "startMonth",
    "endMonth",
    "applyDenominationOf1998"
})
@XmlRootElement(name = "GetValueChange")
public class GetValueChange {

    @XmlElement(required = true)
    protected BigDecimal startAmount;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startMonth;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endMonth;
    protected boolean applyDenominationOf1998;

    /**
     * Gets the value of the startAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStartAmount() {
        return startAmount;
    }

    /**
     * Sets the value of the startAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStartAmount(BigDecimal value) {
        this.startAmount = value;
    }

    /**
     * Gets the value of the startMonth property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartMonth() {
        return startMonth;
    }

    /**
     * Sets the value of the startMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartMonth(XMLGregorianCalendar value) {
        this.startMonth = value;
    }

    /**
     * Gets the value of the endMonth property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndMonth() {
        return endMonth;
    }

    /**
     * Sets the value of the endMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndMonth(XMLGregorianCalendar value) {
        this.endMonth = value;
    }

    /**
     * Gets the value of the applyDenominationOf1998 property.
     * 
     */
    public boolean isApplyDenominationOf1998() {
        return applyDenominationOf1998;
    }

    /**
     * Sets the value of the applyDenominationOf1998 property.
     * 
     */
    public void setApplyDenominationOf1998(boolean value) {
        this.applyDenominationOf1998 = value;
    }

}
