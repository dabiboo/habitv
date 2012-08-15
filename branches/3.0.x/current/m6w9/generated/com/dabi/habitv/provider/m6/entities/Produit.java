//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.08.15 at 07:04:28 PM CEST 
//


package com.dabi.habitv.provider.m6.entities;

import java.math.BigDecimal;
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
 *         &lt;element ref="{}resume"/>
 *         &lt;element ref="{}copyright"/>
 *         &lt;element ref="{}fichemedia"/>
 *         &lt;element ref="{}diffusion"/>
 *       &lt;/sequence>
 *       &lt;attribute name="big_img_url" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="date_next_diff" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="episode" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="format_video" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="nb_visualisation" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="nb_votant" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="note_moyenne" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="public" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="reg_img_url" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="s4m" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="saison" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="sml_img_url" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="url_sem" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
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
    "resume",
    "copyright",
    "fichemedia",
    "diffusion"
})
@XmlRootElement(name = "produit")
public class Produit {

    @XmlElement(required = true)
    protected String nom;
    @XmlElement(required = true)
    protected String resume;
    @XmlElement(required = true)
    protected String copyright;
    @XmlElement(required = true)
    protected Fichemedia fichemedia;
    @XmlElement(required = true)
    protected Diffusion diffusion;
    @XmlAttribute(name = "big_img_url", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String bigImgUrl;
    @XmlAttribute(name = "date_next_diff", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String dateNextDiff;
    @XmlAttribute(required = true)
    protected BigInteger episode;
    @XmlAttribute(name = "format_video", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String formatVideo;
    @XmlAttribute(required = true)
    protected BigInteger id;
    @XmlAttribute(name = "nb_visualisation", required = true)
    protected BigInteger nbVisualisation;
    @XmlAttribute(name = "nb_votant", required = true)
    protected BigInteger nbVotant;
    @XmlAttribute(name = "note_moyenne", required = true)
    protected BigDecimal noteMoyenne;
    @XmlAttribute(name = "public", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String _public;
    @XmlAttribute(name = "reg_img_url", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String regImgUrl;
    @XmlAttribute(name = "s4m", required = true)
    protected BigInteger s4M;
    @XmlAttribute(required = true)
    protected BigInteger saison;
    @XmlAttribute(name = "sml_img_url", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String smlImgUrl;
    @XmlAttribute(name = "url_sem", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String urlSem;

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
     * Gets the value of the resume property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResume() {
        return resume;
    }

    /**
     * Sets the value of the resume property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResume(String value) {
        this.resume = value;
    }

    /**
     * Gets the value of the copyright property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Sets the value of the copyright property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopyright(String value) {
        this.copyright = value;
    }

    /**
     * Gets the value of the fichemedia property.
     * 
     * @return
     *     possible object is
     *     {@link Fichemedia }
     *     
     */
    public Fichemedia getFichemedia() {
        return fichemedia;
    }

    /**
     * Sets the value of the fichemedia property.
     * 
     * @param value
     *     allowed object is
     *     {@link Fichemedia }
     *     
     */
    public void setFichemedia(Fichemedia value) {
        this.fichemedia = value;
    }

    /**
     * Gets the value of the diffusion property.
     * 
     * @return
     *     possible object is
     *     {@link Diffusion }
     *     
     */
    public Diffusion getDiffusion() {
        return diffusion;
    }

    /**
     * Sets the value of the diffusion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Diffusion }
     *     
     */
    public void setDiffusion(Diffusion value) {
        this.diffusion = value;
    }

    /**
     * Gets the value of the bigImgUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBigImgUrl() {
        return bigImgUrl;
    }

    /**
     * Sets the value of the bigImgUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBigImgUrl(String value) {
        this.bigImgUrl = value;
    }

    /**
     * Gets the value of the dateNextDiff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateNextDiff() {
        return dateNextDiff;
    }

    /**
     * Sets the value of the dateNextDiff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateNextDiff(String value) {
        this.dateNextDiff = value;
    }

    /**
     * Gets the value of the episode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getEpisode() {
        return episode;
    }

    /**
     * Sets the value of the episode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEpisode(BigInteger value) {
        this.episode = value;
    }

    /**
     * Gets the value of the formatVideo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatVideo() {
        return formatVideo;
    }

    /**
     * Sets the value of the formatVideo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatVideo(String value) {
        this.formatVideo = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
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
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the nbVisualisation property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNbVisualisation() {
        return nbVisualisation;
    }

    /**
     * Sets the value of the nbVisualisation property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNbVisualisation(BigInteger value) {
        this.nbVisualisation = value;
    }

    /**
     * Gets the value of the nbVotant property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNbVotant() {
        return nbVotant;
    }

    /**
     * Sets the value of the nbVotant property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNbVotant(BigInteger value) {
        this.nbVotant = value;
    }

    /**
     * Gets the value of the noteMoyenne property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNoteMoyenne() {
        return noteMoyenne;
    }

    /**
     * Sets the value of the noteMoyenne property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNoteMoyenne(BigDecimal value) {
        this.noteMoyenne = value;
    }

    /**
     * Gets the value of the public property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublic() {
        return _public;
    }

    /**
     * Sets the value of the public property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublic(String value) {
        this._public = value;
    }

    /**
     * Gets the value of the regImgUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegImgUrl() {
        return regImgUrl;
    }

    /**
     * Sets the value of the regImgUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegImgUrl(String value) {
        this.regImgUrl = value;
    }

    /**
     * Gets the value of the s4M property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getS4M() {
        return s4M;
    }

    /**
     * Sets the value of the s4M property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setS4M(BigInteger value) {
        this.s4M = value;
    }

    /**
     * Gets the value of the saison property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSaison() {
        return saison;
    }

    /**
     * Sets the value of the saison property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSaison(BigInteger value) {
        this.saison = value;
    }

    /**
     * Gets the value of the smlImgUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmlImgUrl() {
        return smlImgUrl;
    }

    /**
     * Sets the value of the smlImgUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmlImgUrl(String value) {
        this.smlImgUrl = value;
    }

    /**
     * Gets the value of the urlSem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlSem() {
        return urlSem;
    }

    /**
     * Sets the value of the urlSem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlSem(String value) {
        this.urlSem = value;
    }

}
