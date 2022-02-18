package org.anderes.edu.xml.jaxb.modeldriven;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
//@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlElement(name="doorCount")
    private int doors;
    @XmlElement(name="tint")
    private String color;
    @XmlElement(name="serialNumber")
    private int serialNo;
    
    Car() {
        super();
    };
    
    public Car(int doors, String color, int serialNo) {
        this.doors = doors;
        this.color = color;
        this.serialNo = serialNo;
    }
    
    public int getDoors() {
        return doors;
    }
    
//    public void setDoors(int doors) {
//        this.doors = doors;
//    }
    
    public String getColor() {
        return color;
    }
    
//    public void setColor(String color) {
//        this.color = color;
//    }
    
//    @XmlElement(name="serialNumber")
    public int getSerialNo() {
        return serialNo;
    }
    
//    public void setSerialNo(int serialNo) {
//        this.serialNo = serialNo;
//    }
    
    
}
