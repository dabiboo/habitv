//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.15 at 07:04:26 PM CEST 
//


package com.dabi.habitv.provider.canalplus.mea.entities;

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
 *         &lt;element ref="{}UNIVERS"/>
 *         &lt;element ref="{}RUBRIQUE"/>
 *         &lt;element ref="{}CATEGORIE"/>
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
    "univers",
    "rubrique",
    "categorie"
})
@XmlRootElement(name = "RUBRIQUAGE")
public class RUBRIQUAGE {

    @XmlElement(name = "UNIVERS", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String univers;
    @XmlElement(name = "RUBRIQUE", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String rubrique;
    @XmlElement(name = "CATEGORIE", required = true)
    protected String categorie;

    /**
     * Gets the value of the univers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUNIVERS() {
        return univers;
    }

    /**
     * Sets the value of the univers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUNIVERS(String value) {
        this.univers = value;
    }

    /**
     * Gets the value of the rubrique property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRUBRIQUE() {
        return rubrique;
    }

    /**
     * Sets the value of the rubrique property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRUBRIQUE(String value) {
        this.rubrique = value;
    }

    /**
     * Gets the value of the categorie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCATEGORIE() {
        return categorie;
    }

    /**
     * Sets the value of the categorie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCATEGORIE(String value) {
        this.categorie = value;
    }

}
