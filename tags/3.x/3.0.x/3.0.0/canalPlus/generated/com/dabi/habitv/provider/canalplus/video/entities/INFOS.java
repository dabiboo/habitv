//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.10 at 07:56:39 PM CEST 
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
 *         &lt;element ref="{}DESCRIPTION"/>
 *         &lt;element ref="{}NB_VUES"/>
 *         &lt;element ref="{}AUTEUR"/>
 *         &lt;element ref="{}PARTIE"/>
 *         &lt;element ref="{}EMBED"/>
 *         &lt;element ref="{}CONTENU_LIE"/>
 *         &lt;element ref="{}NOTE"/>
 *         &lt;element ref="{}PUBLICATION"/>
 *         &lt;element ref="{}TITRAGE"/>
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
    "description",
    "nbvues",
    "auteur",
    "partie",
    "embed",
    "contenulie",
    "note",
    "publication",
    "titrage"
})
@XmlRootElement(name = "INFOS")
public class INFOS {

    @XmlElement(name = "DESCRIPTION", required = true)
    protected String description;
    @XmlElement(name = "NB_VUES", required = true)
    protected BigInteger nbvues;
    @XmlElement(name = "AUTEUR", required = true)
    protected String auteur;
    @XmlElement(name = "PARTIE", required = true)
    protected BigInteger partie;
    @XmlElement(name = "EMBED", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String embed;
    @XmlElement(name = "CONTENU_LIE", required = true)
    protected CONTENULIE contenulie;
    @XmlElement(name = "NOTE", required = true)
    protected NOTE note;
    @XmlElement(name = "PUBLICATION", required = true)
    protected PUBLICATION publication;
    @XmlElement(name = "TITRAGE", required = true)
    protected TITRAGE titrage;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESCRIPTION() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESCRIPTION(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the nbvues property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNBVUES() {
        return nbvues;
    }

    /**
     * Sets the value of the nbvues property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNBVUES(BigInteger value) {
        this.nbvues = value;
    }

    /**
     * Gets the value of the auteur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUTEUR() {
        return auteur;
    }

    /**
     * Sets the value of the auteur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUTEUR(String value) {
        this.auteur = value;
    }

    /**
     * Gets the value of the partie property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPARTIE() {
        return partie;
    }

    /**
     * Sets the value of the partie property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPARTIE(BigInteger value) {
        this.partie = value;
    }

    /**
     * Gets the value of the embed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMBED() {
        return embed;
    }

    /**
     * Sets the value of the embed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMBED(String value) {
        this.embed = value;
    }

    /**
     * Gets the value of the contenulie property.
     * 
     * @return
     *     possible object is
     *     {@link CONTENULIE }
     *     
     */
    public CONTENULIE getCONTENULIE() {
        return contenulie;
    }

    /**
     * Sets the value of the contenulie property.
     * 
     * @param value
     *     allowed object is
     *     {@link CONTENULIE }
     *     
     */
    public void setCONTENULIE(CONTENULIE value) {
        this.contenulie = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link NOTE }
     *     
     */
    public NOTE getNOTE() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link NOTE }
     *     
     */
    public void setNOTE(NOTE value) {
        this.note = value;
    }

    /**
     * Gets the value of the publication property.
     * 
     * @return
     *     possible object is
     *     {@link PUBLICATION }
     *     
     */
    public PUBLICATION getPUBLICATION() {
        return publication;
    }

    /**
     * Sets the value of the publication property.
     * 
     * @param value
     *     allowed object is
     *     {@link PUBLICATION }
     *     
     */
    public void setPUBLICATION(PUBLICATION value) {
        this.publication = value;
    }

    /**
     * Gets the value of the titrage property.
     * 
     * @return
     *     possible object is
     *     {@link TITRAGE }
     *     
     */
    public TITRAGE getTITRAGE() {
        return titrage;
    }

    /**
     * Sets the value of the titrage property.
     * 
     * @param value
     *     allowed object is
     *     {@link TITRAGE }
     *     
     */
    public void setTITRAGE(TITRAGE value) {
        this.titrage = value;
    }

}