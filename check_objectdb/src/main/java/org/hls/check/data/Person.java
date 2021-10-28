package org.hls.check.data;

import org.hls.check.data.Address;

import javax.persistence.Entity;

@Entity
public class Person {
    private String name;
    private String familyName;
    private Address address;

    public Person(String n, String fn) {
        name = n;
        familyName = fn;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
