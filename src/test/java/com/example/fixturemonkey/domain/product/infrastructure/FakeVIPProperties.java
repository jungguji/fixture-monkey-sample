package com.example.fixturemonkey.domain.product.infrastructure;

import java.util.List;

public class FakeVIPProperties implements VIPProperties {

    private List<Long> ids;

    public FakeVIPProperties(List<Long> ids) {
        this.ids = ids;
    }

    @Override
    public List<Long> getIds() {
        return this.ids;
    }
}