package org.anderes.edu.dojo.examination;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Oryx implements Comparable<Oryx> {

    /** Alter des Tiers */
    private Integer age;
    /** Schulterhöhe */
    private Double acromion;
    /** Besondere Merkmale */
    private String characteristics;
    /** Eindeutige Bezeichnung */
    private final String description;
    
    public Oryx(final String description) {
        age = 0;
        acromion = 0d;
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Double getAcromion() {
        return acromion;
    }
    public void setAcromion(Double acromion) {
        this.acromion = acromion;
    }
    public String getCharacteristics() {
        return characteristics;
    }
    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }
    
    @Override
    public int compareTo(Oryx rhs) {
        return rhs.description.compareTo(description);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                        .append(acromion).append(age)
                        .append(characteristics).append(description)
                        .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
          return false;
        }
        Oryx rhs = (Oryx) obj;
        return new EqualsBuilder()
                      .append(acromion, rhs.acromion)
                      .append(age, rhs.age)
                      .append(characteristics, rhs.characteristics)
                      .append(description, rhs.description)
                      .isEquals();
    }
    
}
