package com.example.fixturemonkey.domain.order.domain;

import com.example.fixturemonkey.domain.product.domain.Product;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testOrderCreation() {
        // Address 생성
        Address address = new Address("123 Main St", "Seoul", "12345");

        // Customer 생성
        Customer customer = new Customer(1L, 1L, "John Doe", "john@example.com", address);

        // Product 생성
        Product product = new Product(1001L, "Laptop", 1500.00);

        // Order 생성
        Order order = new Order(5001L, customer, product, LocalDateTime.now());

        // 테스트
        assertNotNull(order.getCustomer());
        assertEquals("Laptop", order.getProduct().getProductName());
    }
}