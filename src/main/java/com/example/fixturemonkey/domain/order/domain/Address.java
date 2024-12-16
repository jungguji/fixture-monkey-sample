package com.example.fixturemonkey.domain.order.domain;

import lombok.Getter;

@Getter
public class Address {
    private final String street;
    private final String city;
    private final String zipCode;

    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
}
