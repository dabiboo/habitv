//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.07.24 at 07:47:49 PM CEST 
//


package com.dabi.habitv.config.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dabi.habitv.config.entities package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Config_QNAME = new QName("http://www.dabi.com/habitv/config/entities", "config");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dabi.habitv.config.entities
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConditionType }
     * 
     */
    public ConditionType createConditionType() {
        return new ConditionType();
    }

    /**
     * Create an instance of {@link Exporter }
     * 
     */
    public Exporter createExporter() {
        return new Exporter();
    }

    /**
     * Create an instance of {@link Downloader }
     * 
     */
    public Downloader createDownloader() {
        return new Downloader();
    }

    /**
     * Create an instance of {@link SimultaneousTaskNumber }
     * 
     */
    public SimultaneousTaskNumber createSimultaneousTaskNumber() {
        return new SimultaneousTaskNumber();
    }

    /**
     * Create an instance of {@link Config }
     * 
     */
    public Config createConfig() {
        return new Config();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Config }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.dabi.com/habitv/config/entities", name = "config")
    public JAXBElement<Config> createConfig(Config value) {
        return new JAXBElement<Config>(_Config_QNAME, Config.class, null, value);
    }

}
