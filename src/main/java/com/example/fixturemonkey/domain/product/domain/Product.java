package com.example.fixturemonkey.domain.product.domain;

import lombok.Getter;

@Getter
public class Product {
    private final long productId;
    private final String productName;
    private final double price;

    public Product(long productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }
}