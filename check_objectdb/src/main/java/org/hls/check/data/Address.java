package org.hls.check.data;

import javax.persistence.Entity;

@Entity
public class Address {

    private String address;

    public Address(String a) {
        address = a;
    }

    public String getAddress() {
        return address;
    }
}
