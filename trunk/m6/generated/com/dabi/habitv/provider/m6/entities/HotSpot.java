//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.23 at 08:46:10 AM CEST 
//


package com.dabi.habitv.provider.m6.entities;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="imgurl" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="tcin" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="tcout" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="titre" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "HotSpot")
public class HotSpot {

    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String imgurl;
    @XmlAttribute(required = true)
    protected BigDecimal tcin;
    @XmlAttribute(required = true)
    protected BigDecimal tcout;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String titre;

    /**
     * Gets the value of the imgurl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * Sets the value of the imgurl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImgurl(String value) {
        this.imgurl = value;
    }

    /**
     * Gets the value of the tcin property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTcin() {
        return tcin;
    }

    /**
     * Sets the value of the tcin property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTcin(BigDecimal value) {
        this.tcin = value;
    }

    /**
     * Gets the value of the tcout property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTcout() {
        return tcout;
    }

    /**
     * Sets the value of the tcout property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTcout(BigDecimal value) {
        this.tcout = value;
    }

    /**
     * Gets the value of the titre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Sets the value of the titre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitre(String value) {
        this.titre = value;
    }

}
