//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.02.22 um 01:23:36 PM CET 
//


package org.anderes.edu.xml.saxdom.exercise.weatherdata;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.anderes.edu.xml.saxdom.exercise.weatherdata package. 
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

    private final static QName _Comment_QNAME = new QName("", "Comment");
    private final static QName _Value_QNAME = new QName("", "Value");
    private final static QName _Unit_QNAME = new QName("", "Unit");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.anderes.edu.xml.saxdom.exercise.weatherdata
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Wind }
     * 
     */
    public Wind createWind() {
        return new Wind();
    }

    /**
     * Create an instance of {@link MeasuredValue }
     * 
     */
    public MeasuredValue createMeasuredValue() {
        return new MeasuredValue();
    }

    /**
     * Create an instance of {@link WeatherData }
     * 
     */
    public WeatherData createWeatherData() {
        return new WeatherData();
    }

    /**
     * Create an instance of {@link GpsCoordinate }
     * 
     */
    public GpsCoordinate createGpsCoordinate() {
        return new GpsCoordinate();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Comment")
    public JAXBElement<String> createComment(String value) {
        return new JAXBElement<String>(_Comment_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Value")
    public JAXBElement<Double> createValue(Double value) {
        return new JAXBElement<Double>(_Value_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Unit")
    public JAXBElement<String> createUnit(String value) {
        return new JAXBElement<String>(_Unit_QNAME, String.class, null, value);
    }

}
