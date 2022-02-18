package org.anderes.edu.dojo.jdbc;

import java.util.ArrayList;
import java.util.Optional;

public abstract class Person {

    private int id;
    private int personNo;
    private ArrayList<Address> addresses = new ArrayList<>();

    public int getPersonNo() {
        return personNo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPersonNo(int personNo) {
        this.personNo = personNo;
    }

    public int getId() {
        return id;
    }

    public void addAddress(final Address address) {
        addresses.add(address);
    }

    public Optional<Address> getAddress(final String addressType) {
        return addresses.stream().filter(a -> a.getAddressType().equalsIgnoreCase(addressType)).findFirst();
    }

}
