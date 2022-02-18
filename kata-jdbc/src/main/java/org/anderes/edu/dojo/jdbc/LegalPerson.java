package org.anderes.edu.dojo.jdbc;

public class LegalPerson extends Person {

    private String legalForm;
    private String name;

    public void setLegalForm(String legalForm) {
        this.legalForm = legalForm;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLegalForm() {
        return legalForm;
    }

}
