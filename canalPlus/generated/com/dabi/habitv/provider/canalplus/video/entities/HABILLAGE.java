//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.02 at 04:32:24 PM CEST 
//


package com.dabi.habitv.provider.canalplus.video.entities;

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
 *         &lt;element ref="{}VISUEL"/>
 *         &lt;element ref="{}DESTINATION"/>
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
    "visuel",
    "destination"
})
@XmlRootElement(name = "HABILLAGE")
public class HABILLAGE {

    @XmlElement(name = "VISUEL", required = true)
    protected VISUEL visuel;
    @XmlElement(name = "DESTINATION", required = true)
    protected DESTINATION destination;

    /**
     * Gets the value of the visuel property.
     * 
     * @return
     *     possible object is
     *     {@link VISUEL }
     *     
     */
    public VISUEL getVISUEL() {
        return visuel;
    }

    /**
     * Sets the value of the visuel property.
     * 
     * @param value
     *     allowed object is
     *     {@link VISUEL }
     *     
     */
    public void setVISUEL(VISUEL value) {
        this.visuel = value;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link DESTINATION }
     *     
     */
    public DESTINATION getDESTINATION() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link DESTINATION }
     *     
     */
    public void setDESTINATION(DESTINATION value) {
        this.destination = value;
    }

}
