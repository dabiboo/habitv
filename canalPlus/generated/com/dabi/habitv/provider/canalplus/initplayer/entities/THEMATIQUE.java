//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.05.24 at 09:12:23 AM CEST 
//


package com.dabi.habitv.provider.canalplus.initplayer.entities;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element ref="{}ID"/>
 *         &lt;element ref="{}NOM"/>
 *         &lt;element ref="{}URL_RACINE"/>
 *         &lt;element ref="{}COULEUR"/>
 *         &lt;element ref="{}RUBRIQUAGE"/>
 *         &lt;element ref="{}PUB"/>
 *         &lt;element ref="{}SELECTIONS"/>
 *       &lt;/sequence>
 *       &lt;attribute name="EVENEMENT" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "nom",
    "urlracine",
    "couleur",
    "rubriquage",
    "pub",
    "selections"
})
@XmlRootElement(name = "THEMATIQUE")
public class THEMATIQUE {

    @XmlElement(name = "ID", required = true)
    protected BigInteger id;
    @XmlElement(name = "NOM", required = true)
    protected String nom;
    @XmlElement(name = "URL_RACINE", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String urlracine;
    @XmlElement(name = "COULEUR", required = true)
    protected String couleur;
    @XmlElement(name = "RUBRIQUAGE", required = true)
    protected RUBRIQUAGE rubriquage;
    @XmlElement(name = "PUB", required = true)
    protected PUB pub;
    @XmlElement(name = "SELECTIONS", required = true)
    protected SELECTIONS selections;
    @XmlAttribute(name = "EVENEMENT")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String evenement;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setID(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the nom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOM() {
        return nom;
    }

    /**
     * Sets the value of the nom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOM(String value) {
        this.nom = value;
    }

    /**
     * Gets the value of the urlracine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURLRACINE() {
        return urlracine;
    }

    /**
     * Sets the value of the urlracine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURLRACINE(String value) {
        this.urlracine = value;
    }

    /**
     * Gets the value of the couleur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOULEUR() {
        return couleur;
    }

    /**
     * Sets the value of the couleur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOULEUR(String value) {
        this.couleur = value;
    }

    /**
     * Gets the value of the rubriquage property.
     * 
     * @return
     *     possible object is
     *     {@link RUBRIQUAGE }
     *     
     */
    public RUBRIQUAGE getRUBRIQUAGE() {
        return rubriquage;
    }

    /**
     * Sets the value of the rubriquage property.
     * 
     * @param value
     *     allowed object is
     *     {@link RUBRIQUAGE }
     *     
     */
    public void setRUBRIQUAGE(RUBRIQUAGE value) {
        this.rubriquage = value;
    }

    /**
     * Gets the value of the pub property.
     * 
     * @return
     *     possible object is
     *     {@link PUB }
     *     
     */
    public PUB getPUB() {
        return pub;
    }

    /**
     * Sets the value of the pub property.
     * 
     * @param value
     *     allowed object is
     *     {@link PUB }
     *     
     */
    public void setPUB(PUB value) {
        this.pub = value;
    }

    /**
     * Gets the value of the selections property.
     * 
     * @return
     *     possible object is
     *     {@link SELECTIONS }
     *     
     */
    public SELECTIONS getSELECTIONS() {
        return selections;
    }

    /**
     * Sets the value of the selections property.
     * 
     * @param value
     *     allowed object is
     *     {@link SELECTIONS }
     *     
     */
    public void setSELECTIONS(SELECTIONS value) {
        this.selections = value;
    }

    /**
     * Gets the value of the evenement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEVENEMENT() {
        return evenement;
    }

    /**
     * Sets the value of the evenement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEVENEMENT(String value) {
        this.evenement = value;
    }

}
