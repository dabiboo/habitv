//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.07.25 at 07:07:30 PM CEST 
//


package com.dabi.habitv.provider.m6.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="art_banner" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="link_banner" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "M6Replay_banner")
public class M6ReplayBanner {

    @XmlAttribute(name = "art_banner", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String artBanner;
    @XmlAttribute(name = "link_banner", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String linkBanner;

    /**
     * Gets the value of the artBanner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArtBanner() {
        return artBanner;
    }

    /**
     * Sets the value of the artBanner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArtBanner(String value) {
        this.artBanner = value;
    }

    /**
     * Gets the value of the linkBanner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkBanner() {
        return linkBanner;
    }

    /**
     * Sets the value of the linkBanner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkBanner(String value) {
        this.linkBanner = value;
    }

}
