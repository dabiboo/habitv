//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.01 at 08:33:54 AM CET 
//


package com.dabi.habitv.config.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for config complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="config">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cmdProcessor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="demonTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fileNameCutSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="workingDir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="indexDir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="providerPluginDir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="downloaderPluginDir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="exporterPluginDir" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="downloadOuput" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="downloader" type="{http://www.dabi.com/habitv/config/entities}downloader" maxOccurs="unbounded"/>
 *         &lt;element name="exporter" type="{http://www.dabi.com/habitv/config/entities}exporter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="taskDefinition" type="{http://www.dabi.com/habitv/config/entities}taskDefinition" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "config", propOrder = {
    "cmdProcessor",
    "demonTime",
    "fileNameCutSize",
    "workingDir",
    "indexDir",
    "providerPluginDir",
    "downloaderPluginDir",
    "exporterPluginDir",
    "downloadOuput",
    "downloader",
    "exporter",
    "taskDefinition"
})
public class Config {

    protected String cmdProcessor;
    protected Integer demonTime;
    protected Integer fileNameCutSize;
    @XmlElement(required = true)
    protected String workingDir;
    @XmlElement(required = true)
    protected String indexDir;
    @XmlElement(required = true)
    protected String providerPluginDir;
    @XmlElement(required = true)
    protected String downloaderPluginDir;
    @XmlElement(required = true)
    protected String exporterPluginDir;
    @XmlElement(required = true)
    protected String downloadOuput;
    @XmlElement(required = true)
    protected List<Downloader> downloader;
    protected List<Exporter> exporter;
    protected List<TaskDefinition> taskDefinition;

    /**
     * Gets the value of the cmdProcessor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmdProcessor() {
        return cmdProcessor;
    }

    /**
     * Sets the value of the cmdProcessor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmdProcessor(String value) {
        this.cmdProcessor = value;
    }

    /**
     * Gets the value of the demonTime property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDemonTime() {
        return demonTime;
    }

    /**
     * Sets the value of the demonTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDemonTime(Integer value) {
        this.demonTime = value;
    }

    /**
     * Gets the value of the fileNameCutSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFileNameCutSize() {
        return fileNameCutSize;
    }

    /**
     * Sets the value of the fileNameCutSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFileNameCutSize(Integer value) {
        this.fileNameCutSize = value;
    }

    /**
     * Gets the value of the workingDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkingDir() {
        return workingDir;
    }

    /**
     * Sets the value of the workingDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkingDir(String value) {
        this.workingDir = value;
    }

    /**
     * Gets the value of the indexDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndexDir() {
        return indexDir;
    }

    /**
     * Sets the value of the indexDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndexDir(String value) {
        this.indexDir = value;
    }

    /**
     * Gets the value of the providerPluginDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderPluginDir() {
        return providerPluginDir;
    }

    /**
     * Sets the value of the providerPluginDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderPluginDir(String value) {
        this.providerPluginDir = value;
    }

    /**
     * Gets the value of the downloaderPluginDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDownloaderPluginDir() {
        return downloaderPluginDir;
    }

    /**
     * Sets the value of the downloaderPluginDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDownloaderPluginDir(String value) {
        this.downloaderPluginDir = value;
    }

    /**
     * Gets the value of the exporterPluginDir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExporterPluginDir() {
        return exporterPluginDir;
    }

    /**
     * Sets the value of the exporterPluginDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExporterPluginDir(String value) {
        this.exporterPluginDir = value;
    }

    /**
     * Gets the value of the downloadOuput property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDownloadOuput() {
        return downloadOuput;
    }

    /**
     * Sets the value of the downloadOuput property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDownloadOuput(String value) {
        this.downloadOuput = value;
    }

    /**
     * Gets the value of the downloader property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the downloader property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDownloader().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Downloader }
     * 
     * 
     */
    public List<Downloader> getDownloader() {
        if (downloader == null) {
            downloader = new ArrayList<Downloader>();
        }
        return this.downloader;
    }

    /**
     * Gets the value of the exporter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the exporter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExporter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Exporter }
     * 
     * 
     */
    public List<Exporter> getExporter() {
        if (exporter == null) {
            exporter = new ArrayList<Exporter>();
        }
        return this.exporter;
    }

    /**
     * Gets the value of the taskDefinition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taskDefinition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaskDefinition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaskDefinition }
     * 
     * 
     */
    public List<TaskDefinition> getTaskDefinition() {
        if (taskDefinition == null) {
            taskDefinition = new ArrayList<TaskDefinition>();
        }
        return this.taskDefinition;
    }

}
