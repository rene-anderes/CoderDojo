package org.anderes.edu.dojo.examination;

public class Klipspringer {
    
    public enum Sex { MALE, FEMALE, NOT_SPECIFIC };
    
    /** Eindeutige Bezeichnung */
    private final String description;
    /** Geschlecht */
    public Sex sex;
    /** Alter des Tiers */
    private Integer age;

    public Klipspringer(String description) {
        super();
        this.description = description;
        sex = Sex.NOT_SPECIFIC;
        age = 0;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((sex == null) ? 0 : sex.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Klipspringer other = (Klipspringer) obj;
        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (sex != other.sex)
            return false;
        return true;
    }

}
