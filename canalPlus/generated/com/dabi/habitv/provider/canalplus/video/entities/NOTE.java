//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.02 at 04:16:21 PM CEST 
//


package com.dabi.habitv.provider.canalplus.video.entities;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{}AUTORISER"/>
 *         &lt;element ref="{}MOYENNE"/>
 *         &lt;element ref="{}NB_VOTES"/>
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
    "autoriser",
    "moyenne",
    "nbvotes"
})
@XmlRootElement(name = "NOTE")
public class NOTE {

    @XmlElement(name = "AUTORISER", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String autoriser;
    @XmlElement(name = "MOYENNE", required = true)
    protected String moyenne;
    @XmlElement(name = "NB_VOTES", required = true)
    protected BigInteger nbvotes;

    /**
     * Gets the value of the autoriser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUTORISER() {
        return autoriser;
    }

    /**
     * Sets the value of the autoriser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUTORISER(String value) {
        this.autoriser = value;
    }

    /**
     * Gets the value of the moyenne property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMOYENNE() {
        return moyenne;
    }

    /**
     * Sets the value of the moyenne property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMOYENNE(String value) {
        this.moyenne = value;
    }

    /**
     * Gets the value of the nbvotes property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNBVOTES() {
        return nbvotes;
    }

    /**
     * Sets the value of the nbvotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNBVOTES(BigInteger value) {
        this.nbvotes = value;
    }

}
