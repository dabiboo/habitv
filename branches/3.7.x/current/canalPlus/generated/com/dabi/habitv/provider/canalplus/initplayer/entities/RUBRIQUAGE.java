//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.14 at 08:38:13 PM CEST 
//


package com.dabi.habitv.provider.canalplus.initplayer.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *       &lt;sequence>
 *         &lt;element ref="{}UNIVERS"/>
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
    "univers"
})
@XmlRootElement(name = "RUBRIQUAGE")
public class RUBRIQUAGE {

    @XmlElement(name = "UNIVERS", required = true)
    protected UNIVERS univers;

    /**
     * Gets the value of the univers property.
     * 
     * @return
     *     possible object is
     *     {@link UNIVERS }
     *     
     */
    public UNIVERS getUNIVERS() {
        return univers;
    }

    /**
     * Sets the value of the univers property.
     * 
     * @param value
     *     allowed object is
     *     {@link UNIVERS }
     *     
     */
    public void setUNIVERS(UNIVERS value) {
        this.univers = value;
    }

}
