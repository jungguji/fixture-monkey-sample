package com.example.fixturemonkey.domain.order.domain;

import com.example.fixturemonkey.domain.product.domain.Product;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Order {

    private final long orderId;
    private final Customer customer; // 연관된 객체
    private final Product product;   // 연관된 객체
    private final LocalDateTime orderDate;

    public Order(long orderId, Customer customer, Product product, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.product = product;
        this.orderDate = orderDate;
    }
}
