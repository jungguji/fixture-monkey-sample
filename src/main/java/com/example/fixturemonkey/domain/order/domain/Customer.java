package com.example.fixturemonkey.domain.order.domain;

import lombok.Getter;

@Getter
public class Customer {
    private final long customerId;
    private final long memberId;
    private final String name;
    private final String email;
    private final Address address; // 연관된 객체

    public Customer(long customerId, long memberId, String name, String email, Address address) {
        this.customerId = customerId;
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
