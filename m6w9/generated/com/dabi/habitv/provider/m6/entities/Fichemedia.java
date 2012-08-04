//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.02 at 04:16:23 PM CEST 
//


package com.dabi.habitv.provider.m6.entities;

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
 *         &lt;element ref="{}nom"/>
 *         &lt;element ref="{}Chapitres"/>
 *         &lt;element ref="{}HotSpots"/>
 *       &lt;/sequence>
 *       &lt;attribute name="duree" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="langue" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="tag_pub" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="video_csa_url" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="video_url" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nom",
    "chapitres",
    "hotSpots"
})
@XmlRootElement(name = "fichemedia")
public class Fichemedia {

    @XmlElement(required = true)
    protected String nom;
    @XmlElement(name = "Chapitres", required = true)
    protected Chapitres chapitres;
    @XmlElement(name = "HotSpots", required = true)
    protected HotSpots hotSpots;
    @XmlAttribute(required = true)
    protected BigInteger duree;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String langue;
    @XmlAttribute(name = "tag_pub", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String tagPub;
    @XmlAttribute(name = "video_csa_url", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String videoCsaUrl;
    @XmlAttribute(name = "video_url", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String videoUrl;

    /**
     * Gets the value of the nom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNom() {
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
    public void setNom(String value) {
        this.nom = value;
    }

    /**
     * Gets the value of the chapitres property.
     * 
     * @return
     *     possible object is
     *     {@link Chapitres }
     *     
     */
    public Chapitres getChapitres() {
        return chapitres;
    }

    /**
     * Sets the value of the chapitres property.
     * 
     * @param value
     *     allowed object is
     *     {@link Chapitres }
     *     
     */
    public void setChapitres(Chapitres value) {
        this.chapitres = value;
    }

    /**
     * Gets the value of the hotSpots property.
     * 
     * @return
     *     possible object is
     *     {@link HotSpots }
     *     
     */
    public HotSpots getHotSpots() {
        return hotSpots;
    }

    /**
     * Sets the value of the hotSpots property.
     * 
     * @param value
     *     allowed object is
     *     {@link HotSpots }
     *     
     */
    public void setHotSpots(HotSpots value) {
        this.hotSpots = value;
    }

    /**
     * Gets the value of the duree property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDuree() {
        return duree;
    }

    /**
     * Sets the value of the duree property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDuree(BigInteger value) {
        this.duree = value;
    }

    /**
     * Gets the value of the langue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLangue() {
        return langue;
    }

    /**
     * Sets the value of the langue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLangue(String value) {
        this.langue = value;
    }

    /**
     * Gets the value of the tagPub property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTagPub() {
        return tagPub;
    }

    /**
     * Sets the value of the tagPub property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTagPub(String value) {
        this.tagPub = value;
    }

    /**
     * Gets the value of the videoCsaUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideoCsaUrl() {
        return videoCsaUrl;
    }

    /**
     * Sets the value of the videoCsaUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideoCsaUrl(String value) {
        this.videoCsaUrl = value;
    }

    /**
     * Gets the value of the videoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * Sets the value of the videoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideoUrl(String value) {
        this.videoUrl = value;
    }

}
