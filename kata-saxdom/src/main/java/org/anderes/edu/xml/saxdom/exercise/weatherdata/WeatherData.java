//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2017.02.22 um 01:23:36 PM CET 
//


package org.anderes.edu.xml.saxdom.exercise.weatherdata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Time" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element ref="{}GpsCoordinate"/>
 *         &lt;element ref="{}Wind"/>
 *         &lt;element name="BarometricPressure" type="{}measuredValue"/>
 *         &lt;element name="Temperatur" type="{}measuredValue"/>
 *         &lt;element name="Density" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element ref="{}Comment" minOccurs="0"/>
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
    "date",
    "time",
    "gpsCoordinate",
    "wind",
    "barometricPressure",
    "temperatur",
    "density",
    "comment"
})
@XmlRootElement(name = "weatherData")
public class WeatherData {

    @XmlElement(name = "Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "Time", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar time;
    @XmlElement(name = "GpsCoordinate", required = true)
    protected GpsCoordinate gpsCoordinate;
    @XmlElement(name = "Wind", required = true)
    protected Wind wind;
    @XmlElement(name = "BarometricPressure", required = true)
    protected MeasuredValue barometricPressure;
    @XmlElement(name = "Temperatur", required = true)
    protected MeasuredValue temperatur;
    @XmlElement(name = "Density")
    protected double density;
    @XmlElement(name = "Comment")
    protected String comment;

    /**
     * Ruft den Wert der date-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Legt den Wert der date-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Ruft den Wert der time-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Legt den Wert der time-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * Ruft den Wert der gpsCoordinate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link GpsCoordinate }
     *     
     */
    public GpsCoordinate getGpsCoordinate() {
        return gpsCoordinate;
    }

    /**
     * Legt den Wert der gpsCoordinate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link GpsCoordinate }
     *     
     */
    public void setGpsCoordinate(GpsCoordinate value) {
        this.gpsCoordinate = value;
    }

    /**
     * Ruft den Wert der wind-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Wind }
     *     
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * Legt den Wert der wind-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Wind }
     *     
     */
    public void setWind(Wind value) {
        this.wind = value;
    }

    /**
     * Ruft den Wert der barometricPressure-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MeasuredValue }
     *     
     */
    public MeasuredValue getBarometricPressure() {
        return barometricPressure;
    }

    /**
     * Legt den Wert der barometricPressure-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasuredValue }
     *     
     */
    public void setBarometricPressure(MeasuredValue value) {
        this.barometricPressure = value;
    }

    /**
     * Ruft den Wert der temperatur-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MeasuredValue }
     *     
     */
    public MeasuredValue getTemperatur() {
        return temperatur;
    }

    /**
     * Legt den Wert der temperatur-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasuredValue }
     *     
     */
    public void setTemperatur(MeasuredValue value) {
        this.temperatur = value;
    }

    /**
     * Ruft den Wert der density-Eigenschaft ab.
     * 
     */
    public double getDensity() {
        return density;
    }

    /**
     * Legt den Wert der density-Eigenschaft fest.
     * 
     */
    public void setDensity(double value) {
        this.density = value;
    }

    /**
     * Ruft den Wert der comment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Legt den Wert der comment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

}
