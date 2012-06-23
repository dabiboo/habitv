//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.23 at 08:46:09 AM CEST 
//


package com.dabi.habitv.provider.canalplus.mea.entities;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dabi.habitv.provider.canalplus.mea.entities package. 
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

    private final static QName _MOYENNE_QNAME = new QName("", "MOYENNE");
    private final static QName _DESCRIPTION_QNAME = new QName("", "DESCRIPTION");
    private final static QName _NBVOTES_QNAME = new QName("", "NB_VOTES");
    private final static QName _RUBRIQUE_QNAME = new QName("", "RUBRIQUE");
    private final static QName _UNIVERS_QNAME = new QName("", "UNIVERS");
    private final static QName _GRAND_QNAME = new QName("", "GRAND");
    private final static QName _NBVUES_QNAME = new QName("", "NB_VUES");
    private final static QName _CHAINE_QNAME = new QName("", "CHAINE");
    private final static QName _ID_QNAME = new QName("", "ID");
    private final static QName _TITRE_QNAME = new QName("", "TITRE");
    private final static QName _SOUSTITRE_QNAME = new QName("", "SOUS_TITRE");
    private final static QName _PETIT_QNAME = new QName("", "PETIT");
    private final static QName _TYPE_QNAME = new QName("", "TYPE");
    private final static QName _CATEGORIE_QNAME = new QName("", "CATEGORIE");
    private final static QName _AUTORISER_QNAME = new QName("", "AUTORISER");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dabi.habitv.provider.canalplus.mea.entities
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RUBRIQUAGE }
     * 
     */
    public RUBRIQUAGE createRUBRIQUAGE() {
        return new RUBRIQUAGE();
    }

    /**
     * Create an instance of {@link IMAGES }
     * 
     */
    public IMAGES createIMAGES() {
        return new IMAGES();
    }

    /**
     * Create an instance of {@link TITRAGE }
     * 
     */
    public TITRAGE createTITRAGE() {
        return new TITRAGE();
    }

    /**
     * Create an instance of {@link NOTE }
     * 
     */
    public NOTE createNOTE() {
        return new NOTE();
    }

    /**
     * Create an instance of {@link INFOS }
     * 
     */
    public INFOS createINFOS() {
        return new INFOS();
    }

    /**
     * Create an instance of {@link MEA }
     * 
     */
    public MEA createMEA() {
        return new MEA();
    }

    /**
     * Create an instance of {@link MEDIA }
     * 
     */
    public MEDIA createMEDIA() {
        return new MEDIA();
    }

    /**
     * Create an instance of {@link MEAS }
     * 
     */
    public MEAS createMEAS() {
        return new MEAS();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "MOYENNE")
    public JAXBElement<String> createMOYENNE(String value) {
        return new JAXBElement<String>(_MOYENNE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DESCRIPTION")
    public JAXBElement<String> createDESCRIPTION(String value) {
        return new JAXBElement<String>(_DESCRIPTION_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "NB_VOTES")
    public JAXBElement<BigInteger> createNBVOTES(BigInteger value) {
        return new JAXBElement<BigInteger>(_NBVOTES_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RUBRIQUE")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createRUBRIQUE(String value) {
        return new JAXBElement<String>(_RUBRIQUE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "UNIVERS")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createUNIVERS(String value) {
        return new JAXBElement<String>(_UNIVERS_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "GRAND")
    public JAXBElement<String> createGRAND(String value) {
        return new JAXBElement<String>(_GRAND_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "NB_VUES")
    public JAXBElement<BigInteger> createNBVUES(BigInteger value) {
        return new JAXBElement<BigInteger>(_NBVUES_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "CHAINE")
    public JAXBElement<BigInteger> createCHAINE(BigInteger value) {
        return new JAXBElement<BigInteger>(_CHAINE_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ID")
    public JAXBElement<BigInteger> createID(BigInteger value) {
        return new JAXBElement<BigInteger>(_ID_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "TITRE")
    public JAXBElement<String> createTITRE(String value) {
        return new JAXBElement<String>(_TITRE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "SOUS_TITRE")
    public JAXBElement<String> createSOUSTITRE(String value) {
        return new JAXBElement<String>(_SOUSTITRE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "PETIT")
    public JAXBElement<String> createPETIT(String value) {
        return new JAXBElement<String>(_PETIT_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "TYPE")
    public JAXBElement<String> createTYPE(String value) {
        return new JAXBElement<String>(_TYPE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "CATEGORIE")
    public JAXBElement<String> createCATEGORIE(String value) {
        return new JAXBElement<String>(_CATEGORIE_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "AUTORISER")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createAUTORISER(String value) {
        return new JAXBElement<String>(_AUTORISER_QNAME, String.class, null, value);
    }

}
